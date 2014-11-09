package ca.csf.minesweeper.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ca.csf.minesweeper.model.GameDifficulty;
import ca.csf.minesweeper.model.HighScore;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;

public class HighScoresWindowController extends SimpleFXController implements Initializable {

  HighScore highScore;

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

  @Override
  public void initialize(URL location, ResourceBundle resources) {


    btnOk.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        Stage stage = ((Stage) btnOk.getScene().getWindow());
        stage.close();
      }
    });

    loadData();

  }

  private void loadData() {
    highScore = new HighScore();
    ArrayList<String> bestTimeBeginner = highScore.getHighestScoreBeginner();
    ArrayList<String> bestTimeIntermediate = highScore.getHighestScoreIntermediate();
    ArrayList<String> bestTimeExpert = highScore.getHighestScoreExpert();

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
      highScore.deleteHighScores();
      loadData();
    }

  }

  @FXML
  public void setTestHighScore() {
    highScore.setHighScore(GameDifficulty.BEGINNER.difficultyName, "Raphaël Doré",
        123);
    highScore.setHighScore(GameDifficulty.INTERMEDIATE.difficultyName,
        "Ginette Renault", 532);
    highScore.setHighScore(GameDifficulty.EXPERT.difficultyName, "Johny Capala", 312);

    loadData();
  }
}
