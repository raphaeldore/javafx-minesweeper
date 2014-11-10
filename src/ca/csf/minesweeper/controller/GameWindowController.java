package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_EIGHT_MINES;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_FIVE_MINES;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_FLAG;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_FOUR_MINES;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_MINE;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_MINE_RED;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_ONE_MINE;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_QUESTION_MARK;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_SEVEN_MINES;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_SIX_MINES;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_SMILE_DEAD;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_SMILE_HAPPY;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_SMILE_NORMAL;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_THREE_MINES;
import static ca.csf.minesweeper.controller.ControllerConsts.IMAGE_TWO_MINES;
import static ca.csf.minesweeper.controller.ControllerConsts.resourcesPath;
import static ca.csf.minesweeper.controller.ControllerConsts.highScores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;

public class GameWindowController extends SimpleFXController implements Initializable,
    Observer<GameTile> {

  private MinesweeperGame game;
  private Timeline timeline;
  private ToggleButton[][] gameTiles;
  private IntegerProperty timePlayed;
  private boolean isFirstClick;
  private boolean isHighestScoreForDifficulty;
  private String playerName;

  @FXML
  GridPane gameBoard;
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

  void updateTimer() {
    if (Configuration.currentGameState != GameStates.PAUSE) {
      timePlayed.setValue(timePlayed.getValue() + 1);
    }

    if (timePlayed.intValue() == 999) {
      timeline.stop();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    playerName = "NoName";
    Font.loadFont(GameWindowController.class.getResource(resourcesPath + "Terminus.ttf")
        .toExternalForm(), 10);
    timePlayed = new SimpleIntegerProperty(0);
    lblTimer.textProperty().bind(timePlayed.asString());
    timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> updateTimer()));
    timeline.setCycleCount(Animation.INDEFINITE);

    startNewGame();
  }

  void populateGameBoard() {
    gameTiles =
        new ToggleButton[Configuration.selectedGameDifficulty.nbrOfRows][Configuration.selectedGameDifficulty.nbrOfColumns];
    for (int i = 0; i < Configuration.selectedGameDifficulty.nbrOfRows; i++) {
      for (int j = 0; j < Configuration.selectedGameDifficulty.nbrOfColumns; j++) {
        gameTiles[i][j] = new ToggleButton();
        gameTiles[i][j].setMinSize(36, 36);
        gameTiles[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ToggleButtonEventHandler toggleButtonEventHandler = new ToggleButtonEventHandler(i, j, game);
        gameTiles[i][j].setOnMouseReleased(toggleButtonEventHandler);
        gameTiles[i][j].setOnMouseEntered(toggleButtonEventHandler);
        // Disables keypresses on the ToggleButtons:
        gameTiles[i][j].setOnAction((event) -> {
          ToggleButton sourceButton = (ToggleButton) event.getSource();
          sourceButton.setSelected(false); 
        });
        gameBoard.add(gameTiles[i][j], i, j);
      }
    }
  }

  @FXML
  public void openBestTimesWindow() {
    HighScoresWindowController highScoresWindowController;
    if (isHighestScoreForDifficulty && game.getGameState() == GameStates.WON ) {
      highScoresWindowController = new HighScoresWindowController(playerName, timePlayed.get());
    } else {
      highScoresWindowController = new HighScoresWindowController();
    }
    
    SimpleFXStage stage =
        new WindowBuilder().fxmlPath("../view/HighScoresWindow.fxml").windowName("Meilleurs Temps")
            .simpleFXController(highScoresWindowController)
            .simpleFXApplication(this.getSimpleFXApplication())
            .SimpleFXStage(this.getSimpleFxStage()).buildStage();

    stage.setResizable(false);
    stage.show();
  }

  @FXML
  public void openAboutWindow() {
    SimpleFXStage stage =
        new WindowBuilder().fxmlPath("../view/AboutWindow.fxml").windowName("À propos")
            .simpleFXController(new AboutWindowController())
            .simpleFXApplication(this.getSimpleFXApplication())
            .SimpleFXStage(this.getSimpleFxStage()).buildStage();

    stage.setResizable(false);
    stage.show();
  }

  @FXML
  public void openHelpWindow() {
    SimpleFXStage stage =
        new WindowBuilder().fxmlPath("../view/HelpWindow.fxml").windowName("Aide")
            .simpleFXController(new HelpWindowController())
            .simpleFXApplication(this.getSimpleFXApplication())
            .SimpleFXStage(this.getSimpleFxStage()).buildStage();

    stage.sizeToScene();
    stage.show();
  }

  @Override
  public void update(Subject<GameTile> sender, GameTile argument) {
    if (game.getGameState() != GameStates.PLAYING && argument.getState() != TileState.FLAGGED) {
      timeline.stop();
      if (game.getGameState() == GameStates.LOST) {
        btnNewGame.setGraphic(new ImageView(IMAGE_SMILE_DEAD));
        for (int i = 0; i < Configuration.selectedGameDifficulty.nbrOfRows; i++) {
          for (int j = 0; j < Configuration.selectedGameDifficulty.nbrOfColumns; j++) {
            gameTiles[i][j].setDisable(true);
          }
        }
      } else { //if he is not playing anymore and has not lost, then he has won
        won();
      }
    }
    
    if (isFirstClick && argument.getState() != TileState.FLAGGED && argument.getState() != TileState.QUESTIONNED) {
      timeline.playFromStart();
      isFirstClick = false;
    }

    switch (argument.getState()) {
      case FLAGGED:
        gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_FLAG));
        break;
      case QUESTIONNED:
        gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_QUESTION_MARK));
        break;
      case MINE_REVEALED:
        gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_MINE));
        break;
      case REVEALED:
        
        // don't want the user to be able to click it again
        gameTiles[argument.getROW()][argument.getCOLUMN()].setDisable(true);

        // if tile is revealed without clicking it, we want to toggle it's state too
        gameTiles[argument.getROW()][argument.getCOLUMN()].setSelected(true);

        if (argument.isMine()) {
          gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_MINE_RED));
        } else {
          switch (argument.getNeighboringMineCount()) {
            case 0:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(null); // No image
              break;
            case 1:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_ONE_MINE));
              break;
            case 2:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_TWO_MINES));
              break;
            case 3:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_THREE_MINES));
              break;
            case 4:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_FOUR_MINES));
              break;
            case 5:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_FIVE_MINES));
              break;
            case 6:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_SIX_MINES));
              break;
            case 7:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_SEVEN_MINES));
              break;
            case 8:
              gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(new ImageView(IMAGE_EIGHT_MINES));
              break;
          }
        }
        break;
        
      case HIDDEN:
        gameTiles[argument.getROW()][argument.getCOLUMN()].setGraphic(null); // No image
        break;
    }
    lblremainingMines.setText(Integer.toString(Configuration.selectedGameDifficulty.nbrOfMines - game.getFlagCount()));
  }

  @FXML
  public void changeGodModeState() {
    Configuration.godModeEnabled = !Configuration.godModeEnabled;
    if (Configuration.godModeEnabled) {
      game.revealMines();
    } else {
      game.hideMines();
    }
  }

  void won() {
    btnNewGame.setGraphic(new ImageView(IMAGE_SMILE_HAPPY));
    // TODO: something to congratulate the player as well as update the highscore
    for (int i = 0; i < Configuration.selectedGameDifficulty.nbrOfRows; i++) {
      for (int j = 0; j < Configuration.selectedGameDifficulty.nbrOfColumns; j++) {
        gameTiles[i][j].setDisable(true);
      }
    }
    
    isHighestScoreForDifficulty = highScores.isHighestScoreForDifficulty(Configuration.selectedGameDifficulty.difficultyName, timePlayed.get());
    if (isHighestScoreForDifficulty) {
      playerName = SimpleFXDialogs.showInputBox(getSimpleFxStage().getTitle(), "Félicitation! Vous avez le meilleur score pour cette difficulté. Quel est votre nom ?", playerName, getSimpleFxStage());
      openBestTimesWindow();
    }
    
    isHighestScoreForDifficulty = false;
    
  }

  @FXML
  public void startNewGame() {
    Configuration.godModeEnabled = false;
    menuGodMode.setSelected(false);
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

  //TODO: 1. make timer start on first reveal rather than click
  //TODO: 2. Update highscores on win if necessary
  //TODO: 3. disable god mode on win/loss
  //TODO: 4. allow for erasing of the highscores
  //TODO: 5. allow for saving of the highscores
  //TODO: 6. put normal values only as default highscores and clean it's interface up
  
  //TODO: Test classes
  //TODO: Programming folder
  //TODO: teammate report
}