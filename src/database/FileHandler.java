package database;

import date.Date;
import date.InvalidDateException;
import ride.Ride;
import student.Student;
import time.InvalidTimeException;
import time.Time;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler
{
    private static String basePath = "files/";

    /**
     * Creates a new file. The name of the file is the largest number found
     * plus one.
     *
     * @param pathName The name of the folder where the files will be created
     * @return Name of the file that was generated
     */
    private static String generateFile(String pathName)
    {
        File[] fileArr;
        int maxFileNumber = 0;
        boolean fileCreated;

        File folder = new File(basePath + pathName);
        fileArr = folder.listFiles();

        if (fileArr != null && fileArr.length > 0)
        {
            // Search for the largest number of file names
            for (File value : fileArr)
            {
                if (value.isFile() && Integer.parseInt(value.getName()) > maxFileNumber)
                {
                    maxFileNumber = Integer.parseInt(value.getName());
                }
            }
            maxFileNumber++;
        }
        // Create the file name
        File file = new File(folder.getAbsolutePath() + "/" + maxFileNumber);

        // Create the new file
        String result = null;
        try
        {
            fileCreated = file.createNewFile();
            if (fileCreated)
            {
                result = file.getAbsolutePath();
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Writes an Object to the directory that stores the files
     *
     * @param pathName Name of the folder that the file will be stored in
     * @param obj Object that will be serialized and written to a file
     */
    public static void writeObject(String pathName, Object obj)
    {
        File file;
        FileOutputStream fOut;
        ObjectOutputStream oos;
        String path = generateFile(pathName);

        if (path != null)
        {
            file = new File(path);
            try
            {
                fOut = new FileOutputStream(file.getAbsolutePath());
                oos = new ObjectOutputStream(fOut);

                oos.writeObject(obj);
                oos.close();
                fOut.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns array list of Objects that were written to a series of files
     *
     * @param pathName Name of the folder where files were stored
     * @return ArrayList of Objects that were serialized to a file
     */
    public static ArrayList<Object> readObject(String pathName)
    {
        ArrayList<Object> list = new ArrayList<>();
        File folder = new File(basePath + pathName);
        File[] fileArr = folder.listFiles();
        FileInputStream fIn;
        ObjectInputStream ois;

        if(fileArr != null && fileArr.length > 0)
        {
            for (File value : fileArr)
            {
                try
                {
                    fIn = new FileInputStream(value.getAbsolutePath());
                    ois = new ObjectInputStream(fIn);

                    list.add(ois.readObject());

                    ois.close();
                    fIn.close();
                } catch (IOException | ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ArrayList<Ride> getCurrentRides()
    {
        ArrayList<Object> rides = readObject("rides");
        ArrayList<Ride> currentRides = new ArrayList<>();
        Date today = new Date();
        Date rideDate = new Date();
        Time now = new Time();
        Time rideTime = new Time();

        for (Object ride : rides)
        {
            try
            {
                rideDate.setDate(((Ride) ride).getLeaveDate());
                rideTime.setTime(((Ride) ride).getLeaveTime());

                // Future Ride
                if (rideDate.compareTo(today) > 0 ||
                        rideDate.compareTo(today) == 0 && rideTime.compareTo(now) > 0)
                {
                    currentRides.add((Ride) ride);
                }
            } catch (InvalidDateException | InvalidTimeException ignored) { }
        }
        return currentRides;
    }

    public static ArrayList<Student> getStudents()
    {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Object>  objects  = readObject("students");

        for (Object object : objects)
        {
            students.add((Student) object);
        }
        return students;
    }
}
