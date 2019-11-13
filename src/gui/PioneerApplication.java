package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PioneerApplication extends Application
{
    // Global Constants
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    // Global Variables
    private Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
                Button login = new Button("Login/Signup");
        login.setOnAction(e -> buttonLoginSignupClicked());

        StackPane root = new StackPane();
        root.getChildren().add(login);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("Pioneer Rideshare");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void buttonLoginSignupClicked() {
        System.out.println("Login/Signup");

        SplashScreenPanel splash = new SplashScreenPanel(stage);

        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);

        Label log = new Label("Login");

        TextField tField = new TextField();
        tField.setPromptText("Enter username");

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> System.exit(0));

        box.getChildren().addAll(log, tField, loginBtn);

        Scene scene1 = new Scene(splash.getRootPane(), WIDTH, HEIGHT);
        stage.setScene(scene1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
