package socketCommunication;

import java.io.Serializable;

public class Packet<T> implements Serializable
{
	private T object;
	private static final long serialVersionUID = -3971273030591442229L;
	
	public Packet(T object)
	{
		this.setPacket(object);
	}
	
	public T getObject()
	{
		return this.object;
	}
	
	public void setPacket(T object)
	{
		this.object = object;
	}
}