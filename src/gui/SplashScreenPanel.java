package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

final class SplashScreenPanel extends DefaultView
{
    // Global Constants


    // Global Variables
    private Button exitBtn = null;
    private Button offerBtn = null;
    private Button requestBtn = null;
    private Button viewRidesBtn = null;
    private Button loginSignupButton = null;

    private RideListingPanel ridePanel = null;
    private LoginPanel loginPanel = null;
    private OfferRequestRidePanel requestPanel = null;
    private OfferRequestRidePanel offerPanel = null;

    public SplashScreenPanel(Stage primaryStage, int width, int height)
    {
       super(primaryStage, null, width, height);

        this.createComponents();
    }

    void createComponents() {
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
        viewRidesBtn.setTranslateX(super.getWidth() / 2 - SIZE_X / 2);
        viewRidesBtn.setTranslateY(super.getHeight() / 3 * 2);
        viewRidesBtn.setStyle(PioneerApplication.VIEW_RIDES_STYLE);
        viewRidesBtn.setOnAction(e -> this.buttonViewRidesClicked());

        super.getPane().getChildren().add(viewRidesBtn);
    }

    private void createLoginSignupButton() {
        final int SIZE_X = 100;
        final int SIZE_Y = 25;

        loginSignupButton = new Button("Login/Signup");
        loginSignupButton.setPrefSize(SIZE_X, SIZE_Y);
        loginSignupButton.setFont(Font.font(24));
        loginSignupButton.setTranslateX(super.getWidth() - SIZE_X);
        loginSignupButton.setTranslateY(0);
        loginSignupButton.setStyle(PioneerApplication.LOGIN_SIGNUP_STYLE);
        loginSignupButton.setOnAction(e -> this.buttonLoginSignupClicked());

        super.getPane().getChildren().add(loginSignupButton);
    }

    private void createOfferButton() {
        final int SIZE_X = 300;
        final int SIZE_Y = 300;

        offerBtn = new Button("Offer\n Ride");
        offerBtn.setPrefSize(SIZE_X, SIZE_Y);
        offerBtn.setFont(Font.font(32));
        offerBtn.setTranslateX(super.getWidth() / 2 - SIZE_X - 25);
        offerBtn.setTranslateY(super.getHeight() / 2 - SIZE_Y / 2 - 50);
        offerBtn.setStyle(PioneerApplication.OFFER_REQUEST_STYLE);
        offerBtn.setOnAction(event -> buttonOfferClicked());

        super.getPane().getChildren().add(offerBtn);
    }

    private void createRequestButton() {
        final int SIZE_X = 300;
        final int SIZE_Y = 300;

        requestBtn = new Button("Request\n   Ride");
        requestBtn.setPrefSize(SIZE_X, SIZE_Y);
        requestBtn.setFont(Font.font(32));
        requestBtn.setTranslateX(super.getWidth() / 2 + 25);
        requestBtn.setTranslateY(super.getHeight() / 2 - SIZE_Y / 2 - 50);
        requestBtn.setStyle(PioneerApplication.OFFER_REQUEST_STYLE);
        requestBtn.setOnAction(e -> buttonRequestClicked());

        super.getPane().getChildren().add(requestBtn);
    }

    private void createExitButton() {
        exitBtn = new Button("Exit");
        exitBtn.setPrefSize(200, 75);
        exitBtn.setFont(Font.font(32));
        exitBtn.setTranslateX(0);
        exitBtn.setTranslateY(0);
        exitBtn.setStyle(PioneerApplication.EXIT_STYLE);
        exitBtn.setOnAction(e -> this.buttonExitClicked());

        super.getPane().getChildren().add(exitBtn);
    }

    private void buttonViewRidesClicked()
    {
        ridePanel = new RideListingPanel(super.getStage(), super.getStage().getScene(),
                                         super.getWidth(), super.getHeight());

        super.getStage().setScene(new Scene(ridePanel.getPane(), super.getWidth(), super.getHeight()));
    }

    private void buttonLoginSignupClicked()
    {
        loginPanel = new LoginPanel(super.getStage(), super.getStage().getScene(),
                                    super.getWidth(), super.getHeight());

        super.getStage().setScene(new Scene(loginPanel.getPane(), super.getWidth(), super.getHeight()));
    }

    private void buttonRequestClicked()
    {
        requestPanel = new OfferRequestRidePanel(super.getStage(), super.getStage().getScene(),
                                                 super.getWidth(), super.getHeight());

        super.getStage().setScene(new Scene(requestPanel.getPane(), super.getWidth(), super.getHeight()));
    }

    private void buttonOfferClicked()
    {
        offerPanel = new OfferRequestRidePanel(super.getStage(), super.getStage().getScene(),
                                               super.getWidth(), super.getHeight());

        super.getStage().setScene(new Scene(offerPanel.getPane(), super.getWidth(), super.getHeight()));
    }

    private void buttonExitClicked()
    {
        System.exit(0);
    }
}