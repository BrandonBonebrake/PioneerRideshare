package student;

import date.Date;

import java.io.Serializable;

public final class Student implements Serializable
{
    private final int MAX_RIDE_OFFERS  = 2;
    private final int MAX_RIDE_REQUESTS = 2;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date accountCreationDate;
    private int numCurrentRideRequests = 0;
    private int numCurrentRideOffers   = 0;
    private String accountNumber;

    private static final long serialVersionUID = 4099097738532790605L;

    /***
     *   Creates a client.student.Student object that holds their
     *   name and email
     *
     *   @param firstName First name of client.student
     *   @param lastName  Last name of client.student
     *   @param email     Email of client.student
     *
     *   @throws InvalidStudentException Must have access to the system client.time
     *
     *  ***/
    public Student(String firstName, String lastName, String email, String password) throws InvalidStudentException
    {
        super();

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setAccountCreationDate();
        this.setAccountNumber();
    }

    /***
     *   Return the Student's first name
     *
     *   @return Student's first name
     *
     * ***/
    public String getFirstName() { return this.firstName; }

    /***
     *   Return the Student's last name
     *
     *   @return Student's last name
     *
     * ***/
    public String getLastName() { return this.lastName; }

    /***
     *   Return the Student's uwplatt email
     *
     *   @return Student's uwplatt email
     *
     * ***/
    public String getEmail() { return this.getEmail(); }

    public String getPassword()
    {
        return this.password;
    }

    /***
     *    Return the date the students account
     *    created on
     *
     *    @return Students account creation date
     *
     * ***/
    public Date getAccountCreationDate()
    {
        return accountCreationDate;
    }

    /***
     *    Returns the hex account number
     *    of the user
     *
     *    @return Account number of the student
     *
     * ***/
    public String getAccountNumber()
    {
        return this.accountNumber;
    }

    /***
     *    Set the Student first name to the passed
     *    in value
     *
     *    @param firstName Student's first name
     *
     * ***/
    public void setFirstName(String firstName) throws InvalidStudentException
    {
        if(firstName == null || firstName.trim().equals(""))
        {
            throw new InvalidStudentException("Invalid First Name: EMPTY");
        }
        this.firstName = firstName;
    }

    /***
     *    Set the Student first name to the passed
     *    in value
     *
     *    @param lastName Student's last name
     *
     * ***/
    public void setLastName(String lastName) throws InvalidStudentException
    {
        if(lastName == null || lastName.trim().equals(""))
        {
            throw new InvalidStudentException("Invalid Last Name: EMPTY");
        }
        this.lastName = lastName;
    }

    /***
     *    Set the email as long as it is a @uwplatt.edu email
     *
     *    @param email @uwplatt.edu email
     *
     * ***/
    public void setEmail(String email) throws InvalidStudentException
    {
        if(email.contains("@uwplatt.edu"))
        {
            this.email = email;
        }
        else
        {
            throw new InvalidStudentException("Invalid Email: " + email);
        }
    }

    public void setPassword(String password) throws InvalidStudentException
    {
        char[] passArr = password.toCharArray();

        // Password must be at least 8 characters
        if(passArr.length < 8)
            throw new InvalidStudentException("Invalid Password: Must be at least 8 characters long");

        // Looking for a number in the password
        for(int i = 0; i < passArr.length; i++)
        {
            // Found a number in password
            if(passArr[i] >= 48 && passArr[i] <= 57)
                break;
            if( i == passArr.length - 1)
                throw new InvalidStudentException("Invalid Password: Must contain at least one number");
        }

        // Looking for a symbol in the password
        for(int i = 0; i < passArr.length; i++)
        {
            // Found a symbol in password
            if(((passArr[i] >= 33) && (passArr[i] <= 47)) || ((passArr[i] >= 58) && (passArr[i] <= 64)) || ((passArr[i] >= 91) && (passArr[i] <= 96)) || ((passArr[i] >= 123) && (passArr[i] <= 126)))
                break;
            if( i == passArr.length - 1)
                throw new InvalidStudentException("Invalid Password: Must contain at least one symbol");
        }
        this.password = password;
    }

    /***
     *    Sets the date of the account creation
     *    to the current system date
     *
     * ***/
    private void setAccountCreationDate()
    {
        this.accountCreationDate = new Date();
    }

    /***
     *    Set the account number of the student
     *    at the creation of the object
     *
     * ***/
    private void setAccountNumber()
    {
        StringBuilder st = new StringBuilder();
        long accountNumber = 0;
        char[] yearArr   = String.valueOf(this.getAccountCreationDate().getYear()).toCharArray();
        char[] monthArr  = String.valueOf(this.getAccountCreationDate().getMonth()).toCharArray();
        char[] dayArr    = String.valueOf(this.getAccountCreationDate().getDay()).toCharArray();
        char[] lastName  = this.getLastName().toUpperCase().toCharArray();
        char[] firstName = this.getFirstName().toUpperCase().toCharArray();

        // Append the year value to account number
        st.append(Character.getNumericValue(yearArr[0]));
        st.append(Character.getNumericValue(yearArr[1]));
        st.append(Character.getNumericValue(yearArr[2]));
        st.append(Character.getNumericValue(yearArr[3]));

        // Append month value to account number
        st.append(Character.getNumericValue(monthArr[0]));

        if(monthArr.length == 2)
            st.append(Character.getNumericValue(monthArr[1]));
        else
            st.append("00");

        // Append day value to account number
        st.append(Character.getNumericValue(dayArr[0]));

        if(dayArr.length == 2)
            st.append(Character.getNumericValue(dayArr[1]));
        else
            st.append("00");

        // Append last name to account number
        for (char c : lastName)
        {
            st.append(Character.getNumericValue(c));
        }

        // Append first name to account number
        for (char c : firstName)
        {
            st.append(Character.getNumericValue(c));
        }

        while (st.length() < 54)
            st.append("0");

        // Remove everything in st and convert it to hex
        st.delete(0, st.length());
        st.append(Long.toHexString(accountNumber));

        // Set hex value to the account number
        this.accountNumber = st.toString();
    }

    public void incrementRideRequests() throws InvalidStudentException
    {
        if(this.numCurrentRideRequests >= MAX_RIDE_REQUESTS)
        {
            throw new InvalidStudentException("Invalid Number Ride Requests. Max of " + MAX_RIDE_REQUESTS);
        }
        this.numCurrentRideRequests++;
    }

    public void incrementRideOffers() throws InvalidStudentException
    {
        if(this.numCurrentRideOffers >= MAX_RIDE_OFFERS)
        {
            throw new InvalidStudentException("Invalid Number Ride Offers. Max of " + MAX_RIDE_OFFERS);
        }
        this.numCurrentRideOffers++;
    }


    /***
     * @return client.student information
     * ***/
    public String toString()
    {
        return this.firstName + " " + this.lastName + " " +
                this.email + " " + this.password;
    }
}