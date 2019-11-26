package gui;

import date.Date;
import date.InvalidDateException;
import date.PioneerDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import location.InvalidLocationException;
import location.Location;
import ride.Ride;
import ride.RideOffer;
import ride.RideRequest;
import socketCommunication.Client;
import student.InvalidStudentException;
import student.Student;
import time.InvalidTimeException;
import time.PioneerTime;
import time.Time;

/**
 * This class creates the offer/request Pane and the components
 * that make up the Pane. The calling class is responsible
 * for adding the Pane of this class to its view to change
 * the scene so that this view can be interacted with.
 *
 * @author Brandon Bonebrake
 */
final class OfferRequestRidePanel extends DefaultView
{
    // Global Constants
    private final int DEFAULT_X_TRANS = 50;  // Sets components distance from x-axis
    private final int DEFAULT_Y_TRANS = 100; // Sets components distance from y-axis

    private final int LEAVE_COMP_Y_START = 0;                       // Starting y-dist for the leave components
    private final int DEST_COMP_Y_START = LEAVE_COMP_Y_START + 150; // Starting y-dist for the destination components

    private final int DIST_Y_BETWEEN_COMP = 50; // Default y-dist between components

    private final int DATE_PICKER_WIDTH  = 150; // Width of the date pickers
    private final int DATE_PICKER_HEIGHT = 37;  // Height of the date pickers

    private final int CITY_TEXT_BOX_WIDTH = 220; // Width of the text fields
    private final int CITY_TEXT_BOX_HEIGHT = 40; // Height of the text fields

    private final int TIME_TEXT_BOX_WIDTH = 100; // Width of the time text field
    private final int TIME_TEXT_BOX_HEIGHT = 40; // Height of the time text field

    private final int CHOICE_BOX_WIDTH = 64;  // Width of the choice boxes
    private final int CHOICE_BOX_HEIGHT = 36; // Height of the choice boxes

    private final String VALID_FIELD = "-fx-control-inner-background: white; -fx-font-weight: bold;";
    private final String INVALID_FIELD = "-fx-control-inner-background: red; -fx-font-weight: bold;";

    // Global Variables
    private String  title;        // Holds whether ride is an Offer or a Request
    private boolean isValidRide;  // Boolean that determines if a ride will be submitted to the server
    private boolean isOffer;      // Boolean that determines if a RideOffer or RideRequest Object will be created

    private ChoiceBox destState;  // Destination state
    private ChoiceBox leaveState; // Leave state

    private TextField leaveCity;  // Leave city
    private TextField destCity;   // Destination city
    private TextField leaveTime;  // Leave time
    private TextField returnTime; // Return time

    private DatePicker leaveDate;  // Leave date
    private DatePicker returnDate; // Return date

    private Label errorLabel; // Label that displays problems with the student's input

    private ObservableList<String> stateList = FXCollections.observableArrayList(); // List of all current ride listings

    private Location leaveLocation;       // Leave location object
    private Location destinationLocation; // Destination location object
    private Date dateLeaving;             // Leave date object
    private Date dateReturning;           // Return date object
    private Time timeLeaving;             // Leave time object
    private Time timeReturning;           // Return time object
    private Student student;              // Student that is creating the ride

    /**
     * Creates the Offer/Request Pane that will allow students
     * to create ride offers/requests.
     *
     * @param stage  Main container that all scenes are a part of
     * @param splash Previous scene that will can be set back to
     * @param width  Width of the view
     * @param height Height of the view
     * @param title  Whether it is a ride offer or request
     */
    OfferRequestRidePanel(Stage stage, Scene splash, int width, int height, String title)
    {
        super(stage, splash, width, height);

        this.title = title;
        this.isOffer = this.getIsOffer();
        this.createTestStudent();
        this.createComponents();
    }

    /**
     * Creates the components that are in the Pane
     */
    void createComponents()
    {
        // List of every state abbreviation
        this.stateList = Location.getStateList();

        // Title at the top of the pane
        this.createTitleLabel();

        // Buttons on the pane
        this.createBackButton();
        this.createSubmitButton();

        // Leave components
        this.createLeaveLabel();
        this.createLeaveCityTxtBox();
        this.createLeaveStateChoiceBox();

        // Destination Components
        this.createDestinationLabel();
        this.createDestinationCityTxtBox();
        this.createDestinationStateChoiceBox();

        // Leave and return date components
        this.createLeaveDateLabel();
        this.createReturnDateLabel();
        this.createLeaveDatePicker();
        this.createReturnDatePicker();

        this.createLeaveTimeLabel();
        this.createReturnTimeLabel();
        this.createLeaveTimePicker();
        this.createReturnTimePicker();

        this.createErrorLabel();
    }

    /**
     * Dummy method to test while accounts are not implemented
     */
    private void createTestStudent()
    {
        try
        {
            student = new Student("John", "Smith", "dummy@uwplatt.edu", "1234567qQ!");
        }
        catch (InvalidStudentException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Sets whether the student is creating a ride offer or
     * request based on the title that is passed in to
     * this Object.
     */
    private boolean getIsOffer()
    {
        return title.toLowerCase().contains("offer");
    }

    /**
     * Creates and displays the title of this view.
     * Title is created from the passed in String in
     * the constructor.
     */
    private void createTitleLabel()
    {
        super.createLabel(this.title, super.getWidth() / 3, 0);
    }

    /**
     * Creates and displays the leave label.
     */
    private void createLeaveLabel()
    {
        super.createLabel("Leaving From", DEFAULT_X_TRANS,
                DEFAULT_Y_TRANS + LEAVE_COMP_Y_START);
    }

    /**
     * Creates and displays the TextField that is used
     * to obtain the leave city.
     */
    private void createLeaveCityTxtBox()
    {
        leaveCity = super.createTextField("Departure City", CITY_TEXT_BOX_WIDTH, CITY_TEXT_BOX_HEIGHT,
                DEFAULT_X_TRANS, DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP);
    }

    /**
     * Creates and displays the ChoiceBox that is used
     * to obtain the state that the ride is leaving
     * form.
     */
    private void createLeaveStateChoiceBox()
    {
        leaveState = super.createChoiceBox(stateList, CHOICE_BOX_WIDTH, CHOICE_BOX_HEIGHT,
                DEFAULT_X_TRANS, DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
    }

    /**
     * Creates and displays the destination label
     */
    private void createDestinationLabel()
    {
        super.createLabel("Destination", DEFAULT_X_TRANS,
                DEFAULT_Y_TRANS + DEST_COMP_Y_START);
    }

    /**
     * Creates and displays the TextField that is used
     * to obtain the destination city.
     */
    private void createDestinationCityTxtBox()
    {
        destCity = super.createTextField("Destination City", CITY_TEXT_BOX_WIDTH, CITY_TEXT_BOX_HEIGHT,
                DEFAULT_X_TRANS, DEFAULT_Y_TRANS + DEST_COMP_Y_START + DIST_Y_BETWEEN_COMP);
    }

    /**
     * Creates and displays the ChoiceBox that is used
     * to obtain the destination state.
     */
    private void createDestinationStateChoiceBox()
    {
        destState = super.createChoiceBox(stateList, CHOICE_BOX_WIDTH, CHOICE_BOX_HEIGHT,
                DEFAULT_X_TRANS, DEFAULT_Y_TRANS + DEST_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
    }

    /**
     * Creates and displays the leave date Label.
     */
    private void createLeaveDateLabel()
    {
        super.createLabel("Leave Date", DEFAULT_X_TRANS + 300,
                DEFAULT_Y_TRANS + LEAVE_COMP_Y_START);
    }

    /**
     * Creates and displays the DatePicker that is used
     * to pick the date that the student is leaving.
     */
    private void createLeaveDatePicker()
    {
        leaveDate = super.createDatePicker(DATE_PICKER_WIDTH, DATE_PICKER_HEIGHT,
                DEFAULT_X_TRANS + 300, DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP);
        leaveDate.setOnAction(e -> leaveDateChanged());
    }

    /**
     * Creates and displays the return date Label.
     */
    private void createReturnDateLabel()
    {
        super.createLabel("Return Date", DEFAULT_X_TRANS + 525,
                DEFAULT_Y_TRANS + LEAVE_COMP_Y_START);
    }

    /**
     * Creates and displays the DatePicker that is used
     * to pick the date that the student is returning.
     */
    private void createReturnDatePicker()
    {
        returnDate = super.createDatePicker(DATE_PICKER_WIDTH, DATE_PICKER_HEIGHT,
                DEFAULT_X_TRANS + 525, DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP);
        returnDate.setOnAction(event -> returnDateChanged());
    }

    /**
     * Creates and displays the leave time Label.
     */
    private void createLeaveTimeLabel()
    {
        super.createLabel("Leave Time", DEFAULT_X_TRANS + 300,
                DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
    }

    /**
     * Creates and displays the TextField that is used
     * by the student to enter the time that the user is
     * leaving.
     */
    private void createLeaveTimePicker()
    {
        leaveTime = super.createTextField("hh:mm", TIME_TEXT_BOX_WIDTH, TIME_TEXT_BOX_HEIGHT,
                DEFAULT_X_TRANS + 300, DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 3);
    }

    /**
     * Creates and displays the return time Label.
     */
    private void createReturnTimeLabel()
    {
        super.createLabel("Return Time", DEFAULT_X_TRANS + 525,
                DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
    }

    /**
     * Creates and displays the TextField that is used
     * by the student to enter the time that the user is
     * returning.
     */
    private void createReturnTimePicker()
    {
        returnTime = super.createTextField("hh:mm", TIME_TEXT_BOX_WIDTH, TIME_TEXT_BOX_HEIGHT,
                DEFAULT_X_TRANS + 525, DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 3);
    }

    /**
     * Creates the error Label that is used to
     * display any problems with the ride information
     * that was entered by the student.
     */
    private void createErrorLabel()
    {
        errorLabel = super.createLabel("", DEFAULT_X_TRANS + 300,
                DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 5);
        errorLabel.setTextFill(Color.RED);
    }

    /**
     * Creates and displays the back button that will return the
     * view back to the splash screen.
     */
    private void createBackButton()
    {
        Button backBtn = super.createButton("Back", 200, 75,
                0, 0, PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());
    }

    /**
     * Creates and displays the submit button that will submit the
     * ride offer/request to the server to be added to the database.
     */
    private void createSubmitButton()
    {
        Button submitBtn = super.createButton("Submit", 200, 75, super.getWidth() - 220,
                super.getHeight() - 95, PioneerApplication.EXIT_STYLE);

        submitBtn.setOnAction(e -> buttonSubmitClicked());
    }

    /**
     * Returns the view when the back button is clicked.
     */
    private void buttonBackClicked()
    {
        super.returnView();
    }

    /**
     * Sends the ride offer/request to the server when the
     * Submit button is clicked.
     */
    private void buttonSubmitClicked()
    {
        Ride ride;

        this.colorizeBasedOnInput();

        if(isValidRide)
        {
            try
            {
                leaveLocation = new Location(null, this.leaveCity.getText(), leaveState.getSelectionModel().getSelectedItem().toString(), 11111);
                destinationLocation = new Location(null, this.destCity.getText(), destState.getSelectionModel().getSelectedItem().toString(), 11111);
            }
            catch (InvalidLocationException e)
            {
                // Send the error to the Server as this should be unlikely
                new Client(e);
            }

            try
            {
                timeLeaving = new PioneerTime(leaveTime.getText());
                timeReturning = new PioneerTime(returnTime.getText());
            }
            catch (InvalidTimeException e)
            {
                // Send the error to the Server as this should be unlikely
                new Client(e);
            }

            // Ride object that will store all necessary ride information
            if(isOffer)
            {
                ride = new RideOffer(leaveLocation, destinationLocation, (PioneerDate) dateLeaving,
                        (PioneerDate) dateReturning, (PioneerTime) timeLeaving, (PioneerTime) timeReturning, student);
            }
            else
            {
                ride = new RideRequest(leaveLocation, destinationLocation, (PioneerDate) dateLeaving,
                        (PioneerDate) dateReturning, (PioneerTime) timeLeaving, (PioneerTime) timeReturning, student);
            }

            // Send the ride information to the server
            new Client(ride);
            PopUpPanel.display("Success");
            this.buttonBackClicked();
        }
    }

    /**
     * Updates the leave date when the leave DatePicker is changed.
     */
    private void leaveDateChanged()
    {
        String[] dateArr = leaveDate.getEditor().getText().split("/");

        try
        {
            if(dateLeaving == null)
            {
                dateLeaving = new PioneerDate(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]));
            }
            else
            {
                dateLeaving.setYear(Integer.parseInt(dateArr[2]));
                dateLeaving.setMonth(Integer.parseInt(dateArr[0]));
                dateLeaving.setDay(Integer.parseInt(dateArr[1]));
            }

            if(this.dateReturning != null && this.dateReturning.compareTo(this.dateLeaving) < 0)
            {
                this.leaveDate.setStyle(INVALID_FIELD);
            }
            else
            {
                this.leaveDate.setStyle(VALID_FIELD);
            }
        }
        catch (InvalidDateException e)
        {
            this.leaveDate.setStyle(INVALID_FIELD);
        }
    }

    /**
     * Updates the return date when the return DatePicker is changed.
     */
    private void returnDateChanged()
    {
        String[] dateArr = returnDate.getEditor().getText().split("/");

        try
        {
            if(dateReturning == null)
            {
                dateReturning = new PioneerDate(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]));
            }
            else
            {
                dateReturning.setYear(Integer.parseInt(dateArr[2]));
                dateReturning.setMonth(Integer.parseInt(dateArr[0]));
                dateReturning.setDay(Integer.parseInt(dateArr[1]));
            }

            if(this.dateLeaving != null && this.dateReturning.compareTo(this.dateLeaving) < 0)
            {
                this.returnDate.setStyle(INVALID_FIELD);
            }
            else
            {
                this.returnDate.setStyle(VALID_FIELD);
            }
        }
        catch (InvalidDateException e)
        {
            this.returnDate.setStyle(INVALID_FIELD);
        }
    }

    /**
     * Colorizes the fields based on if the input is valid or invalid.
     */
    private void colorizeBasedOnInput()
    {
        int numberInvalidInput = 0;

        // Check that the return time is valid
        try
        {
            new PioneerTime(returnTime.getText());

            // Check if the dates are the same and that the return time is after the leave time
            if(dateLeaving != null && dateReturning != null &&
                    dateLeaving.compareTo(dateReturning) == 0 &&
                    returnTime.getText().compareTo(leaveTime.getText()) <= 0)
            {
                returnTime.setStyle(INVALID_FIELD);
                this.errorLabel.setText("Return Time Is In The Past");
                numberInvalidInput++;
            }
            else
            {
                returnTime.setStyle(VALID_FIELD);
            }
        }
        catch (InvalidTimeException e)
        {
            returnTime.setStyle(INVALID_FIELD);
            this.errorLabel.setText("Invalid Return Time");
            numberInvalidInput++;
        }

        // Check that the leave time is valid
        try
        {
            new PioneerTime(leaveTime.getText());
            leaveTime.setStyle(VALID_FIELD);
        }
        catch (InvalidTimeException e)
        {
            leaveTime.setStyle(INVALID_FIELD);
            this.errorLabel.setText("Invalid Departure Time");
            numberInvalidInput++;
        }

        // Check that the return date is valid
        try
        {
            new PioneerDate(returnDate.getEditor().getText());
            returnDate.setStyle(VALID_FIELD);
        }
        catch (InvalidDateException e)
        {
            returnDate.setStyle(INVALID_FIELD);
            this.errorLabel.setText("Invalid Return Date");
            numberInvalidInput++;
        }

        // Check that the leave date is valid
        try
        {
            new PioneerDate(leaveDate.getEditor().getText());
            leaveDate.setStyle(VALID_FIELD);
        }
        catch (InvalidDateException e)
        {
            leaveDate.setStyle(INVALID_FIELD);
            this.errorLabel.setText("Invalid Departure Date");
            numberInvalidInput++;
        }

        // Check that the destination location is valid
        try
        {
            new Location(null, this.destCity.getText(),
                    this.destState.getSelectionModel().getSelectedItem().toString(), 11111);
            this.destCity.setStyle(VALID_FIELD);
        }
        catch (InvalidLocationException e)
        {
            this.destCity.setStyle(INVALID_FIELD);
            this.errorLabel.setText("Invalid Destination City");
            numberInvalidInput++;
        }

        // Check that the leave location is valid
        try
        {
            new Location(null, this.leaveCity.getText(),
                    this.destState.getSelectionModel().getSelectedItem().toString(), 11111);
            this.leaveCity.setStyle(VALID_FIELD);
        }
        catch (InvalidLocationException e)
        {
            this.leaveCity.setStyle(INVALID_FIELD);
            this.errorLabel.setText("Invalid Departure City");
            numberInvalidInput++;
        }

        if(numberInvalidInput == 0)
        {
            isValidRide = true;
            this.errorLabel.setText("");
        }
        else
        {
            isValidRide = false;
        }
    }
}