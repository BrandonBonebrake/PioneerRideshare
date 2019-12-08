package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

final class SignupPanel extends DefaultView
{
    private TextField emailTextbox;
    private TextField passwordTextbox;
    private TextField passwordTextbox2;

    private final int SIGNUP_LABEL_X = 250;
    private final int SIGNUP_LABEL_Y = 125;

    private final int DEFAULT_X_COMP = 300;  // Sets components distance from x-axis
    private final int DEFAULT_Y_COMP = 300; // Sets components distance from y-axis

    private final int INPUT_TEXTBOX_HEIGHT = 40;
    private final int INPUT_TEXTBOX_WIDTH = 220;

    private final int DIST_Y_BETWEEN_COMPONENTS = 60;

    private final int EMAIL_LABEL_X = DEFAULT_X_COMP - 150;
    private final int REENTER_PASSWORD_LABEL_X = EMAIL_LABEL_X - 70;

    private final String VALID_FIELD = "-fx-control-inner-background: white; -fx-font-weight: bold;";
    private final String INVALID_FIELD = "-fx-control-inner-background: red; -fx-font-weight: bold;";

    /**
     *
     *
     * @param stage  The stage that was created at runtime
     * @param scene  The previous scene that can be set back to
     * @param width  The width of the application window
     * @param height The height of the application window
     */
    SignupPanel(Stage stage, Scene scene, int width, int height)
    {
        super(stage, scene, width, height);
        this.createComponents();
    }

    @Override
    void createComponents()
    {
        this.createBackButton();
        this.createSignupLabel();
        this.createEmailTextbox();
        this.createPasswordTextbox();
        this.createPasswordTextbox2();
        this.createEmailLabel();
        this.createPasswordLabel();
        this.createPasswordLabel2();
        this.createSubmitButton();
    }

    private void createBackButton()
    {
        Button backBtn = super.createButton("Back", 200, 75,
                0, 0, PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());
    }

    private void createSignupLabel()
    {
        Label signupLabel = super.createLabel("Signup", SIGNUP_LABEL_X,
                SIGNUP_LABEL_Y);
        signupLabel.setFont(Font.font(100));
    }

    private void createEmailTextbox()
    {
        emailTextbox = super.createTextField("example@uwplatt.edu", INPUT_TEXTBOX_WIDTH, INPUT_TEXTBOX_HEIGHT,
                DEFAULT_X_COMP, DEFAULT_Y_COMP);

    }

    private void createPasswordTextbox()
    {
        passwordTextbox = super.createTextField("Password", INPUT_TEXTBOX_WIDTH, INPUT_TEXTBOX_HEIGHT,
                DEFAULT_X_COMP, DEFAULT_Y_COMP + DIST_Y_BETWEEN_COMPONENTS);
    }

    private void createPasswordTextbox2()
    {
        passwordTextbox2 = super.createTextField("Password", INPUT_TEXTBOX_WIDTH, INPUT_TEXTBOX_HEIGHT,
                DEFAULT_X_COMP, DEFAULT_Y_COMP + (2 * DIST_Y_BETWEEN_COMPONENTS));
    }

    private void createSubmitButton()
    {
        Button submitButton = super.createButton("Submit", INPUT_TEXTBOX_WIDTH, INPUT_TEXTBOX_HEIGHT,
                DEFAULT_X_COMP, DEFAULT_Y_COMP + (3 * DIST_Y_BETWEEN_COMPONENTS), PioneerApplication.EXIT_STYLE);
        submitButton.setOnAction(event -> submitButtonClicked());
    }

    private void createEmailLabel()
    {
        Label emailLabel = super.createLabel("   Email:", EMAIL_LABEL_X, DEFAULT_Y_COMP);
        emailLabel.setFont(Font.font(24));
    }

    private void createPasswordLabel()
    {
        Label passwordLabel = super.createLabel("Password:", EMAIL_LABEL_X,
                DEFAULT_Y_COMP +  DIST_Y_BETWEEN_COMPONENTS);
        passwordLabel.setFont(Font.font(24));
    }

    private void createPasswordLabel2()
    {
        Label passwordLabel2 = super.createLabel("Reenter Password:", REENTER_PASSWORD_LABEL_X,
                DEFAULT_Y_COMP + (2 * DIST_Y_BETWEEN_COMPONENTS));
        passwordLabel2.setFont(Font.font(24));
    }

    private void buttonBackClicked()
    {
        super.returnView();
    }

    private void submitButtonClicked()
    {
        if(emailTextbox.getText().toLowerCase().contains("@uwplatt.edu") &&
           passwordTextbox.getText().equals(passwordTextbox2.getText()))
        {
            this.emailTextbox.setStyle(VALID_FIELD);
            this.passwordTextbox.setStyle(VALID_FIELD);
            this.passwordTextbox2.setStyle(VALID_FIELD);
            PopUpPanel.display("Success");
            super.returnView();
        }
        else if(!(emailTextbox.getText().toLowerCase().contains("@uwplatt.edu")) &&
                passwordTextbox.getText().equals(passwordTextbox2.getText()))
        {
            this.emailTextbox.setStyle(INVALID_FIELD);
        }
        else if(emailTextbox.getText().toLowerCase().contains("@uwplatt.edu") &&
                !(passwordTextbox.getText().equals(passwordTextbox2.getText())))
        {
            this.passwordTextbox.setStyle(INVALID_FIELD);
            this.passwordTextbox2.setStyle(INVALID_FIELD);
        }
        else
        {
            this.emailTextbox.setStyle(INVALID_FIELD);
            this.passwordTextbox.setStyle(INVALID_FIELD);
            this.passwordTextbox2.setStyle(INVALID_FIELD);
        }
    }
}
