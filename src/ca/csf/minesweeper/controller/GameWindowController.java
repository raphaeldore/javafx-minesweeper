package ca.csf.minesweeper.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import ca.csf.minesweeper.model.Observable;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;

public class GameWindowController extends SimpleFXController implements Initializable, Observable {

    private SimpleFXStage parentStage;

    private ToggleButton toggleButton = new ToggleButton();

    public void initialize(SimpleFXStage stage) {
	this.parentStage = stage;
    }

    @FXML
    public Button btnPatate;

    @FXML
    public void patate() {
	toggleButton.isArmed();
	BooleanProperty oulala = toggleButton.selectedProperty();
	SimpleFXDialogResult simpleFXDialogResult = SimpleFXDialogs.showMessageBox("My Application Name",
		"Do you want to save before you exit the application ?", SimpleFXDialogIcon.QUESTION,
		SimpleFXDialogChoiceSet.YES_NO_CANCEL, SimpleFXDialogResult.CANCEL, getSimpleFxStage());
	if (simpleFXDialogResult == SimpleFXDialogResult.YES) {
	    // Do something
	} else if (simpleFXDialogResult == SimpleFXDialogResult.NO) {
	    // Do something else
	} else if (simpleFXDialogResult == SimpleFXDialogResult.CANCEL) {
	    // Do some other thing
	}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}