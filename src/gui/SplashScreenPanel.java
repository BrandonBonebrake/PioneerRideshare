package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SplashScreenPanel
{
    // Global Constants
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    // Global Variables
    private Stage stage = null;
    private Pane pane = null;
    private Button exitBtn = null;
    private Button offerBtn = null;
    private Button requestBtn = null;
    private Button viewRidesBtn = null;
    private Button loginSignupButton = null;

    public SplashScreenPanel(Stage primaryStage) {
       super();
       stage = primaryStage;
       pane = new Pane();

       this.createComponents();
    }


    private void createComponents() {
        this.createExitButton();
        this.createOfferButton();
        this.createRequestButton();
        this.createLoginSignupButton();
        this.createViewRidesButton();
    }

    private void createViewRidesButton() {
        final int SIZE_X = 650;
        final int SIZE_Y = 150;

        viewRidesBtn = new Button("View Rides");
        viewRidesBtn.setPrefSize(SIZE_X, SIZE_Y);
        viewRidesBtn.setTranslateX(WIDTH / 2 - SIZE_X / 2);
        viewRidesBtn.setTranslateY(HEIGHT / 3 * 2);
        viewRidesBtn.setOnAction(e -> this.buttonViewRidesClicked());

        pane.getChildren().add(viewRidesBtn);
    }

    private void createLoginSignupButton() {
        final int SIZE_X = 200;
        final int SIZE_Y = 75;

        loginSignupButton = new Button("Login/Signup");
        loginSignupButton.setPrefSize(SIZE_X, SIZE_Y);
        loginSignupButton.setTranslateX(WIDTH - SIZE_X);
        loginSignupButton.setTranslateY(0);
        loginSignupButton.setOnAction(e -> this.buttonLoginSignupClicked());

        pane.getChildren().add(loginSignupButton);
    }

    private void createOfferButton() {
        final int SIZE_X = 300;
        final int SIZE_Y = 300;

        offerBtn = new Button("Offer Ride");
        offerBtn.setPrefSize(SIZE_X, SIZE_Y);
        offerBtn.setTranslateX(WIDTH / 2 - SIZE_X - 25);
        offerBtn.setTranslateY(HEIGHT / 2 - SIZE_Y / 2 - 50);
        offerBtn.setOnAction(event -> buttonOfferClicked());

        pane.getChildren().add(offerBtn);
    }

    private void createRequestButton() {
        final int SIZE_X = 300;
        final int SIZE_Y = 300;

        requestBtn = new Button("Request Ride");
        requestBtn.setPrefSize(SIZE_X, SIZE_Y);
        requestBtn.setTranslateX(WIDTH / 2 + 25);
        requestBtn.setTranslateY(HEIGHT / 2 - SIZE_Y / 2 - 50);
        requestBtn.setOnAction(e -> buttonRequestClicked());

        pane.getChildren().add(requestBtn);
    }

    private void createExitButton() {
        exitBtn = new Button("Exit");
        exitBtn.setPrefSize(200, 75);
        exitBtn.setTranslateX(0);
        exitBtn.setTranslateY(0);
        exitBtn.setOnAction(e -> this.buttonExitClicked());

        pane.getChildren().add(exitBtn);
    }

    private void buttonViewRidesClicked() {

    }

    private void buttonLoginSignupClicked() {
        LoginPanel loginPanel = new LoginPanel(stage);

        stage.setScene(new Scene(loginPanel.getPane(), WIDTH, HEIGHT));
    }

    private void buttonRequestClicked() {
        OfferRequestRidePanel requestPanel = new OfferRequestRidePanel(stage);

        stage.setScene(new Scene(requestPanel.getPane(), WIDTH, HEIGHT));
    }

    private void buttonOfferClicked() {
        OfferRequestRidePanel offerPanel = new OfferRequestRidePanel(stage);

        stage.setScene(new Scene(offerPanel.getPane(), WIDTH, HEIGHT));
    }

    private void buttonExitClicked() {
        System.exit(0);
    }

    public Pane getPane(){
        return this.pane;
    }
}