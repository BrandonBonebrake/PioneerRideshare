package newGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController extends Application implements Initializable
{
	@FXML private Button btnOfferRide;
	@FXML private Button btnRequestRide;
	@FXML private Button btnViewRides;
	@FXML private Button btnLoginSignup;
	
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/SplashScreen.fxml"));
		
		primaryStage.setTitle("Pioneer Ride Share");
		primaryStage.setScene(new Scene(root, 800, 620));
		primaryStage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		if(HeldData.student != null)
		{
			btnLoginSignup.setText("Sign out");
			
			btnOfferRide.setDisable(false);
			btnRequestRide.setDisable(false);
			btnViewRides.setDisable(false);
		}
		else
		{
			btnOfferRide.setDisable(true);
			btnRequestRide.setDisable(true);
			btnViewRides.setDisable(true);
		}
	}
	
	@FXML
	private void buttonExitClicked()
	{
		System.exit(0);
	}
	
	@FXML
	private void buttonOfferRideClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/OfferRide.fxml"))));
	}
	
	@FXML
	private void buttonRequestRideClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/RequestRide.fxml"))));
	}
	
	@FXML
	private void buttonViewRidesClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/ViewRides.fxml"))));
	}
	
	@FXML
	private void buttonLoginSignupClicked(ActionEvent actionEvent) throws IOException
	{
		if(HeldData.student == null)
		{
			((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/Login.fxml"))));
		}
		else
		{
			HeldData.student = null;
			btnLoginSignup.setText("Login/Signup");
			
			btnOfferRide.setDisable(true);
			btnRequestRide.setDisable(true);
			btnViewRides.setDisable(true);
		}
	}
}
