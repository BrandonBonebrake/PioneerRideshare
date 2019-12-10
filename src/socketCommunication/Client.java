package socketCommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client
{

    Socket client;

    ObjectOutputStream objOutStream;
    ObjectInputStream objInStream;

    public Client(Object obj)
    {
        super();

        try
        {
            client = new Socket(InetAddress.getLocalHost(), 63341);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.sendObject(obj);
    }
    public void sendObject(Object obj)
    {
        try
        {

            if(objOutStream == null)
            {
                objOutStream = new ObjectOutputStream(client.getOutputStream());
            }
            objOutStream.writeObject(obj);
            objOutStream.flush();
        }
        catch (IOException ignored)
        {

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