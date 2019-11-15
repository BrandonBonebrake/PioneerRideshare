package student;

public class InvalidStudentException extends Exception {
    public InvalidStudentException()
    {
        super();
    }

    public InvalidStudentException(String message)
    {
        super(message);
    }
}
