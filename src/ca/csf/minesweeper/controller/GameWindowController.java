package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_MINE;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_SMILE_HAPPY;
import static ca.csf.minesweeper.controller.ControllerConsts.resourcesPath;

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
import ca.csf.minesweeper.model.GameState;
import ca.csf.minesweeper.model.GameState.GameStates;
import ca.csf.minesweeper.model.GameTile;
import ca.csf.minesweeper.model.MinesweeperGame;
import ca.csf.minesweeper.model.Observer;
import ca.csf.minesweeper.model.Subject;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.SimpleFXStage;

public class GameWindowController extends SimpleFXController implements Initializable,
    Observer<GameTile> {

  private Timeline timeline;
  private ToggleButton[][] gameTiles;
  private IntegerProperty timePlayed;

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
  CheckMenuItem godMode;
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
    // lblremainingMines.textProperty().bind(timePlayed.asString()); // TODO: Delete this line
    timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> updateTimer()));
    timeline.setCycleCount(Animation.INDEFINITE);

    startNewGame();
  }

  public void populateGameBoard() {
    gameTiles =
        new ToggleButton[Configuration.selectedGameDifficulty.nbrOfRows][Configuration.selectedGameDifficulty.nbrOfColumns];

    for (int i = 0; i < Configuration.selectedGameDifficulty.nbrOfRows; i++) {
      for (int j = 0; j < Configuration.selectedGameDifficulty.nbrOfColumns; j++) {
        gameTiles[i][j] = new ToggleButton();
        gameTiles[i][j].setMinSize(36, 36);
        gameTiles[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gameTiles[i][j].setOnMouseReleased(new ToggleButtonEventHandler(i, j));
        gameTiles[i][j].setOnAction(this::disableToggleButtonOnAction);
        gameBoard.add(gameTiles[i][j], i, j);
      }
    }
  }

  public void disableToggleButtonOnAction(ActionEvent event) {
    ToggleButton toggleButton = (ToggleButton) event.getSource();
    // toggleButton.setDisable(true); // Once the button has been clicked, we

    // don't want the user to be
    // able to click it again
    // ToggleButton toggleButton = (ToggleButton) event.getSource();
    toggleButton.setSelected(true);

    // toggleButton.setStyle("-fx-background-color: black");
    toggleButton.setGraphic(new ImageView(IMAGE_MINE)); // TODO: TEMP
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
    argument.revealedGameTileAreaIsClean(); // TODO: TEMP
  }

  @FXML
  public void changeGodModeState() {
    // reveal mines
  }

  @FXML
  public void startNewGame() {
    populateGameBoard();
    timePlayed.setValue(0);
    lblremainingMines.setText(Integer.toString(Configuration.selectedGameDifficulty.nbrOfMines));
    btnNewGame.setGraphic(new ImageView(IMAGE_SMILE_HAPPY));
    timeline.playFromStart();
    game = new MinesweeperGame(Configuration.selectedGameDifficulty, this);
    
  }

}
