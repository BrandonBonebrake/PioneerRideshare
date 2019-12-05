package time;

import java.io.Serializable;

public class PioneerTime extends Time implements Serializable
{
    private static final long serialVersionUID = 4926157420631594654L;
    private final int EARLIEST_TIME_IN_DAY = 4;
    private final int LATEST_TIME_IN_DAY = 22;

    /**
     *    Default constructor for the PioneerTime class.
     */
    public PioneerTime()
    {
        super();
    }

    /**
     *    Constructor for the PioneerTime class.
     *
     *    @param strTime   String containing the time
     *    @throws InvalidTimeException Thrown if string has an invalid format
     *
     */
    public PioneerTime(String strTime) throws InvalidTimeException
    {
        super(strTime);
    }

    /**
     *    Constructor for the PioneerTimeClass.
     *
     *    @param hours     int containing hours
     *    @param minutes   int containing minutes
     *
     *    @throws InvalidTimeException Thrown if hours are outside of range
     */
    public PioneerTime(int hours, int minutes)  throws InvalidTimeException
    {
        super(hours, minutes);
    }

    /**
     *    Overridden setHours method that checks if hours are valid.
     *
     *    @param hour valid only if between 4-22
     *
     *    @throws InvalidTimeException Thrown if hour is outside of range
     */
    @Override
    public void setHours(int hour) throws InvalidTimeException
    {
        if(this.isValidHour(hour))
        {
            super.setHours(hour);
        }
        else
        {
            throw new InvalidTimeException("Invalid hour: " + hour +
                    " - Must be in the range 4 - 22");
        }
    }

    /**
     *    Overridden isValidHour method that checks if hour is in range of 6 - 22.
     *
     *    @param hour Current hour to check
     *
     *    @return true if hour in valid range 6 - 22
     *            false if hour not in valid range
     */
    @Override
    public boolean isValidHour(int hour)
    {
        // Checks if in valid range 6 - 22
        if(hour >= EARLIEST_TIME_IN_DAY && hour <= LATEST_TIME_IN_DAY)
            return true;
        return false;
    }
}