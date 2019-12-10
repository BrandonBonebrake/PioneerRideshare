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
        if(objReceive != null && objReceive.getClass() == ride.RideOffer.class)
        {
            System.out.println("Client Ride Offer: " + objReceive.toString());
            FileHandler.writeObject("rides", objReceive);
            currentRides.add((Ride)objReceive);
            objOutStream.writeObject(objReceive);
        }
        else if(objReceive != null && objReceive.getClass() == ride.RideRequest.class)
        {
            System.out.println("Client Ride Request: " + objReceive.toString());
            FileHandler.writeObject("rides", objReceive);
            currentRides.add((Ride)objReceive);
            objOutStream.writeObject(objReceive);
        }
        else if(objReceive != null && objReceive.getClass() == student.Student.class)
        {
            System.out.println("Client Student: " + ((Student) objReceive).getFirstName() + " " +
                    ((Student) objReceive).getLastName() + ", " + objReceive.toString() + ", " +
                    ((Student) objReceive).getAccountNumber());

        }
        else if(objReceive != null && objReceive.getClass() == student.InvalidStudentException.class)
        {
            System.out.println("Client Student Exception: " + ((Exception) objReceive).getMessage());
        }
        else if(objReceive != null && objReceive.getClass() == String.class)
        {
            if(objReceive.toString().equals("currentRides"))
            {
                System.out.print("Client " + client.getInetAddress() + " requesting current rides. Sending "
                        + currentRides.size() + " rides to the client...");

                objOutStream.writeObject(currentRides);

                System.out.println(" Rides Sent");
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

    public static void main(String[] args)
    {
        new Server();
    }
}