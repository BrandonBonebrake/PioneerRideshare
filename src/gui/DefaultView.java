package gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import student.InvalidStudentException;

/**
    This class creates the defaults that every view in the GUI will follow.
    The stage that is created at runtime is passed in to allow the scene to
    be changed without needing to create a new window. The scene will
    instead change within the same stage.

    @author Brandon Bonebrake
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
        Creates the DefaultView Object. This Object is abstract as there
        is no reason to create views directly from this Object as they will
        only ever be empty.

        @param stage  The stage that was created at runtime
        @param scene  The previous scene that can be set back to
        @param width  The width of the application window
        @param height The height of the application window
    **/
    DefaultView(Stage stage, Scene scene, int width, int height)
    {
        super();

        this.stage = stage;
        this.scene = scene;
        this.pane = new Pane();
        this.width = width;
        this.height = height;

        this.paneDefaults();
    }

    /**
        Method that all classes must implement where the methods to create the
        components are called.
     **/
    abstract void createComponents() throws InvalidStudentException;

    /**
        Method that contains all the default pane settings.
        Adds a stylized background that every view uses.
     **/
    private void paneDefaults()
    {
        pane.setStyle(PioneerApplication.BACKGROUND_STYLE);
    }

    /**
        Quick method to add components to the view so a
        super.getPane().getChildren().add(####); is not
        needed to add a component to the pane.
     **/
    void addComponent(Node component)
    {
        this.getPane().getChildren().add(component);
    }

    /**
     Sets the scene back to the scene that originally
     created this scene.
     **/
    void returnView()
    {
        this.getStage().setScene(this.getScene());
    }

    void changeScene(Pane newPane)
    {
        this.getStage().setScene(new Scene(newPane, this.getWidth(), this.getHeight()));
    }

    /**
        Return the stage that is created at runtime.

        @return Stage that is created at runtime
     **/
    Stage getStage()
    {
        return this.stage;
    }

    /**
        Return the previous Scene that the Stage was on.
        Setting the Scene on the Stage will change it back to
        the previous scene.

        @return Previous scene that the view was on
     **/
    Scene getScene()
    {
        return this.scene;
    }

    /**
        Return the pane that all the current components
        are on. This pane is a part of the current scene.

        @return Pane that all the current components are on
     **/
    Pane getPane()
    {
        return this.pane;
    }

    /**
        Return the width that the scene is set to

        @return Number of pixels on the width
     **/
    int getWidth()
    {
        return this.width;
    }

    /**
        Return the height that the scene is set to

        @return Number of pixels on the height
     **/
    int getHeight()
    {
        return this.height;
    }
}