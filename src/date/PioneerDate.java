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
    private static final long serialVersionUID = 4099097738532790603L;

    /**
     *     Constructor for the PioneerDate class.
     */
    public PioneerDate()
    {
        super();
    }

    /**
     *    Constructor for the PioneerDate class.
     *
     *    @param year int year for the PioneerDate
     *    @param month int month for the PioneerDate
     *    @param day int day for the PioneerDate
     *    @throws InvalidDateException thrown if the date is invalid
     */
    public PioneerDate(int year, int month, int day) throws InvalidDateException
    {
        super();

        this.changeDate(day, month, year);
    }

    /**
     *    Constructor for the PioneerDate class.
     *
     *    @param date String that contains a date.
     *    @throws InvalidDateException thrown if the date is invalid
     */
    public PioneerDate(String date) throws InvalidDateException
    {
        this.setDate(date);
    }

    /**
     *    Method to set the date for a PioneerDate using a String.
     *
     *    @param date String that the date will be set to
     *    @throws InvalidDateException thrown if the date is invalid
     */
    public void setDate(String date) throws InvalidDateException
    {
        String[] dateArr;
        int newYear;
        int newMonth;
        int newDay;

        if(date == null)
        {
            throw new InvalidDateException("Empty date");
        }

        dateArr = date.split("/");

        // Check that the date is the proper length
        if(dateArr.length != 3)
        {
            throw new InvalidDateException("Invalid Date Format (mm/dd/yyyy): " + date);
        }

        // Set the year value
        try
        {
            newYear = Integer.parseInt(dateArr[2]);
        }
        catch (NumberFormatException e)
        {
            throw new InvalidDateException("Invalid Year: " + dateArr[2]);
        }

        // Set the month value
        try
        {
            newMonth = Integer.parseInt(dateArr[0]);
        }
        catch (NumberFormatException e)
        {
            throw new InvalidDateException("Invalid Month: " + dateArr[0]);
        }

        try
        {
            newDay = Integer.parseInt(dateArr[1]);
        }
        catch (NumberFormatException e)
        {
            throw new InvalidDateException("Invalid Day: " + dateArr[1]);
        }
        this.changeDate(newDay, newMonth, newYear);
    }

    /**
     *    Overridden setDay method that sets the day and checks if it
     *    is valid or not under different restrictions.
     *
     *    @param day int the day of the Date
     *    @throws InvalidDateException thrown if the date is invalid
     */
    @Override
    public void setDay(int day) throws InvalidDateException
    {
        if(this.isValidDate(super.getYear(), super.getMonth(), day))
        {
            super.setDay(day);
        }
    }

    /**
     *    Overridden setMonth method that sets the month and checks if it
     *    is valid or not under different restrictions.
     *
     *    @param month int the month of the Date
     *    @throws InvalidDateException thrown if the date is invalid
     */
    @Override
    public void setMonth(int month) throws InvalidDateException
    {
        if(this.isValidDate(super.getYear(), month, super.getDay()))
        {
            super.setMonth(month);
        }
    }

    /**
     *    Overridden setYear method that sets the year and checks if it
     *    is valid or not under different restrictions.
     *
     *    @param year int the year of the Date
     *    @throws InvalidDateException thrown if the date is invalid
     */
    @Override
    public void setYear(int year) throws InvalidDateException
    {
        if(this.isValidDate(year, super.getMonth(), super.getDay()))
        {
            super.setYear(year);
        }
    }

    /**
     *    Overridden compareDay method to compare two dates to one another.
     *
     *    @param date to compare to
     *    @return 0 if the dates are equal
     *            negative integer if this date is before the other date
     *            positive integer if this date if after the other date
     */
    @Override
    public int compareDay(Date date)
    {
        return super.compareDay(date);
    }

    /**
     *    Method used to change the date.
     *
     *    @param day int day to change to
     *    @param month int month to change to
     *    @param year int year to change to
     *    @throws InvalidDateException thrown if the date is invalid
     */
    private void changeDate(int day, int month, int year) throws InvalidDateException
    {
        if(this.isValidDate(year, month, day))
        {
            super.setYear(year);
            super.setMonth(month);
            super.setDay(day);
        }
    }

    /**
     *    Method used to check if a date is valid or not.
     *
     *    @param year int year of the date
     *    @param month int month of the date
     *    @param day int day of the date
     *    @return true if the date is valid
     *            false if the date is invalid
     *    @throws InvalidDateException thrown if the date is invalid
     */
    private boolean isValidDate(int year, int month, int day) throws InvalidDateException
    {
        int MAX_MONTHS_IN_FUTURE = 2;
        int relativeMonth = month;
        Date today = new Date(); // Object with the current date

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