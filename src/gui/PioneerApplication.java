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

    @Override
    public void start(Stage primaryStage) throws IOException {

        SplashScreenPanel splash = new SplashScreenPanel(primaryStage);

        primaryStage.setTitle("Pioneer Rideshare");
        primaryStage.setScene(new Scene(splash.getRootPane(), WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}