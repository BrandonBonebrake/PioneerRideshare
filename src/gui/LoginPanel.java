package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

final class LoginPanel extends DefaultView
{
    // Global Variables

    public LoginPanel(Stage stage, Scene splash, int width, int height)
    {
        super(stage, splash, width, height);

        this.createComponents();
    }

    void createComponents()
    {
        this.createBackButton();
    }

    private void createBackButton()
    {
        Button backBtn =super.createButton("Back", 200, 75,
                0, 0, PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());
    }

    private void buttonBackClicked()
    {
        super.returnView();
    }
}