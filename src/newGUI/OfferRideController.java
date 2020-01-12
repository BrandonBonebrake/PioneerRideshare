package newGUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import ride.Ride;
import ride.RideOffer;
import socketCommunication.NewClient;

import java.io.IOException;

public class OfferRideController extends OfferRequestRideController
{
	
	
	@Override
	void buttonSubmitClicked(ActionEvent actionEvent) throws IOException
	{
		RideOffer ride = (RideOffer) createRideObject(true);
		if(ride != null)
		{
			new NewClient<String, Ride>("PUT ride TO currentRides", ride);
			super.buttonBackClicked(actionEvent);
		}
	}
}