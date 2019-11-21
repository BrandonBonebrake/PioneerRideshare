package time;

import java.io.Serializable;

public class PioneerTime extends Time implements Serializable
{
    private static final long serialVersionUID = 4926157420631594654L;
    private final int EARLIEST_TIME_IN_DAY = 6;
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
    public boolean isValidHour(int hour)
    {
        // Checks if in valid range 6 - 22

        if(hour >= 6 && hour <= 22)
            return true;
        return false;
    }

    @Override
    public boolean isValidMinutes(int minutes)
    {
        if(this.getHours() == 22)
            return (minutes == 0);
        else
            return (minutes >= 0 && minutes <= 59);
    }

}
