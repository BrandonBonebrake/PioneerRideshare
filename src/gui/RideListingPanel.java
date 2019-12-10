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
import ride.Ride;
import socketCommunication.Client;

import java.util.ArrayList;

/**
 * This class creates the ride listing Pane and the components
 * that make up the Pane. The calling class is responsible
 * for adding the Pane of this class to its view to change
 * the scene so that this view can be interacted with.
 *
 * @author Brandon Bonebrake
 */
final class RideListingPanel extends DefaultView
{
    private TextField searchTextbox;
    private final int DEFAULT_COMP_HEIGHT = 40;
    private final int DEFAULT_COMP_WIDTH = 220;
    private final int DEFAULT_X_COMP = 350;
    private final int DEFAULT_Y_COMP = 20;

    // Dummy rides used to test the table
    private ObservableList<Ride> data = FXCollections.observableArrayList();

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

        this.populatedTableFromServer();
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

    /**
     * Creates the table that all the current ride offers/requests are a part of
     */
    private void createTable()
    {
        double CELL_WIDTH = super.getWidth() / 9.0;
        TableView table = new TableView();
        table.setEditable(false);

        // Create all the columns that will represent the different data points we will display to the user
        TableColumn location                           = new TableColumn("City/State");
        TableColumn dateTime                           = new TableColumn("Date/Time");
        TableColumn<String, Ride> offerRequest         = new TableColumn<>("Offer/Request");
        offerRequest.impl_setReorderable(false);
        offerRequest.setResizable(false);
        TableColumn<String, Ride> leaveCityState       = new TableColumn<>("Leaving");
        leaveCityState.impl_setReorderable(false);
        leaveCityState.setSortable(false);
        leaveCityState.setResizable(false);
        TableColumn<String, Ride> destinationCityState = new TableColumn<>("Destination");
        destinationCityState.impl_setReorderable(false);
        destinationCityState.setSortable(false);
        destinationCityState.setResizable(false);
        TableColumn<String, Ride> leaveDateTime        = new TableColumn<>("Leaving");
        leaveDateTime.impl_setReorderable(false);
        leaveDateTime.setSortable(false);
        leaveDateTime.setResizable(false);
        TableColumn<String, Ride> returnDateTime       = new TableColumn<>("Returning");
        returnDateTime.impl_setReorderable(false);
        returnDateTime.setSortable(false);
        returnDateTime.setResizable(false);
        TableColumn<String, Ride> email                = new TableColumn<>("Email @uwplatt.edu");
        email.impl_setReorderable(false);
        email.setResizable(false);
        email.setSortable(false);
        TableColumn<Ride, Button> request              = new TableColumn<>("Request to Join/Offer to Drive");
        request.impl_setReorderable(false);
        request.setResizable(false);
        request.setSortable(false);

        // Set the column widths
        offerRequest.setPrefWidth(105);
        leaveCityState.setPrefWidth(CELL_WIDTH);
        destinationCityState.setPrefWidth(CELL_WIDTH);
        leaveDateTime.setPrefWidth(CELL_WIDTH);
        returnDateTime.setPrefWidth(CELL_WIDTH);
        email.setPrefWidth(super.getWidth() / 5.0);
        request.setPrefWidth(super.getWidth() / 5.0 + 15);

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
        request.setCellValueFactory(new PropertyValueFactory<>("button"));

        FilteredList<Ride> filteredList = new FilteredList<>(data, p -> true);
        searchTextbox.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(ride1 ->
                        Search.searchFor(ride1, newValue.toLowerCase().trim())));

        SortedList<Ride> sortedList = new SortedList<Ride>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());

        table.getColumns().addAll(offerRequest, location, dateTime, email, request);
        table.setPrefSize(super.getWidth(), super.getHeight() - 75);
        table.setTranslateY(75);
        table.setPlaceholder(new Label("No rides"));
        table.setItems(sortedList);

        super.addComponent(table);
    }

    private void populatedTableFromServer()
    {
        data.addAll((ArrayList<Ride>) (new Client("currentRides").receiveObject()));
    }
}