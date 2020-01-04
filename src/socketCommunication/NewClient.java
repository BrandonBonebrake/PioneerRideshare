package socketCommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NewClient<String, T>
{
	Socket client;
	
	ObjectOutputStream objOutStream;
	ObjectInputStream objInStream;
	
	public NewClient(String string, T object)
	{
		super();
		
		try
		{
			NewPacket<String, ?> packet = new NewPacket<>(string, object);
			client = new Socket(InetAddress.getLocalHost(), 63341);
			this.sendObject(packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendObject(NewPacket<String, ?> packet)
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
		{}
	}
	
	public NewPacket<?, ?> receiveObject()
	{
		NewPacket<?, ?> objReceive = null;
		
		try
		{
			if(client != null)
			{
				if (objInStream == null)
				{
					objInStream = new ObjectInputStream(client.getInputStream());
				}
				objReceive = (NewPacket<?, ?>) objInStream.readObject();
			}
		}
		catch (IOException | ClassNotFoundException | ClassCastException ignored)
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