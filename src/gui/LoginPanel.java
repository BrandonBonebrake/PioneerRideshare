package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginPanel
{
    Pane pane = null;
    Stage stage = null;
    Button backBtn = null;

    public LoginPanel(Stage primaryStage)
    {
        pane = new Pane();
        stage = primaryStage;

        this.createComponents();
    }

    private void createComponents()
    {
        this.createBackButton();
    }

    private void createBackButton()
    {
        backBtn = new Button("Back");
        backBtn.setPrefSize(150, 100);
        backBtn.setOnAction(event -> buttonBackClicked());

        pane.getChildren().add(backBtn);
    }

    private void buttonBackClicked()
    {
        SplashScreenPanel splash = new SplashScreenPanel(stage);

        stage.setScene(new Scene(splash.getPane(), 1280, 720));
    }

    protected Pane getPane()
    {
        return this.pane;
    }
}
