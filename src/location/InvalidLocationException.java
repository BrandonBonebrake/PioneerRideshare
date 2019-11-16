package location;

public class InvalidLocationException extends Exception
{
    /*** Class Constructor - InvalidAddressException
     *
     *    This constructor is used to handle exceptions
     *    that occur when the user tries to set a address
     *    that is outside of the acceptable range
     *
     * ***/

    public InvalidLocationException()
    {
        super();
    }

    /*** Class Constructor - InvalidAddressException
     *
     *    This constructor is used to handle exceptions
     *    that occur when the user tries to set a address
     *    that is outside of the acceptable range
     *
     *    @param message Error message to be created instead
     *                   of the default message
     *
     * ***/

    public InvalidLocationException(String message)
    {
        super(message);
    }
}