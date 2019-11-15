package database;

import student.InvalidStudentException;
import student.Student;
import ride.Ride;


import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class PSRDatabase
{

    private ArrayList<Student> studentList;
    private ArrayList<Ride> currentRides;
    private ArrayList<Ride> pastRides;

    private static final String CORRECT_EMAIL_DOMAIN = "uwplatt.edu";


    public PSRDatabase()
    {
        //file read
        //{
            //get studentList, currentRides, pastRides.
            //set them all
        //}
        //copyMemoryToFile(filepath); CALL IF CLOSE OR GOING TO CRAS    H
        //pull arraylists from database, populate
    }

    //adds a student to the list
    public void addStudent(student.Student aStudent)
    {
        studentList.add(aStudent);

    }

    //verifies if a student's email is a uwplatt email,
    // or if the email has been taken already.
    public boolean isValidStudentInfo(String email)
    {
        boolean isValid = true;
        try
        {
            String[] emailDomain = email.split("@");

            if(emailDomain[1] != CORRECT_EMAIL_DOMAIN)
            {
                isValid = false;
                throw new InvalidStudentException("Email does not have correct domain");
            }
            //pull email
            isValid = isEmailTaken(email);

            if (!isValid) {
                throw new InvalidStudentException("Email already exists in database");
            }
        }
        catch (InvalidStudentException e) {
            System.out.println();
            e.printStackTrace();
            return isValid;
        }
        catch (PatternSyntaxException e ) {
            System.out.println("Invalid email");
            e.printStackTrace();
            return isValid;
        }
        return true;

    }

    public void copyMemoryToFile(String filepath)
    {
        //this one is wild

    }

    public void createAccount(String email, String password)
    {


    }

    public boolean isEmailTaken(String email)
    {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getEmail() == email) {
                return true;
            }
        }

        return false;
    }

    public int createAccountActivationCode()
    {

        return 0;
    }
}
