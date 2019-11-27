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
    private static final String dbms = "mysql";
    private static final String MySQLup = "PRS";
    private
    Connection conn = null;

    public PSRDatabase() throws IOException {
        try {
            conn = getConnection(MySQLup, MySQLup);

        } catch (SQLException e) {
            e.printStackTrace();
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

    public static void tableInsert(Connection con, String tableName, String values) throws SQLException {
        Statement stmt = null;
        String query = "INSERT INTO " + "prsrideshare." + tableName + "(" + values + ")";
        try
        {
            stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println("Query Ok \n" + rowsAffected + " Rows Affected.");

        } catch (SQLException e)
        {
            System.out.println("Query Unsuccessful");
            e.printStackTrace();
        } finally
        {
            if (stmt != null) {stmt.close();};

        }
    }

    public static void viewTable(Connection con, String dbName)
            throws SQLException {

        Statement stmt = null;
        String query = "select F_NAME, L_NAME, EMAIL, PASSWORD, " +
                "ACCT_CREATION_DATE" +
                "from " + dbName + ".STUDENTS";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String firstName = rs.getString("F_NAME");
                String lastName = rs.getString("L_NAME");
                String email = rs.getString("EMAIL");
                String ENCpassword = rs.getString("PASSWORD");
                String acctCreateDate = rs.getString("ACCT_CREATION_DATE");


                System.out.println(firstName + "\t" + lastName +
                        "\t" + email + "\t" + ENCpassword +
                        "\t" + acctCreateDate);
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
