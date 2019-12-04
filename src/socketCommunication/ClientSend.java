package socketCommunication;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSend implements Runnable
{
    private int port;
    private Object object;

    ClientSend(Object obj, int port)
    {
        this.object = obj;
        this.port = port;
        Thread tSend = new Thread(this);
        tSend.start();
    }

    @Override
    public void run()
    {
        try
        {
            // Creates a client connection with a name and port
            Socket client = new Socket(InetAddress.getLocalHost(), port);
            ObjectOutputStream objOutStream = new ObjectOutputStream(client.getOutputStream());

            // Sends an object through the output stream
            objOutStream.writeObject(this.object);
            objOutStream.flush();
            //objOutStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
