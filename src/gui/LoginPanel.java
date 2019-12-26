package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import socketCommunication.Client;
import socketCommunication.Packet;
import student.InvalidStudentException;
import student.Student;

final class LoginPanel extends DefaultView
{
	// Global Variables
	
	private TextField emailTextbox;
	private PasswordField passwordTextbox;
	
	private final int DEFAULT_X_COMP = 300;  // Sets components distance from x-axis
	private final int DEFAULT_Y_COMP = 300; // Sets components distance from y-axis
	
	private final int LOGIN_LABEL_X = 270;
	private final int LOGIN_LABEL_Y = 125;
	
	private final int DEFAULT_COMP_HEIGHT = 40;
	private final int DEFAULT_COMP_WIDTH = 220;
	
	private final int SIGNUP_BUTTON_WIDTH = 180;
	private final int SIGNUP_X_TRANS = DEFAULT_X_COMP + 19;
	
	private final int FORGOT_BUTTON_WIDTH = 160;
	private final int FORGOT_X_TRANS = DEFAULT_X_COMP + 28;
	
	private final int DIST_Y_BETWEEN_COMPONENTS = 60; // Default y-dist between components
	
	private final int EMAIL_LABEL_X = DEFAULT_X_COMP - 150;
	
	private final static String SIGNUP_STYLE = "-fx-background-color: linear-gradient(blue, black)," +
			"linear-gradient(blue, darkBlue), linear-gradient(orange, #ffc266);" +
			"-fx-background-radius: 0; -fx-font-weight: bold; -fx-font-size: 12px;" +
			"-fx-text-fill: blue; -fx-border-color: orange; -fx-border-width: 0;";
	
	public LoginPanel(Stage stage, Scene splash, int width, int height)
	{
		super(stage, splash, width, height);
		
		this.createComponents();
	}
	
	void createComponents()
	{
		this.createBackButton();
		this.createEmailTextbox();
		this.createPasswordTextbox();
		this.createLoginButton();
		this.createSignupButton();
		this.createForgotButton();
		this.createLoginLabel();
		this.createEmailLabel();
		this.createPasswordLabel();
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
	
	private void createPasswordTextbox()
	{
		passwordTextbox = super.createPasswordField("Password", DEFAULT_COMP_WIDTH, DEFAULT_COMP_HEIGHT,
				DEFAULT_X_COMP, DEFAULT_Y_COMP + DIST_Y_BETWEEN_COMPONENTS);
	}
	
	private void createLoginButton()
	{
		Button loginButton = super.createButton("Login", DEFAULT_COMP_WIDTH, DEFAULT_COMP_HEIGHT, DEFAULT_X_COMP,
				DEFAULT_Y_COMP + (2 * DIST_Y_BETWEEN_COMPONENTS), SIGNUP_STYLE);
		
		loginButton.setOnAction(event -> buttonLoginClicked());
	}
	
	private void createSignupButton()
	{
		Button signupButton = super.createButton("New? Signup here!", SIGNUP_BUTTON_WIDTH, DEFAULT_COMP_HEIGHT,
				SIGNUP_X_TRANS, DEFAULT_Y_COMP + (3 * DIST_Y_BETWEEN_COMPONENTS), SIGNUP_STYLE);
		
		signupButton.setOnAction(event -> signupButtonClicked());
	}
	
	private void createForgotButton()
	{
		Button forgotPasswordButton = super.createButton("Forgot Password!", FORGOT_BUTTON_WIDTH, DEFAULT_COMP_HEIGHT,
				FORGOT_X_TRANS, DEFAULT_Y_COMP + (4 * DIST_Y_BETWEEN_COMPONENTS), SIGNUP_STYLE);
		
		forgotPasswordButton.setOnAction(event -> forgotButtonClicked());
	}
	
	private void createLoginLabel()
	{
		Label loginLabel = super.createLabel("Login", LOGIN_LABEL_X,
				LOGIN_LABEL_Y);
		loginLabel.setFont(Font.font(100));
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
	
	private void buttonBackClicked()
	{
		super.returnView();
	}
	
	private void buttonLoginClicked()
	{
		try
		{
			Student student = new Student("Place", "Holder", emailTextbox.getText(), passwordTextbox.getText());
			Client client = new Client(new Packet<Student>(student));
			Object objRec = client.receiveObject().getObject(); // Returned Student Object. Use to validate rides
			client.close();
			if(objRec instanceof Student)
			{
				PioneerApplication.studentLoggedIn = (Student) objRec;
				SplashScreenPanel.loginSignupButton.setText("Sign out");
				SplashScreenPanel.viewRidesBtn.setDisable(false);
				SplashScreenPanel.requestBtn.setDisable(false);
				SplashScreenPanel.offerBtn.setDisable(false);
				PopUpPanel.display("Login Successful");
				super.returnView();
			}
		}
		catch (InvalidStudentException ignored) {}
	}
	
	private void signupButtonClicked()
	{
		SignupPanel signupPanel = new SignupPanel(super.getStage(), super.getStage().getScene(),
				super.getWidth(), super.getHeight());
		
		super.changeScene(signupPanel.getPane());
	}
	
	private void forgotButtonClicked()
	{
		ForgotPasswordPanel forgotPasswordPanel = new ForgotPasswordPanel(super.getStage(), super.getStage().getScene(),
				super.getWidth(), super.getHeight());
		
		super.changeScene(forgotPasswordPanel.getPane());
	}
}