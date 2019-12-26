package socketCommunication;

import database.EmailHandler;
import database.FileHandler;
import date.PioneerDate;
import ride.MatchRide;
import ride.Ride;
import ride.RideOffer;
import ride.RideRequest;
import student.InvalidStudentException;
import student.Student;
import time.Time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
	private ServerSocket sSocket;
	private ObjectOutputStream objOutStream;
	private Socket client;
	
	private ArrayList<Ride> currentRides = null;
	private ArrayList<Student> students  = null;
	
	public Server() throws IOException
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
					Packet<?> objReceive = (Packet<?>) objInStream.readObject();
					this.interpretPacket(objReceive);
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
	
	private void interpretPacket(Packet<?> packet)
	{
		Object object = packet.getObject();
		
		try
		{
			this.lineHeader();
			
			if (object instanceof String)
			{
				this.interpretString(object);
			}
			else if (object instanceof RideOffer || object instanceof RideRequest)
			{
				this.interpretRide(object);
			}
			else if (object instanceof Student)
			{
				this.interpretStudent(object);
			}
		}
		catch (IOException e)
		{
			System.out.println("Could not send Object to client!");
		}
	}
	
	private void interpretStudent(Object object) throws IOException
	{
		System.out.print("Login                      | ");
		Student student = getStudent(((Student)object).getEmail(), ((Student)object).getPassword());
		
		if(student == null)
		{
			System.out.println("Failed");
		}
		else
		{
			if(!((Student)object).getIsBanned())
			{
				System.out.println("Successful");
			}
			else
			{
				System.out.println("Failed, Student is Banned: " + ((Student)object).getEmail());
			}
		}
		objOutStream.writeObject(student);
	}
	
	private void interpretRide(Object object) throws IOException
	{
		System.out.print("Creating New Ride          | ");
		
		if (compareRides((Ride) object) == null)
		{
			FileHandler.writeObject("rides", object);
			currentRides.add((Ride) object);
			System.out.print("Ride Created");
			objOutStream.writeObject(object);
			System.out.println(", Ride Saved, Response Sent");
		}
		else
		{
			System.out.println("Similar Ride Exists Already, Not Created");
			objOutStream.writeObject("Ride Already Exists");
		}
	}
	
	private void interpretString(Object object) throws IOException
	{
		if (object.toString().contains("currentRides"))
		{
			this.updateRideList();
			System.out.print("Requesting Current Rides   | Sending " + currentRides.size() + " Rides");
			objOutStream.writeObject(currentRides);
			System.out.println(", Rides Sent");
		}
		else if (object.toString().contains("Ride: "))
		{
			String[] received = object.toString().split(" ");
			Ride ride = findRide(received[1]);
			Student student;
			
			System.out.print("Requesting to fill ride    | ");
			
			if (ride == null)
			{
				System.out.print("Could Not Find Ride");
			}
			else
			{
				System.out.print("Ride Found");
				
				student = getStudent(received[2]);
				
				EmailHandler.rideFilled(ride, ride.getStudent(), student);
			}
			objOutStream.writeObject(ride);
			this.removeRide(received[1]);
			System.out.println(", Client Informed, Ride Fulfilled");
		}
		else if (object.toString().contains("Remove: "))
		{
			String[] received = object.toString().split(" ");
			
			System.out.print("Filling Ride               | ");
			
			if (removeRide(received[1]))
			{
				System.out.println("Successful, Removed");
			}
			else
			{
				System.out.println("Failed, Not Found");
			}
		}
		else if (object.toString().contains("New User: "))
		{
			String[] clientInfo = object.toString().split(" ");
			
			System.out.print("New User Signup            | ");
			
			if (clientInfo.length == 6 && !emailTaken(clientInfo[2]))
			{
				objOutStream.writeObject(createStudent(clientInfo[2], clientInfo[3], clientInfo[4], clientInfo[5]));
				
				System.out.print("Successful");
			}
			else
			{
				objOutStream.writeObject(null);
				System.out.print("Failed");
			}
			System.out.println(", Response Sent");
		}
		else
		{
			objOutStream.writeObject(null);
		}
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
	
	private boolean removeRide(String rideID)
	{
		boolean removed = false;
		
		for (int i = 0; i < currentRides.size(); i++)
		{
			if (rideID.equals(currentRides.get(i).getRideIdentificationNumber()))
			{
				currentRides.remove(i);
				removed = true;
				break;
			}
		}
		return removed;
	}
	
	private Student createStudent(String email, String password, String firstName, String lastName) throws IOException
	{
		Student student = null;
		try
		{
			student = new Student(firstName, lastName, email, password);
			objOutStream.writeObject(student);
			FileHandler.writeObject("students", student);
			students.add(student);
			
		}
		catch (InvalidStudentException e)
		{
			FileHandler.writeObject("exceptions", e);
			objOutStream.writeObject(null);
			System.out.println("Failed");
		}
		return student;
	}
	
	private Student getStudent(String email)
	{
		Student student = null;
		
		// Look to make certain student exists, else return null
		for (Student value : students)
		{
			// Check that emails match
			if (email.equals(value.getEmail()))
			{
				student = value;
				break;
			}
		}
		return student;
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
		new Server();
	}
}