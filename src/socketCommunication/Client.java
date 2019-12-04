package socketCommunication;

import student.InvalidStudentException;
import student.Student;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Serializable
{
    private static final long serialVersionUID = 4099097738532790605L;

    public Client(Object obj)
    {
        final int port = 63341;

        try
        {
            ClientSend cSend = new ClientSend(obj,  port);
            //ClientReceive cReceive = new ClientReceive(port);
            //ServerReceive cReceive = new ServerReceive(new Socket(), null);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}