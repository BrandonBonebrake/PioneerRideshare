package student;

public class InvalidStudentException extends Exception
{
    /**
     *    Constructor for the InvalidStudentException that is thrown
     *    if the Student is invalid.
     */
    public InvalidStudentException()
    {
        super();
    }

    /**
     *    Constructor for the InvalidStudentException that is thrown
     *    if the Student is invalid with a message.
     */
    public InvalidStudentException(String message)
    {
        super(message);
    }
}
