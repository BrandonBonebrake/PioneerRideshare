package ride;

import com.sun.org.apache.xpath.internal.operations.Bool;
import date.Date;
import date.PioneerDate;
import gui.PioneerApplication;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import location.Location;
import location.Map;
import socketCommunication.Client;
import student.Student;
import time.PioneerTime;
import time.Time;

import java.io.Serializable;
import java.util.Comparator;

/**
 *    This class is utilized to create Ride objects which
 *    hold all pertinent ride information for the program.
 *
 *    @author Bryce Bierman
 */

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
     *    Constructor for the Ride class. Holds all pertinent ride information.
     *
     *    @param departLoc   Depart Location for Ride
     *    @param destination Destination for Ride
     *    @param leaveDate   Leave date for Ride
     *    @param returnDate  Return date for Ride
     *    @param leaveTime   Leave time for Ride
     *    @param returnTime  Return time for Ride
     *    @param isOffer     Whether the Ride is an Offer/Request
     *    @param student     Student that created Ride
     */
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

        this.setRideIdentificationNumber();
    }

    /**
     *    Method used for RideListingPanel to create button for table
     *    based of if it isOffer or not.
     *
     *    @return button Button added to the table
     */
    public Button getButton()
    {
        Button button = new Button();

        button.setStyle(PioneerApplication.RIDE_STYLE);
        button.setOnAction(e -> new Client("Ride: " + this.getRideIdentificationNumber()));

        if(isOffer)
            button.setText("Request To Join");
        else
            button.setText("Offer To Drive");

        return button;
    }

    /**
     *    Method used to return String that says "Offer" or "Request".
     *
     *    @return String with "Offer" or "Request"
     */
    public String getOfferRequest()
    {
        String offerRequest;

        if(isOffer)
            offerRequest = "Offer";
        else
            offerRequest = "Request";
        return offerRequest;
    }

    /**
     *    Return the departure location of the Ride.
     *
     *    @return departLocation
     */
    public String getDepartLocation()
    {
        return departLocation.getCity() + ", " + departLocation.getState();
    }

    /**
     *    Set the departure location of the Ride.
     *
     *    @param departLocation What departLocation will be set to
     */
    public void setDepartLocation(Location departLocation)
    {
        this.departLocation = departLocation;
    }

    /**
     *    Return the returning location of the Ride.
     *
     *    @return returnLocation
     */
    public String getReturnLocation()
    {
        return returnLocation.getCity() + ", " + returnLocation.getState();
    }

    /**
     *    Set the return location of the Ride.
     *
     *    @param returnLocation  What returnLocation will be set to
     */
    public void setReturnLocation(Location returnLocation)
    {
        this.returnLocation = returnLocation;
    }

    /**
     *    Return the leaving date of the Ride.
     *
     *    @return leaveDate
     */
    public String getLeaveDate()
    {
        return leaveDate.getDate();
    }

    /**
     *    Set the leaving date of the Ride.
     *
     *    @param leaveDate What leaveDate will be set to
     *
     */
    public void setLeaveDate(Date leaveDate)
    {
        this.leaveDate = leaveDate;
    }

    /**
     *    Return the returning date of the Ride.
     *
     *    @return returnDate
     */
    public String getReturnDate()
    {
        return returnDate.getDate();
    }

    /**
     *    Set the returning date of the Ride.
     *
     *    @param returnDate What returnDate will be set to
     */
    public void setReturnDate(Date returnDate)
    {
        this.returnDate = returnDate;
    }

    /**
     *    Return the leaving time of the Ride.
     *
     *    @return leaveTime
     */
    public String getLeaveTime()
    {
        return leaveTime.getTime();
    }

    /**
     *    Set the leaving time of the Ride.
     *
     *    @param leaveTime What leaveTime will be set to
     */
    public void setLeaveTime(Time leaveTime)
    {
        this.leaveTime = leaveTime;
    }

    /**
     *    Return the returning time of the Ride.
     *
     *    @return returnTime
     */
    public String getReturnTime()
    {
        return returnTime.getTime();
    }

    /**
     *    Set the returning time of the Ride.
     *
     *    @param returnTime What returnTime will be set to
     */
    public void setReturnTime(Time returnTime)
    {
        this.returnTime = returnTime;
    }

    /**
     *    Return the return date and return time for the ride.
     *
     *    @return String     Contains return date and return time
     */
    public String getReturnDateTime()
    {
        return this.getReturnDate() + "\n" + this.getReturnTime();
    }

    /**
     *    Return the leave date and leave time for the ride.
     *
     *    @return String     Contains Leave date and Leave time
     */
    public String getLeaveDateTime()
    {
        return this.getLeaveDate() + "\n" + this.getLeaveTime();
    }

    /**
     *    Returns true if Ride is an offer and false is not an offer.
     *
     *    @return isOffer
     */
    public boolean getIsOffer()
    {
        return isOffer;
    }

    /**
     *    Set whether the Ride is an offer or not an offer.
     *
     *    @param isOffer What isOffer will be set to
     */
    private void setIsOffer(boolean isOffer)
    {
        this.isOffer = isOffer;
    }

    /**
     *    Returns the coordinates of the Ride.
     *
     *    @return coordinates
     */
    public Map getCoordinates()
    {
        return coordinates;
    }

    /**
     *    Set the coordinates of the Ride.
     *
     *    @param coordinates Map object that holds the coordinates of the location
     */
    public void setCoordinates(Map coordinates)
    {
        this.coordinates = coordinates;
    }

    /**
     *    Returns the ride identification number of the Ride.
     *
     *    @return rideIdentificationNumber
     */
    public String getRideIdentificationNumber()
    {
        return rideIdentificationNumber;
    }

    /**
     *    Set the ride identification number of the Ride.
     *
     */
    private void setRideIdentificationNumber()
    {
        this.rideIdentificationNumber = this.getLeaveDate();
        this.rideIdentificationNumber += this.getLeaveTime();
        this.rideIdentificationNumber += this.getReturnTime();
    }

    /**
     *    Set the ride identification number of the Ride.
     *
     *    @param rideIdentificationNumber What the rideIdentificationNumber will be set to
     */
    private void setRideIdentificationNumber(String rideIdentificationNumber)
    {
        this.rideIdentificationNumber = rideIdentificationNumber;
    }

    /**
     *    Returns the student that created the Ride.
     *
     *    @return student
     */
    public Student getStudent()
    {
        return student;
    }

    /**
     *    Set the student that created the Ride.
     *
     *    @param student What student will be set to
     */
    public void setStudent(Student student)
    {
        this.student = student;
    }

    /**
     *    Return the email of the student that created the ride.
     *
     *    @return String     Email of Student
     */
    public String getStudentEmail()
    {
        return student.getEmail();
    }

    /**
     *    Overridden compare method that compares the ride
     *    identification numbers of one ride to another.
     *
     *    @param ride1 Ride1 to be compared
     *    @param ride2 Ride2 to be compared
     *    @return int  negative if ride1 less than ride2
     *            zero if ride1 equal to ride2
     *            positive if ride1 greater than ride2
     */
    @Override
    public int compare(Ride ride1, Ride ride2)
    {
        //return (ride1.rideIdentificationNumber.compareTo(ride2.rideIdentificationNumber));
        return Boolean.toString(ride1.isOffer).compareTo(Boolean.toString(ride2.isOffer));
    }

    /**
     *    Overridden compareTo method that compares this
     *    identification number to another ride's
     *    identification number.
     *
     *    @param ride Ride to be compared
     *    @return int negative if this less than ride2
     *                zero if this equal to ride2
     *                positive if this larger than ride2
     */
    @Override
    public int compareTo(Ride ride)
    {
        return compare(this, ride);
    }

    /**
     *    Overridden equals method that returns
     *    whether this and another ride are equal.
     *
     *    @param obj         Object to check is equal
     *    @return boolean    false if they are not equal
     *                       true if they are equal
     */
    @Override
    public boolean equals(Object obj)
    {
        return (obj != null && obj instanceof Ride && this.compareTo((Ride)obj) == 0);
    }

    /**
     *    Overridden toString method that returns a String
     *    will all pertinent information on the Ride.
     *
     *    @return String     All information on Ride
     */
    @Override
    public String toString()
    {
        return departLocation.toString() + " " + returnLocation.toString() + " " + leaveDate.toString() + " " +
                returnDate.toString() + " " + leaveTime.toString() + " " + returnTime.toString() + " " + isOffer +
                " " + rideIdentificationNumber + " " + student.toString();
    }
}