package date;

import java.time.LocalDateTime;

class DateTest
{
    Date date = new Date();

    @org.junit.jupiter.api.Test
    void getDay()
    {
        assert date.getDay() == LocalDateTime.now().getDayOfMonth();
    }

    @org.junit.jupiter.api.Test
    void getMonth()
    {
        assert date.getMonth() == LocalDateTime.now().getMonthValue();
    }

    @org.junit.jupiter.api.Test
    void getYear()
    {
        assert date.getYear() == LocalDateTime.now().getYear();
    }

    @org.junit.jupiter.api.Test
    void getDate()
    {
        String month = String.valueOf(LocalDateTime.now().getMonthValue());
        String day   = String.valueOf(LocalDateTime.now().getDayOfMonth());

        if(Integer.parseInt(month) < 10)
        {
            month = "0" + month;
        }
        if(Integer.parseInt(day) < 10)
        {
            day = "0" + day;
        }

        assert date.getDate().equals(month + "/" + day + "/" + LocalDateTime.now().getYear());
    }

    @org.junit.jupiter.api.Test
    void setDay()
    {
        try
        {
            date.setDay(32);
            assert false;
        } catch (InvalidDateException e){}

        try
        {
            date.setDay(0);
            assert false;
        } catch (InvalidDateException e){}

        try
        {
            date.setDay(1);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }

        try
        {
            date.setDay(28);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }

        try
        {
            date.setDay(15);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }
    }

    @org.junit.jupiter.api.Test
    void setMonth()
    {
        try
        {
            date.setMonth(0);
            assert false;
        } catch (InvalidDateException e) { }

        try
        {
            date.setMonth(13);
            assert false;
        } catch (InvalidDateException e) { }

        try
        {
            date.setMonth(1);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }

        try
        {
            date.setMonth(12);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }

        try
        {
            date.setMonth(6);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }
    }

    @org.junit.jupiter.api.Test
    void setYear()
    {
        try
        {
            date.setYear(-1);
            assert false;
        } catch (InvalidDateException e) { }

        try
        {
            date.setYear(2201);
            assert false;
        } catch (InvalidDateException e) { }

        try
        {
            date.setYear(0);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }

        try
        {
            date.setYear(2200);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }

        try
        {
            date.setYear(LocalDateTime.now().getYear());
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }
    }

    @org.junit.jupiter.api.Test
    void isValidDay()
    {
        assert !date.isValidDay(-1);
        assert !date.isValidDay(0);
        assert !date.isValidDay(32);
        assert date.isValidDay(1);
        assert date.isValidDay(15);
        assert date.isValidDay(28);
    }

    @org.junit.jupiter.api.Test
    void testEquals() throws InvalidDateException
    {
        Date date1 = new Date(date);

        assert date1.equals(date);
        assert date1.equals(date1);
        assert !date1.equals(new Date(11, 11, 1));
        assert !date.equals(new Date(1, 1, 1));
    }

    @org.junit.jupiter.api.Test
    void testToString()
    {
        Date date1 = new Date(date);

        assert date.toString().equals(date1.toString());
        assert !date.toString().equals(date1.toString() + " ");
    }

    @org.junit.jupiter.api.Test
    void compareTo() throws InvalidDateException
    {
        Date date1 = new Date(date);
        Date date2 = new Date(1, 1, 1);

        assert date1.compareTo(date) == 0;
        assert date1.compareTo(date2) != 0;
    }

    @org.junit.jupiter.api.Test
    void compare() throws InvalidDateException
    {
        Date date1 = new Date(date);
        Date date2 = new Date(1, 1, 1);

        assert date1.compare(date1, date) == 0;
        assert date2.compare(date2, date2) == 0;
        assert date.compare(date1, date2) != 0;
    }

    @org.junit.jupiter.api.Test
    void compareDay() throws InvalidDateException
    {
        Date date1 = new Date(1, 1, 1);

        if(date.getDay() == 1)
        {
            date1.setDay(2);
        }

        assert date.compareDay(date) == 0;
        assert date.compareDay(date1) != 0;
    }
}