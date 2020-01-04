package newGUI;

import javafx.event.ActionEvent;
import ride.RideOffer;
import socketCommunication.NewClient;

import java.io.IOException;

public class RequestRideController extends OfferRequestRideController
{
	@Override
	void buttonSubmitClicked(ActionEvent actionEvent) throws IOException
	{
		RideOffer ride = (RideOffer) createRideObject(false);
		if(ride != null)
		{
			new NewClient<>("PUT ride TO currentRides", ride);
			super.buttonBackClicked(actionEvent);
		}
	}
}