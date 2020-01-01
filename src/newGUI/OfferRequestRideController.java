package newGUI;

import date.InvalidDateException;
import date.PioneerDate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import location.InvalidLocationException;
import location.Location;
import ride.Ride;
import ride.RideOffer;
import ride.RideRequest;
import time.InvalidTimeException;
import time.PioneerTime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class OfferRequestRideController implements Initializable
{
	// Global Variables Created In FXML
	@FXML DatePicker datePickerLeave;
	@FXML DatePicker datePickerReturn;
	@FXML TextField txtfieldLeaveTime;
	@FXML TextField txtfieldReturnTime;
	@FXML TextField txtfieldDepartureCity;
	@FXML TextField txtfieldDestinationCity;
	@FXML ChoiceBox<String> choiceboxDepartureState;
	@FXML ChoiceBox<String> choiceboxDestinationState;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ObservableList<String> states = Location.getStateList();
		
		choiceboxDestinationState.setItems(states);
		choiceboxDepartureState.setItems(states);
		
		choiceboxDestinationState.setValue(states.get(48));
		choiceboxDepartureState.setValue(states.get(48));
	}
	
	@FXML
	void buttonBackClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/SplashScreen.fxml"))));
	}
	
	@FXML
	abstract void buttonSubmitClicked(ActionEvent actionEvent) throws IOException;
	
	Ride createRideObject(boolean isOffer)
	{
		String VALID_FIELD = "-fx-control-inner-background: white; -fx-font-weight: bold;";
		String INVALID_FIELD = "-fx-control-inner-background: red; -fx-font-weight: bold;";
		boolean isValid = true;
		
		// Handle Validating Return Time
		PioneerTime timeReturning = null;
		try
		{
			timeReturning = new PioneerTime(txtfieldReturnTime.getText());
			txtfieldReturnTime.setStyle(VALID_FIELD);
		}
		catch (InvalidTimeException e)
		{
			txtfieldReturnTime.setStyle(INVALID_FIELD);
			isValid = false;
		}
		
		// Handle Validating Leave Time
		PioneerTime timeLeaving = null;
		try
		{
			timeLeaving = new PioneerTime(txtfieldLeaveTime.getText());
			txtfieldLeaveTime.setStyle(VALID_FIELD);
		}
		catch (InvalidTimeException e)
		{
			txtfieldLeaveTime.setStyle(INVALID_FIELD);
			isValid = false;
		}
		
		// Handle Validating Return Date
		PioneerDate dateReturning = null;
		try
		{
			dateReturning = new PioneerDate(datePickerReturn.getEditor().getText());
			datePickerReturn.getEditor().setStyle(VALID_FIELD);
		}
		catch (InvalidDateException e)
		{
			datePickerReturn.getEditor().setStyle(INVALID_FIELD);
			isValid = false;
		}
		
		// Handle Validating Leave Date
		PioneerDate dateLeaving = null;
		try
		{
			dateLeaving = new PioneerDate(datePickerLeave.getEditor().getText());
			datePickerLeave.getEditor().setStyle(VALID_FIELD);
		}
		catch (InvalidDateException e)
		{
			datePickerLeave.getEditor().setStyle(INVALID_FIELD);
			isValid = false;
		}
		
		// Handle Validating Destination Location
		Location destinationLocation = null;
		try
		{
			destinationLocation = new Location(null, txtfieldDestinationCity.getText(), choiceboxDestinationState.getValue(), "12345");
			txtfieldDestinationCity.setStyle(VALID_FIELD);
		}
		catch (InvalidLocationException e)
		{
			txtfieldDestinationCity.setStyle(INVALID_FIELD);
			isValid = false;
		}
		
		// Handle Validating Departure Location
		Location leaveLocation = null;
		try
		{
			leaveLocation = new Location(null, txtfieldDepartureCity.getText(), choiceboxDepartureState.getValue(), "12345");
			txtfieldDepartureCity.setStyle(VALID_FIELD);
		}
		catch (InvalidLocationException e)
		{
			txtfieldDepartureCity.setStyle(INVALID_FIELD);
			isValid = false;
		}
		Ride ride = null;
		
		if(isValid)
		{
			if(isOffer)
			{
				ride = new RideOffer(leaveLocation, destinationLocation, dateLeaving, dateReturning, timeLeaving, timeReturning, null);
			}
			else
			{
				ride = new RideRequest(leaveLocation, destinationLocation, dateLeaving, dateReturning, timeLeaving, timeReturning, null);
			}
		}
		return ride;
	}
}
