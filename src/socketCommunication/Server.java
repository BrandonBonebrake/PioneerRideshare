package socketCommunication;

import database.EmailHandler;
import database.FileHandler;
import date.PioneerDate;
import ride.Ride;
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
    private ObjectOutputStream objOutStream;
    private ServerSocket sSocket;
    private Socket client;
    private Object objReceive;

    private ArrayList<Ride>    currentRides = null;
    private ArrayList<Student> students     = null;

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
            while (true)
            {
                client = sSocket.accept(); // Wait for the client to connect

                objOutStream = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream objInStream = new ObjectInputStream(client.getInputStream());

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
        String clientAddress = "Client " + client.getInetAddress();
        while (clientAddress.length() < 23)
            clientAddress += " ";
        clientAddress += " | ";
        System.out.print(clientAddress);

        if(objReceive.getClass() == ride.RideOffer.class ||
                objReceive.getClass() == ride.RideRequest.class)
        {
            System.out.print("Creating New Ride...       | ");

            FileHandler.writeObject("rides", objReceive);
            currentRides.add((Ride)objReceive);

            System.out.print("Ride Created... ");

            objOutStream.writeObject(objReceive);

            System.out.println("Ride Saved... Response Sent");
        }
        else if(objReceive.getClass() == Student.class)
        {
            System.out.print("Login...                   | ");
            Student student = getStudent(((Student)objReceive).getEmail(), ((Student)objReceive).getPassword());

            if(student == null)
            {
                System.out.println("Failed");
            }
            else
            {
                if(!((Student)objReceive).getIsBanned())
                {
                    System.out.println("Successful...");
                }
                else
                {
                    System.out.println("Failed... Student is Banned: " + ((Student)objReceive).getEmail());
                }
            }
            objOutStream.writeObject(student);
        }
        else if(objReceive.getClass() == student.InvalidStudentException.class)
        {
            System.out.println("Client Student Exception: " + ((Exception) objReceive).getMessage());
        }
        else if(objReceive.getClass() == String.class)
        {
            if(objReceive.toString().contains("currentRides"))
            {
                this.updateRideLists(); // Remove past rides
                System.out.print("Requesting Current Rides   | Sending " + currentRides.size()
                        + " Rides...");

                objOutStream.writeObject(currentRides);

                System.out.println(" Rides Sent");
            }
            else if(objReceive.toString().contains("Ride: "))
            {
                String[] received = objReceive.toString().split(" ");
                Ride ride = findRide(received[1]);
                Student student;

                System.out.print("Requesting to fill ride    | ");

                if(ride == null)
                {
                    System.out.print("Could Not Find Ride... ");
                }
                else
                {
                    System.out.print("Ride Found... ");

                    student = getStudent(received[2]);

                    EmailHandler.rideFilled(ride, ride.getStudent(), student);
                }
                objOutStream.writeObject(ride);
                System.out.println("Client Informed");
            }
            else if(objReceive.toString().contains("New User: "))
            {
                String[] clientInfo = objReceive.toString().split(" ");

                System.out.print("New User Signup...         | ");

                if(clientInfo.length == 6 && !emailTaken(clientInfo[2]))
                {
                    objOutStream.writeObject(createStudent(clientInfo[2], clientInfo[3], clientInfo[4], clientInfo[5]));

                    System.out.print("Successful... ");
                }
                else
                {
                    objOutStream.writeObject(null);
                    System.out.print("Failed...     ");
                }
                System.out.println("Response Sent");
            }
            else
            {
                objOutStream.writeObject(null);
            }
            objOutStream.flush();
        }
        else
        {
            System.out.println("Uncaught Class: " + objReceive.toString());
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

    private Student createStudent(String email, String password, String firstName, String lastName) throws IOException
    {
        Student student = null;
        try
        {
            student = new Student(firstName, lastName, email, password);
            objOutStream.writeObject(student);
            FileHandler.writeObject("students", student);
            students.add(student);

        } catch (InvalidStudentException e)
        {
            FileHandler.writeObject("exceptions", e);
            objOutStream.writeObject(null);
            System.out.println("Failed...");
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
                currentRides.remove(i);
                i--;
            }
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        new Server();
    }
}