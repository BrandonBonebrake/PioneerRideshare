package comparator;

import ride.Ride;

import java.util.Comparator;

@Deprecated
public class RideComparator<T> implements Comparator<T>
{
    /**
     *     Constructor for the RideComparator class.
     */
    public RideComparator()
    {
        super();
    }

    /**
     *     Overridden compare method that compares two rides to one another.
     *
     *     @param o1 Ride to be compared
     *     @param o2 Ride to compare to
     *     @return negative integer if o1 is less than o2
     *             0 if o1 is equal to o2
     *             positive integer if o1 is greater than o2
     */
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
