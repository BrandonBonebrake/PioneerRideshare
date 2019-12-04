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
            ObjectInputStream objInStream;

            while (true)
            {
                objInStream = new ObjectInputStream(client.getInputStream());
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
                if(objReceive != null)
                {
                    System.out.println(objReceive.toString());
                    break;
                }
                System.out.println(objReceive.toString());
            }
            objInStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}