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
	
	public Client(Packet<?> obj)
	{
		super();
		
		try
		{
			client = new Socket(InetAddress.getLocalHost(), 63341);
			this.sendObject(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendObject(Packet<?> packet)
	{
		try
		{
			if(objOutStream == null)
			{
				objOutStream = new ObjectOutputStream(client.getOutputStream());
			}
			objOutStream.writeObject(packet);
			objOutStream.flush();
		}
		catch (IOException ignored)
		{ }
	}
	
	public Packet<?> receiveObject()
	{
		Packet<?> objReceive = null;
		
		try
		{
			if(client != null)
			{
				if (objInStream == null)
				{
					objInStream = new ObjectInputStream(client.getInputStream());
				}
				objReceive = new Packet<>(objInStream.readObject());
			}
		}
		catch (IOException | ClassNotFoundException ignored)
		{}
		return objReceive;
	}
	
	public void close()
	{
		try
		{
			objInStream.close();
		}
		catch (IOException ignored)
		{}
		
		try
		{
			objOutStream.close();
		}
		catch (IOException ignored)
		{}
	}
}