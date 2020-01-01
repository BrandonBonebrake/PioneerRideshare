package newGUI;

import javafx.event.ActionEvent;
import ride.RideOffer;
import socketCommunication.Client;
import socketCommunication.Packet;

import java.io.IOException;

public class RequestRideController extends OfferRequestRideController
{
	@Override
	void buttonSubmitClicked(ActionEvent actionEvent) throws IOException
	{
		RideOffer ride = (RideOffer) createRideObject(false);
		if(ride != null)
		{
			new Client(new Packet<>(ride));
			super.buttonBackClicked(actionEvent);
		}
	}
}
