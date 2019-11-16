package socketCommunication;

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
            if(objReceive != null && objReceive.getClass() == java.lang.StringBuilder.class)
            {
                System.out.println("Client: " + ((StringBuilder) objReceive).toString());
            }
            else if(objReceive != null && objReceive.getClass() == student.Student.class)
            {
                System.out.println("Client: " + ((Student)objReceive).toString());
                this.database.addStudent((Student)objReceive);
            }
            else if (objReceive != null)
            {
                System.out.println(objReceive.getClass());
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