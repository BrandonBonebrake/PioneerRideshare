package time;

import java.io.Serializable;

public class PioneerTime extends Time implements Serializable
{
    private static final long serialVersionUID = 4926157420631594654L;

    public PioneerTime()
    {
        super();
    }

    public PioneerTime(String strTime) throws InvalidTimeException
    {
        super(strTime);
    }
}
