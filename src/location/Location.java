package location;

/***
 *    Object used to hold an address to a location.
 *
 *    @author  Brandon Bonebrake
 *
 ***/

public final class Location
{
    // Class Variables
    private String street = null;
    private String city   = null;
    private String state  = null;
    private int    zip    = 0;

    /*** Constructor Method - Date <p>
     *
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
     *  ***/

    public Location(String street, String city, String state, int zip) throws InvalidLocationException
    {
        super();

        this.setState(state);
        this.setCity(city);
        this.setStreet(street);
        this.setZip(zip);
    }

    /***
     *   Return the street
     *
     *   @return Street Address
     *
     * ***/

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

    public int getZip()
    {
        return this.zip;
    }

    /***
     *    Set the street to the value that is passed in
     *
     *    @param street Set the street to the value that is passed in
     *
     * ***/

    public void setStreet(String street)
    {
        this.street = street;
    }

    /***
     *    Set the city to the value that is passed in
     *
     *    @param city Set the city to the value that is passed in
     *
     * ***/

    public void setCity(String city)
    {
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

    public void setState(String state)
    {
        this.state = state.toUpperCase().trim();
    }

    /***
     *    Set the zip code to passed in
     *    integer value so long as it is
     *    5 characters long.
     *
     *    @param zip zip code that is 5 characters long
     *
     * ***/

    public void setZip(int zip) throws InvalidLocationException
    {
        if(Integer.toString(zip).length() == 5)
        {
            this.zip = zip;
        }
        else
        {
            throw new InvalidLocationException("Invalid Zip Code: " + zip);
        }
    }
}