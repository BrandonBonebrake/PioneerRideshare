package location;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

/***
 *    Object used to hold an address to a location.
 *
 *    @author  Brandon Bonebrake
 *
 ***/

public final class Location implements Serializable
{
    // Global Constants
    private final int MIN_CITY_NAME_LENGTH = 2;

    // Global Variables
    private String street = null;
    private String city   = null;
    private String state  = null;
    private String zip    = null;

    private static ObservableList<String> stateList = FXCollections.observableArrayList(
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
            "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
            "NY", "NC" ,"ND", "OH", "OK", "OR", "PA", "RI" ,"SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    );

    private static final long serialVersionUID = 2765881055827127532L;

    /**
     *   Creates an Address object to hold
     *   an object
     *
     *   @param street Street Address
     *   @param city   City
     *   @param state  State
     *   @param zip    Zip Code
     *
     *   @throws InvalidLocationException Must have access to the system client.time
     *
     **/

    public Location(String street, String city, String state, int zip) throws InvalidLocationException
    {
        super();

        this.setState(state);
        this.setCity(city);
        this.setStreet(street);
        this.setZip(Integer.toString(zip));
    }

    /**
     *   Creates an Address object to hold
     *   an object
     *
     *   @param street Street Address
     *   @param city   City
     *   @param state  State
     *   @param zip    Zip Code
     *
     *   @throws InvalidLocationException Must have access to the system client.time
     *
     **/

    public Location(String street, String city, String state, String zip) throws InvalidLocationException
    {
        super();

        this.setState(state);
        this.setCity(city);
        this.setStreet(street);
        this.setZip(zip);
    }

    public static ObservableList<String> getStateList()
    {
        return stateList;
    }

    /**
     *   Return the street
     *
     *   @return Street Address
     *
     **/
    public String getStreet()
    {
        return this.street;
    }

    /***
     *   Return the city
     *
     *   @return City
     *
     * ***/
    public String getCity()
    {
        return this.city;
    }

    /***
     *   Return the State
     *
     *   @return State
     *
     * ***/
    public String getState()
    {
        return this.state;
    }

    /***
     *   Return the zipcode
     *
     *   @return zip code
     *
     * ***/
    public String getZip()
    {
        return this.zip;
    }

    /***
     *    Set the street to the value that is passed in
     *
     *    @param street Set the street to the value that is passed in
     *
     * ***/
    private void setStreet(String street)
    {
        this.street = street;
    }

    /***
     *    Set the city to the value that is passed in
     *
     *    @throws InvalidLocationException Thrown if passed in
     *    String is null, empty, or smaller than the minimum
     *    length set above in the global constants.
     *
     *    @param city Set the city to the value that is passed in
     * ***/
    private void setCity(String city) throws InvalidLocationException
    {
        char[] cityArr;

        if(city == null || city.length() == 0)
        {
            throw new InvalidLocationException("No City Set");
        }
        else if(city.length() < MIN_CITY_NAME_LENGTH)
        {
            throw new InvalidLocationException("Invalid City: " + city);
        }
        cityArr = city.toCharArray();

        for (char c : cityArr) {
            if (!((c >= 65 && c <= 90) ||
                    (c >= 97 && c <= 122) || c == 32 || c == 46)) {
                throw new InvalidLocationException("Invalid Character: " + c);
            }
        }
        this.city = city;
    }

    /***
     *    Set the state to the one that is passed in.
     *    Removes leading and trailing whitespace and
     *    converts value to uppercase.
     *
     *    @param state State in the long or shorthand version
     *
     * ***/
    private void setState(String state)
    {
        this.state = state.toUpperCase().trim();
    }

    /***
     * Set the zip code to passed in
     * integer value so long as it is
     * 5 characters long.
     *
     * @param zip zip code that is 5 characters long
     * @throws InvalidLocationException Thrown if not five characters included
     * ***/
    private void setZip(String zip) throws InvalidLocationException
    {
        if(zip.length() == 5)
        {
            this.zip = zip;
        }
        else
        {
            throw new InvalidLocationException("Invalid Zip Code: " + zip);
        }
    }

    /***
     * Returns the City and State, Delimited by a space
     * @return String
     */
    public String toString()
    {
        return this.getCity() + " " + this.getState();
    }
}