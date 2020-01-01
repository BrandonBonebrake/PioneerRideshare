package newGUI;

import javafx.event.ActionEvent;
import ride.RideOffer;
import socketCommunication.Client;
import socketCommunication.Packet;

import java.io.IOException;

public class OfferRideController extends OfferRequestRideController
{
	@Override
	void buttonSubmitClicked(ActionEvent actionEvent) throws IOException
	{
		RideOffer ride = (RideOffer) createRideObject(true);
		if(ride != null)
		{
			new Client(new Packet<>(ride));
			super.buttonBackClicked(actionEvent);
		}
	}
}