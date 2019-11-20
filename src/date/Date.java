package date;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

/**
 This class creates a Date Object that can be used to hold
 any valid date that can exist. It can take in three ints
 in the constructor to set the date if valid or it will
 throw an error with details about what the error is.

 @author Brandon Bonebrake
 **/

public class Date implements Comparable<Date>, Comparator<Date>, Serializable
{
    // Class Constants
    private final int DEFAULT_YEAR  = LocalDate.now().getYear();
    private final int DEFAULT_MONTH = LocalDate.now().getMonthValue();
    private final int DEFAULT_DAY   = LocalDate.now().getDayOfMonth();

    private final int MIN_YEAR  = 0;
    private final int MAX_YEAR  = 2200;
    private final int MIN_MONTH = 1;
    private final int MAX_MONTH = 12;

    // Class Variables
    private int year  = DEFAULT_YEAR;
    private int month = DEFAULT_MONTH;
    private int day   = DEFAULT_DAY;

    private static final long serialVersionUID = 4099097738532790602L;

    /**
     *
     */
    public Date()
    {
        super();
    }

    public Date(int year, int month, int day) throws InvalidDateException
    {
        super();

        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public String getDate()
    {
        return this.getMonth() + "/" + this.getDay() + "/" + this.getYear();
    }

    /*** Setter Methods ***/

    public void setDay(int day) throws InvalidDateException
    {
        if(!this.isValidDay(day))
        {
            throw new InvalidDateException("D/**/ay " + day + " in month " + this.getMonth());
        }
        this.day = day;
    }

    public void setMonth(int month) throws InvalidDateException
    {
        if(!this.isValidMonth(month))
        {
            throw new InvalidDateException("Month " + month);
        }
        this.month = month;
    }

    public void setYear(int year) throws InvalidDateException
    {
        if(!this.isValidYear(year))
        {
            throw new InvalidDateException("Year " + year);
        }
        this.year = year;
    }

    /*** Observer Methods ***/

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

    public boolean isValidMonth(int month)
    {
        return (month >= MIN_MONTH && month <= MAX_MONTH);
    }

    public boolean isValidYear(int year)
    {
        return (year >= MIN_YEAR && year <= MAX_YEAR);
    }

    public boolean isLeapYear()
    {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    /*** Overridden Methods ***/

    public boolean equals(Date date)
    {
        return (this.compareTo(date) == 0);
    }

    @Override
    public String toString()
    {
        return this.getDate();
    }

    @Override
    public int compareTo(Date date)
    {
        // If == 0, they are equal. If > 0, this is farther in the future. If < 0, this is farther in the past
        int compareValue = compareYear(date);

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

    @Override
    public int compare(Date date1, Date date2)
    {
        return date1.compareTo(date2);
    }

    public int compareYear(Date date)
    {
        return this.getYear() - date.getYear();
    }

    public int compareMonth(Date date)
    {
        return this.getMonth() - date.getMonth();
    }

    public int compareDay(Date date)
    {
        return this.getDay() - date.getDay();
    }
}
