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

    // Global Variables
    private Button backBtn;
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

        this.createLeaveLabel();
        this.createLeaveCityTxtBox();
        this.createLeaveStateChoiceBox();

        this.createDestinationLabel();
        this.createDestinationCityTxtBox();
        this.createDestinationStateChoiceBox();

        this.createLeaveDatePicker();
        this.createReturnDatePicker();
    }

    private void createStateList()
    {
        stateList.addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
                "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
                "NY", "NC" ,"ND", "OH", "OK", "OR", "PA", "RI" ,"SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");
    }

    private void createLeaveDatePicker()
    {
        leaveDate = new DatePicker();

        leaveDate.setPrefSize(super.getWidth() / 6, 37);
        leaveDate.setTranslateX(DEFAULT_X_TRANS + 70);
        leaveDate.setTranslateY(DEFAULT_Y_TRANS + 100);
        leaveDate.setStyle("-fx-font-weight: bold;");
        leaveDate.setPromptText("dd/mm/yyyy");

        super.addComponent(leaveDate);
    }

    private void createReturnDatePicker()
    {
        returnDate = new DatePicker();

        returnDate.setPrefSize(super.getWidth() / 6, 37);
        returnDate.setTranslateX(DEFAULT_X_TRANS + 70);
        returnDate.setTranslateY(DEFAULT_Y_TRANS + 250);
        returnDate.setStyle("-fx-font-weight: bold;");
        returnDate.setPromptText("dd/mm/yyyy");

        super.addComponent(returnDate);
    }

    private void createDestinationLabel()
    {
        Label destination = new Label("Destination:");
        destination.setTranslateX(DEFAULT_X_TRANS - destination.getWidth() / 2);
        destination.setTranslateY(DEFAULT_Y_TRANS + 150);
        destination.setFont(Font.font(32));
        destination.setTextFill(Color.WHITE);
        destination.setStyle("-fx-font-weight: bold;");

        super.addComponent(destination);
    }

    private void createDestinationCityTxtBox()
    {
        destCity = new TextField();
        destCity.setPrefSize(super.getWidth() / 5 + 25, 40);
        destCity.setTranslateX(DEFAULT_X_TRANS - destCity.getWidth() / 2);
        destCity.setTranslateY(DEFAULT_Y_TRANS + destCity.getHeight() + 200);
        destCity.setPromptText("Destination City");
        destCity.setStyle("-fx-font-weight: bold;");
        destCity.setFont(Font.font(18));

        super.addComponent(destCity);
    }

    private void createDestinationStateChoiceBox()
    {
        destState = new ChoiceBox();

        destState.setPrefSize(super.getWidth() / 20, super.getHeight() / 20);
        destState.setItems(stateList);
        destState.setTranslateX(DEFAULT_X_TRANS - destState.getWidth() / 2);
        destState.setTranslateY(DEFAULT_Y_TRANS + destState.getHeight() + 250);
        destState.setValue(stateList.get(48)); // Default to Wisconsin
        destState.setOnAction(e -> ChoiceStateChanged());


        super.addComponent(destState);
    }

    private void createBackButton()
    {
        backBtn = new Button("Back");
        backBtn.setPrefSize(200, 75);
        backBtn.setFont(Font.font(32));
        backBtn.setStyle(PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());

        super.addComponent(backBtn);
    }

    private void createLeaveLabel()
    {
        Label leave = new Label("Leaving From:");
        leave.setTranslateX(DEFAULT_X_TRANS - leave.getWidth() / 2);
        leave.setTranslateY(DEFAULT_Y_TRANS);
        leave.setFont(Font.font(32));
        leave.setTextFill(Color.WHITE);
        leave.setStyle("-fx-font-weight: bold;");

        super.addComponent(leave);
    }

    private void createLeaveCityTxtBox()
    {
        leaveCity = new TextField();
        leaveCity.setPrefSize(super.getWidth() / 5 + 25, 40);
        leaveCity.setTranslateX(DEFAULT_X_TRANS - leaveCity.getWidth() / 2);
        leaveCity.setTranslateY(DEFAULT_Y_TRANS + leaveCity.getHeight() + 50);
        leaveCity.setPromptText("Departure City");
        leaveCity.setStyle("-fx-font-weight: bold;");
        leaveCity.setFont(Font.font(18));

        super.addComponent(leaveCity);
    }

    private void createLeaveStateChoiceBox()
    {
        leaveState = new ChoiceBox();

        leaveState.setPrefSize(super.getWidth() / 20, super.getHeight() / 20);
        leaveState.setItems(stateList);
        leaveState.setTranslateX(DEFAULT_X_TRANS - leaveState.getWidth() / 2);
        leaveState.setTranslateY(DEFAULT_Y_TRANS + leaveState.getHeight() + 100);
        leaveState.setValue(stateList.get(48)); // Default to Wisconsin
        leaveState.setOnAction(e -> ChoiceStateChanged());

        super.addComponent(leaveState);
    }

    private void ChoiceStateChanged()
    {

    }

    private void buttonBackClicked()
    {
        super.returnView();
    }
}
