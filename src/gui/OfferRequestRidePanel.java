package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OfferRequestRidePanel
{
    private int width = 1280;
    private int height = 720;

    private Pane pane = null;
    private Button backBtn = null;
    private Stage primaryStage = null;
    private Scene scene = null;

    public OfferRequestRidePanel(Stage stage, Scene splash, int width, int height)
    {
        this.width = width;
        this.height = height;

        primaryStage = stage;
        pane = new Pane();
        scene = splash;

        createComponents();
    }

    private void createComponents()
    {
        this.createBackButton();
    }

    private void createBackButton()
    {
        backBtn = new Button("Back");
        backBtn.setPrefSize(200, 75);
        backBtn.setFont(Font.font(32));
        backBtn.setOnAction(event -> buttonBackClicked());

        pane.getChildren().add(backBtn);
    }

    private void buttonBackClicked()
    {
        //SplashScreenPanel splash = new SplashScreenPanel(primaryStage, width, height);

        primaryStage.setScene(scene);
    }

    public Pane getPane()
    {
        return this.pane;
    }

}
