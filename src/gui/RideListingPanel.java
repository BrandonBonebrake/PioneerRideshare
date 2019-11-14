package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ride.Ride;

public class RideListingPanel
{
    // Global Constants
    private int WIDTH = 1280;
    private int HEIGHT = 720;

    // Global Variables
    private Stage stage = null;
    private Pane pane = null;
    private TableView table = null;

    private final ObservableList<String> data =
            FXCollections.observableArrayList(
               "Offer", "Platteville, WI", "Madison, WI", "11/1/10 10:00AM", "11/3/19 11:00AM", "placeholder@uwplatt.edu", "Offer to Join"
            );

    public RideListingPanel(Stage stage)
    {
        super();

        this.stage = stage;
        this.pane = new Pane();

        this.createComponents();
    }

    private void createComponents()
    {
        final double CELL_WIDTH = WIDTH / 8.0 + 5;
        table = new TableView();
        table.setEditable(false);

        TableColumn offerRequest = new TableColumn("Offer/Request");
        TableColumn leaveCityState = new TableColumn("Leaving");
        TableColumn destinationCityState = new TableColumn("Destination");
        TableColumn location = new TableColumn("City/Sate");
        TableColumn leaveDateTime = new TableColumn("Leaving");
        TableColumn returnDateTime = new TableColumn("Returning");
        TableColumn dateTime = new TableColumn("Date/Time");
        TableColumn email = new TableColumn("Email @ uwplatt.edu");
        TableColumn request = new TableColumn("Request to Join/Offer to Drive");

        offerRequest.setPrefWidth(105);
        leaveCityState.setPrefWidth(CELL_WIDTH);
        destinationCityState.setPrefWidth(CELL_WIDTH);
        leaveDateTime.setPrefWidth(CELL_WIDTH);
        returnDateTime.setPrefWidth(CELL_WIDTH);
        email.setPrefWidth(WIDTH / 5.0);
        request.setPrefWidth(WIDTH / 5.0);

        location.getColumns().addAll(leaveCityState, destinationCityState);
        dateTime.getColumns().addAll(leaveDateTime, returnDateTime);

        //offerRequest.setCellFactory(new PropertyValueFactory<>("Offer/Request"));
        //leaveCityState.setCellFactory(new PropertyValueFactory<>("Leaving"));
        //destinationCityState.setCellFactory(new PropertyValueFactory<>("Destination"));
        //leaveDateTime.setCellFactory(new PropertyValueFactory<>("Leaving"));
        //returnDateTime.setCellFactory(new PropertyValueFactory<>("Returning"));
        //email.setCellFactory(new PropertyValueFactory<>("Email @uwplatt.edu"));
        //request.setCellFactory(new PropertyValueFactory<>("Request to Join/Offer to Drive"));

        //Ride ride = new Ride();

        //table.getItems().add(ride);
        table.getColumns().addAll(offerRequest, location, dateTime, email, request);
        table.setPrefSize(WIDTH, HEIGHT - 20);
        table.setTranslateY(30);

        pane.getChildren().add(table);
    }

    protected Pane getPane()
    {
        return this.pane;
    }
}
