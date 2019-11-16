package date;

public final class InvalidDateException  extends Exception
{
    /*** Class Constructor - InvalidDateException
     *
     *    This constructor is used to handle exceptions
     *    that occur when the user tries to set a date
     *    that is outside of the acceptable range
     *
     * ***/

    public InvalidDateException()
    {
        super();
    }

    /*** Class Constructor - InvalidDateException
     *
     *    This constructor is used to handle exceptions
     *    that occur when the user tries to set a date
     *    that is outside of the acceptable range
     *
     *    @param message Error message to be created instead
     *                   of the default message
     *
     * ***/

    public InvalidDateException( String message )
    {
        super( message );
    }
}