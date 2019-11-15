package ride;

import date.Date;
import date.PioneerDate;
import location.Location;
import location.Map;
import student.Student;
import time.PioneerTime;
import time.Time;
import date.PioneerDate;

import java.io.Serializable;
import java.util.Comparator;

public abstract class Ride implements Comparable<Ride>, Comparator<Ride>, Serializable
{
    private Location departLocation;
    private Location returnLocation;
    private Date leaveDate;
    private Date returnDate;
    private Time leaveTime;
    private Time returnTime;
    private boolean isOffer;
    private Map coordinates;
    private String rideIdentificationNumber;
    private Student student;

    public Ride(Location departLoc, Location destination, PioneerDate leaveDate, PioneerDate returnDate, PioneerTime leaveTime, PioneerTime returnTime, boolean isOffer, Student student)
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

    public Location getDepartLocation()
    {
        return departLocation;
    }

    public void setDepartLocation(Location departLocation)
    {
        this.departLocation = departLocation;
    }

    public Location getReturnLocation()
    {
        return returnLocation;
    }

    public void setReturnLocation(Location returnLocation)
    {
        this.returnLocation = returnLocation;
    }

    public Date getLeaveDate()
    {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate)
    {
        this.leaveDate = leaveDate;
    }

    public Date getReturnDate()
    {
        return returnDate;
    }

    public void setReturnDate(Date returnDate)
    {
        this.returnDate = returnDate;
    }

    public Time getLeaveTime()
    {
        return leaveTime;
    }

    public void setLeaveTime(Time leaveTime)
    {
        this.leaveTime = leaveTime;
    }

    public Time getReturnTime()
    {
        return returnTime;
    }

    public void setReturnTime(Time returnTime)
    {
        this.returnTime = returnTime;
    }

    public boolean getIsOffer()
    {
        return isOffer;
    }

    public void setIsOffer(boolean isOffer)
    {
        this.isOffer = isOffer;
    }

    public Map getCoordinates()
    {
        return coordinates;
    }

    public void setCoordinates(Map coordinates)
    {
        this.coordinates = coordinates;
    }

    public String getRideIdentificationNumber()
    {
        return rideIdentificationNumber;
    }

    public void setRideIdentificationNumber(String rideIdentificationNumber)
    {
        this.rideIdentificationNumber = rideIdentificationNumber;
    }

    public int compare(Ride ride1, Ride ride2)
    {
        if(ride1.rideIdentificationNumber.compareTo(ride2.rideIdentificationNumber) > 0 )
            return 1;
        else if(ride1.rideIdentificationNumber.compareTo(ride1.rideIdentificationNumber) < 0)
            return -1;
        else
            return 0;
    }

    @Override
    public int compareTo(Ride ride)
    {
        return 0;
    }

    @Override
    public boolean equals(Object obj)
    {
        return false;
    }

    @Override
    public String toString()
    {
        return "Hi";
    }
}
