package newGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SplashScreenController extends Application
{
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
	
	@FXML
	public void buttonExitClicked()
	{
		System.exit(0);
	}
	
	@FXML
	public void buttonOfferRideClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/OfferRide.fxml"))));
	}
	
	@FXML
	public void buttonRequestRideClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/RequestRide.fxml"))));
	}
	
	@FXML
	public void buttonViewRidesClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/ViewRides.fxml"))));
	}
	
	@FXML
	public void buttonLoginSignupClicked(ActionEvent actionEvent)
	{
	
	}
}
