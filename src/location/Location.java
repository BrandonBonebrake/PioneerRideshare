package location;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 *    Object used to hold an address to a location.
 *
 *    @author  Brandon Bonebrake
 *
 */

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
     *    Creates an Address object to hold
     *    an object
     *
     *    @param street Street Address
     *    @param city   City
     *    @param state  State
     *    @param zip    Zip Code
     *
     *    @throws InvalidLocationException Must have access to the system client.time
     */
    public Location(String street, String city, String state, int zip) throws InvalidLocationException
    {
        super();

        this.setState(state);
        this.setCity(city);
        this.setStreet(street);
        this.setZip(Integer.toString(zip));
    }

    /**
     *    Creates an Address object to hold an object.
     *
     *    @param street Street Address
     *    @param city   City
     *    @param state  State
     *    @param zip    Zip Code
     *
     *    @throws InvalidLocationException Must have access to the system client.time
     */
    public Location(String street, String city, String state, String zip) throws InvalidLocationException
    {
        super();

        this.setState(state);
        this.setCity(city);
        this.setStreet(street);
        this.setZip(zip);
    }

    /**
     *    Method used to return stateList.
     *
     *    @return stateList ObservableList<String> that holds states
     */
    public static ObservableList<String> getStateList()
    {
        return stateList;
    }

    /**
     *   Method that returns the street.
     *
     *   @return street String holding Street Address
     */
    public String getStreet()
    {
        return this.street;
    }

    /**
     *    Method that returns the city.
     *
     *    @return city String holding City
     */
    public String getCity()
    {
        return this.city;
    }

    /**
     *    Method that returns the state.
     *
     *    @return state String holding State
     */
    public String getState()
    {
        return this.state;
    }

    /**
     *   Method that returns the zipcode.
     *
     *   @return zip String holding Zipcode
     */
    public String getZip()
    {
        return this.zip;
    }

    /**
     *    Method that sets the street.
     *
     *    @param street String to set the street to
     */
    private void setStreet(String street)
    {
        this.street = street;
    }

    /**
     *    Method that sets the city.
     *
     *    @throws InvalidLocationException Thrown if passed in
     *    String is null, empty, or smaller than the minimum
     *    length set above in the global constants.
     *
     *    @param city String to set the city to
     */
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

    /**
     *    Method that sets the state.
     *    Removes leading and trailing whitespace and
     *    converts value to uppercase.
     *
     *    @param state String of State in the long or shorthand version
     */
    private void setState(String state)
    {
        this.state = state.toUpperCase().trim();
    }

    /**
     * Set the zip code to passed in
     * integer value so long as it is
     * 5 characters long.
     *
     * @param zip zip code that is 5 characters long
     * @throws InvalidLocationException Thrown if not five characters included
     */
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

    /**
     *    Overridden toString method that returns the City and State,
     *    delimited by a space.
     *
     *    @return String containing the city and state
     */
    @Override
    public String toString()
    {
        return this.getCity() + " " + this.getState();
    }
}