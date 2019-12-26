package date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class PioneerDateTest
{
	PioneerDate date = new PioneerDate();
	
	@Test
	void setDate()
	{
		String dateStr = LocalDate.now().getMonthValue() + "/"
				+ LocalDate.now().getDayOfMonth() + "/"
				+ LocalDate.now().getYear();
		
		try
		{
			date.setDate("1/1/2019");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
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
		
		try
		{
			new PioneerDate(dateStr);
			new PioneerDate(LocalDate.now().getYear(),
					LocalDate.now().getMonthValue(),
					LocalDate.now().getDayOfMonth());
		} catch (InvalidDateException e)
		{
			assert false;
		}
		
		try
		{
			date.setDate("");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setDate(null);
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setDate("/1/1/1/1");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setDate("13/1/2000");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setDate("12/33/2000");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setDate("12/31/2300");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setDate("12/31/2a300");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setDate("12/3a1/2300");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setDate("1a2/31/2300");
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			new PioneerDate("2/1/2020");
			assert true;
		} catch (InvalidDateException e)
		{
			System.out.println(e.getMessage());
			assert false;
		}
		
		try
		{
			date.setDate("12/1/2020");
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
		
		try
		{
			date.setMonth(LocalDate.now().getMonthValue());
			date.setDay(LocalDate.now().getDayOfMonth() - 1);
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
	}
	
	@Test
	void setMonth()
	{
		try
		{
			date.setMonth(0);
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setMonth(13);
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
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