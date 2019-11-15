package ride;

import date.PioneerDate;
import location.Location;
import student.Student;
import time.PioneerTime;

/**
 This class is utilized to create RideOffer objects which
 hold all pertinent ride information for the program.
 The data member isOffer is set to true.

 @author Bryce Bierman
 **/

public class RideOffer extends Ride
{
    /**
     Constructor for the RideOffer class. Holds all pertinent ride information.
     The data member isOffer is set to true.

     @param departLoc   Depart Location for Ride
     @param destination Destination for Ride
     @param leaveDate   Leave date for Ride
     @param returnDate  Return date for Ride
     @param leaveTime   Leave time for Ride
     @param returnTime  Return time for Ride
     @param student     Student that created Ride
     **/
    public RideOffer(Location departLoc, Location destination, PioneerDate leaveDate, PioneerDate returnDate,
                     PioneerTime leaveTime, PioneerTime returnTime, Student student)
    {
        super(departLoc, destination, leaveDate, returnDate, leaveTime, returnTime, true, student);
    }
}
