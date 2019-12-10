package socketCommunication;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NewClient
{
    private int port = 63341;
    private Object recObject;

    Socket client;

    ObjectOutputStream objOutStream;
    ObjectInputStream objInStream;

    public NewClient(Object obj)
    {
        super();

        try
        {
            client = new Socket(InetAddress.getLocalHost(), port);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.sendObject(obj);
    }
    public boolean sendObject(Object obj)
    {
        try
        {

            if(objOutStream == null)
            {
                objOutStream = new ObjectOutputStream(client.getOutputStream());
            }
            objOutStream.writeObject(obj);
            objOutStream.flush();

            return true;
        }
        catch (IOException e)
        {
            return false;
        }

    }

    public Object receiveObject()
    {
        Object objReceive = null;
        try
        {
            if(objInStream == null)
            {
                objInStream = new ObjectInputStream(client.getInputStream());
            }
            objReceive = objInStream.readObject();

            if(objReceive != null)
            {
                System.out.println(objReceive.getClass());
            }
            else
            {
                System.out.println("NULL CLASS");
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return objReceive;
    }

    public void close()
    {
        try
        {
            objInStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            objOutStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}