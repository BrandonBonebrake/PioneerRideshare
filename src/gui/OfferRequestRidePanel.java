package gui;

import date.Date;
import date.InvalidDateException;
import date.PioneerDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

    private final int CITY_TXTBOX_WIDTH  = 220; // Width of the text fields
    private final int CITY_TXTBOX_HEIGHT = 40;  // Height of the text fields

    private final int TIME_TXTBOX_WIDTH = 100;
    private final int TIME_TXTBOX_HEIGHT = CITY_TXTBOX_HEIGHT;

    private final int CHOICEBOX_WIDTH  = 64; // Width of the choice boxes
    private final int CHOICEBOX_HEIGHT = 36; // Height of the choice boxes

    private final int DEFAULT_STATE_SELECTED = 48; // Default choice selected on the choice boxes (Wisconsin)
    private final int DEFAULT_FONT_SIZE = 32;

    private final String VALID_FIELD = "-fx-control-inner-background: white; -fx-font-weight: bold;";
    private final String INVALID_FIELD = "-fx-control-inner-background: red; -fx-font-weight: bold;";

    // Global Variables
    private String title;
    private boolean isValidRide = false;
    private boolean isOffer = false;

    private ChoiceBox destState;
    private ChoiceBox leaveState;

    private TextField leaveCity;
    private TextField destCity;
    private TextField leaveTime;
    private TextField returnTime;

    private DatePicker leaveDate;
    private DatePicker returnDate;

    private ObservableList<String> stateList = FXCollections.observableArrayList();

    private Ride ride; // Ride object that will store all necessary ride information
    private Location leaveLocation;
    private Location destinationLocation;
    private Date dateLeaving;
    private Date dateReturning;
    private Time timeLeaving;
    private Time timeReturning;
    private Student student;

    OfferRequestRidePanel(Stage stage, Scene splash, int width, int height, String title)
    {
        super(stage, splash, width, height);

        this.title = title;
        this.setIsOffer();
        this.createTestStudent();
        this.createComponents();
    }

    void createComponents()
    {
        // List of every state abbreviation
        this.createStateList();

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
    }

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

    private void setIsOffer()
    {
        if(title.toLowerCase().contains("offer"))
        {
            isOffer = true;
        }
        else
        {
            isOffer = false;
        }
    }

    /**
     * Method that creates every abbreviation for the states in the US. Alaska and Hawaii are
     * included but likely not a valid place to go. This method only exists so that the global
     * variables are not cluttered with a large list like this.
     */
    private void createStateList()
    {
        stateList.addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
                "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
                "NY", "NC" ,"ND", "OH", "OK", "OR", "PA", "RI" ,"SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");
    }

    private void createTitleLabel()
    {
        Label titleLbl = new Label(this.title);
        titleLbl.setTranslateX(super.getWidth() / 3);
        titleLbl.setFont(Font.font(DEFAULT_FONT_SIZE + 20));
        titleLbl.setTextFill(Color.WHITE);
        titleLbl.setStyle("-fx-font-weight: bold;");

        super.addComponent(titleLbl);
    }

    private void createLeaveLabel()
    {
        Label leave = new Label("Leaving From");
        leave.setTranslateX(DEFAULT_X_TRANS);
        leave.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START);
        leave.setFont(Font.font(DEFAULT_FONT_SIZE));
        leave.setTextFill(Color.WHITE);
        leave.setStyle("-fx-font-weight: bold;");

        super.addComponent(leave);
    }

    private void createLeaveCityTxtBox()
    {
        leaveCity = new TextField();
        leaveCity.setPrefSize(CITY_TXTBOX_WIDTH, CITY_TXTBOX_HEIGHT);
        leaveCity.setTranslateX(DEFAULT_X_TRANS);
        leaveCity.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP);
        leaveCity.setPromptText("Departure City");
        leaveCity.setStyle("-fx-font-weight: bold;");
        leaveCity.setFont(Font.font(18));

        super.addComponent(leaveCity);
    }

    private void createLeaveStateChoiceBox()
    {
        leaveState = new ChoiceBox<>();

        leaveState.setPrefSize(CHOICEBOX_WIDTH, CHOICEBOX_HEIGHT);
        leaveState.setItems(stateList);
        leaveState.setTranslateX(DEFAULT_X_TRANS);
        leaveState.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
        leaveState.setValue(stateList.get(DEFAULT_STATE_SELECTED)); // Default to Wisconsin

        super.addComponent(leaveState);
    }

    private void createDestinationLabel()
    {
        Label destination = new Label("Destination");
        destination.setTranslateX(DEFAULT_X_TRANS);
        destination.setTranslateY(DEFAULT_Y_TRANS + DEST_COMP_Y_START);
        destination.setFont(Font.font(DEFAULT_FONT_SIZE));
        destination.setTextFill(Color.WHITE);
        destination.setStyle("-fx-font-weight: bold;");

        super.addComponent(destination);
    }

    private void createDestinationCityTxtBox()
    {
        destCity = new TextField();
        destCity.setPrefSize(CITY_TXTBOX_WIDTH, CITY_TXTBOX_HEIGHT);
        destCity.setTranslateX(DEFAULT_X_TRANS);
        destCity.setTranslateY(DEFAULT_Y_TRANS + DEST_COMP_Y_START + DIST_Y_BETWEEN_COMP);
        destCity.setPromptText("Destination City");
        destCity.setStyle("-fx-font-weight: bold;");
        destCity.setFont(Font.font(18));

        super.addComponent(destCity);
    }

    private void createDestinationStateChoiceBox()
    {
        destState = new ChoiceBox<>();

        destState.setPrefSize(CHOICEBOX_WIDTH, CHOICEBOX_HEIGHT);
        destState.setItems(stateList);
        destState.setTranslateX(DEFAULT_X_TRANS);
        destState.setTranslateY(DEFAULT_Y_TRANS + DEST_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
        destState.setValue(stateList.get(DEFAULT_STATE_SELECTED)); // Default to Wisconsin

        super.addComponent(destState);
    }

    private void createLeaveDateLabel()
    {
        Label leaveDate = new Label("Leave Date");
        leaveDate.setTranslateX(DEFAULT_X_TRANS + 300);
        leaveDate.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START);
        leaveDate.setFont(Font.font(DEFAULT_FONT_SIZE));
        leaveDate.setTextFill(Color.WHITE);
        leaveDate.setStyle("-fx-font-weight: bold;");

        super.addComponent(leaveDate);
    }

    private void createLeaveDatePicker()
    {
        leaveDate = new DatePicker();
        leaveDate.setEditable(false);
        leaveDate.setPrefSize(DATE_PICKER_WIDTH, DATE_PICKER_HEIGHT);
        leaveDate.setTranslateX(DEFAULT_X_TRANS + 300);
        leaveDate.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP);
        leaveDate.setStyle("-fx-font-weight: bold;");
        leaveDate.setPromptText("dd/mm/yyyy");
        leaveDate.setOnAction(e -> leaveDateChanged());

        super.addComponent(leaveDate);
    }

    private void createReturnDateLabel()
    {
        Label returnDate = new Label("Return Date");
        returnDate.setTranslateX(DEFAULT_X_TRANS + 525);
        returnDate.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START);
        returnDate.setFont(Font.font(DEFAULT_FONT_SIZE));
        returnDate.setTextFill(Color.WHITE);
        returnDate.setStyle("-fx-font-weight: bold;");

        super.addComponent(returnDate);
    }

    private void createReturnDatePicker()
    {
        returnDate = new DatePicker();
        returnDate.setEditable(false);
        returnDate.setPrefSize(DATE_PICKER_WIDTH, DATE_PICKER_HEIGHT);
        returnDate.setTranslateX(DEFAULT_X_TRANS + 525);
        returnDate.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP);
        returnDate.setStyle("-fx-font-weight: bold;");
        returnDate.setPromptText("dd/mm/yyyy");
        returnDate.setOnAction(event -> returnDateChanged());

        super.addComponent(returnDate);
    }

    private void createLeaveTimeLabel()
    {
        Label leaveTime = new Label("Leave Time");
        leaveTime.setTranslateX(DEFAULT_X_TRANS + 300);
        leaveTime.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
        leaveTime.setFont(Font.font(DEFAULT_FONT_SIZE));
        leaveTime.setTextFill(Color.WHITE);
        leaveTime.setStyle("-fx-font-weight: bold;");

        super.addComponent(leaveTime);
    }

    private void createLeaveTimePicker()
    {
        leaveTime = new TextField();
        leaveTime.setPrefSize(TIME_TXTBOX_WIDTH, TIME_TXTBOX_HEIGHT);
        leaveTime.setTranslateX(DEFAULT_X_TRANS + 300);
        leaveTime.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 3);
        leaveTime.setPromptText("hh:mm");
        leaveTime.setStyle("-fx-font-weight: bold;");
        leaveTime.setFont(Font.font(18));

        super.addComponent(leaveTime);
    }

    private void createReturnTimeLabel()
    {
        Label returnTime = new Label("Return Time");
        returnTime.setTranslateX(DEFAULT_X_TRANS + 525);
        returnTime.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
        returnTime.setFont(Font.font(DEFAULT_FONT_SIZE));
        returnTime.setTextFill(Color.WHITE);
        returnTime.setStyle("-fx-font-weight: bold;");

        super.addComponent(returnTime);
    }

    private void createReturnTimePicker()
    {
        returnTime = new TextField();
        returnTime.setPrefSize(TIME_TXTBOX_WIDTH, TIME_TXTBOX_HEIGHT);
        returnTime.setTranslateX(DEFAULT_X_TRANS + 525);
        returnTime.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 3);
        returnTime.setPromptText("hh:mm");
        returnTime.setStyle("-fx-font-weight: bold;");
        returnTime.setFont(Font.font(18));

        super.addComponent(returnTime);
    }

    private void createBackButton()
    {
        Button backBtn = new Button("Back");

        backBtn.setPrefSize(200, 75);
        backBtn.setFont(Font.font(DEFAULT_FONT_SIZE));
        backBtn.setStyle(PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());

        super.addComponent(backBtn);
    }

    private void createSubmitButton()
    {
        final int WIDTH = 200;
        final int HEIGHT = 75;

        Button submitBtn = new Button("Submit");

        submitBtn.setPrefSize(WIDTH, HEIGHT);
        submitBtn.setFont(Font.font(DEFAULT_FONT_SIZE));
        submitBtn.setStyle(PioneerApplication.EXIT_STYLE);
        submitBtn.setTranslateX(super.getWidth() - WIDTH - 20);
        submitBtn.setTranslateY(super.getHeight() - HEIGHT - 20);
        submitBtn.setOnAction(e -> buttonSubmitClicked());

        super.addComponent(submitBtn);
    }

    private void buttonBackClicked()
    {
        super.returnView();
    }

    private void buttonSubmitClicked()
    {
        colorizeBasedOnInput();

        if(isValidRide)
        {
            try
            {
                leaveLocation = new Location(null, this.leaveCity.getText(), leaveState.getSelectionModel().getSelectedItem().toString(), 11111);
                destinationLocation = new Location(null, this.destCity.getText(), destState.getSelectionModel().getSelectedItem().toString(), 11111);
            }
            catch (InvalidLocationException e)
            {
                System.err.println(e.getCause() + " " + e.getMessage());
            }

            try
            {
                timeLeaving = new PioneerTime(leaveTime.getText());
                timeReturning = new PioneerTime(returnTime.getText());
            }
            catch (InvalidTimeException e)
            {
                System.err.println(e.getCause() + " " + e.getMessage());
            }

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

            new Client(ride);
        }
    }

    private void leaveDateChanged()
    {
        String[] dateArr = leaveDate.getEditor().getText().split("/");

        try
        {
            dateLeaving = new PioneerDate(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]));

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

    private void returnDateChanged()
    {
        String[] dateArr = returnDate.getEditor().getText().split("/");

        try
        {
            dateReturning = new PioneerDate(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]));

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

    private void colorizeBasedOnInput()
    {
        int numberInvalidInput = 0;

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
            numberInvalidInput++;
        }

        // Check that the return time is valid
        try
        {
            new PioneerTime(returnTime.getText());
            returnTime.setStyle(VALID_FIELD);
        }
        catch (InvalidTimeException e)
        {
            returnTime.setStyle(INVALID_FIELD);
            numberInvalidInput++;
        }

        if(dateLeaving.compareTo(dateReturning) == 0)
        {

        }

        if(numberInvalidInput == 0)
        {
            isValidRide = true;
        }
        else
        {
            isValidRide = false;
        }
    }
}