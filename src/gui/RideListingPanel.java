package gui;

import date.Date;
import date.PioneerDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import location.InvalidLocationException;
import location.Location;
import ride.Ride;
import ride.RideOffer;
import ride.RideRequest;
import student.InvalidStudentException;
import student.Student;
import time.InvalidTimeException;
import time.PioneerTime;
import time.Time;

final class RideListingPanel extends DefaultView
{
    // Global Variables
    private TableView table = null;
    private Button backBtn = null;

    Location loc = new Location("street", "Platteville", "WI", 53818);

    ObservableList<Ride> data = FXCollections.observableArrayList(
            new RideOffer(loc, new Location("street", "Madison", "WI", 53818), new PioneerDate(), new PioneerDate(), new PioneerTime(), new PioneerTime(), new Student("John", "Smith", "dummy@uwplatt.edu", "123456789!a")),
            new RideRequest(loc, loc, new PioneerDate(), new PioneerDate(), new PioneerTime(), new PioneerTime(), new Student("Jane", "Doe", "dummy01@uwplatt.edu", "123456789!a"))
    );

    public RideListingPanel(Stage stage, Scene splash, int width, int height) throws InvalidLocationException, InvalidStudentException, InvalidTimeException
    {
        super(stage, splash, width, height);

        this.createComponents();
    }

    @Override
    void createComponents() throws InvalidStudentException
    {
        this.createBackButton();
        this.createTable();
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
        super.returnView();
    }

    private void createTable() throws InvalidStudentException
    {
        final double CELL_WIDTH = super.getWidth() / 9.0;
        table = new TableView();
        table.setEditable(true);

        // Create all the columns that will represent the different data points we will display to the user
        TableColumn offerRequest = new TableColumn("Offer/Request");
        TableColumn leaveCityState = new TableColumn("Leaving");
        TableColumn destinationCityState = new TableColumn("Destination");
        TableColumn location = new TableColumn("City/State");
        TableColumn leaveDateTime = new TableColumn("Leaving");
        TableColumn returnDateTime = new TableColumn("Returning");
        TableColumn dateTime = new TableColumn("Date/Time");
        TableColumn email = new TableColumn("Email @uwplatt.edu");
        TableColumn request = new TableColumn("Request to Join/Offer to Drive");

        // Set the column widths
        offerRequest.setPrefWidth(105);
        leaveCityState.setPrefWidth(CELL_WIDTH);
        destinationCityState.setPrefWidth(CELL_WIDTH);
        leaveDateTime.setPrefWidth(CELL_WIDTH);
        returnDateTime.setPrefWidth(CELL_WIDTH);
        email.setPrefWidth(super.getWidth() / 5.0);
        request.setPrefWidth(super.getWidth() / 5.0 + 15);

        PropertyValueFactory student = new PropertyValueFactory<String, Ride>("student");

        // Add the sub-columns to the columns
        location.getColumns().addAll(leaveCityState, destinationCityState);
        dateTime.getColumns().addAll(leaveDateTime, returnDateTime);

        offerRequest.setCellValueFactory(new PropertyValueFactory<String,Ride>("offerRequest"));
        leaveCityState.setCellValueFactory(new PropertyValueFactory<String, Ride>("departLocation"));
        destinationCityState.setCellValueFactory(new PropertyValueFactory<String, Ride>("returnLocation"));
        leaveDateTime.setCellValueFactory(new PropertyValueFactory<String, Ride>("leaveDateTime"));
        returnDateTime.setCellValueFactory(new PropertyValueFactory<String, Ride>("returnDateTime"));
        email.setCellValueFactory(new PropertyValueFactory<String, Ride>("student"));
        request.setCellValueFactory(new PropertyValueFactory<Ride, Button>("button"));

        table.getColumns().addAll(offerRequest, location, dateTime, email, request);
        table.setPrefSize(super.getWidth(), super.getHeight() - 75);
        table.setTranslateY(75);
        table.setPlaceholder(new Label("No rides"));
        table.setItems(data);

        super.addComponent(table);
    }
}
