package ride;

public class MatchRide
{
	private MatchRide()
	{
		super();
	}
	
	public static double rideSimilarity(Ride ride1, Ride ride2)
	{
		double rideSimilarity = 0.0f;
		
		// Check depart location similarity
		if (ride1.getDepartLocation().equals(ride2.getDepartLocation()))
		{
			rideSimilarity += 0.75f;
		}
		
		// Check ride leave date similarity
		int difference = Math.abs(ride1.getLeaveDate().compareTo(ride2.getLeaveDate()));
		if (difference == 0)
		{
			rideSimilarity += 0.1f;
		}
		
		// Check return date similarity
		difference = Math.abs(ride1.getReturnDate().compareTo(ride2.getReturnDate()));
		if (difference <= 2)
		{
			rideSimilarity += 0.05f;
		}
		
		// Check leave time similarity
		difference = Math.abs(ride1.getLeaveTime().compareTo(ride2.getLeaveTime()));
		if (difference <= 2)
		{
			rideSimilarity += 0.05f;
		}
		
		difference = Math.abs(ride1.getReturnTime().compareTo(ride2.getReturnTime()));
		if (difference <= 10)
		{
			rideSimilarity += 0.05f;
		}
		return rideSimilarity;
	}
}