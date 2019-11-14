package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

final class OfferRequestRidePanel extends DefaultView
{
    // Global Variables
    private Button backBtn = null;

    public OfferRequestRidePanel(Stage stage, Scene splash, int width, int height)
    {
        super(stage, splash, width, height);

        createComponents();
    }

    void createComponents()
    {
        this.createBackButton();
    }

    private void createBackButton()
    {
        backBtn = new Button("Back");
        backBtn.setPrefSize(200, 75);
        backBtn.setFont(Font.font(32));
        backBtn.setStyle(PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());

        super.getPane().getChildren().add(backBtn);
    }

    private void buttonBackClicked()
    {
        //SplashScreenPanel splash = new SplashScreenPanel(primaryStage, width, height);

        super.getStage().setScene(super.getScene());
    }
}
