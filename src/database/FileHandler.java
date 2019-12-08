package database;

import date.InvalidDateException;
import date.PioneerDate;
import location.InvalidLocationException;
import location.Location;
import ride.RideOffer;
import student.InvalidStudentException;
import student.Student;
import time.InvalidTimeException;
import time.PioneerTime;

import java.io.*;
import java.util.ArrayList;

public class FileHandler
{
    /**
     * Creates a new file. The name of the file is the largest number found
     * plus one.
     *
     * @param pathName The name of the folder where the files will be created
     * @return Name of the file that was generated
     */
    private static String generateFile(String pathName)
    {
        String result = null;
        File folder = new File("src/database/files/" + pathName);
        File file;
        File[] fileArr;
        int maxFileNumber = 0;
        boolean fileCreated;

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
        file = new File(folder.getAbsolutePath() + "/" + maxFileNumber);

        // Create the new file
        try
        {
            fileCreated = file.createNewFile();
            if(fileCreated)
            {
                result = file.getAbsolutePath();
            }
        } catch (IOException e)
        {
            System.err.println("Couldn't Create File");
        }
        return result;
    }

    public static void writeObject(String fileName, Object obj)
    {
        File file;
        FileOutputStream fOut;
        ObjectOutputStream oos;
        String path = generateFile(fileName);

        if(path != null)
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

    public static ArrayList<Object> readObject(String pathName)
    {
        ArrayList<Object> list = new ArrayList<>();
        File folder = new File("src/database/files/" + pathName);
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

    public static void main(String[] args) throws InvalidLocationException, InvalidDateException, InvalidTimeException, InvalidStudentException
    {
        for (int i = 0; i < 1000000; i++)
        {
            writeObject("rides", new RideOffer(new Location("","Platteville","WI","11111"),
                    new Location("","Milwaukee","WI","11111"), new PioneerDate("12/31/2019"),
                    new PioneerDate("2/1/2020"), new PioneerTime("10:00"), new PioneerTime("10:01"),
                    new Student("John","Smith","jSmith@uwplatt.edu","12345678&")));
        }

        ArrayList<Object> currentRides = readObject("rides");

        for (int i = 0; i <currentRides.size(); i++)
        {
            System.out.println(i + 1 + ") " + currentRides.get(i));
        }
    }
}