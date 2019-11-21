package socketCommunication;

import student.InvalidStudentException;
import student.Student;

import java.io.Serializable;

public class Client implements Serializable
{
    private static final long serialVersionUID = 4099097738532790605L;

    public Client(Object obj)
    {
        final int port = 63341;

        try
        {
            new ClientSend(obj,  port);
            //new ClientReceive(port);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
