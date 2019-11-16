package ride;

import date.Date;
import date.PioneerDate;
import location.Location;
import location.Map;
import student.Student;
import time.PioneerTime;
import time.Time;

import java.io.Serializable;
import java.util.Comparator;

/**
 This class is utilized to create Ride objects which
 hold all pertinent ride information for the program.

 @author Bryce Bierman
 **/

public abstract class Ride implements Comparable<Ride>, Comparator<Ride>, Serializable
{
    // Global Variables
    private Location departLocation;            // Departure Location for Ride
    private Location returnLocation;            // Destination for Ride
    private Date leaveDate;                     // Leave date for Ride
    private Date returnDate;                    // Return date for Ride
    private Time leaveTime;                     // Leave time for Ride
    private Time returnTime;                    // Return time for Ride
    private boolean isOffer;                    // Tells if Offer/Request
    private Map coordinates;                    // Coordinates for Ride
    private String rideIdentificationNumber;    // Identification Number for Ride
    private Student student;                    // Student that submitted Ride

    /**
     Constructor for the Ride class. Holds all pertinent ride information.

     @param departLoc   Depart Location for Ride
     @param destination Destination for Ride
     @param leaveDate   Leave date for Ride
     @param returnDate  Return date for Ride
     @param leaveTime   Leave time for Ride
     @param returnTime  Return time for Ride
     @param isOffer     Whether the Ride is an Offer/Request
     @param student     Student that created Ride
     **/
    public Ride(Location departLoc, Location destination, PioneerDate leaveDate, PioneerDate returnDate,
                PioneerTime leaveTime, PioneerTime returnTime, boolean isOffer, Student student)
    {
        this.departLocation = departLoc;
        this.returnLocation = destination;
        this.leaveDate = leaveDate;
        this.returnDate = returnDate;
        this.leaveTime = leaveTime;
        this.returnTime = returnTime;
        this.isOffer = isOffer;
        this.student = student;
    }

    /**
     Return the departure location of the Ride.

     @return departLocation
     **/
    public Location getDepartLocation()
    {
        return departLocation;
    }

    /**
     Set the departure location of the Ride.

     @param departLocation What departLocation will be set to
     **/
    public void setDepartLocation(Location departLocation)
    {
        this.departLocation = departLocation;
    }

    /**
     Return the returning location of the Ride.

     @return returnLocation
     **/
    public Location getReturnLocation()
    {
        return returnLocation;
    }

    /**
     Set the return location of the Ride.

     @param returnLocation  What returnLocation will be set to
     **/
    public void setReturnLocation(Location returnLocation)
    {
        this.returnLocation = returnLocation;
    }

    /**
     Return the leaving date of the Ride.

     @return leaveDate
     **/
    public Date getLeaveDate()
    {
        return leaveDate;
    }

    /**
     Set the leaving date of the Ride.

     @param leaveDate What leaveDate will be set to
     **/
    public void setLeaveDate(Date leaveDate)
    {
        this.leaveDate = leaveDate;
    }

    /**
     Return the returning date of the Ride.

     @return returnDate
     **/
    public Date getReturnDate()
    {
        return returnDate;
    }

    /**
     Set the returning date of the Ride.

     @param returnDate What returnDate will be set to
     **/
    public void setReturnDate(Date returnDate)
    {
        this.returnDate = returnDate;
    }

    /**
     Return the leaving time of the Ride.

     @return leaveTime
     **/
    public Time getLeaveTime()
    {
        return leaveTime;
    }

    /**
     Set the leaving time of the Ride.

     @param leaveTime What leaveTime will be set to
     **/
    public void setLeaveTime(Time leaveTime)
    {
        this.leaveTime = leaveTime;
    }

    /**
     Return the returning time of the Ride.

     @return returnTime
     **/
    public Time getReturnTime()
    {
        return returnTime;
    }

    /**
     Set the returning time of the Ride.

     @param returnTime What returnTime will be set to
     **/
    public void setReturnTime(Time returnTime)
    {
        this.returnTime = returnTime;
    }

    /**
     Returns true if Ride is an offer and false is not an offer.

     @return isOffer
     **/
    public boolean getIsOffer()
    {
        return isOffer;
    }

    /**
     Set whether the Ride is an offer or not an offer.

     @param isOffer What isOffer will be set to
     **/
    private void setIsOffer(boolean isOffer)
    {
        this.isOffer = isOffer;
    }

    /**
     Returns the coordinates of the Ride.

     @return coordinates
     **/
    public Map getCoordinates()
    {
        return coordinates;
    }

    /**
     Set the coordinates of the Ride.

     @param coordinates
     **/
    public void setCoordinates(Map coordinates)
    {
        this.coordinates = coordinates;
    }

    /**
     Returns the ride identification number of the Ride.

     @return rideIdentificationNumber
     **/
    public String getRideIdentificationNumber()
    {
        return rideIdentificationNumber;
    }

    /**
     Set the ride identification number of the Ride.

     @param rideIdentificationNumber What the rideIdentificationNumber will be set to
     **/
    private void setRideIdentificationNumber(String rideIdentificationNumber)
    {
        this.rideIdentificationNumber = rideIdentificationNumber;
    }

    /**
     Returns the student that created the Ride.

     @return student
     **/
    public Student getStudent()
    {
        return student;
    }

    /**
     Set the student that created the Ride.

     @param student What student will be set to
     **/
    public void setStudent(Student student)
    {
        this.student = student;
    }

    /**
     Overridden compare method that compares the ride
     identification numbers of one ride to another.

     @param ride1       Ride1 to be compared
     @param ride2       Ride2 to be compared
     @return int        negative if ride1 < ride2
                        zero if ride1 == ride2
                        positive if ride1 > ride2
     **/
    @Override
    public int compare(Ride ride1, Ride ride2)
    {
        return (ride1.rideIdentificationNumber.compareTo(ride2.rideIdentificationNumber));
    }

    /**
     Overridden compareTo method that compares this
     identification number to another ride's
     identification number.

     @param ride        Ride to be compared
     @return int        negative if this < ride2
                        zero if this == ride2
                        positive if this > ride2
     **/
    @Override
    public int compareTo(Ride ride)
    {
        return compare(this, ride);
    }

    /**
     Overridden equals method that returns
     whether this and another ride are equal.

     @param obj         Object to check is equal
     @return boolean    false if they are not equal
                        true if they are equal
     **/
    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof Ride && this.compareTo((Ride)obj) == 0);
    }

    /**
     Overridden toString method that returns a String
     will all pertinent information on the Ride.

     @return String     All information on Ride
     **/
    @Override
    public String toString()
    {
        return (departLocation.toString() + " " + returnLocation.toString() + " " + leaveDate.toString()
                + " " + returnDate.toString() + " " + leaveTime.toString() + " " + returnTime.toString()
                + " " + isOffer + " " + coordinates.toString() + " " + rideIdentificationNumber
                + " " + student.toString());
    }
}
