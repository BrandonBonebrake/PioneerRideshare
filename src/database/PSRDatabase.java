package database;

import ride.Ride;
import student.InvalidStudentException;
import student.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.PatternSyntaxException;
import java.lang.Class;
//import oracle.sql.*;


public class PSRDatabase
{

    private ArrayList<Student> studentList;
    private ArrayList<Ride> currentRides;
    private ArrayList<Ride> pastRides;
    private ArrayList<Exception> exceptions;

    private static final String CORRECT_EMAIL_DOMAIN = "uwplatt.edu";
    private static final String dbms = "mysql";
    private static final String MySQLup = "PRS";
    Connection conn = null;

    public PSRDatabase() throws IOException {
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

    /**
     * Insert an item with the properties of "values" into "tableName"
     * @param con
     * @param tableName
     * @param values
     * @throws SQLException
     */
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

    /**
     * Remove an element from the table. "condition" needs to be a data value
     * in the specific table, eg. F_NAME = Larry
     * @param con
     * @param tableName
     * @param condition
     * @throws SQLException
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

    public static List<String> getCurrentRides(Connection con)
    {
        List<String> list = null;
        Statement stmt = null;
        try
        {
            stmt = con.createStatement();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet myRS = null;
            //myRS = stmt.executeQuery(
                    //"SELECT * FROM " + "prsrideshare.current_rides");

            while (myRS.next())
            {
                   list.add(myRS.getString("D_LOC") + ", " + myRS.getString("R_LOC") + ", " +
                           myRS.getString("D_DATE") + ", " + myRS.getString("R_DATE") + ", " +
                           myRS.getString("D_TIME") + ", " + myRS.getString("R_TIME") + ", " +
                           myRS.getBoolean("IS_OFFER") + ", " + myRS.getString("STUDENT_EMAIL"));
            }
        } catch (SQLException e)
        {
            System.out.println("Query Unsuccessful");
            e.printStackTrace();
        }
        return list;
    }
    /*
    D_LOC	      VARCHAR(30),
	R_LOC	      VARCHAR(30),
	D_DATE        CHAR(10),
	R_DATE        CHAR(10),
	D_TIME        CHAR(5),
	R_TIME        CHAR(5),
	IS_OFFER      BOOLEAN,
	STUDENT_EMAIL VARCHAR(30) PRIMARY KEY

     */


    /**
     * Print out the tables in a specific database
     * @param con
     * @param dbName
     * @throws SQLException
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
