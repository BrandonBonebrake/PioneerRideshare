package socketCommunication;

import java.io.Serializable;

public class NewPacket<String, T> implements Serializable
{
	private String string;
	private T object;
	private static final long serialVersionUID = 6034379265577128812L;
	
	public NewPacket(String str, T object)
	{
		this.setPacket(str, object);
	}
	
	public T getObject()
	{
		return this.object;
	}
	
	public String getString()
	{
		return this.string;
	}
	
	public void setPacket(String str, T object)
	{
		this.string = str;
		this.object = object;
	}
}