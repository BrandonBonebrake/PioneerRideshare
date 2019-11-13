package gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SplashScreenPanel
{
    // Global Constants
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    // Global Variables
    private Stage stage = null;
    private Pane rootPane = null;
    private Button exitBtn = null;
    private Button offerBtn = null;
    private Button requestBtn = null;
    private Button viewRidesBtn = null;

    public SplashScreenPanel(Stage primaryStage)
    {
       super();
       stage = primaryStage;
       rootPane = new Pane();

       this.createComponents();
    }


    private void createComponents() {
        this.createExitButton();
        this.createOfferButton();
        this.createRequestButton();
    }

    private void createOfferButton() {
        offerBtn = new Button("Offer Ride");
        offerBtn.setPrefSize(200, 200);
        offerBtn.setTranslateX(200);
        offerBtn.setTranslateY(350);
        offerBtn.setOnAction(event -> buttonOfferClicked());

        rootPane.getChildren().add(offerBtn);
    }

    private void createRequestButton() {
        requestBtn = new Button("Request Ride");
        requestBtn.setPrefSize(200, 200);
        requestBtn.setTranslateX(450);
        requestBtn.setTranslateY(350);
        requestBtn.setOnAction(e -> buttonRequestClicked());

        rootPane.getChildren().add(requestBtn);
    }

    private void createExitButton() {
        exitBtn = new Button("Exit");
        exitBtn.setPrefSize(150, 100);
        exitBtn.setTranslateX(0);
        exitBtn.setTranslateY(0);
        exitBtn.setOnAction(e -> this.buttonExitClicked());

        rootPane.getChildren().add(exitBtn);
    }

    private void buttonRequestClicked() {
        OfferRequestRidePanel requestPanel = new OfferRequestRidePanel(stage);

        stage.setScene(new Scene(requestPanel.getRootPane(), WIDTH, HEIGHT));
    }

    private void buttonOfferClicked() {
        OfferRequestRidePanel offerPanel = new OfferRequestRidePanel(stage);

        stage.setScene(new Scene(offerPanel.getRootPane(), WIDTH, HEIGHT));
    }

    private void buttonExitClicked() {
        System.exit(0);
    }

    public Pane getRootPane(){
        return this.rootPane;
    }
}