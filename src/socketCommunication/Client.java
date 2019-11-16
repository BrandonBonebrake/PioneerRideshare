package socketCommunication;

import student.InvalidStudentException;
import student.Student;

public class Client
{
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
