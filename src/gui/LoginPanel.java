package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginPanel
{
    private int width = 1280;
    private int height = 720;

    private Pane pane = null;
    private Stage stage = null;
    private Scene scene = null;
    private Button backBtn = null;

    public LoginPanel(Stage primaryStage, Scene splash, int width, int height)
    {
        this.width = width;
        this.height = height;

        pane = new Pane();
        pane.setStyle(PioneerApplication.BACKGROUND_STYLE);

        stage = primaryStage;
        scene = splash;

        this.createComponents();
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
        backBtn.setStyle(PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());

        pane.getChildren().add(backBtn);
    }

    private void buttonBackClicked()
    {
        //SplashScreenPanel splash = new SplashScreenPanel(stage, width, height);

        stage.setScene(scene);
    }

    protected Pane getPane()
    {
        return this.pane;
    }
}
