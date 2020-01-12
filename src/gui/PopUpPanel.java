package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Deprecated
public class PopUpPanel
{
	public static void display(String title)
	{
		Stage popUp = new Stage();
		VBox pane = new VBox(10);
		Scene scene = new Scene(pane, 300, 300);
		Button close = new Button("Close");
		Label statusDescription = new Label(title);
		
		// Label
		statusDescription.setFont(Font.font(32));
		statusDescription.setStyle("-fx-font-weight: bold;");
		statusDescription.setTextFill(Color.WHITE);
		
		// Button
		close.setStyle(PioneerApplication.EXIT_STYLE);
		close.setOnAction(e -> popUp.close());
		
		// Pane
		pane.setStyle(PioneerApplication.BACKGROUND_STYLE);
		pane.getChildren().addAll(statusDescription, close);
		pane.setAlignment(Pos.CENTER);
		
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.setTitle(title);
		popUp.setScene(scene);
		popUp.showAndWait();
	}
}
