package time;

public final class InvalidTimeException extends Exception
{
	/**
	 *
	 *    Constructor for InvalidTimeException that is used to handle exceptions
	 *    that occur when the user tries to set a client.time
	 *    that is outside of the acceptable range
	 *
	 */
	public InvalidTimeException()
	{
		super();
	}
	
	/**
	 *
	 *    Constructor for InvalidTimeException that is used to handle exceptions
	 *    that occur when the user tries to set a client.time
	 *    that is outside of the acceptable range
	 *
	 *    @param message Error message to be created instead
	 *                   of the default message
	 */
	public InvalidTimeException( String message )
	{
		super( message );
	}
}
