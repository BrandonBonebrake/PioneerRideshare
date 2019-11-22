package time;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

/** Class - Time <p>
 *
 *    Object used to hold the current client.time
 *    of day or set a specific client.time of day
 *    to hold
 *
 *    @author  Brandon Bonebrake, Bryce Bierman
 *
 **/

public class Time implements Serializable, Comparable<Time>, Comparator<Time>
{
    // Class Constants
    private final int DEFAULT_HOUR    = LocalDateTime.now().getHour();
    private final int DEFAULT_MINUTES = LocalDateTime.now().getMinute();

    // Class Variables
    private int hour    = DEFAULT_HOUR;
    private int minutes = DEFAULT_MINUTES;

    private static final long serialVersionUID = 4099097738532790604L;

    /**
     *    Default constructor for the Time class.
     *
     **/
    public Time()
    {
        super();
    }

    /**
     *    Constructor for Time class that sets hours and minutes.
     *
     *    @param hour   Hour of the day to set ( 0 - 23 )
     *    @param minutes Minute of the day to set ( 0 - 59 )
     *
     *    @throws InvalidTimeException Must have a valid hour 0 - 23 and minute 0 - 59
     *
     **/
    public Time(int hour, int minutes) throws InvalidTimeException
    {
        this.setTime(hour, minutes);
    }

    /**
     *    Constructor for the Time class that sets hours and minutes
     *
     *    @param strTime Time in the format hh:mm
     *
     *    @throws InvalidTimeException Must be on the format hh:mm
     *
     **/
    public Time(String strTime) throws InvalidTimeException
    {
        this.setTime(strTime);
    }

    /**
     *   Return the hours
     *
     *   @return hours
     *
     **/
    public int getHours() { return this.hour; }

    /**
     *   Return the minutes
     *
     *   @return minutes
     *
     **/
    public int getMinutes() { return this.minutes; }

    /**
     *   Return the time in string format.
     *
     *   @return String of time stored in object
     *
     **/
    public String getTime()
    {
        // Local Variables
        String hours   = Integer.toString(this.getHours());
        String minutes = Integer.toString(this.getMinutes());

        // Add leading zero if hours is not two chars long
        if(this.getHours() < 10)
        {
            hours = "0" + hours;
        }

        // Add leading zero if minutes is not two chars long
        if(this.getMinutes() < 10)
        {
            minutes = "0" + minutes;
        }
        return hours + ":" + minutes;
    }

    /**
     *   Return the time that the object was created
     *   in the format HH:MM
     *
     *   @return String of time object was created in the format HH:MM
     *
     **/
    public String getTimeCreated()
    {
        return this.DEFAULT_HOUR + ":" + this.DEFAULT_MINUTES;
    }

    /**
     *   Sets the hour to the value passed
     *   in if <code>isValidHour</code> is true
     *
     *   @param hour valid only if between 0-23
     *
     *   @throws InvalidTimeException thrown if hour is invalid
     *
     **/
    public void setHours(int hour) throws InvalidTimeException
    {
        if(this.isValidHour(hour))
        {
            this.hour = hour;
        }
        else
        {
            throw new InvalidTimeException("Invalid hour: " + hour +
                    " - Must be in the range 0 - 23");
        }
    }

    /**
     *   Sets the minutes to the value passed
     *   in if <code>isValidMinutes</code> is true
     *
     *   @param minutes valid only if between 0-59
     *
     *   @throws InvalidTimeException thrown if minutes are invalid
     *
     **/
    public void setMinutes(int minutes) throws InvalidTimeException
    {
        if(this.isValidMinutes(minutes))
        {
            this.minutes = minutes;
        }
        else
        {
            throw new InvalidTimeException("Invalid minute: " + minutes +
                    " - Must be in the range 0 - 59");
        }
    }

    /**
     *   Sets the time to the system current local time.
     *
     **/
    public void setTime()
    {
        this.hour    = LocalDateTime.now().getHour();
        this.minutes = LocalDateTime.now().getMinute();
    }

    /**
     *   Sets the time to the time passed in String
     *
     *   @param strTime String in the format HH:MM
     *
     *   @throws InvalidTimeException thrown if strTime String is invalid
     *
     **/
    public void setTime(String strTime) throws InvalidTimeException
    {
        // Local Variable

        String[] time = null;

        // Validating if strTime can be tested

        if(strTime == null)
        {
            throw new InvalidTimeException("Time string format is invalid (Valid hh:mm): time cannot be null ");
        }
        time = strTime.split( ":" );

        // Tests after stTime is validated

        if(time.length == 2 && !strTime.substring(strTime.length() - 1).equals(":"))
        {
            // Convert first part of strTime to the hours integer
            try
            {
                this.setHours(Integer.parseInt(time[ 0 ]));
            }
            catch(NumberFormatException e)
            {
                throw new InvalidTimeException("Time string format is invalid (Valid hh:mm): " + strTime +
                        " Hours contains a characters that are not integers ( " + time[0] + " )");
            }
            // Convert second part of strTime to the minutes integer

            try
            {
                this.setMinutes(Integer.parseInt(time[1]));
            }
            catch( NumberFormatException e )
            {
                throw new InvalidTimeException("Time string format is invalid (Valid hh:mm): " + strTime +
                        " Minutes contains a characters that are "
                        + "not integers ( " + time[ 1 ] + " )" );
            }
        }
        else
        {
            throw new InvalidTimeException("Time string format is invalid (Valid hh:mm): " + strTime +
                    " The only character can be one colon "
                    + "':' to divide the client.time");
        }
    }

    /**
     *   Sets the time to passed in Time object
     *
     *   @param time Time object
     *
     *   @throws InvalidTimeException thrown if Time object is null
     *
     **/
    public void setTime(Time time) throws InvalidTimeException
    {
        this.setTime(time.getHours(), time.getMinutes());
    }

    /**
     *     Method used to set the time in hours and minutes.
     *
     *     @param hours     Hour of the day to set ( 0 - 23 )
     *     @param minutes   Minute of the day to set ( 0 - 59 )
     *
     *     @throws InvalidTimeException Must have a valid hour 0 - 23 and minute 0 - 59
     *
     **/
    public void setTime(int hours, int minutes) throws InvalidTimeException
    {
        this.setHours(hours);
        this.setMinutes(minutes);
    }

    /**
     *    Returns true/false if the value
     *    supplied by the user is valid.
     *    Valid range is ( 0 - 23 )
     *
     *    @param hour Current hour to check
     *
     *    @return     Whether the hour is valid
     *
     **/
    public boolean isValidHour(int hour)
    {
        if(hour >= 0 && hour <= 23)
            return true;
        return false;
    }

    /**
     *    Returns true/false if the value
     *    supplied by the user is valid.
     *    Valid range is ( 0 - 59 )
     *
     *    @param minutes Current minute to check
     *
     *    @return        Whether the minute is valid
     *
     **/
    public boolean isValidMinutes(int minutes)
    {
        if(minutes >= 0 && minutes <= 59)
            return true;
        return false;
    }

    /**
     *    Compares two times to each other and returns
     *    the difference between the two in minutes.
     *
     *    @param    time1
     *    @param    time2
     *    @return   int Difference between time1 & time2 in minutes
     **/
    public int compare(Time time1, Time time2)
    {
        return time1.compareTo(time2);
    }

    /**
     *    Compares two times to each other and returns
     *    the difference between the two in minutes.
     *
     *    @param    time compare to this
     *    @return   int Difference between time1 & time2 in minutes
     **/
    public int compareTo(Time time)
    {
        int minutesTotal = (this.getHours() * 60) + this.getMinutes();
        int minutesTotal2 = (time.getHours() * 60) + time.getMinutes();
        return minutesTotal - minutesTotal2;
    }

    /**
     *    Overridden equals method to check if two times are equal.
     *
     *    @param obj what this is compared to
     *    @return true if the times are the same
     *            false if the times are not the same
     **/
    @Override
    public boolean equals(Object obj)
    {
        return (obj != null && obj instanceof Time && this.compareTo((Time)obj) == 0);
    }

    /**
     *    Overridden toString method outputs the time in the format hh:mm.
     *
     *    @return time in the format hh:mm
     *
     **/
    public String toString()
    {
        return this.getTime();
    }
}
