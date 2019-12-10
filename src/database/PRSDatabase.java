package database;

import date.InvalidDateException;
import date.PioneerDate;
import location.InvalidLocationException;
import location.Location;
import ride.Ride;
import ride.RideOffer;
import student.InvalidStudentException;
import student.Student;
import time.InvalidTimeException;
import time.PioneerTime;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.PatternSyntaxException;
import java.lang.Class;
//import oracle.sql.*;

public class PRSDatabase
{
    private ArrayList<Student> studentList;
    private static ArrayList<Ride> currentRides;
    private ArrayList<Ride> pastRides;
    private ArrayList<Exception> exceptions;

    private static final String CORRECT_EMAIL_DOMAIN = "uwplatt.edu";
    private static final String dbms = "mysql";
    private static final String MySQLup = "PRS";
    Connection conn = null;

    public PRSDatabase() throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = getConnection(MySQLup, MySQLup);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     *    Method used to make a connection.
     *
     *    @param user
     *    @param pass
     *    @return
     *    @throws SQLException
     */
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

    /**
     *    Insert an item with the properties of "values" into "tableName"
     *
     *    @param con
     *    @param tableName
     *    @param values
     *    @throws SQLException
     */
    public static void tableInsert(Connection con, String tableName, String values) throws SQLException {
        Statement stmt = null;
        String query = "INSERT INTO " + "prsrideshare." + tableName + "(" + values + ");";
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
            if (stmt != null) {stmt.close();}

        }
    }

    /**
     *    Remove an element from the table. "condition" needs to be a data value
     *    in the specific table, eg. F_NAME = Larry
     *
     *    @param con
     *    @param tableName
     *    @param condition
     *    @throws SQLException
     */
    public static void tableRemove(Connection con, String tableName, String condition) throws SQLException {
        Statement stmt = null;
        String query = "DELETE FROM " + "prsrideshare." + tableName + " WHERE " + condition;
        try
        {
            stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println("Query Ok \n" + rowsAffected + " Rows Affected");
        } catch (SQLException e) {
            System.out.println("Query Unsuccessful");
            e.printStackTrace();
        } finally
        {
            if (stmt != null) {stmt.close();};
        }
    }

    public static void updateCurrentRides(Connection con) throws SQLException {
        currentRides.clear();
        Statement stmt = null;
        try
        {
            stmt = con.createStatement();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet myRS = null;
            myRS = stmt.executeQuery(
                    "SELECT * FROM " + "prsrideshare.current_rides");

            while (myRS.next())
            {
                String s[] = myRS.getString("D_LOC").split(",");
                Location dloc = new Location("empty", s[0], s[1], 11111);
                String u[] = myRS.getString("R_LOC").split(",");
                Location rloc = new Location("empty", u[0], u[1], 11111);
                PioneerDate ddate = new PioneerDate(myRS.getString("D_DATE"));
                PioneerDate rdate = new PioneerDate(myRS.getString("R_DATE"));
                PioneerTime dtime = new PioneerTime(myRS.getString("D_TIME"));
                PioneerTime rtime = new PioneerTime(myRS.getString("R_TIME"));
                boolean isOffer = myRS.getBoolean("IS_OFFER");
                String sEmail = myRS.getString("STUDENT_EMAIL");
                Student st = getStudent(sEmail, con);
                if(isOffer)
                {
                    RideOffer r = new RideOffer(dloc,rloc,ddate,rdate,dtime,rtime,st);
                    currentRides.add(r);
                }
            }

        } catch (SQLException | InvalidLocationException | InvalidDateException | InvalidTimeException e)
        {
            System.out.println("Query Unsuccessful");
            e.printStackTrace();

        } finally {
            if (stmt != null) {stmt.close();}
        }

    }

    public static void addPastRide(Ride r, Connection con) throws SQLException {
        Statement stmt = null;
        try
        {
            stmt = con.createStatement();
            int rowsaff = stmt.executeUpdate("INSERT INTO prsrideshare.past_rides " + "VALUES (" +
                    r.getDepartLocation() + ", " + r.getReturnLocation() + ", " +
                    r.getLeaveDate() + ", " + r.getReturnDate() + ", " +
                    r.getLeaveTime() + ", " + r.getReturnTime() + ", " +
                    r.getIsOffer() + ", " + r.getStudentEmail() +
                    ")");

            System.out.println("Query OK \n" + rowsaff + " Rows Affected");
        } catch (SQLException e) {
            System.out.println("Query Unsuccessful");
        } finally {
            if (stmt != null) {stmt.close();}
        }

    }

    public static void addCurrentRide(Ride r, Connection con) throws SQLException {
        Statement stmt = null;
        try
        {
            stmt = con.createStatement();
            int rowsaff = stmt.executeUpdate("INSERT INTO prsrideshare.current_rides VALUES (" +
                            r.getDepartLocation() + ", " + r.getReturnLocation() + ", " +
                            r.getLeaveDate() + ", " + r.getReturnDate() + ", " +
                            r.getLeaveTime() + ", " + r.getReturnTime() + ", " +
                            r.getIsOffer() + ", " + r.getStudentEmail()+ ")");

            System.out.println("Query OK \n" + rowsaff + " Rows Affected");
        } catch (SQLException e) {
            System.out.println("Query Unsuccessful");
        } finally {
            if (stmt != null) {stmt.close();};
        }

    }

    public static void addStudent(Student s, Connection con) throws SQLException {
        Statement stmt = null;
        try
        {
            stmt = con.createStatement();
            int rowsaff = stmt.executeUpdate("INSERT INTO prsrideshare.students VALUES (" +
                    s.getFirstName() + ", " + s.getLastName() + ", " + s.getEmail() + ", " +
                    s.getPassword() + ", " + s.getAccountCreationDate() + ")");

            System.out.println("Query OK \n" + rowsaff + " Rows Affected");
        } catch (SQLException e) {
            System.out.println("Query Unsuccessful");
        } finally {
            if (stmt != null) {stmt.close();};
        }

    }

    private static Student getStudent(String sEmail, Connection con) throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet myRS = null;
            myRS = stmt.executeQuery(
                    "SELECT * FROM " + "prsrideshare.students" +
                            " WHERE EMAIL IS " + sEmail );
            Student st = new Student(myRS.getString("F_NAME"),
                                     myRS.getString("L_NAME"),
                                     myRS.getString("EMAIL"),
                                     myRS.getString("PASSWORD"));
            return st;
        } catch (SQLException | InvalidStudentException e)
        {
            System.out.println("Query Unsuccessful");
            return null;
        } finally
        {
            if (stmt != null) {stmt.close();};
        }
    }

    /**
     *    Print out the tables in a specific database
     *
     *    @param con
     *    @param dbName
     *    @throws SQLException
     */
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

    public void updateStudentList(Connection con) throws SQLException {
        Statement stmt = null;
        studentList.clear();
        try
        {
            stmt = con.createStatement();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet myRS = null;
            myRS = stmt.executeQuery(
                    "SELECT * FROM " + "prsrideshare.students");

            while (myRS.next())
            {
                Student st = new Student(myRS.getString("F_NAME"),
                        myRS.getString("L_NAME"),
                        myRS.getString("EMAIL"),
                        myRS.getString("PASSWORD"));
                studentList.add(st);
            }

        } catch (SQLException | InvalidStudentException e)
        {
            System.out.println("Query Unsuccessful");
            e.printStackTrace();

        } finally {
            if (stmt != null) {stmt.close();};
        }

    }

    public static ArrayList<Ride> getCurrentRides()
    {
        return currentRides;
    }

    //verifies if a student's email is a uwplatt email,
    // or if the email has been taken already.
    public boolean isValidStudentInfo(String email)
    {
        boolean isValid = true;
        try
        {
            if(!(email.contains("@uwplatt.edu")))
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