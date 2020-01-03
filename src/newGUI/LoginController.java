package newGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import socketCommunication.Client;
import socketCommunication.Packet;
import student.InvalidStudentException;
import student.Student;

import java.io.IOException;

public class LoginController
{
	@FXML private TextField     txtfieldEmail;
	@FXML private PasswordField txtfieldPassword;
	
	String VALID_FIELD   = "-fx-control-inner-background: white; -fx-font-weight: bold;";
	String INVALID_FIELD = "-fx-control-inner-background: red; -fx-font-weight: bold;";
	
	@FXML
	private void buttonLoginClicked(ActionEvent actionEvent)
	{
		try
		{
			Client client = new Client(new Packet<>(new Student( "First", "Last", txtfieldEmail.getText(), txtfieldPassword.getText())));
			txtfieldEmail.setStyle(VALID_FIELD);
			txtfieldPassword.setStyle(VALID_FIELD);
			HeldData.student = (Student) client.receiveObject().getObject();
			this.buttonBackClicked(actionEvent);
		}
		catch (InvalidStudentException | IOException e)
		{
			if (e.getMessage().contains("Invalid Email:"))
			{
				txtfieldEmail.setStyle(INVALID_FIELD);
			}
			else if (e.getMessage().contains("Invalid Password:"))
			{
				txtfieldPassword.setStyle(INVALID_FIELD);
			}
			else
			{
				txtfieldEmail.setStyle(VALID_FIELD);
				txtfieldPassword.setStyle(VALID_FIELD);
			}
		}
	}
	
	@FXML
	private void buttonSignupClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/Signup.fxml"))));
	}
	
	@FXML
	private void buttonForgotPasswordClicked(ActionEvent actionEvent)
	{
	
	}
	
	@FXML
	private void buttonBackClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/SplashScreen.fxml"))));
	}
}