package date;

import java.io.Serializable;

public final class PioneerDate extends Date implements Serializable
{
    private final int MAX_MONTHS_IN_FUTURE = 3;

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

    public void changeDate(int day, int month, int year) throws InvalidDateException
    {
        if(this.isValidDate(year, month))
        {
            super.setYear(year);
            super.setMonth(month);
            super.setDay(day);
        }
    }

    public boolean isValidDate(int year, int month) throws InvalidDateException
    {
        Date today = new Date();
        int relativeMonth = month;

        // Check if this or next year
        if(year != today.getYear() || year != today.getYear() + 1)
        {
            throw new InvalidDateException("Invalid Date: Invalid Year");
        }
        if(year == today.getYear() + 1)
        {
            relativeMonth += 12; // Used to obtain the difference in months
        }

        relativeMonth -= today.getMonth(); // How many months difference is there

        // Check that the difference is within the range
        if(relativeMonth > MAX_MONTHS_IN_FUTURE)
        {
            throw new InvalidDateException("Invalid Date: Date to far in the future");
        }
        return true;
    }
}