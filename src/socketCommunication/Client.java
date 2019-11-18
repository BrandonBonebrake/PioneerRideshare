package socketCommunication;

import student.InvalidStudentException;
import student.Student;

import java.io.Serializable;

public class Client implements Serializable
{
    private static final long serialVersionUID = 4099097738532790605L;

    private Client() throws InvalidStudentException
    {
        final int port = 63341;
        StringBuilder str  = new StringBuilder("First String");
        StringBuilder str1 = new StringBuilder("Second String");
        Student student = new Student("Brandon", "Bonebrake","bbonebrakebr@uwplatt.edu", "This is a test#1");

        try
        {
            new ClientSend(str,  port);
            //new ClientReceive(port);
            new ClientSend(str1, port);
            new ClientSend(student, port);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
