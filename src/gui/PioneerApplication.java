package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PioneerApplication extends Application
{
    // Global static constants
    protected final static String BACKGROUND_STYLE = "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, blue 0%, #903b12 100%)," +
            "#000080,"+
            "#d86e3a," +
            "radial-gradient(center 50% 50%, radius 100%, blue, #000080); " +
            "-fx-background-radius: 0;";
    protected final static String EXIT_STYLE = "-fx-background-color: linear-gradient(blue, black)," +
            "linear-gradient(blue, darkBlue), linear-gradient(orange, #ffc266);" +
            "-fx-background-radius: 0; -fx-font-weight: bold; -fx-font-size: 30px;" +
            "-fx-text-fill: blue; -fx-border-color: orange; -fx-border-width: 0;";
    protected final static String OFFER_REQUEST_STYLE = "-fx-background-color: linear-gradient(darkBlue, blue)," +
            "linear-gradient(blue, blue)," +
            "linear-gradient(orange, #ffc266);" +
            "-fx-background-radius: 50;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 45px;" +
            "-fx-text-fill: blue;";
    protected final static String VIEW_RIDES_STYLE = "-fx-background-color: linear-gradient(darkBlue, blue), linear-gradient(blue, blue)," +
            "linear-gradient(#ffc266, orange); -fx-background-radius: 50; -fx-font-weight: bold;" +
            "-fx-font-size: 45px; -fx-text-fill: blue;";
    protected final static String LOGIN_SIGNUP_STYLE = "-fx-background-color: transparent; -fx-background-radius: 0; -fx-font-weight: bold;" +
            "-fx-font-size: 12px; -fx-text-fill: white;";

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