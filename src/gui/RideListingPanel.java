package gui;

import gui.additionalFeatures.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import ride.Ride;
import socketCommunication.Client;
import socketCommunication.Packet;

import java.util.ArrayList;

/**
 * This class creates the ride listing Pane and the components
 * that make up the Pane. The calling class is responsible
 * for adding the Pane of this class to its view to change
 * the scene so that this view can be interacted with.
 *
 * @author Brandon Bonebrake
 */
@Deprecated
final class RideListingPanel extends DefaultView
{
	private TextField searchTextbox;
	private final int DEFAULT_COMP_HEIGHT = 40;
	private final int DEFAULT_COMP_WIDTH = 220;
	private final int DEFAULT_X_COMP = 350;
	private final int DEFAULT_Y_COMP = 20;
	
	private ObservableList<Ride> data = FXCollections.observableArrayList();
	FilteredList<Ride> filteredList;
	
	/**
	 * Creates the ride listing pane that shows all the current ride
	 * listings.
	 *
	 * @param stage     Outer container that all scenes are a part of
	 * @param prevScene The scene of the previous view
	 * @param width     Width of the scene
	 * @param height    Height of the scene
	 */
	RideListingPanel(Stage stage, Scene prevScene, int width, int height)
	{
		super(stage, prevScene, width, height);
		
		if (PioneerApplication.studentLoggedIn == null)
		{
			super.returnView();
		}
		else
		{
			this.populatedTableFromServer();
		}
		this.createComponents();
	}
	
	/**
	 * Creates the components that are on the Pane
	 */
	@Override
	void createComponents()
	{
		this.createBackButton();
		this.createSearchTextbox();
		this.createTable();
	}
	
	/**
	 * Creates the back button on the Pane
	 */
	private void createBackButton()
	{
		Button backBtn = super.createButton("Back", 200, 75,
				0, 0, PioneerApplication.EXIT_STYLE);
		backBtn.setOnAction(e -> this.buttonBackClicked());
	}
	
	private void createSearchTextbox()
	{
		searchTextbox = super.createTextField("Search", DEFAULT_COMP_WIDTH, DEFAULT_COMP_HEIGHT,
				DEFAULT_X_COMP, DEFAULT_Y_COMP);
	}
	
	/**
	 * Returns the view back to the scene that originally created the scene
	 */
	private void buttonBackClicked()
	{
		super.returnView();
	}
	
	private TableColumn<String, Ride> createColumn(String methodName, int width)
	{
		TableColumn<String, Ride> tColumn = new TableColumn<>(methodName);
		
		tColumn.impl_setReorderable(false);
		tColumn.setResizable(false);
		tColumn.setSortable(false);
		tColumn.setPrefWidth(width);
		
		return tColumn;
	}
	
	/**
	 * Creates the table that all the current ride offers/requests are a part of
	 */
	private void createTable()
	{
		double CELL_WIDTH = super.getWidth() / 9.0;
		TableView table = new TableView<>();
		table.setEditable(false);
		
		// Create all the columns that will represent the different data points we will display to the user
		TableColumn<String, Ride> location = createColumn("City/State", 0);
		TableColumn<String, Ride> dateTime = createColumn("Date/Time", 0);
		TableColumn<String, Ride> offerRequest = createColumn("Offer/Request", 105);
		TableColumn<String, Ride> leaveCityState = createColumn("Leaving", (int) (super.getWidth() / 9.0));
		TableColumn<String, Ride> destinationCityState = createColumn("Destination", (int) (super.getWidth() / 9.0));
		TableColumn<String, Ride> leaveDateTime = createColumn("Leaving", (int) (super.getWidth() / 9.0));
		TableColumn<String, Ride> returnDateTime = createColumn("Returning", (int) (super.getWidth() / 9.0));
		TableColumn<String, Ride> email = createColumn("Email @uwplatt.edu", (int) (super.getWidth() / 5.0 + 5));
		TableColumn<String, Ride> request = createColumn("Request to Join/Offer to Drive", (int) (super.getWidth() / 5.0 + 15));
		
		offerRequest.setSortable(true);
		
		// Add the sub-columns to the columns
		location.getColumns().addAll(leaveCityState, destinationCityState);
		dateTime.getColumns().addAll(leaveDateTime, returnDateTime);
		
		// Set factory methods to pull information from the Ride Object
		offerRequest.setCellValueFactory(new PropertyValueFactory<>("offerRequest"));
		leaveCityState.setCellValueFactory(new PropertyValueFactory<>("departLocation"));
		destinationCityState.setCellValueFactory(new PropertyValueFactory<>("returnLocation"));
		leaveDateTime.setCellValueFactory(new PropertyValueFactory<>("leaveDateTime"));
		returnDateTime.setCellValueFactory(new PropertyValueFactory<>("returnDateTime"));
		email.setCellValueFactory(new PropertyValueFactory<>("student"));
		request.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		
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
									setGraphic(null);
								} else
								{
									btn.setStyle(PioneerApplication.RIDE_STYLE);
									btn.setOnAction(event ->
											buttonJoinDriveClicked(table.getSelectionModel().getSelectedIndex()));
									setGraphic(btn);
								}
								setText(null);
							}
						};
					}
				};
		request.setCellFactory(cellFactory);
		{
		}
		
		filteredList = new FilteredList<>(data, p -> true);
		searchTextbox.textProperty().addListener((observable, oldValue, newValue) ->
				filteredList.setPredicate(ride1 ->
						Search.searchFor(ride1, newValue.toLowerCase().trim())));
		
		SortedList<Ride> sortedList = new SortedList<>(filteredList);
		sortedList.comparatorProperty().bind(table.comparatorProperty());
		
		table.getColumns().addAll(offerRequest, location, dateTime, email, request);
		table.setPrefSize(super.getWidth(), super.getHeight() - 75);
		table.setTranslateY(75);
		table.setPlaceholder(new Label("No rides"));
		table.setItems(sortedList);
		
		super.addComponent(table);
	}
	
	private void buttonJoinDriveClicked(int selectedIndex)
	{
		if (selectedIndex >= 0)
		{
			Client client = new Client(new Packet<>("Ride: " + data.get(selectedIndex).getRideIdentificationNumber() + " "
					+ PioneerApplication.studentLoggedIn.getEmail()));
			Object objReceived = client.receiveObject();
			
			removeRide((Ride) objReceived);
			client.close();
			
			PopUpPanel.display("Student has\nbeen notified");
		}
	}
	
	private void removeRide(Ride ride)
	{
		for (int i = 0; i < data.size(); i++)
		{
			if (ride.getRideIdentificationNumber().equals(data.get(i).getRideIdentificationNumber()))
			{
				new Client(new Packet<>("Remove: " + ride.getRideIdentificationNumber()));
				data.remove(i);
				break;
			}
		}
	}
	
	private void populatedTableFromServer()
	{
		data.addAll((ArrayList<Ride>) (new Client(new Packet<>("currentRides")).receiveObject().getObject()));
	}
	
}
