package gui;

import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class creates the splash screen and the components
 * that make up the Pane. The calling class is responsible
 * for adding the Pane of this class to its view to change
 * the scene so that this view can be interacted with.
 *
 * @author Brandon Bonebrake
 */
final class SplashScreenPanel extends DefaultView
{
    // Global Constants
    private final int DEFAULT_X_TRANS = 400;
    private final int DEFAULT_Y_TRANS = 300;

    SplashScreenPanel(Stage primaryStage, int width, int height)
    {
        super(primaryStage, null, width, height);

        this.createComponents();
    }

    void createComponents()
    {
        this.createExitButton();
        this.createOfferButton();
        this.createRequestButton();
        this.createLoginSignupButton();
        this.createViewRidesButton();
    }

    private void createViewRidesButton()
    {
        Button viewRidesBtn = super.createButton("View Rides", 650, 150,
                DEFAULT_X_TRANS - 650 / 2, DEFAULT_Y_TRANS + 125,
                PioneerApplication.VIEW_RIDES_STYLE);
        viewRidesBtn.setOnAction(e -> this.buttonViewRidesClicked());
    }

    private void createLoginSignupButton()
    {
        Button loginSignupButton = super.createButton("Login/Signup", 100, 25,
                super.getWidth() - 100, 0,
                PioneerApplication.LOGIN_SIGNUP_STYLE);
        loginSignupButton.setOnAction(e -> this.buttonLoginSignupClicked());
    }

    private void createOfferButton()
    {
        Button offerBtn = super.createButton("Offer\n Ride", 300, 300,
                DEFAULT_X_TRANS - 325, DEFAULT_Y_TRANS - 200,
                PioneerApplication.OFFER_REQUEST_STYLE);
        offerBtn.setOnAction(event -> buttonOfferClicked());
    }

    private void createRequestButton()
    {
        Button requestBtn = super.createButton("Request\n   Ride", 300, 300,
                DEFAULT_X_TRANS + 25, DEFAULT_Y_TRANS - 200,
                PioneerApplication.OFFER_REQUEST_STYLE);
        requestBtn.setOnAction(e -> buttonRequestClicked());
    }

    private void createExitButton()
    {
        // Global Variables
        Button exitBtn = super.createButton("Exit", 200, 75,
                0, 0, PioneerApplication.EXIT_STYLE);
        exitBtn.setOnAction(e -> this.buttonExitClicked());
    }

    private void buttonViewRidesClicked()
    {
        RideListingPanel ridePanel = new RideListingPanel(super.getStage(), super.getStage().getScene(),
                                             super.getWidth(), super.getHeight());
        super.changeScene(ridePanel.getPane());
    }

    private void buttonLoginSignupClicked()
    {
        LoginPanel loginPanel = new LoginPanel(super.getStage(), super.getStage().getScene(),
                super.getWidth(), super.getHeight());

        super.changeScene(loginPanel.getPane());
    }

    private void buttonRequestClicked()
    {
        OfferRequestRidePanel requestPanel = new OfferRequestRidePanel(super.getStage(), super.getStage().getScene(),
                super.getWidth(), super.getHeight(),
                "Request Ride");

        super.changeScene(requestPanel.getPane());
    }

    private void buttonOfferClicked()
    {
        OfferRequestRidePanel offerPanel = new OfferRequestRidePanel(super.getStage(), super.getStage().getScene(),
                super.getWidth(), super.getHeight(),
                "Offer Ride");
        
        super.changeScene(offerPanel.getPane());
    }

    private void buttonExitClicked()
    {
        System.exit(0);
    }
}