package newGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ride.Ride;
import student.Student;

class HeldData
{
	// Holds all the rides that the server sends to the client
	static ObservableList<Ride> currentRides = FXCollections.observableArrayList();
	
	// Holds the student information that is used to sign the rides
	static Student student = null;
}