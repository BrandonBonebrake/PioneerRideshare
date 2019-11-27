package comparator;

import ride.Ride;

import java.util.Comparator;

public class RideComparator<T> implements Comparator<T>
{
    public RideComparator()
    {
        super();
    }

    @Override
    public int compare(T o1, T o2)
    {
        int compareResults = 0;

        // Verify objects are not null
        if(o1 != null && o2 != null)
        {
            // Verify both objects are the same
            if(o1.getClass() == ride.Ride.class &&
                o2.getClass() == ride.Ride.class)
            {
                compareResults = ((Ride)o1).compareTo((Ride)o2);
            }
        }
        return compareResults;
    }
}
