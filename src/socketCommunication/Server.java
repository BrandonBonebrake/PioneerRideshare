package socketCommunication;

import database.FileHandler;
import date.PioneerDate;
import ride.Ride;
import student.InvalidStudentException;
import student.Student;
import time.Time;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
    private ObjectOutputStream objOutStream;
    private ObjectInputStream  objInStream;
    private ServerSocket sSocket;
    private Socket client;
    private Object objReceive;
    private int port = 63341;

    private ArrayList<Ride>    currentRides = null;
    private ArrayList<Ride>    pastRides    = new ArrayList<>();
    private ArrayList<Student> students     = null;

    public Server()
    {
        super();

        this.serverStart();
        this.createStreams();
    }

    private void serverStart()
    {
        currentRides = FileHandler.getCurrentRides();
        students     = FileHandler.getStudents();
    }

    private void createStreams()
    {
        try
        {
            sSocket = new ServerSocket(port);
            System.out.println("Server Started");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("Time  | Client Information      | Request                    | Status");
            System.out.println("-----------------------------------------------------------------------------------------------------------");

            while (true)
            {
                client = sSocket.accept(); // Wait for the client to connect

                objOutStream = new ObjectOutputStream(client.getOutputStream());
                objInStream  = new ObjectInputStream(client.getInputStream());

                objReceive = objInStream.readObject();
                this.interpretObject();

                objOutStream.close();
                objInStream.close();
                client.close();
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Interprets the Objects that the server receives form the client
     *
     * @throws IOException Thrown if file or folder could not be found. Should not occur
     */
    private void interpretObject() throws IOException
    {
        System.out.print((new Time()).getTime() + " | ");

        if(objReceive.getClass() == ride.RideOffer.class ||
                objReceive.getClass() == ride.RideRequest.class)
        {
            System.out.print("Client " + client.getInetAddress() + " | Creating New Ride...       | ");
            FileHandler.writeObject("rides", objReceive);
            currentRides.add((Ride)objReceive);

            System.out.print("Ride Created... ");

            objOutStream.writeObject(objReceive);

            System.out.println("Ride Saved... Response Sent");
        }
        else if(objReceive.getClass() == Student.class)
        {
            System.out.print("Client " + client.getInetAddress() + " | Login...                   | ");
            Student student = getStudent(((Student)objReceive).getEmail(), ((Student)objReceive).getPassword());

            if(student == null)
            {
                System.out.println("Failed");
            }
            else
            {
                System.out.println("Successful");
            }
            objOutStream.writeObject(student);
        }
        else if(objReceive.getClass() == student.InvalidStudentException.class)
        {
            System.out.println("Client Student Exception: " + ((Exception) objReceive).getMessage());
        }
        else if(objReceive.getClass() == String.class)
        {
            System.out.print("Client " + client.getInetAddress() + " | ");
            if(objReceive.toString().contains("currentRides"))
            {
                System.out.print("Requesting current rides   | Sending " + currentRides.size()
                        + " rides to the client...");

                this.updateRideLists(); // Remove past rides
                objOutStream.writeObject(currentRides);

                System.out.println(" Rides Sent");
            }
            else if(objReceive.toString().contains("Ride: "))
            {
                String received = objReceive.toString().split(" ")[1];
                Ride ride = findRide(received);
                System.out.print("Requesting to fill ride    | ");

                if(ride == null)
                {
                    System.out.print("Could Not Find Ride... ");
                }
                else
                {
                    System.out.print("Ride Found... ");
                }
                objOutStream.writeObject(ride);
                System.out.println("Client Informed");
            }
            else if(objReceive.toString().contains("New User: "))
            {
                String[] clientInfo = objReceive.toString().split(" ");

                System.out.print("New User Signup...         | ");

                if(clientInfo.length == 4 && !emailTaken(clientInfo[2]))
                {
                    objOutStream.writeObject(createStudent(clientInfo[2], clientInfo[3]));
                    System.out.println("Success");
                }
                else
                {
                    objOutStream.writeObject(null);
                    System.out.println("Failed");
                }
            }
            else
            {
                objOutStream.writeObject(null);
            }
            objOutStream.flush();
        }
        else
        {
            System.out.println("Uncaught Client Class: " + objReceive.toString());
        }
    }

    private Ride findRide(String rideID)
    {
        Ride ride = null;

        for(Ride currentRide : currentRides)
        {
            if(rideID.equals(currentRide.getRideIdentificationNumber()))
            {
                ride = currentRide;
                break;
            }
        }
        return ride;
    }

    private Student createStudent(String email, String password) throws IOException
    {
        Student student = null;
        try
        {
            student = new Student("First", "Last", email, password);
            objOutStream.writeObject(student);
            FileHandler.writeObject("students", student);
            students.add(student);

        } catch (InvalidStudentException e)
        {
            FileHandler.writeObject("exceptions", e);
            objOutStream.writeObject(null);
            System.out.println("Failed");
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

        for(Student student : students)
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

    private void updateRideLists()
    {
        PioneerDate today = new PioneerDate();
        Time now = new Time();

        for(int i = 0; i < currentRides.size(); i++)
        {
            // Remove ride from current listing if it is passed the leave date and time
            if(currentRides.get(i).getLeaveDate().compareTo(today.getDate()) <= 0
            && currentRides.get(i).getLeaveTime().compareTo(now.getTime()) <= 0)
            {
                pastRides.add(currentRides.get(i));
                currentRides.remove(i);
                i--;
            }
        }
    }
    
    public static void main(String[] args)
    {
        new Server();
    }
}