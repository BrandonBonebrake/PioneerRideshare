package gui;

import date.Date;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import location.Location;
import ride.Ride;
import student.Student;

public class RideListingPanel
{
    // Global Constants
    private int width = 1280;
    private int height = 720;

    // Global Variables
    private Scene splashScene = null;
    private Stage stage = null;
    private Pane pane = null;
    private TableView table = null;
    private Button backBtn = null;

    public RideListingPanel(Stage stage, Scene splash, int width, int height)
    {
        super();

        this.splashScene = splash;
        this.stage = stage;
        this.pane = new Pane();
        this.width = width;
        this.height = height;

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
        backBtn.setMinSize(200, 75);
        backBtn.setFont(Font.font(32));
        backBtn.setOnAction(e -> this.buttonBackClicked());

        pane.getChildren().add(backBtn);
    }

    private void buttonBackClicked()
    {
        stage.setScene(splashScene);
    }

    private void createTable()
    {
        final double CELL_WIDTH = width / 8.0 + 5;
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
        email.setPrefWidth(width / 5.0);
        request.setPrefWidth(width / 5.0);

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
        table.setPrefSize(width, height - 75);
        table.setTranslateY(75);

        pane.getChildren().add(table);
    }

    protected Pane getPane()
    {
        return this.pane;
    }
}
