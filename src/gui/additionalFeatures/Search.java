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
            ride.getDepartLocation().toLowerCase().contains(str.toLowerCase()) ||
            ride.getReturnLocation().toLowerCase().contains(str.toLowerCase()) ||
            ride.getLeaveDate().toLowerCase().contains(str.toLowerCase()) ||
            ride.getReturnDate().toLowerCase().contains(str.toLowerCase()))
        {
            return true;
        }
        return false;
    }
}
