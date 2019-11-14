package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListingPanel
{

    Pane pane = null;
    Stage stage = null;
    Button backBtn;

    public ListingPanel(Stage PrimaryStage)
    {
        this.stage = PrimaryStage;
        pane = new Pane();

        this.createComponents();
    }

    private void createComponents()
    {
        backBtn = new Button("Back");
        backBtn.setPrefSize(150, 100);
        backBtn.setOnAction(event -> buttonBackClicked());

        pane.getChildren().add(backBtn);
    }

    private void buttonBackClicked()
    {
        SplashScreenPanel splash = new SplashScreenPanel(stage, 1280, 720);

        stage.setScene(new Scene(splash.getPane(), 1280, 720));
    }


}
