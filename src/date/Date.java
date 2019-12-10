package date;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

/**
 *    This class creates a Date Object that can be used to hold
 *    any valid date that can exist. It can take in three ints
 *    in the constructor to set the date if valid or it will
 *    throw an error with details about what the error is.
 *
 *    @author Brandon Bonebrake
 */

public class Date implements Comparable<Date>, Comparator<Date>, Serializable
{
    // Class Constants
    private final int DEFAULT_YEAR  = LocalDate.now().getYear();
    private final int DEFAULT_MONTH = LocalDate.now().getMonthValue();
    private final int DEFAULT_DAY   = LocalDate.now().getDayOfMonth();

    // Class Variables
    private int year  = DEFAULT_YEAR;
    private int month = DEFAULT_MONTH;
    private int day   = DEFAULT_DAY;

    private static final long serialVersionUID = 4099097738532790602L;

    /**
     *    Constructor for the Date class.
     */
    public Date()
    {
        super();
    }

    /**
     *    Constructor for the Date class.
     *
     *    @param year the year of the date
     *    @param month the month of the date
     *    @param day the day of the date
     *    @throws InvalidDateException thrown if the year, month, and day are not valid
     */
    public Date(int year, int month, int day) throws InvalidDateException
    {
        super();

        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    /**
     * Constructor for the Date class.
     *
     * @param date Date class that already exists
     */
    public Date(Date date)
    {
        super();

        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
    }

    /**
     *    Method that returns the day.
     *
     *    @return int day stored in date
     */
    public int getDay()
    {
        return day;
    }

    /**
     *    Method that returns the month.
     *
     *    @return int month stored in date
     */
    public int getMonth()
    {
        return month;
    }

    /**
     *    Method that returns the year.
     *
     *    @return int year stored in date
     */
    public int getYear()
    {
        return year;
    }

    /**
     *    Method that returns the date as a String.
     *
     *    @return String the date with /'s between numbers
     */
    public String getDate()
    {
        String month = String.valueOf(this.getMonth());
        String day   = String.valueOf(this.getDay());

        if(month.length() == 1)
        {
            month = "0" + month;
        }
        if(day.length() == 1)
        {
            day = "0" + day;
        }
        return month + "/" + day + "/" + this.getYear();
    }

    /**
     *    Method to set the day of a Date.
     *
     *    @param day int the day of the Date
     *    @throws InvalidDateException thrown if the day is invalid
     */
    public void setDay(int day) throws InvalidDateException
    {
        if(!this.isValidDay(day))
        {
            throw new InvalidDateException("D/**/ay " + day + " in month " + this.getMonth());
        }
        this.day = day;
    }

    /**
     *    Method to set the month of a Date.
     *
     *    @param month int the day of the Date
     *    @throws InvalidDateException thrown if the month is invalid
     */
    public void setMonth(int month) throws InvalidDateException
    {
        if(!this.isValidMonth(month))
        {
            throw new InvalidDateException("Month " + month);
        }
        this.month = month;
    }

    /**
     *    Method to set the year of a Date.
     *
     *    @param year int the day of the Date
     *    @throws InvalidDateException thrown if the year is invalid
     */
    public void setYear(int year) throws InvalidDateException
    {
        if(!this.isValidYear(year))
        {
            throw new InvalidDateException("Year " + year);
        }
        this.year = year;
    }

    public void setDate(String date) throws InvalidDateException
    {
        String[] dateArr;

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
            this.setYear(Integer.parseInt(dateArr[2]));
        }
        catch (NumberFormatException e)
        {
            throw new InvalidDateException("Invalid Year: " + dateArr[2]);
        }

        // Set the month value
        try
        {
            this.setMonth(Integer.parseInt(dateArr[0]));
        }
        catch (NumberFormatException e)
        {
            throw new InvalidDateException("Invalid Month: " + dateArr[0]);
        }

        try
        {
            this.setDay(Integer.parseInt(dateArr[1]));
        }
        catch (NumberFormatException e)
        {
            throw new InvalidDateException("Invalid Day: " + dateArr[1]);
        }
    }

    /**
     *    Method used to check if a day is valid.
     *
     *    @param day int day to be set for Date
     *    @return true if the day is valid
     *            false if the day is invalid
     */
    public boolean isValidDay(int day)
    {
        // Check if month has 31, 30, or 28/29 days
        return (((month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12) &&
                (day >= 1 && day <= 31)) ||
                (((month == 4 || month == 6 || month == 9 || month == 11)
                        && (day >= 1 && day <= 30))) ||
                (month == 2 && day >= 1) && (day <= 28 ||
                        (this.isLeapYear() && day <= 29)));
    }

    /**
     *    Method used to check if a month is valid.
     *
     *    @param month int month to be set for Date
     *    @return true if the month is valid
     *            false if the month is invalid
     */
    private boolean isValidMonth(int month)
    {
        int MIN_MONTH = 1;
        int MAX_MONTH = 12;

        return (month >= MIN_MONTH && month <= MAX_MONTH);
    }

    /**
     *    Method used to check if a year is valid.
     *
     *    @param year int year to be set for Date
     *    @return true if the year is valid
     *            false if the year is invalid
     */
    private boolean isValidYear(int year)
    {
        int MIN_YEAR = 0;
        int MAX_YEAR = 2200;

        return (year >= MIN_YEAR && year <= MAX_YEAR);
    }

    /**
     *    Method used to check if a year is a leap year.
     *
     *    @return true if the year is a leap year
     *            false if the year is not a leap year
     */
    private boolean isLeapYear()
    {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    /**
     *    Overridden equals method that returns whether
     *    this and another Date are equal to one another.
     *
     *    @param obj Object to be compared to
     *    @return true if the two Objects are equal
     *            false if the two Objects are not equal
     */
    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof Date && this.compareTo((Date) obj) == 0);
    }

    /**
     *    Overridden toString method that returns a String of the Date.
     *
     *    @return String of the Date
     */
    @Override
    public String toString()
    {
        return this.getDate();
    }

    /**
     *    Overridden compareTo method that compares two Dates to one another.
     *
     *    @param date Date to compare to
     *    @return 0 if the dates are equal
     *            negative value if this date is before the other
     *            positive value if this date is after the other
     */
    @Override
    public int compareTo(Date date)
    {
        // If == 0, they are equal. If > 0, this is farther in the future. If < 0, this is farther in the past
        int compareValue = this.compareYear(date);

        // Compare month values
        if(compareValue == 0)
        {
            compareValue = this.compareMonth(date);
        }

        // Compare day values
        if(compareValue == 0)
        {
            compareValue = this.compareDay(date);
        }
        return compareValue;
    }

    /**
     *    Overridden method that compares two dates to one another.
     *
     *    @param date1 date to be compared
     *    @param date2 date to compare to
     *    @return 0 if the two dates are equal
     *            negative integer if the date1 is before date2
     *            positive integer if the date1 is after date2
     */
    @Override
    public int compare(Date date1, Date date2)
    {
        return date1.compareTo(date2);
    }

    /**
     *    Overridden method that compares two years to one another.
     *
     *    @param date to compare to
     *    @return 0 if the two years are equal
     *            negative integer if this year is before the other
     *            positive integer if this year is after the other
     */
    private int compareYear(Date date)
    {
        return this.getYear() - date.getYear();
    }

    /**
     *    Overridden method that compares two months to one another.
     *
     *    @param date to compare to
     *    @return 0 if the two months are equal
     *            negative integer if this month is before the other
     *            positive integer if this month is after the other
     */
    private int compareMonth(Date date)
    {
        return this.getMonth() - date.getMonth();
    }

    /**
     *    Overridden method that compares two days to one another.
     *
     *    @param date to compare to
     *    @return 0 if the two days are equal
     *            negative integer if this day is before the other
     *            positive integer if this day is after the other
     */
    int compareDay(Date date)
    {
        return this.getDay() - date.getDay();
    }
}