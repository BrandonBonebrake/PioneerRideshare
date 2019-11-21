package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import location.InvalidLocationException;
import student.InvalidStudentException;
import time.InvalidTimeException;

final class SplashScreenPanel extends DefaultView
{
    // Global Constants
    private final int DEFAULT_X_TRANS = 400;
    private final int DEFAULT_Y_TRANS = 300;

    // Global Variables
    private Button exitBtn;
    private Button offerBtn;
    private Button requestBtn;
    private Button viewRidesBtn;
    private Button loginSignupButton;

    private RideListingPanel ridePanel;
    private LoginPanel loginPanel;
    private OfferRequestRidePanel requestPanel;
    private OfferRequestRidePanel offerPanel;

    public SplashScreenPanel(Stage primaryStage, int width, int height)
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
        final int SIZE_X = 650;
        final int SIZE_Y = 150;

        viewRidesBtn = new Button("View Rides");
        viewRidesBtn.setPrefSize(SIZE_X, SIZE_Y);
        viewRidesBtn.setTranslateX(DEFAULT_X_TRANS - SIZE_X / 2);
        viewRidesBtn.setTranslateY(DEFAULT_Y_TRANS + 125);
        viewRidesBtn.setStyle(PioneerApplication.VIEW_RIDES_STYLE);
        viewRidesBtn.setOnAction(e -> this.buttonViewRidesClicked());

        super.addComponent(viewRidesBtn);
    }

    private void createLoginSignupButton()
    {
        final int SIZE_X = 100;
        final int SIZE_Y = 25;

        loginSignupButton = new Button("Login/Signup");
        loginSignupButton.setPrefSize(SIZE_X, SIZE_Y);
        loginSignupButton.setFont(Font.font(24));
        loginSignupButton.setTranslateX(super.getWidth() - SIZE_X);
        loginSignupButton.setTranslateY(0);
        loginSignupButton.setStyle(PioneerApplication.LOGIN_SIGNUP_STYLE);
        loginSignupButton.setOnAction(e -> this.buttonLoginSignupClicked());

        super.addComponent(loginSignupButton);
    }

    private void createOfferButton()
    {
        final int SIZE_X = 300;
        final int SIZE_Y = 300;

        offerBtn = new Button("Offer\n Ride");
        offerBtn.setPrefSize(SIZE_X, SIZE_Y);
        offerBtn.setFont(Font.font(32));
        offerBtn.setTranslateX(DEFAULT_X_TRANS - SIZE_X - 25);
        offerBtn.setTranslateY(DEFAULT_Y_TRANS - SIZE_Y / 2 - 50);
        offerBtn.setStyle(PioneerApplication.OFFER_REQUEST_STYLE);
        offerBtn.setOnAction(event -> buttonOfferClicked());

        super.addComponent(offerBtn);
    }

    private void createRequestButton()
    {
        final int SIZE_X = 300;
        final int SIZE_Y = 300;

        requestBtn = new Button("Request\n   Ride");
        requestBtn.setPrefSize(SIZE_X, SIZE_Y);
        requestBtn.setFont(Font.font(32));
        requestBtn.setTranslateX(DEFAULT_X_TRANS + 25);
        requestBtn.setTranslateY(DEFAULT_Y_TRANS - SIZE_Y / 2 - 50);
        requestBtn.setStyle(PioneerApplication.OFFER_REQUEST_STYLE);
        requestBtn.setOnAction(e -> buttonRequestClicked());

        super.addComponent(requestBtn);
    }

    private void createExitButton()
    {
        exitBtn = new Button("Exit");
        exitBtn.setPrefSize(200, 75);
        exitBtn.setFont(Font.font(32));
        exitBtn.setStyle(PioneerApplication.EXIT_STYLE);
        exitBtn.setOnAction(e -> this.buttonExitClicked());

        super.addComponent(exitBtn);
    }

    private void buttonViewRidesClicked()
    {
        try
        {
            ridePanel = new RideListingPanel(super.getStage(), super.getStage().getScene(),
                                             super.getWidth(), super.getHeight());
        }
        catch (InvalidLocationException e)
        {
            e.printStackTrace();
        }
        catch (InvalidStudentException e)
        {
            e.printStackTrace();
        } catch (InvalidTimeException e)
        {
            e.printStackTrace();
        }

        super.changeScene(ridePanel.getPane());
    }

    private void buttonLoginSignupClicked()
    {
        loginPanel = new LoginPanel(super.getStage(), super.getStage().getScene(),
                                    super.getWidth(), super.getHeight());

        super.changeScene(loginPanel.getPane());
    }

    private void buttonRequestClicked()
    {
        requestPanel = new OfferRequestRidePanel(super.getStage(), super.getStage().getScene(),
                                                 super.getWidth(), super.getHeight(),
                                                 "Request Ride");

        super.changeScene(requestPanel.getPane());
    }

    private void buttonOfferClicked()
    {
        offerPanel = new OfferRequestRidePanel(super.getStage(), super.getStage().getScene(),
                                               super.getWidth(), super.getHeight(),
                                               "Offer Ride");

        super.changeScene(offerPanel.getPane());
    }

    private void buttonExitClicked()
    {
        System.exit(0);
    }
}