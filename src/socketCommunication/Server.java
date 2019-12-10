package socketCommunication;

import database.FileHandler;
import ride.Ride;
import student.Student;

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

    private ArrayList<Ride> currentRides = new ArrayList<>();
    private ArrayList<Ride> pastRides = new ArrayList<>();

    public Server()
    {
        super();

        this.serverStart();
        this.createStreams();
    }

    private void serverStart()
    {
        currentRides = FileHandler.getCurrentRides();
    }

    private void createStreams()
    {
        try
        {
            sSocket = new ServerSocket(port);
            System.out.println("Server Started\n---------------------------------------------------------------------------------------------------------");

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
     * Inteprepts the Objects that the server receives form the client
     *
     * @throws IOException Thrown if file or folder could not be found. Should not occur
     */
    private void interpretObject() throws IOException
    {
        if(objReceive.getClass() == ride.RideOffer.class ||
                objReceive.getClass() == ride.RideRequest.class)
        {
            System.out.println("Client Ride Offer: " + objReceive.toString());
            FileHandler.writeObject("rides", objReceive);
            currentRides.add((Ride)objReceive);
            objOutStream.writeObject(objReceive);
        }
        else if(objReceive.getClass() == Student.class)
        {
            System.out.println("Client Student: " + ((Student) objReceive).getFirstName() + " " +
                    ((Student) objReceive).getLastName() + ", " + objReceive.toString() + ", " +
                    ((Student) objReceive).getAccountNumber());

        }
        else if(objReceive.getClass() == student.InvalidStudentException.class)
        {
            System.out.println("Client Student Exception: " + ((Exception) objReceive).getMessage());
        }
        else if(objReceive.getClass() == String.class)
        {
            System.out.print("Client " + client.getInetAddress());
            if(objReceive.toString().equals("currentRides"))
            {
                System.out.print(" requesting current rides. Sending " + currentRides.size() + " rides to the client...");

                objOutStream.writeObject(currentRides);

                System.out.println(" Rides Sent");
            }
            else if(objReceive.toString().contains("Ride: "))
            {
                String received = objReceive.toString().split(" ")[1];
                Ride ride = findRide(received);
                System.out.print(" requesting to fill ride " + received + "... ");

                if(ride == null)
                {
                    System.out.print("Could Not Find Ride");
                }
                else
                {
                    System.out.print("Ride Found");
                }
                System.out.println("... Informing Client");
                objOutStream.writeObject(ride);
            }
            else
            {
                objOutStream.writeObject(null);
            }
            objOutStream.flush();
        }
        else if(objReceive != null)
        {
            System.out.println("Uncaught Client Class: " + objReceive.toString());
        }
        else
        {
            System.out.println("Null Class");
        }
    }

    private Ride findRide(String rideID)
    {
        Ride ride = null;

        for (int i = 0; i < currentRides.size(); i++)
        {
            if(rideID.equals(currentRides.get(i).getRideIdentificationNumber()))
            {
                ride = currentRides.get(i);
                break;
            }
        }
        return ride;
    }
    
    public static void main(String[] args)
    {
        new Server();
    }
}