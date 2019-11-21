package socketCommunication;

import ride.Ride;
import ride.RideOffer;
import student.Student;
import database.PSRDatabase;

import java.io.ObjectInputStream;
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
            Object objReceive = (new ObjectInputStream(client.getInputStream())).readObject();

            // Check if object class is a StringBuilder and display if it is
            if(objReceive != null && objReceive.getClass() == ride.RideOffer.class)
            {
                System.out.println("Client: " + (objReceive).toString());
            }
            else
            {
                System.out.println("null class");
            }
        }
        catch (Exception e)
        {
            System.out.println("\n------------------------------------------------------------------------");
            System.out.println(e);
            System.out.println("------------------------------------------------------------------------\n");
        }
    }
}