package ca.csf.minesweeper.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;
import ca.csf.minesweeper.model.GameState;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;
import javafx.scene.control.Label;

public class GameWindowController extends SimpleFXController implements Initializable {

  private SimpleFXStage parentStage;
  private Timer timer;
  private GameState gameState;
  private Integer timePlayed = new Integer(980);
  private Timeline timeline;
  private ToggleButton toggleButton = new ToggleButton();

  public void initialize(SimpleFXStage stage) {
    this.parentStage = stage;
  }

  @FXML
  public Button btnPatate;
  @FXML
  Label lblLabel1;

  @FXML
  public void patate() {
    toggleButton.isArmed();
    BooleanProperty oulala = toggleButton.selectedProperty();
    SimpleFXDialogResult simpleFXDialogResult =
        SimpleFXDialogs.showMessageBox("My Application Name",
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

  public void timer() {
    // Basic timer template
    timeline =
        new Timeline(new KeyFrame(Duration.millis(1000),
            actionEvent -> lblLabel1.setText(updateTimer()))); // TODO: Placeholder. Eventually
                                                               // replace with
                                                               // gameState.incrementTimePlayedByOneSecond()
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  public String updateTimer() {
    if (timePlayed == 998) {
      timeline.stop();
    }

    timePlayed++;
    return timePlayed.toString();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // gameState = new GameState();
    timer();
  }
}
