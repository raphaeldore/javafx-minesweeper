package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import ca.csf.minesweeper.Configuration;
import ca.csf.minesweeper.model.GameDifficulty;
import ca.csf.minesweeper.model.GameStates;
import ca.csf.minesweeper.model.GameTile;
import ca.csf.minesweeper.model.MinesweeperGame;
import ca.csf.minesweeper.model.Observer;
import ca.csf.minesweeper.model.Subject;
import ca.csf.minesweeper.model.TileState;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;

public class GameWindowController extends SimpleFXController implements Initializable,
    Observer<GameTile> {

  private MinesweeperGame game;
  private Timeline timeline;
  private ToggleButton[][] gameTiles;
  private IntegerProperty timePlayed;
  private boolean isFirstClick;

  @FXML
  GridPane gameBoard;
  @FXML
  Label lblLabel1;
  @FXML
  MenuItem menuAboutWindow;
  @FXML
  MenuItem menuHelpWindow;
  @FXML
  ToggleGroup difficultyToggleGroup;
  @FXML
  RadioMenuItem menuBeginnerDifficulty;
  @FXML
  RadioMenuItem menuIntermediateDifficulty;
  @FXML
  RadioMenuItem menuHardDifficulty;
  @FXML
  MenuItem menuHighScores;
  @FXML
  CheckMenuItem menuGodMode;
  @FXML
  Label lblTimer;
  @FXML
  Button btnNewGame;
  @FXML
  Label lblremainingMines;

  public void updateTimer() {
    if (Configuration.currentGameState != GameStates.PAUSE) {
      timePlayed.setValue(timePlayed.getValue() + 1);
    }

    if (timePlayed.intValue() == 999) {
      timeline.stop();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Font.loadFont(GameWindowController.class.getResource(resourcesPath + "Terminus.ttf")
        .toExternalForm(), 10);
    timePlayed = new SimpleIntegerProperty(0);
    lblTimer.textProperty().bind(timePlayed.asString());
    timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> updateTimer()));
    timeline.setCycleCount(Animation.INDEFINITE);

    startNewGame();
  }

  public void populateGameBoard() {
    // ToggleButton[][] gameTiles =
    // new
    // ToggleButton[Configuration.selectedGameDifficulty.nbrOfRows][Configuration.selectedGameDifficulty.nbrOfColumns];
    gameTiles =
        new ToggleButton[Configuration.selectedGameDifficulty.nbrOfRows][Configuration.selectedGameDifficulty.nbrOfColumns];
    for (int i = 0; i < Configuration.selectedGameDifficulty.nbrOfRows; i++) {
      for (int j = 0; j < Configuration.selectedGameDifficulty.nbrOfColumns; j++) {
        gameTiles[i][j] = new ToggleButton();
        gameTiles[i][j].setMinSize(36, 36);
        gameTiles[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ToggleButtonEventHandler toggleButtonEventHandler =
            new ToggleButtonEventHandler(i, j, game);
        gameTiles[i][j].setOnMouseReleased(toggleButtonEventHandler);
        gameTiles[i][j].setOnMouseEntered(toggleButtonEventHandler);
        gameBoard.add(gameTiles[i][j], i, j);
      }
    }
    // TODO: get the Configuration.selectedDifficulty to work again and replace the numbers with it

    // gameTiles[i][j].setOnAction(this::disableToggleButtonOnAction);
  }

  @FXML
  public void openBestTimesWindow(ActionEvent event) {
    SimpleFXStage stage =
        new WindowBuilder().fxmlPath("../view/HighScoresWindow.fxml").windowName("Meilleurs Temps")
            .simpleFXController(new HighScoresWindowController())
            .simpleFXApplication(this.getSimpleFXApplication())
            .SimpleFXStage(this.getSimpleFxStage()).buildStage();

    stage.setResizable(false);
    stage.show();
  }

  @FXML
  public void openAboutWindow(ActionEvent event) {
    SimpleFXStage stage =
        new WindowBuilder().fxmlPath("../view/AboutWindow.fxml").windowName("À propos")
            .simpleFXController(new AboutWindowController())
            .simpleFXApplication(this.getSimpleFXApplication())
            .SimpleFXStage(this.getSimpleFxStage()).buildStage();

    stage.setResizable(false);
    stage.show();
  }

  @FXML
  public void openHelpWindow(ActionEvent event) {
    SimpleFXStage stage =
        new WindowBuilder().fxmlPath("../view/HelpWindow.fxml").windowName("Aide")
            .simpleFXController(new HelpWindowController())
            .simpleFXApplication(this.getSimpleFXApplication())
            .SimpleFXStage(this.getSimpleFxStage()).buildStage();

    stage.show();
  }

  @Override
  public void update(Subject<GameTile> sender, GameTile argument) {
    if (game.getGameState() == GameStates.LOST) {
      btnNewGame.setGraphic(new ImageView(IMAGE_SMILE_WORRY));
      // TODO: change interface to display defeat :O :(
      // TODO: reveal mines here too
      // TODO: prevent player from continuing to play
    } else if (game.getGameState() == GameStates.WON) {
      btnNewGame.setGraphic(new ImageView(IMAGE_SMILE_HAPPY));
      // TODO: something to congratulate the player
    }

    if (isFirstClick) {
      timeline.playFromStart();
      isFirstClick = false;
    }

    if (argument.getState() == TileState.FLAGGED) {
      gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_FLAG));
      gameTiles[argument.getROW()][argument.getCOLUMN()].setOnAction((event) -> {
        ToggleButton sourceButton = (ToggleButton) event.getSource();
        sourceButton.setSelected(false);
      });

    } else if (argument.getState() == TileState.QUESTIONNED) {
      gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
          IMAGE_QUESTION_MARK));
    } else if (argument.getState() == TileState.REVEALED) {

      // don't want the user to be able to click it again
      gameTiles[argument.getROW()][argument.getCOLUMN()].setDisable(true);

      // if tile is revealed without clicking it, we want to toggle it's state too
      gameTiles[argument.getROW()][argument.getCOLUMN()].setSelected(true);

      if (argument.isMine()) {
        // TODO: decide whether we should do something else regarding the loss here, also remove
        // comment
        // note that this means the game is lost
        gameTiles[argument.getROW()][argument.getCOLUMN()]
            .setGraphic(new ImageView(IMAGE_MINE_RED));
      } else {
        switch (argument.getNeighboringMineCount()) {
          case 0:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(null); // No image
            break;
          case 1:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
                IMAGE_ONE_MINE));
            break;
          case 2:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
                IMAGE_TWO_MINES));
            break;
          case 3:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
                IMAGE_THREE_MINES));
            break;
          case 4:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
                IMAGE_FOUR_MINES));
            break;
          case 5:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
                IMAGE_FIVE_MINES));
            break;
          case 6:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
                IMAGE_SIX_MINES));
            break;
          case 7:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
                IMAGE_SEVEN_MINES));
            break;
          case 8:
            gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(
                IMAGE_EIGHT_MINES));
            break;
        }
      }
    } else {
      gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(null); // No image
    }
  }

  @FXML
  public void changeGodModeState() {

    if (Configuration.godModeEnabled == false) {
      SimpleFXDialogs.showMessageBox("Démineur",
          "Lorsque votre souris survolera une mine, la tuile affichera une image de mine.",
          SimpleFXDialogIcon.INFORMATION, SimpleFXDialogChoiceSet.OK, SimpleFXDialogResult.OK,
          getSimpleFxStage());
    }

    Configuration.godModeEnabled = !Configuration.godModeEnabled;
  }

  @FXML
  public void startNewGame() {
    gameBoard.getChildren().clear();
    btnNewGame.setGraphic(new ImageView(IMAGE_SMILE_NORMAL));
    isFirstClick = true;
    timePlayed.setValue(0);
    lblremainingMines.setText(Integer.toString(Configuration.selectedGameDifficulty.nbrOfMines));
    game = new MinesweeperGame(Configuration.selectedGameDifficulty, this);
    populateGameBoard();
    gameBoard.autosize();
  }

  @FXML
  public void changeDifficultyToBeginner() {
    Configuration.selectedGameDifficulty = GameDifficulty.BEGINNER;
    startNewGame();
    this.getSimpleFxStage().sizeToScene();
  }

  @FXML
  public void changeDifficultyToIntermediate() {
    Configuration.selectedGameDifficulty = GameDifficulty.INTERMEDIATE;
    startNewGame();
    this.getSimpleFxStage().sizeToScene();
  }

  @FXML
  public void changeDifficultyToExpert() {
    Configuration.selectedGameDifficulty = GameDifficulty.EXPERT;
    startNewGame();
    this.getSimpleFxStage().sizeToScene();
  }

}
