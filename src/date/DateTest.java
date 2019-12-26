package date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateTest
{
	Date date = new Date();
	
	@Test
	void getDay()
	{
		assert date.getDay() == LocalDateTime.now().getDayOfMonth();
	}
	
	@Test
	void getMonth()
	{
		assert date.getMonth() == LocalDateTime.now().getMonthValue();
	}
	
	@Test
	void getYear()
	{
		assert date.getYear() == LocalDateTime.now().getYear();
	}
	
	@Test
	void getDate() throws InvalidDateException
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
		
		date.setMonth(1);
		month = "01";
		assert date.getDate().equals(month + "/" + day + "/" + LocalDateTime.now().getYear());
	}
	
	@Test
	void setDay()
	{
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
			date.setDay(0);
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
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
		
		// Try to set 10,000 different days
		for (int i = 0; i < 10000; i++)
		{
			try
			{
				date.setDay(date.getDay() + 1);
				
				if(!date.isValidDay(date.getDay()) ||
						date.getDay() > 31)
				{
					assert false;
				}
			} catch (InvalidDateException e)
			{
				try
				{
					date.setDay(1);
					date.setMonth(date.getMonth() + 1);
				} catch (InvalidDateException ex)
				{
					try
					{
						date.setMonth(1);
						date.setDay(1);
						date.setYear(date.getYear() + 1);
					} catch (InvalidDateException exc)
					{
						assert false;
					}
				}
			}
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
		
		for (int i = 0; i < 96; i++)
		{
			try
			{
				date.setMonth(date.getMonth() + 1);
				
				if(date.getMonth() > 12)
				{
					assert false;
				}
			} catch (InvalidDateException e)
			{
				try
				{
					date.setMonth(1);
					date.setYear(date.getYear() + 1);
				} catch (InvalidDateException ex)
				{
					assert false;
				}
			}
		}
		
	}
	
	@Test
	void setYear()
	{
		try
		{
			date.setYear(-1);
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		try
		{
			date.setYear(2201);
			assert false;
		} catch (InvalidDateException e)
		{
			assert true;
		}
		
		// Attempt every possible valid day
		try
		{
			date.setYear(0);
			
			for (int i = 1; i < 2200; i++)
			{
				date.setYear(i);
			}
		} catch (InvalidDateException e)
		{
			assert false;
		}
	}
	
	@Test
	void isValidDay()
	{
		assert !date.isValidDay(-1);
		assert !date.isValidDay(0);
		assert !date.isValidDay(32);
		assert date.isValidDay(1);
		assert date.isValidDay(15);
		assert date.isValidDay(28);
		
		try
		{
			date.setMonth(12);
		} catch (InvalidDateException e)
		{
			assert false;
		}
		
		for (int i = -1; i < LocalDateTime.MAX.getDayOfMonth() + 2; i++)
		{
			try
			{
				date.setDay(i);
			} catch (InvalidDateException e)
			{
				if(i > 0 && i <= LocalDateTime.MAX.getDayOfMonth())
				{
					assert false;
				}
			}
		}
	}
	
	@Test
	void testEquals() throws InvalidDateException
	{
		Date date1 = new Date(date);
		
		assert date1.equals(date);
		assert date1.equals(date1);
		assert !date1.equals(new Date(11, 11, 1));
		assert !date.equals(new Date(1, 1, 1));
	}
	
	@Test
	void testToString()
	{
		Date date1 = new Date(date);
		
		assert date.toString().equals(date1.toString());
		assert !date.toString().equals(date1.toString() + " ");
	}
	
	@Test
	void compareTo() throws InvalidDateException
	{
		Date date1 = new Date(date);
		Date date2 = new Date(1, 1, 1);
		
		assert date1.compareTo(date) == 0;
		assert date1.compareTo(date2) != 0;
	}
	
	@Test
	void compare() throws InvalidDateException
	{
		Date date1 = new Date(date);
		Date date2 = new Date(1, 1, 1);
		
		assert date1.compare(date1, date) == 0;
		assert date2.compare(date2, date2) == 0;
		assert date.compare(date1, date2) != 0;
	}
	
	@Test
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