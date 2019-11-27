package gui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class creates the defaults that every view in the GUI will follow.
 * The stage that is created at runtime is passed in to allow the scene to
 * be changed without needing to create a new window. The scene will
 * instead change within the same stage.
 *
 * @author Brandon Bonebrake
 **/
abstract class DefaultView
{
    // Global Variables
    private Stage stage; // Stage that is created at runtime which all Scenes
    private Scene scene; // Previous scene that was on the stage to allow switching
    private Pane pane;   // Pane that each view will put its Objects into
    private int width;   // Width of the scene which changes the size of the stage
    private int height;  // Height of the scene which changes the size of the stage

    /**
     * Creates the DefaultView Object. This Object is abstract as there
     * is no reason to create views directly from this Object as they will
     * only ever be empty.
     *
     * @param stage  The stage that was created at runtime
     * @param scene  The previous scene that can be set back to
     * @param width  The width of the application window
     * @param height The height of the application window
     */
    DefaultView(Stage stage, Scene scene, int width, int height)
    {
        super();

        this.stage  = stage;
        this.scene  = scene;
        this.pane   = new Pane();
        this.width  = width;
        this.height = height;

        this.paneDefaults();
    }

    /**
     * Method that all classes must implement where the methods to create the
     * components are called.
     */
    abstract void createComponents();

    /**
     *  Method that contains all the default pane settings.
     *  Adds a stylized background that every view uses.
     */
    private void paneDefaults()
    {
        pane.setStyle(PioneerApplication.BACKGROUND_STYLE);
    }

    /**
     * Quick method to add components to the view so a
     * super.getPane().getChildren().add(####); is not
     * needed to add a component to the pane.
     *
     * @param component Component to add to the pane
     */
    void addComponent(Node component)
    {
        this.getPane().getChildren().add(component);
    }

    /**
     * Sets the scene back to the scene that originally
     * created this scene.
     */
    void returnView()
    {
        this.getStage().setScene(this.getScene());
    }

    /**
     * Changes the scene to the new Pane which will add all the
     * components to this Pane.
     *
     * @param newPane Pane with all components on the view
     */
    void changeScene(Pane newPane)
    {
        this.getStage().setScene(new Scene(newPane, this.getWidth(), this.getHeight()));
    }

    /**
     * General method to create and add labels to the Pane without needing
     * to instantiate the Labels in the calling Object. Returns a label if
     * more dynamic control of the Label is needed.
     *
     * @param text   Display text of the Label
     * @param transX Distance from the x-axis
     * @param transY Distance from the y-axis
     * @return Label with defaults and passed in parameters set
     */
    Label createLabel(String text, int transX, int transY)
    {
        Label label = new Label(text);

        label.setTranslateX(transX);
        label.setTranslateY(transY);
        label.setFont(Font.font(32));
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-weight: bold;");

        this.addComponent(label);

        return label;
    }

    /**
     * General method to create and add Buttons to the Pane without
     * needing to instantiate the Button in the calling Object. Returns
     * a button if more dynamic control of the Button is needed.
     *
     * @param text   Display text of the Button
     * @param sizeX  Size in the x-axis of the button
     * @param sizeY  Size in the y-axis of the button
     * @param transX Distance from the x-axis
     * @param transY Distance from the y-axis
     * @param style  Desired styling of the button
     * @return Button with defaults and passed in parameters set
     */
    Button createButton(String text, int sizeX, int sizeY, int transX, int transY, String style)
    {
        Button button = new Button(text);

        button.setPrefSize(sizeX, sizeY);
        button.setTranslateX(transX);
        button.setTranslateY(transY);
        button.setFont(Font.font(32));
        button.setStyle(style);

        this.addComponent(button);

        return button;
    }

    /**
     * General method to create and add TextFields to the Pane without
     * needing to instantiate the TextField in the calling Object. Returns
     * a text field if more dynamic control of the TextField is needed.
     *
     * @param prompt Text Displayed when there is no input
     * @param sizeX  Size in the x-axis
     * @param sizeY  Size in hte y-axis
     * @param transX Distance from the x-axis
     * @param transY Distance from the y-axis
     * @return TextField with defaults and passed in parameters set
     */
    TextField createTextField(String prompt, int sizeX, int sizeY, int transX, int transY)
    {
        TextField textField = new TextField();

        textField.setPromptText(prompt);
        textField.setPrefSize(sizeX, sizeY);
        textField.setTranslateX(transX);
        textField.setTranslateY(transY);
        textField.setStyle("-fx-font-weight: bold;");

        this.addComponent(textField);

        return textField;
    }

    /**
     * General method to create and add DatePickers to the Pane without
     * needing to instantiate the DatePicker in the calling Object. Returns
     * a date picker if more dynamic control of the DatePicker is needed.
     *
     * @param sizeX  Size in the x-axis
     * @param sizeY  Size in the y-axis
     * @param transX Distance from the x-axis
     * @param transY Distance from the y-axis
     * @return DatePicker with defaults and passed in parameters set
     */
    DatePicker createDatePicker(int sizeX, int sizeY, int transX, int transY)
    {
        DatePicker datePicker = new DatePicker();

        datePicker.setEditable(false);
        datePicker.setPromptText("dd/mm/yyyy");
        datePicker.setPrefSize(sizeX, sizeY);
        datePicker.setTranslateX(transX);
        datePicker.setTranslateY(transY);
        datePicker.setStyle("-fx-font-weight: bold;");

        this.addComponent(datePicker);

        return datePicker;
    }

    /**
     * General method to create and add ChoiceBoxes to the Pane without
     * needing to instantiate the ChoiceBox in the calling Object. Returns
     * a choice box if more dynamic control of the ChoiceBox is needed.
     *
     * @param items        List of all items to display
     * @param sizeX        Size in the x-axis
     * @param sizeY        Size in the y-axis
     * @param transX       Distance from the x-axis
     * @param transY       Distance from the y-axis
     * @return ChoiceBox with defaults and passed in parameters set
     */
    ChoiceBox<String> createChoiceBox(ObservableList<String> items, int sizeX, int sizeY, int transX, int transY)
    {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        choiceBox.setItems(items);
        choiceBox.setValue(items.get(48));
        choiceBox.setPrefSize(sizeX, sizeY);
        choiceBox.setTranslateX(transX);
        choiceBox.setTranslateY(transY);

        this.addComponent(choiceBox);

        return choiceBox;
    }

    /**
     * Return the stage that is created at runtime.
     *
     * @return Stage that is created at runtime
     */
    Stage getStage()
    {
        return this.stage;
    }

    /**
     * Return the previous Scene that the Stage was on.
     * Setting the Scene on the Stage will change it back to
     * the previous scene.
     *
     * @return Previous scene that the view was on
     */
    private Scene getScene()
    {
        return this.scene;
    }

    /**
     * Return the pane that all the current components
     * are on. This pane is a part of the current scene.
     *
     * @return Pane that all the current components are on
     */
    Pane getPane()
    {
        return this.pane;
    }

    /**
     * Return the width that the scene is set to
     *
     * @return Number of pixels on the width
     */
    int getWidth()
    {
        return this.width;
    }

    /**
     * Return the height that the scene is set to
     *
     * @return Number of pixels on the height
     */
    int getHeight()
    {
        return this.height;
    }
}