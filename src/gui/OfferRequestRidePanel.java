package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ride.Ride;

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

    private final int CHOICEBOX_WIDTH  = 64; // Width of the choice boxes
    private final int CHOICEBOX_HEIGHT = 36; // Height of the choice boxes

    private final int DEFAULT_STATE_SELECTED = 48; // Default choice selected on the choice boxes (Wisconsin)
    private final int DEFAULT_FONT_SIZE = 32;

    // Global Variables
    private Button backBtn;
    private Button submitBtn;
    private TextField leaveCity;
    private TextField destCity;
    private ChoiceBox leaveState;
    private ChoiceBox destState;
    private DatePicker leaveDate;
    private DatePicker returnDate;

    private ObservableList<String> stateList = FXCollections.observableArrayList();

    private Ride ride; // Ride object that will store all necessary ride information

    public OfferRequestRidePanel(Stage stage, Scene splash, int width, int height)
    {
        super(stage, splash, width, height);

        createComponents();
    }

    void createComponents()
    {
        this.createStateList();

        this.createBackButton();
        this.createSubmitButton();

        this.createLeaveLabel();
        this.createLeaveCityTxtBox();
        this.createLeaveStateChoiceBox();

        this.createDestinationLabel();
        this.createDestinationCityTxtBox();
        this.createDestinationStateChoiceBox();

        this.createLeaveDateLabel();
        this.createReturnDateLabel();
        this.createLeaveDatePicker();
        this.createReturnDatePicker();
    }

    private void createStateList()
    {
        stateList.addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
                "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
                "NY", "NC" ,"ND", "OH", "OK", "OR", "PA", "RI" ,"SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");
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
        leaveState = new ChoiceBox();

        leaveState.setPrefSize(CHOICEBOX_WIDTH, CHOICEBOX_HEIGHT);
        leaveState.setItems(stateList);
        leaveState.setTranslateX(DEFAULT_X_TRANS);
        leaveState.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
        leaveState.setValue(stateList.get(DEFAULT_STATE_SELECTED)); // Default to Wisconsin
        leaveState.setOnAction(e -> ChoiceStateChanged());

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
        destState = new ChoiceBox();

        destState.setPrefSize(CHOICEBOX_WIDTH, CHOICEBOX_HEIGHT);
        destState.setItems(stateList);
        destState.setTranslateX(DEFAULT_X_TRANS);
        destState.setTranslateY(DEFAULT_Y_TRANS + DEST_COMP_Y_START + DIST_Y_BETWEEN_COMP * 2);
        destState.setValue(stateList.get(DEFAULT_STATE_SELECTED)); // Default to Wisconsin
        destState.setOnAction(e -> ChoiceStateChanged());

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

        leaveDate.setPrefSize(DATE_PICKER_WIDTH, DATE_PICKER_HEIGHT);
        leaveDate.setTranslateX(DEFAULT_X_TRANS + 300);
        leaveDate.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP);
        leaveDate.setStyle("-fx-font-weight: bold;");
        leaveDate.setPromptText("dd/mm/yyyy");

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

        returnDate.setPrefSize(DATE_PICKER_WIDTH, DATE_PICKER_HEIGHT);
        returnDate.setTranslateX(DEFAULT_X_TRANS + 525);
        returnDate.setTranslateY(DEFAULT_Y_TRANS + LEAVE_COMP_Y_START + DIST_Y_BETWEEN_COMP);
        returnDate.setStyle("-fx-font-weight: bold;");
        returnDate.setPromptText("dd/mm/yyyy");

        super.addComponent(returnDate);
    }

    private void createBackButton()
    {
        backBtn = new Button("Back");
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

        submitBtn = new Button("Submit");
        submitBtn.setPrefSize(WIDTH, HEIGHT);
        submitBtn.setFont(Font.font(DEFAULT_FONT_SIZE));
        submitBtn.setStyle(PioneerApplication.EXIT_STYLE);
        submitBtn.setTranslateX(super.getWidth() - WIDTH - 20);
        submitBtn.setTranslateY(super.getHeight() - HEIGHT - 20);
        submitBtn.setOnAction(e -> buttonSubmitClicked());

        super.addComponent(submitBtn);
    }

    private void ChoiceStateChanged()
    {

    }

    private void buttonBackClicked()
    {
        super.returnView();
    }

    private void buttonSubmitClicked()
    {
        System.out.println("YES!!!");
    }
}