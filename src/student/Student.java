package student;

import date.Date;

public final class Student
{
    private String email;
    private String password;
    private Date accountCreationDate;
    private int numCurrentRideRequests;
    private int numCurrentRideOffers;
    private String accountNumber;

    public Student(String email, String password)
    {

    }

    public String getEmail()
    {
        return email;
    }
}
