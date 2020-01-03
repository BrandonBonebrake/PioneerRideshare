package newGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController
{
	public void buttonBackClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/Login.fxml"))));
	}
	
	public void buttonSubmitClicked(ActionEvent actionEvent) throws IOException
	{
	
	}
}
