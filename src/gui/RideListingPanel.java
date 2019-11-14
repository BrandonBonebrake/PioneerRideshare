package gui;

import date.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import location.Location;
import ride.Ride;
import student.Student;

public class RideListingPanel
{
    // Global Constants
    private int WIDTH = 1280;
    private int HEIGHT = 720;

    // Global Variables
    private Stage stage = null;
    private Pane pane = null;
    private TableView table = null;
    private Button backBtn = null;

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
        this.createTable();
        this.createBackButton();
    }

    private void createBackButton()
    {
        backBtn = new Button("Back");
        backBtn.setPrefSize(100, 50);
        backBtn.setOnAction(e -> this.buttonBackClicked());

        pane.getChildren().add(backBtn);
    }

    private void buttonBackClicked()
    {
        SplashScreenPanel splash = new SplashScreenPanel(stage);

        stage.setScene(new Scene(splash.getPane(), 1280, 720));
    }

    private void createTable()
    {
        final double CELL_WIDTH = WIDTH / 8.0 + 5;
        table = new TableView();
        table.setEditable(false);


        // Create all the columns that will represent the different data points we will display to the user
        TableColumn<String, Ride> offerRequest = new TableColumn("Offer/Request");
        TableColumn<String, Location> leaveCityState = new TableColumn("Leaving");
        TableColumn<String, Location> destinationCityState = new TableColumn("Destination");
        TableColumn location = new TableColumn("City/Sate");
        TableColumn<String, Date> leaveDateTime = new TableColumn("Leaving");
        TableColumn<String, Date> returnDateTime = new TableColumn("Returning");
        TableColumn dateTime = new TableColumn("Date/Time");
        TableColumn<String, Student> email = new TableColumn("Email @ uwplatt.edu");
        TableColumn<String, Button> request = new TableColumn("Request to Join/Offer to Drive");

        // Set the column widths
        offerRequest.setPrefWidth(105);
        leaveCityState.setPrefWidth(CELL_WIDTH);
        destinationCityState.setPrefWidth(CELL_WIDTH);
        leaveDateTime.setPrefWidth(CELL_WIDTH);
        returnDateTime.setPrefWidth(CELL_WIDTH);
        email.setPrefWidth(WIDTH / 5.0);
        request.setPrefWidth(WIDTH / 5.0);

        // Add the sub-columns to the columns
        location.getColumns().addAll(leaveCityState, destinationCityState);
        dateTime.getColumns().addAll(leaveDateTime, returnDateTime);

        //offerRequest.setCellFactory(new PropertyValueFactory<>("Offer/Request"));
        //leaveCityState.setCellFactory(new PropertyValueFactory<>("Leaving"));
        //destinationCityState.setCellFactory(new PropertyValueFactory<>("Destination"));
        //leaveDateTime.setCellFactory(new PropertyValueFactory<>("Leaving"));
        //returnDateTime.setCellFactory(new PropertyValueFactory<>("Returning"));
        //email.setCellFactory(new PropertyValueFactory<>("Email @uwplatt.edu"));
        //request.setCellFactory(new PropertyValueFactory<>("Request to Join/Offer to Drive"));

        table.getColumns().addAll(offerRequest, location, dateTime, email, request);
        table.setPrefSize(WIDTH, HEIGHT - 20);
        table.setTranslateY(50);

        pane.getChildren().add(table);
    }

    protected Pane getPane()
    {
        return this.pane;
    }
}
