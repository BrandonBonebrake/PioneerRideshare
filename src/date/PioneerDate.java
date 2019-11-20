package date;

import java.io.Serializable;

/**
 This class creates a PioneerDate Object that can be used to hold
 any valid date at most {@code MAX_MONTHS_IN_FUTURE} months in the
 future. It will also not allow for dates in the past to be set.
 Overloads the setters form Date to disallow invalid dates from
 being set.

 @author Brandon Bonebrake
 **/
public final class PioneerDate extends Date implements Serializable
{
    private final int MAX_MONTHS_IN_FUTURE = 2;

    private static final long serialVersionUID = 4099097738532790603L;

    public PioneerDate()
    {
        super();
    }

    public PioneerDate(int year, int month, int day) throws InvalidDateException
    {
        super();

        this.changeDate(day, month, year);
    }

    @Override
    public void setDay(int day) throws InvalidDateException
    {
        if(this.isValidDate(super.getYear(), super.getMonth(), day))
        {
            super.setDay(day);
        }
    }

    @Override
    public void setMonth(int month) throws InvalidDateException
    {
        if(this.isValidDate(super.getYear(), month, super.getDay()))
        {
            super.setMonth(month);
        }
    }

    @Override
    public void setYear(int year) throws InvalidDateException
    {
        if(this.isValidDate(year, super.getMonth(), super.getDay()))
        {
            super.setYear(year);
        }
    }

    public void changeDate(int day, int month, int year) throws InvalidDateException
    {
        if(this.isValidDate(year, month, day))
        {
            super.setYear(year);
            super.setMonth(month);
            super.setDay(day);
        }
    }

    public boolean isValidDate(int year, int month, int day) throws InvalidDateException
    {
        Date today = new Date(); // Object with the current date
        int relativeMonth = month;

        // Catch any fundamental errors with the date
        new Date(year, month, day);

        // Check if this or next year
        if(year != today.getYear() && year != today.getYear() + 1)
        {
            throw new InvalidDateException("Invalid Year");
        }
        if(year == today.getYear() + 1)
        {
            relativeMonth += 12; // Used to obtain the difference in months
        }

        // Get the absolute difference between the months
        relativeMonth -= today.getMonth(); // How many months difference is there

        // Check that the difference is within the range
        if(relativeMonth > MAX_MONTHS_IN_FUTURE)
        {
            throw new InvalidDateException("Date to many months in the future");
        }
        else if(relativeMonth < 0)
        {
            throw new InvalidDateException("Month is in the past");
        }
        else if(relativeMonth == 0 && day < today.getDay())
        {
            throw new InvalidDateException("Day is in the past");
        }
        return true;
    }
}