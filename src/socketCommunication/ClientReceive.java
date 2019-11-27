package socketCommunication;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientReceive implements Runnable
{
    private int port;

    ClientReceive(int portInput)
    {
        this.port = portInput;

        Thread tReceive = new Thread(this);
        tReceive.start();
    }

    @Override
    public void run()
    {
        try
        {
            Socket client = new Socket(InetAddress.getLocalHost(), port);

            while (true)
            {
                ObjectInputStream objInStream = new ObjectInputStream(client.getInputStream());
                Object objReceive = objInStream.readObject();

                // Display the Objects class
                if(objReceive != null)
                {
                    System.out.println(objReceive.getClass());
                }
                else
                {
                    System.out.println("null Object");
                }
                // Check if object class is a StringBuilder and display if it is
                if(objReceive != null && objReceive.getClass() == java.lang.StringBuilder.class)
                {
                    System.out.println(((StringBuilder) objReceive).toString());
                }
                objInStream.close();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}