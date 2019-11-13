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
    private Stage stage = null;
    private Pane rootPane = null;
    private Button exitBtn = null;
    private Button offerBtn = null;
    private Button requestBtn = null;
    private Button viewRodesBtn = null;

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
    }

    private void createOfferButton() {
        offerBtn = new Button("Offer Ride");
        offerBtn.setPrefSize(200, 200);
        offerBtn.setTranslateX(200);
        offerBtn.setTranslateY(350);
        offerBtn.setOnAction(event -> buttonOfferClicked());

        rootPane.getChildren().add(offerBtn);
    }

    private void createExitButton() {
        exitBtn = new Button("Exit");
        exitBtn.setPrefSize(150, 100);
        exitBtn.setTranslateX(0);
        exitBtn.setTranslateY(0);
        exitBtn.setOnAction(e -> this.buttonExitClicked());

        rootPane.getChildren().add(exitBtn);
    }

    private void buttonOfferClicked()
    {
        OfferRequestRidePanel offerPanel = new OfferRequestRidePanel(stage);
        System.out.println("Offer");
        Scene scene1 = new Scene(offerPanel.getRootPane(), 1280, 720);
        stage.setScene(scene1);
    }

    private void buttonExitClicked() {
        System.exit(0);
    }

    public Pane getRootPane(){
        return this.rootPane;
    }
}

