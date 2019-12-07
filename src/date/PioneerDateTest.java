package date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PioneerDateTest
{
    PioneerDate date = new PioneerDate();

    @Test
    void setDate()
    {
        try
        {
            date.setDate("1/1/2019");
            assert false;
        } catch (InvalidDateException e) { }

        try
        {
            date.setDate(LocalDateTime.now().getMonthValue() + "/"
                    + LocalDateTime.now().getDayOfMonth() + "/"
                    +  LocalDateTime.now().getYear());
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }

        try
        {
            date.setDate(LocalDateTime.now().getMonthValue() + "/"
                    + LocalDateTime.now().getDayOfMonth() + "/"
                    +  LocalDateTime.now().getYear() + 1);
            assert false;
        } catch (InvalidDateException e)
        {
            assert true;
        }

    }

    @Test
    void setDay()
    {
        try
        {
            date.setDay(0);
            assert false;
        } catch (InvalidDateException e)
        {
            assert true;
        }

        try
        {
            date.setDay(32);
            assert false;
        } catch (InvalidDateException e)
        {
            assert true;
        }

        try
        {
            date.setDay(28);
        } catch (InvalidDateException e)
        {
            assert false;
        }
    }

    @Test
    void setMonth()
    {
        try
        {
            date.setMonth(0);
            assert false;
        } catch (InvalidDateException e) {}

        try
        {
            date.setMonth(13);
            assert false;
        } catch (InvalidDateException e) {}

        try
        {
            int month = LocalDateTime.now().getMonthValue() + 1;
            if(month > 12)
            {
                date.setYear(date.getYear() + 1);
                month = 1;
            }
            date.setMonth(month);
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }
    }

    @Test
    void setYear()
    {
        try
        {
            date.setYear(LocalDateTime.now().getYear());
            assert true;
        } catch (InvalidDateException e)
        {
            assert false;
        }

        try
        {
            date.setYear(LocalDateTime.now().getYear() - 1);
            assert false;
        } catch (InvalidDateException e)
        {
            assert true;
        }
    }

    @Test
    void compareDay() throws InvalidDateException
    {
        assert date.compareDay(date) == 0;
        assert date.compareDay(new Date(1, 1, 1)) != 0;
    }
}