package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.defaultPlayerName;
import static ca.csf.minesweeper.controller.ControllerConsts.highScores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ca.csf.minesweeper.Configuration;
import ca.csf.minesweeper.model.GameDifficulty;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;

public class HighScoresWindowController extends SimpleFXController implements Initializable {

  @FXML
  Button btnOk;
  @FXML
  Label lblTimeBeginner;
  @FXML
  Label lblTimeIntermediate;
  @FXML
  Label lblTimeExpert;
  @FXML
  Label lblNameBeginner;
  @FXML
  Label lblNameIntermediate;
  @FXML
  Label lblNameExpert;
  
  public HighScoresWindowController(String playerName, int time) {
    if (playerName == null) {
      setHighScore(Configuration.selectedGameDifficulty, defaultPlayerName, time);
    }
    
    setHighScore(Configuration.selectedGameDifficulty, playerName, time);
  }
  
  public HighScoresWindowController() {
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    btnOk.setOnAction(e -> {
      Stage stage = ((Stage) btnOk.getScene().getWindow());
      stage.close();
    });

    loadData();
  }

  private void loadData() {
    ArrayList<String> bestTimeBeginner = highScores.getHighestScoreForDifficulty(GameDifficulty.BEGINNER);
    ArrayList<String> bestTimeIntermediate = highScores.getHighestScoreForDifficulty(GameDifficulty.INTERMEDIATE);
    ArrayList<String> bestTimeExpert = highScores.getHighestScoreForDifficulty(GameDifficulty.EXPERT);

    lblTimeBeginner.setText(bestTimeBeginner.get(1) + " secondes");
    lblNameBeginner.setText(bestTimeBeginner.get(0));

    lblTimeIntermediate.setText(bestTimeIntermediate.get(1) + " secondes");
    lblNameIntermediate.setText(bestTimeIntermediate.get(0));

    lblTimeExpert.setText(bestTimeExpert.get(1) + " secondes");
    lblNameExpert.setText(bestTimeExpert.get(0));
  }

  @FXML
  public void deleteHighScores() {
    SimpleFXDialogResult simpleFXDialogResult =
        SimpleFXDialogs.showMessageBox("Démineur",
            "Voulez-vous vraiment effacer tous les meilleurs scores?", SimpleFXDialogIcon.WARNING,
            SimpleFXDialogChoiceSet.YES_NO, SimpleFXDialogResult.NO, getSimpleFxStage());
    
    if (simpleFXDialogResult == SimpleFXDialogResult.YES) {
      highScores.deleteHighScores();
      loadData();
    }

  }

  private void setHighScore(GameDifficulty gameDifficulty, String playerName, int time) {
    highScores.setHighScore(gameDifficulty, playerName, time);
  }

}
