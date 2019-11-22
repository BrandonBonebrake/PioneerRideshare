package time;

import java.io.Serializable;

public class PioneerTime extends Time implements Serializable
{
    private static final long serialVersionUID = 4926157420631594654L;
    private final int EARLIEST_TIME_IN_DAY = 4;
    private final int LATEST_TIME_IN_DAY = 22;

    public PioneerTime()
    {
        super();
    }

    public PioneerTime(String strTime) throws InvalidTimeException
    {
        super(strTime);
    }

    public PioneerTime(int hours, int minutes)  throws InvalidTimeException
    {
        super(hours, minutes);
    }

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

    @Override
    public boolean isValidHour(int hour)
    {
        // Checks if in valid range 6 - 22

        if(hour >= EARLIEST_TIME_IN_DAY && hour <= LATEST_TIME_IN_DAY)
            return true;
        return false;
    }

}
