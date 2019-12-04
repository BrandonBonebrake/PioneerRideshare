package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

final class ForgotPasswordPanel extends DefaultView
{
    private final int DEFAULT_X_COMP = 300;
    private final int DEFAULT_Y_COMP = 300;

    private final int FORGOT_LABEL_X = 130;
    private final int FORGOT_LABEL_Y = 125;

    private final int DEFAULT_COMP_HEIGHT = 40;
    private final int DEFAULT_COMP_WIDTH = 220;

    private final int EMAIL_LABEL_X = DEFAULT_X_COMP - 150;

    private final int SUBMIT_BUTTON_WIDTH = 180;
    private final int SUBMIT_X_TRANS = DEFAULT_X_COMP + 19;

    private TextField emailTextbox;

    private final int DIST_Y_BETWEEN_COMPONENTS = 60; // Default y-dist between components


    private final static String SUBMIT_STYLE = "-fx-background-color: linear-gradient(blue, black)," +
            "linear-gradient(blue, darkBlue), linear-gradient(orange, #ffc266);" +
            "-fx-background-radius: 0; -fx-font-weight: bold; -fx-font-size: 12px;" +
            "-fx-text-fill: blue; -fx-border-color: orange; -fx-border-width: 0;";

    ForgotPasswordPanel(Stage stage, Scene scene, int width, int height)
    {
        super(stage, scene, width, height);

        this.createComponents();
    }

    @Override
    void createComponents()
    {
        this.createBackButton();
        this.createEmailTextbox();
        this.createForgotLabel();
        this.createEmailLabel();
        this.createSubmitButton();
    }

    private void createBackButton()
    {
        Button backBtn = super.createButton("Back", 200, 75,
                0, 0, PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());
    }

    private void createEmailTextbox()
    {
        emailTextbox = super.createTextField("example@uwplatt.edu", DEFAULT_COMP_WIDTH, DEFAULT_COMP_HEIGHT,
                DEFAULT_X_COMP, DEFAULT_Y_COMP);
    }

    private void createForgotLabel()
    {
        Label forgotLabel = super.createLabel("Forgot Password", FORGOT_LABEL_X,
                FORGOT_LABEL_Y);
        forgotLabel.setFont(Font.font(70));
    }

    private void createEmailLabel()
    {
        Label emailLabel = super.createLabel("   Email:", EMAIL_LABEL_X, DEFAULT_Y_COMP);
        emailLabel.setFont(Font.font(24));
    }

    private void createSubmitButton()
    {
        Button submitButton = super.createButton("Submit", SUBMIT_BUTTON_WIDTH, DEFAULT_COMP_HEIGHT,
                SUBMIT_X_TRANS, DEFAULT_Y_COMP + DIST_Y_BETWEEN_COMPONENTS, SUBMIT_STYLE);

        submitButton.setOnAction(event -> submitButtonClicked());
    }

    private void submitButtonClicked()
    {
        super.returnView();
    }

    private void buttonBackClicked()
    {
        super.returnView();
    }
}
