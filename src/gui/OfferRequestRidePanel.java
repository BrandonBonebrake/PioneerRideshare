package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OfferRequestRidePanel {

    private Pane rootPane = null;
    private Button backBtn = null;
    private Stage primaryStage = null;

    public OfferRequestRidePanel(Stage stage) {
        primaryStage = stage;
        rootPane = new Pane();
        createComponents();
    }

    private void createComponents() {
        this.createBackButton();
    }

    private void createBackButton() {
        backBtn = new Button("Back");
        backBtn.setPrefSize(150, 100);
        backBtn.setOnAction(event -> buttonBackClicked());

        rootPane.getChildren().add(backBtn);
    }

    private void buttonBackClicked() {
        SplashScreenPanel splash = new SplashScreenPanel(primaryStage);

        primaryStage.setScene(new Scene(splash.getRootPane(), 1280, 720));
    }

    public Pane getRootPane() {
        return this.rootPane;
    }

}
