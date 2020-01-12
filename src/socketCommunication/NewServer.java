package socketCommunication;

import database.EmailHandler;
import database.FileHandler;
import date.PioneerDate;
import ride.MatchRide;
import ride.Ride;
import ride.RideOffer;
import ride.RideRequest;
import student.Student;
import time.Time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public final class NewServer
{
	private ServerSocket sSocket;
	private ObjectOutputStream objOutStream;
	private Socket client;
	
	private ArrayList<Ride> currentRides = null;
	private ArrayList<Student> students  = null;
	
	public NewServer() throws IOException
	{
		super();
		
		this.serverStart();
		this.createStreams();
	}
	
	private void serverStart() throws IOException
	{
		System.out.println("\nStarting Server Processes");
		System.out.print("Reading Files From Disk... ");
		
		currentRides = FileHandler.getCurrentRides();
		students     = FileHandler.getStudents();
		
		System.out.println("Done");
		System.out.println("Creating Object I/O Streams");
		
		sSocket = new ServerSocket(63341);
		System.out.println("Server Started");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println("Time  | Client Information      | Request                    | Status");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}
	
	private void createStreams()
	{
		try
		{
			do
			{
				client = sSocket.accept(); // Wait for the client to connect
				
				objOutStream = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream objInStream = new ObjectInputStream(client.getInputStream());
				
				try
				{
					this.interpretPacket((NewPacket<?, ?>) objInStream.readObject());
				}
				catch (ClassCastException e)
				{
					
					System.out.println("Invalid Object Received - Recommend Banning User");
				}
				objOutStream.close();
				objInStream.close();
				client.close();
			}
			while (!client.getInetAddress().toString().equals("0.0.0.0"));
		}
		catch (IOException | ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
			this.createStreams();
		}
	}
	
	private void interpretPacket(NewPacket<?,?> packetReceived) throws IOException
	{
		String[] strArr = ((String) packetReceived.getString()).split(" ");
		
		// Echoed header that displays the system time and client IP address
		this.lineHeader();
		
		// Handle the String that is passed from the client determining what action to take based on keywords. (Similar to a database query)
		switch (strArr[0])
		{
			case "PUT":
				this.put(strArr, packetReceived.getObject());
				break;
			case "GET":
				this.get(strArr);
				break;
			case "JOIN":
				this.join(strArr, packetReceived.getObject());
				break;
			default:
				System.out.println("Unknown Command Received");
				break;
		}
	}
	
	private void put(String[] strArr, Object object) throws IOException
	{
		if (strArr[1].equals("ride"))
		{
			if (object instanceof RideRequest || object instanceof RideOffer)
			{
				System.out.print("Creating New Ride          | ");
				Ride ride = compareRides((Ride) object);
				
				// Ride will be null when there are no rides that are similar to each other
				if (ride == null)
				{
					currentRides.add((Ride) object);
					objOutStream.writeObject(null);
					System.out.println("Success");
				}
				else
				{
					objOutStream.writeObject(ride);
					System.out.println("Filed, Similar Ride Already Exists");
				}
			}
		}
		else if (strArr[1].equals("student"))
		{
			if (object instanceof Student)
			{
				Student student = (Student) object;
				
				if (!this.emailTaken(student.getEmail()))
				{
					students.add(student);
					objOutStream.writeObject(new NewPacket<>("student", student));
				}
				else
				{
					objOutStream.writeObject(new NewPacket<>("null", null));
				}
			}
		}
	}
	
	private void get(String[] strArr) throws IOException
	{
		if (strArr[1].equals("currentRides"))
		{
			System.out.print("Requesting Current Rides   | ");
			this.updateRideList();
			objOutStream.writeObject(new NewPacket<>("currentRides", currentRides));
			System.out.println("Sent " + this.currentRides.size() + " rides");
		}
		else if (strArr[1].equals("student"))
		{
			System.out.print("Student Login              | ");
			
			Student student = getStudent(strArr[2], strArr[3]);
			if(student == null)
			{
				System.out.println("Failed");
			}
			else
			{
				System.out.println("Success");
			}
			objOutStream.writeObject(new NewPacket<>("student", student));
		}
	}
	
	private void join(String[] strArr, Object object)
	{
		Ride ride = findRide(strArr[1]);
		
		if (ride != null)
		{
			EmailHandler.rideFilled(ride, ride.getStudent(), (Student) object);
		}
	}
	
	private Student getStudent(String email, String password)
	{
		Student student = null;
		
		// Look to make certain student exists, else return null
		for (Student value : students)
		{
			// Check that emails match
			if (email.equals(value.getEmail()))
			{
				// Check that passwords match
				if (password.equals(value.getPassword()))
				{
					student = value;
					break;
				}
			}
		}
		return student;
	}
	
	private boolean emailTaken(String email)
	{
		boolean emailTaken = false;
		
		for (Student student : students)
		{
			// Check that emails match
			if (student.getEmail().equals(email))
			{
				emailTaken = true;
				break;
			}
		}
		return emailTaken;
	}
	
	private Ride compareRides(Ride ride)
	{
		Ride ride1 = null;
		
		for (Ride currentRide : currentRides)
		{
			if (MatchRide.rideSimilarity(ride, currentRide) >= .95f)
			{
				ride1 = currentRide;
				break;
			}
		}
		return ride1;
	}
	
	private Ride findRide(String rideID)
	{
		Ride ride = null;
		
		for (Ride currentRide : currentRides)
		{
			if (rideID.equals(currentRide.getRideIdentificationNumber()))
			{
				ride = currentRide;
				break;
			}
		}
		return ride;
	}
	
	private void updateRideList()
	{
		PioneerDate today = new PioneerDate();
		Time now = new Time();
		
		for (int i = 0; i < currentRides.size(); i++)
		{
			// Remove ride from current listing if it is passed the leave date and time
			if (currentRides.get(i).getLeaveDate().compareTo(today.getDate()) <= 0
					&& currentRides.get(i).getLeaveTime().compareTo(now.getTime()) <= 0)
			{
				currentRides.remove(i);
				i--;
			}
		}
	}
	
	private void lineHeader()
	{
		System.out.print((new Time()).getTime() + " | ");
		StringBuilder clientAddress = new StringBuilder("Client " + client.getInetAddress());
		
		while (clientAddress.length() < 23)
		{
			clientAddress.append(" ");
		}
		clientAddress.append(" | ");
		
		System.out.print(clientAddress);
	}
	
	public static void main(String[] args) throws IOException
	{
		new NewServer();
	}
}