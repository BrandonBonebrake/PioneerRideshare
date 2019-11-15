package gui;

import date.Date;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import location.Location;
import ride.Ride;
import student.Student;

final class RideListingPanel extends DefaultView
{
    // Global Variables
    private TableView table = null;
    private Button backBtn = null;

    public RideListingPanel(Stage stage, Scene splash, int width, int height)
    {
        super(stage, splash, width, height);

        this.createComponents();
    }

    @Override
    void createComponents()
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
        backBtn.setStyle(PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(e -> this.buttonBackClicked());

        super.getPane().getChildren().add(backBtn);
    }

    private void buttonBackClicked()
    {
        super.getStage().setScene(super.getScene());
    }

    private void createTable()
    {
        final double CELL_WIDTH = super.getWidth() / 8.0 + 5;
        table = new TableView();
        table.setEditable(false);

        // Create all the columns that will represent the different data points we will display to the user
        TableColumn<String, Ride> offerRequest = new TableColumn("Offer/Request");
        TableColumn<String, Location> leaveCityState = new TableColumn("Leaving");
        TableColumn<String, Location> destinationCityState = new TableColumn("Destination");
        TableColumn location = new TableColumn("City/State");
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
        email.setPrefWidth(super.getWidth() / 5.0);
        request.setPrefWidth(super.getWidth() / 5.0);

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
        table.setPrefSize(super.getWidth(), super.getHeight() - 75);
        table.setTranslateY(75);
        //table.setStyle(PioneerApplication.BACKGROUND_STYLE);

        super.getPane().getChildren().add(table);
    }

}
