package date;

public final class InvalidDateException  extends Exception
{
	/**
	 *
	 *    Constructor for InvalidDateException that is
	 *    used to handle exceptions that occur when the
	 *    user tries to set a date that is outside of
	 *    the acceptable range.
	 */
	
	public InvalidDateException()
	{
		super();
	}
	
	/**
	 *    Constructor for InvalidDateException that is
	 *    used to handle exceptions that occur when the
	 *    user tries to set a date that is outside of the acceptable range
	 *
	 *    @param message Error message to be created instead
	 *                   of the default message
	 */
	
	public InvalidDateException(String message)
	{
		super("Invalid Date: " + message);
	}
}