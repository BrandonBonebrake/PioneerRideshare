package socketCommunication;

import ride.Ride;
import ride.RideOffer;
import student.Student;
import database.PSRDatabase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerReceive implements Runnable
{
    private Socket client;
    private PSRDatabase database = null;

    ServerReceive(Socket c, PSRDatabase database)
    {
        this.client = c;
        this.database = database;

        Thread tReceive = new Thread(this);
        tReceive.start();
    }

    @Override
    public void run()
    {
        try
        {
            ObjectInputStream objIN = new ObjectInputStream(client.getInputStream());
            Object objReceive = objIN.readObject();

            // Find the type of Object the class is
            if(objReceive != null && objReceive.getClass() == ride.RideOffer.class)
            {
                System.out.println("Client Ride Offer: " + objReceive.toString());
            }
            else if(objReceive != null && objReceive.getClass() == ride.RideRequest.class)
            {
                System.out.println("Client Ride Request: " + objReceive.toString());
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
            else if(objReceive != null)
            {
                System.out.println("Uncaught Client Class: " + objReceive.toString());
            }
            else
            {
                System.out.println("Null Class");
            }
            objIN.close();
            new ServerSend(this.client ,objReceive);
        }
        catch (Exception e)
        {
            System.out.println("\n------------------------------------------------------------------------");
            System.out.println(e);
            System.out.println("------------------------------------------------------------------------\n");
        }
    }
}