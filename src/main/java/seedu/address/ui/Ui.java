package seedu.address.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /** Displays a non-fatal message collected during initialization. */
    void showStartupErrorMessage(String message);

}
