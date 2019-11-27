package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

final class ForgotPasswordPanel extends DefaultView
{
    ForgotPasswordPanel(Stage stage, Scene scene, int width, int height)
    {
        super(stage, scene, width, height);

        this.createComponents();
    }

    @Override
    void createComponents()
    {
        this.createBackButton();
    }

    private void createBackButton()
    {
        Button backBtn = super.createButton("Back", 200, 75,
                0, 0, PioneerApplication.EXIT_STYLE);
        backBtn.setOnAction(event -> buttonBackClicked());
    }

    private void buttonBackClicked()
    {
        super.returnView();
    }
}
