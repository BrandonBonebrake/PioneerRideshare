package newGUI;

import gui.PopUpPanel;
import gui.additionalFeatures.Search;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import ride.Ride;
import socketCommunication.Client;
import socketCommunication.Packet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewRidesController implements Initializable
{
	@FXML private TextField txtfieldSearch;
	@FXML private TableView<Ride> tblRides;
	@FXML private TableColumn<String, Ride> tblColOfferRequest;
	@FXML private TableColumn<String, Ride> tblColDepartLocation;
	@FXML private TableColumn<String, Ride> tblColDestinationLocation;
	@FXML private TableColumn<String, Ride> tblDepartDateTime;
	@FXML private TableColumn<String, Ride> tblReturnDateTime;
	@FXML private TableColumn<String, Ride> tblColEmail;
	@FXML private TableColumn<String, Ride> tblColJoinDrive;
	
	private final String RIDE_STYLE = "-fx-background-color: linear-gradient(blue, black)," +
			"linear-gradient(blue, darkBlue), linear-gradient(orange, #ffc266);" +
			"-fx-background-radius: 0; -fx-font-weight: bold; -fx-font-size: 18px;" +
			"-fx-text-fill: blue; -fx-border-color: orange; -fx-border-width: 0;";
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Implement student login
		//if (HeldData.student != null)
		{
			this.populateTableFromServer();
			this.createTableItems();
		}
	}
	
	@FXML
	private void buttonBackClicked(ActionEvent actionEvent) throws IOException
	{
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("fxmlFiles/SplashScreen.fxml"))));
	}
	
	private void createTableItems()
	{
		tblColOfferRequest.setCellValueFactory(new PropertyValueFactory<>("offerRequest"));
		tblColDepartLocation.setCellValueFactory(new PropertyValueFactory<>("departLocation"));
		tblColDestinationLocation.setCellValueFactory(new PropertyValueFactory<>("returnLocation"));
		tblDepartDateTime.setCellValueFactory(new PropertyValueFactory<>("leaveDateTime"));
		tblReturnDateTime.setCellValueFactory(new PropertyValueFactory<>("returnDateTime"));
		tblColEmail.setCellValueFactory(new PropertyValueFactory<>("student"));
		tblColJoinDrive.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		
		// Allow the TableColumn to have a button on the view
		Callback<TableColumn<String, Ride>, TableCell<String, Ride>> cellFactory =
				new Callback<TableColumn<String, Ride>, TableCell<String, Ride>>()
				{
					@Override
					public TableCell<String, Ride> call(TableColumn<String, Ride> param)
					{
						return new TableCell<String, Ride>()
						{
							Button btn = new Button("Join/Drive");
							
							@Override
							public void updateItem(Ride item, boolean empty)
							{
								super.updateItem(item, empty);
								if (empty)
								{
									super.setGraphic(null);
								}
								else
								{
									btn.setStyle(RIDE_STYLE);
									btn.setOnAction(event ->
											buttonJoinDriveClicked(tblRides.getSelectionModel().getSelectedIndex()));
									super.setGraphic(btn);
								}
								super.setText(null);
							}
						};
					}
				};
		tblColJoinDrive.setCellFactory(cellFactory);{}
		
		FilteredList<Ride> filteredList = new FilteredList<>(HeldData.currentRides, p -> true);
		txtfieldSearch.textProperty().addListener((observable, oldValue, newValue) ->
				filteredList.setPredicate(ride1 ->
						Search.searchFor(ride1, newValue.toLowerCase().trim())));
		
		tblRides.setPlaceholder(new Label("No Rides"));
		tblRides.setItems(filteredList);
	}
	
	private void buttonJoinDriveClicked(int selectedIndex)
	{
		if (selectedIndex >= 0)
		{
			Client client = new Client(new Packet<>("Ride: " + HeldData.currentRides.get(selectedIndex).getRideIdentificationNumber() + " "
					+ HeldData.student.getEmail()));
			Object objReceived = client.receiveObject().getObject();
			
			this.removeRide((Ride) objReceived);
			client.close();
			
			PopUpPanel.display("Student has\nbeen notified");
		}
	}
	
	private void removeRide(Ride ride)
	{
		for (int i = 0; i < HeldData.currentRides.size(); i++)
		{
			if (ride.getRideIdentificationNumber().equals(HeldData.currentRides.get(i).getRideIdentificationNumber()))
			{
				new Client(new Packet<>("Remove: " + ride.getRideIdentificationNumber()));
				HeldData.currentRides.remove(i);
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void populateTableFromServer()
	{
		HeldData.currentRides.addAll((ArrayList<Ride>) (new Client(new Packet<>("currentRides")).receiveObject().getObject()));
	}
}