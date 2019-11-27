package database;

import location.InvalidLocationException;
import location.Location;
import socketCommunication.Server;
import student.InvalidStudentException;
import student.Student;
import ride.Ride;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.PatternSyntaxException;
import java.nio.file.Files;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class PSRDatabase
{

    private ArrayList<Student> studentList;
    private ArrayList<Ride> currentRides;
    private ArrayList<Ride> pastRides;
    private ArrayList<Exception> exceptions;

    private static final String CORRECT_EMAIL_DOMAIN = "uwplatt.edu";
    private Path WRIFilepath; // holds current ride info
    private Path PRIFilepath; // holds past ride info
    private Path SRIFilepath; // holds Student Info
    private Path CRIFilepath; // holds passwords
    private static final String dbms = "mysql";

    public PSRDatabase() throws IOException {
        assert WRIFilepath != null;
        List<String> rawStudentInfo = Files.readAllLines(SRIFilepath);
        for (String studentInfo : rawStudentInfo)
        {
            try {
                String[] studentDetails = studentInfo.split(",");
                Student s = new Student(studentDetails[0], studentDetails[1], studentDetails[2], studentDetails[3]);
                addStudent(s);
            }
            catch (InvalidStudentException e)
            {
                exceptions.add(e);
                e.printStackTrace();
            }
        }
        List<String> rawCurrentRideInfo = Files.readAllLines(CRIFilepath);
        for (String currentRideInfo : rawCurrentRideInfo) {
            String rideDetails[] = currentRideInfo.split(",");
            try {
                Location depLoc = new Location(rideDetails[0], rideDetails[1], rideDetails[2], rideDetails[3]);
            } catch (InvalidLocationException e) {
                e.printStackTrace();
            }
        }
    }

    // Make connection to Server
    public Connection getConnection(String user, String pass) throws SQLException {

        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", pass);

        Connection conn = DriverManager.getConnection(
                "jdbc:" + this.dbms + "://" +
                        "localhost" +
                        ":" + "3306" + "/",
                    connectionProps);

        System.out.println("Connected to database");
        return conn;
    }

    public static void createTable(Connection conn, String dbName, String tableName)
            throws SQLException
    {
        Statement stmt = null;
        String query = "";

    }

    public static void viewTable(Connection con, String dbName)
            throws SQLException {

        Statement stmt = null;
        String query = "select F_NAME, L_NAME, EMAIL, PASSWORD, " +
                "ACCT_CREATION_DATE, ACCT_NUMBER" +
                "from " + dbName + ".STUDENTS";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + "\t" + supplierID +
                        "\t" + price + "\t" + sales +
                        "\t" + total);
            }
        } catch (SQLException e ) {
            //JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    //adds a student to the list
    public void addStudent(Student aStudent)
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

        // call on close

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
}
