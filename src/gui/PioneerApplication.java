package gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class PioneerApplication extends Application
{
    // Global Variables

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Parent root = FXMLLoader.load(getClass().getResource("pioneerApplication.fxml"));
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("pioneerApplication.fxml")));

        // Change the properties of the scene here
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        // Change the properties of the stage here
        primaryStage.setTitle("Pioneer Rideshare");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void buttonExitClicked() {
        System.exit(0);
    }

    @FXML
    public void buttonOfferClicked(){
        System.out.println("Ride Offer");
    }

    @FXML
    public void buttonRequestClicked(){
        System.out.println("Ride Request");
    }

    @FXML
    public void buttonViewRidesClicked() {
        System.out.println("View Rides");
    }

    @FXML
    public void buttonLoginSignupClicked() {
        System.out.println("Login/Signup");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
