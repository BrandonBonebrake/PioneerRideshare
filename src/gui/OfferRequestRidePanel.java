package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

final class OfferRequestRidePanel extends DefaultView
{
    // Global Variables
    private Button backBtn;
    private TextField leaveCity;
    private ChoiceBox leaveState;

    public OfferRequestRidePanel(Stage stage, Scene splash, int width, int height)
    {
        super(stage, splash, width, height);

        createComponents();
    }

    void createComponents()
    {
        this.createBackButton();
        this.createLeaveLabel();
        this.createLeaveCityTxtBox();
        this.createLeaveStateChioceBox();
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
        leave.setTranslateX(super.getWidth() / 7 - leave.getWidth() / 2);
        leave.setTranslateY(super.getHeight() / 3);
        leave.setFont(Font.font(32));
        leave.setTextFill(Color.WHITE);
        leave.setStyle("-fx-font-weight: bold;");

        super.addComponent(leave);
    }

    private void createLeaveCityTxtBox()
    {
        leaveCity = new TextField();
        leaveCity.setPrefSize(super.getWidth() / 5, 40);
        leaveCity.setTranslateX(super.getWidth() / 7 - leaveCity.getWidth() / 2);
        leaveCity.setTranslateY(super.getHeight() / 3 + leaveCity.getHeight() + 50);
        leaveCity.setPromptText("Departure City");
        leaveCity.setStyle("-fx-font-weight: bold;");
        leaveCity.setFont(Font.font(18));

        super.addComponent(leaveCity);
    }

    private void createLeaveStateChioceBox()
    {
        leaveState = new ChoiceBox();

        ObservableList<String> stateList = FXCollections.observableArrayList();

        stateList.addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
                "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
                "NY", "NC" ,"ND", "OH", "OK", "OR", "PA", "RI" ,"SC", "SD", "TN", "TX", "UT", "VT", "WA", "WV", "WI", "WY");

        leaveState.setPrefSize(super.getWidth() / 20, super.getHeight() / 20);
        leaveState.setItems(stateList);
        leaveState.setTranslateX(super.getWidth() / 7 - leaveState.getWidth() / 2);
        leaveState.setTranslateY(super.getHeight() / 3 + leaveState.getHeight() + 100);

        super.addComponent(leaveState);
    }

    private void buttonBackClicked()
    {
        super.getStage().setScene(super.getScene());
    }
}
