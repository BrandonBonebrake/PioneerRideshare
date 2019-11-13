package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class SplashScreenPanel extends StackPane
{
    Button exitBtn = null;

    public SplashScreenPanel() throws IOException
    {
       super();

       this.createComponents();
    }

    private void createComponents() throws IOException
    {
        this.getStylesheets().add("gui/splashScreenPanel.fxml");

        this.createExitButton();
    }

    private void createExitButton()
    {
        // All of the styling for the button is done here
        String style = "-fx-background-color: linear-gradient(#ffd65b, #e68400)," +
                       "linear-gradient(#ffef84, #f2ba44)," +
                       "linear-gradient(#ffea6a, #efaa22);" +
                       "fx-background-radius: 50;" +
                       "-fx-font-weight: bold;" +
                       "-fx-font-size: 18px;" +
                       "-fx-text-fill: blue";

        exitBtn = new Button("Exit");
        exitBtn.setPrefSize(100, 50);
        exitBtn.setOnAction(e -> this.buttonRideOfferClicked());
        //exitBtn.setStyle(style);
        exitBtn.getStyleClass().removeAll();
        exitBtn.getStyleClass().add("/gui/style.css");

        this.getChildren().add(exitBtn);
    }


    public void buttonRideOfferClicked()
    {
        System.exit(0);
    }
}
