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

    /**
     *    Creates a client.student.Student object that holds their
     *    name and email.
     *
     *    @param firstName First name of client.student
     *    @param lastName  Last name of client.student
     *    @param email     Email of client.student
     *    @param password  Password of the students account
     *
     *    @throws InvalidStudentException Must have access to the system client.time
     *
     */
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

    /**
     *    Creates a client.student.Student object that holds their
     *    name and email.
     *
     *    @param firstName First name of client.student
     *    @param lastName  Last name of client.student
     *    @param email     Email of client.student
     *    @param password  Password of the students account
     *    @param accountCreationDate Date account was created
     *    @param accountNumber Unique number tied to the account
     *
     *    @throws InvalidStudentException Must have access to the system client.time
     *
     */
    public Student(String firstName, String lastName, String email, String password, Date accountCreationDate,
                   String accountNumber) throws InvalidStudentException
    {
        super();

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setAccountCreationDate(accountCreationDate);
        this.setAccountNumber(accountNumber);
    }

    /**
     *    Method that returns the Student's first name.
     *
     *    @return Student's first name
     */
    public String getFirstName() { return this.firstName; }

    /**
     *   Method that returns the Student's last name.
     *
     *   @return Student's last name
     */
    public String getLastName() { return this.lastName; }

    /**
     *   Method that returns the Student's uwplatt email.
     *
     *   @return Student's uwplatt email
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     *    Method that returns the Student's password.
     *
     *    @return Student's password
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     *    Method that returns the date the Student's account
     *    was created on.
     *
     *    @return Students account creation date
     */
    public Date getAccountCreationDate()
    {
        return accountCreationDate;
    }

    /**
     *    Method that returns the hex account number
     *    of the user.
     *
     *    @return Account number of the student
     */
    public String getAccountNumber()
    {
        return this.accountNumber;
    }

    /**
     *    Method that sets the Student first name.
     *
     *    @param firstName Student's first name
     *    @throws InvalidStudentException Thrown if name is empty or null
     */
    public void setFirstName(String firstName) throws InvalidStudentException
    {
        if(firstName == null || firstName.trim().equals(""))
        {
            throw new InvalidStudentException("Invalid First Name: EMPTY");
        }
        this.firstName = firstName;
    }

    /**
     *    Method that sets the Student first name.
     *
     *    @param lastName Student's last name
     *    @throws InvalidStudentException Thrown if name is empty or null
     */
    public void setLastName(String lastName) throws InvalidStudentException
    {
        if(lastName == null || lastName.trim().equals(""))
        {
            throw new InvalidStudentException("Invalid Last Name: EMPTY");
        }
        this.lastName = lastName;
    }

    /**
     *    Method that sets the email as long as it is a @uwplatt.edu email.
     *
     *    @param email uwplatt.edu email
     *    @throws InvalidStudentException Thrown if email is not a uw-platteville email
     */
    public void setEmail(String email) throws InvalidStudentException
    {
        if(email == null || email.equals(""))
        {
            throw new InvalidStudentException("Invalid Email: Nothing entered");
        }
        if(!email.contains("@uwplatt.edu"))
        {
            throw new InvalidStudentException("Invalid Email: " + "\"" + email + "\"");

        }
        this.email = email;
    }

    /**
     *    Method that sets the password of the student to the passed in value
     *
     *    @param password Password that the student set
     *    @throws InvalidStudentException Thrown if student password is invalid
     */
    public void setPassword(String password) throws InvalidStudentException
    {
        char[] passArr = password.toCharArray();

        // Password must be at least 8 characters
        if(passArr.length < 8)
            throw new InvalidStudentException("Invalid Password: Must be at least 8 characters long");

        // Looking for a number to be in the password
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
            if(((passArr[i] >= 33) && (passArr[i] <= 47)) || ((passArr[i] >= 58) && (passArr[i] <= 64))
                    || ((passArr[i] >= 91) && (passArr[i] <= 96)) || ((passArr[i] >= 123) && (passArr[i] <= 126)))
                break;
            if( i == passArr.length - 1)
                throw new InvalidStudentException("Invalid Password: Must contain at least one symbol");
        }
        this.password = password;
    }

    /**
     *    Method that sets the date of the account creation
     *    to the current system date.
     */
    private void setAccountCreationDate()
    {
        this.accountCreationDate = new Date();
    }

    /**
     *    Method that sets the account creation date.
     *
     *    @param date Date that the student account was created
     */
    private void setAccountCreationDate(Date date)
    {
        this.accountCreationDate = date;
    }

    /**
     *    Method that sets the account number of the student
     *    at the creation of the object based on
     *    values set to email, account creation
     *    date, etc...
     **/
    private void setAccountNumber()
    {
        StringBuilder st = new StringBuilder();
        String temp = String.valueOf(getAccountCreationDate().getMonth());
        String temp1[]  = getEmail().split("@uwplatt.edu"); // Get the name portion of the email
        char[] emailArr = temp1[0].toCharArray(); // Convert the name of the email to chars

        st.append(getAccountCreationDate().getYear());

        // Add month to account number
        if(temp.length() == 1)
            temp = "0" + temp;
        st.append(temp);

        // Add day to account number
        temp = String.valueOf(getAccountCreationDate().getDay());
        if(temp.length() == 1)
            temp = "0" + temp;
        st.append(temp);

        // Convert date to hex
        temp = Long.toHexString(Long.parseLong(st.toString()));

        // Remove what is in st and add the hex equivalent
        st.delete(0, st.length());
        st.append(temp);

        // XOR the email and add it to the account number
        for(int i = 0; i < emailArr.length; i++)
        {
            emailArr[i] = (char) (emailArr[i] ^ 1);
            st.append((int) emailArr[i] ^ 1);
        }

        // Make the length uniform
        while(st.length() < 64)
        {
            st.append(0);
        }

        // Remove any characters over the limit
        while (st.length() > 64)
        {
            st.deleteCharAt(st.length() - 1);
        }
        this.accountNumber = st.toString();
    }

    /**
     *    Method that sets the account number.
     *
     *    @param acctNo Account number of the student
     */
    private void setAccountNumber(String acctNo)
    {
        this.accountNumber = acctNo;
    }

    /**
     *    Method that increments the number of ride requests that the student has
     *    if it is within the valid range.
     *
     *    @throws InvalidStudentException Thrown if there are too many ride requests
     */
    public void incrementRideRequests() throws InvalidStudentException
    {
        if(this.numCurrentRideRequests >= MAX_RIDE_REQUESTS)
        {
            throw new InvalidStudentException("Invalid Number Ride Requests. Max of " + MAX_RIDE_REQUESTS);
        }
        this.numCurrentRideRequests++;
    }

    /**
     *    Method that increments the number of ride offers that the student has
     *    if it is within the valid range.
     *
     *    @throws InvalidStudentException Thrown if there are too many ride offers
     */
    public void incrementRideOffers() throws InvalidStudentException
    {
        if(this.numCurrentRideOffers >= MAX_RIDE_OFFERS)
        {
            throw new InvalidStudentException("Invalid Number Ride Offers. Max of " + MAX_RIDE_OFFERS);
        }
        this.numCurrentRideOffers++;
    }

    /**
     *    Method that decrements the number of ride requests that the student has
     *    if it is within the valid range.
     *
     *    @throws InvalidStudentException
     */
    public void decrementRideRequests() throws InvalidStudentException
    {
        if(this.numCurrentRideRequests <= 0)
        {
            throw new InvalidStudentException("Invalid Number Ride Requests. Min of 0");
        }
        this.numCurrentRideRequests--;
    }

    /**
     *    Method that decrements the number of ride offers that the student has
     *    if it is within the valid range
     *
     *    @throws InvalidStudentException Thrown if there is no rides on this account
     */
    public void decrementRideOffers() throws InvalidStudentException
    {
        if(this.numCurrentRideOffers <= 0)
        {
            throw new InvalidStudentException("Invalid Number Ride Offers. Min of 0");
        }
        this.numCurrentRideOffers--;
    }

    /**
     *    Overridden toString method that returns a String containing Student information.
     *
     *    @return student information
     */
    public String toString()
    {
        return this.email;
    }
}