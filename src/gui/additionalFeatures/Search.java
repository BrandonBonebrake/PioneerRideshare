package gui.additionalFeatures;

import ride.Ride;

public class Search
{
	private static String off = "offer";
	private static String req = "request";
	
	/**
	 *    This method is used to compare a String to different
	 *    Ride information and see if it is contained.
	 *
	 *    @param ride Ride to check is str has something contained
	 *    @param str String to check if contains ride info
	 *    @return true if str contains ride info
	 *            false if str does not contain ride info
	 */
	public static boolean search(Ride ride, String str)
	{
		if (str == null || str.isEmpty())
		{
			return true;
		}
		else if ((off.contains(str.toLowerCase()) && ride.getIsOffer() == true) ||
				req.contains(str.toLowerCase()) && ride.getIsOffer() != true ||
				/* ride1.getStudentEmail().toLowerCase().contains(newValue.toLowerCase()) || */
				ride.getDepartLocation().toLowerCase().contains(str.toLowerCase()) ||
				ride.getReturnLocation().toLowerCase().contains(str.toLowerCase()) ||
				ride.getLeaveDate().toLowerCase().contains(str.toLowerCase()) ||
				ride.getReturnDate().toLowerCase().contains(str.toLowerCase()))
		{
			return true;
		}
		return false;
	}
	
	public static boolean searchFor(Ride ride, String str)
	{
		if(str == null || str.isEmpty())
		{
			return true;
		}
		else if(str.contains(";"))
		{
			return multiSearch(ride, str);
		}
		else if("offer".contains(str) && ride.getIsOffer())
		{
			return true;
		}
		else if("request".contains(str) && !ride.getIsOffer())
		{
			return true;
		}
		else if(ride.getDepartLocation().toLowerCase().contains(str))
		{
			return true;
		}
		else if(ride.getReturnLocation().toLowerCase().contains(str))
		{
			return true;
		}
		else if(ride.getLeaveDate().contains(str))
		{
			return true;
		}
		else if(ride.getReturnDate().contains(str))
		{
			return true;
		}
		else if(str.contains("leaveloc="))
		{
			return leaveLocation(ride, str);
		}
		else if(str.contains("destinationloc="))
		{
			return destinationLocation(ride, str);
		}
		else if(str.contains("leavedate="))
		{
			return leaveDate(ride, str);
		}
		else if(str.contains("returndate="))
		{
			return returnDate(ride, str);
		}
		else if(str.contains("ride="))
		{
			return rideType(ride, str);
		}
		else if(str.contains("id="))
		{
			return rideID(ride, str);
		}
		else
		{
			return false;
		}
	}
	
	private static boolean multiSearch(Ride ride, String str)
	{
		try
		{
			String[] strArr = str.split(";");
			boolean isValid = true;
			
			for (String s : strArr)
			{
				isValid = searchFor(ride, s.trim());
				
				if (!isValid)
				{
					break;
				}
			}
			return isValid;
		} catch (ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
	}
	
	private static boolean rideID(Ride ride, String str)
	{
		try
		{
			return ride.getRideIdentificationNumber().toLowerCase().contains(str.split("id=")[1]);
		}catch (ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
	}
	
	private static boolean rideType(Ride ride, String str)
	{
		try
		{
			String checkStr = str.split("ride=")[1];
			
			if(ride.getIsOffer())
			{
				return "offer".contains(checkStr);
			}
			else
			{
				return "request".contains(checkStr);
			}
		} catch (ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
	}
	
	private static boolean leaveLocation(Ride ride, String str)
	{
		try
		{
			return ride.getDepartLocation().toLowerCase().contains(str.split("leaveloc=")[1]);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
		
	}
	
	private static boolean destinationLocation(Ride ride, String str)
	{
		try
		{
			return ride.getReturnLocation().toLowerCase().contains(str.split("destinationloc=")[1]);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
	}
	
	private static boolean leaveDate(Ride ride, String str)
	{
		try
		{
			return ride.getLeaveDate().contains(str.split("leavedate=")[1]);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
	}
	
	private static boolean returnDate(Ride ride, String str)
	{
		try
		{
			return ride.getReturnDate().contains(str.split("returndate=")[1]);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
	}
}