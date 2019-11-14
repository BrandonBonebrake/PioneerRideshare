package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PioneerApplication extends Application
{
    // Global Constants
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private final String NAME = "Pioneer Rideshare";

    @Override
    public void start(Stage primaryStage)
    {
        SplashScreenPanel splash = new SplashScreenPanel(primaryStage, WIDTH, HEIGHT);

        primaryStage.setTitle(NAME);
        primaryStage.setScene(new Scene(splash.getPane(), WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}