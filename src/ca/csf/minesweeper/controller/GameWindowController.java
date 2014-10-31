package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.resourcesPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
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
import ca.csf.simpleFx.SimpleFXScene;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.CheckMenuItem;

public class GameWindowController extends SimpleFXController implements Initializable,
    Observer<GameTile> {

  /* @formatter:off */
  public final Image IMAGE_ONE_MINE = new Image(getClass().getResourceAsStream(resourcesPath + "1.png"));
  public final Image IMAGE_TWO_MINES = new Image(getClass().getResourceAsStream(resourcesPath + "2.png"));
  public final Image IMAGE_TREE_MINES = new Image(getClass().getResourceAsStream(resourcesPath + "3.png"));
  public final Image IMAGE_FOUR_MINES = new Image(getClass().getResourceAsStream(resourcesPath + "4.png"));
  public final Image IMAGE_FIVE_MINES = new Image(getClass().getResourceAsStream(resourcesPath + "5.png"));
  public final Image IMAGE_SIX_MINES = new Image(getClass().getResourceAsStream(resourcesPath + "6.png"));
  public final Image IMAGE_SEVEN_MINES = new Image(getClass().getResourceAsStream(resourcesPath + "7.png"));
  public final Image IMAGE_EIGHT_MINES = new Image(getClass().getResourceAsStream(resourcesPath + "8.png"));
  public final Image IMAGE_FLAG = new Image(getClass().getResourceAsStream(resourcesPath + "Flag.png"));
  public final Image IMAGE_MINE = new Image(getClass().getResourceAsStream(resourcesPath + "Mine.png"));
  public final Image IMAGE_MINE_CROSS = new Image(getClass().getResourceAsStream(resourcesPath + "MineCross.png"));
  public final Image IMAGE_MINE_RED = new Image(getClass().getResourceAsStream(resourcesPath + "MineRed.png"));
  public final Image IMAGE_QUESTION_MARK = new Image(getClass().getResourceAsStream(resourcesPath + "QuestionMark.png"));
  public final Image IMAGE_SMILE_DEAD = new Image(getClass().getResourceAsStream(resourcesPath + "Smile_Dead.png"));
  public final Image IMAGE_SMILE_HAPPY = new Image(getClass().getResourceAsStream(resourcesPath + "Smile_Happy.png"));
  public final Image IMAGE_SMILE_NORMAL = new Image(getClass().getResourceAsStream(resourcesPath + "Smile_Normal.png"));
  public final Image IMAGE_SMILE_WORRY = new Image(getClass().getResourceAsStream(resourcesPath + "Smile_Worry.png"));
  /* @formatter:on */

  private SimpleFXStage parentStage;
  private Timer timer;
  private MinesweeperGame game;
  private Integer timePlayed = new Integer(0);
  private Timeline timeline;
  private ToggleButton[][] gameTiles;

  @FXML private BorderPane gameWindow;
  @FXML private GridPane gameBoard;
  @FXML public Button btnPatate;
  @FXML Label lblLabel1;
  @FXML MenuItem btnAboutWindow;
  @FXML MenuItem btnHelpWindow;
  @FXML ToggleGroup difficultyToggleGroup;
  @FXML RadioMenuItem beginnerDifficulty;
  @FXML RadioMenuItem intermediateDifficulty;
  @FXML RadioMenuItem hardDifficulty;
  @FXML CheckMenuItem godMode;
  @FXML Label lblTimer;
  @FXML Button btnNewGame;
  @FXML Label lblremainingMines;

  public void setStage(SimpleFXStage stage) {
    this.parentStage = stage;
  }

  public void timer() {
    // Basic timer template
    timeline =
        new Timeline(new KeyFrame(Duration.millis(1000),
            actionEvent -> lblTimer.setText(updateTimer()))); // TODO: Placeholder. Eventually
                                                               // replace with
                                                               // gameState.incrementTimePlayedByOneSecond()
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  public String updateTimer() {
    if (timePlayed == 998) {
      timeline.stop();
    }

    if (Configuration.currentGameState != GameStates.PAUSE) {
      timePlayed++;
    }

    return timePlayed.toString();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    beginnerDifficulty.setToggleGroup(difficultyToggleGroup);
    intermediateDifficulty.setToggleGroup(difficultyToggleGroup);
    hardDifficulty.setToggleGroup(difficultyToggleGroup);
    btnAboutWindow.setOnAction(this::openAboutWindow);
    btnHelpWindow.setOnAction(this::openHelpWindow);
    startNewGame();
  }

  public void populateGameBoard() {
    ToggleButton[][] gameTiles =
        new ToggleButton[Configuration.selectedGameDifficulty.nbrOfRows][Configuration.selectedGameDifficulty.nbrOfColumns];

    for (int i = 0; i < Configuration.selectedGameDifficulty.nbrOfRows; i++) {
      for (int j = 0; j < Configuration.selectedGameDifficulty.nbrOfColumns; j++) {
        gameTiles[i][j] = new ToggleButton();
        gameTiles[i][j].setPrefSize(16, 16);
        gameTiles[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gameTiles[i][j].setOnMouseReleased(new ToggleButtonEventHandler(i, j));
        gameTiles[i][j].setOnAction(this::disableToggleButtonOnAction);
        gameTiles[i][j].setGraphic(new ImageView(IMAGE_FLAG)); // TODO: TEMP
        gameBoard.add(gameTiles[i][j], i, j);
      }
    }
  }

  public void disableToggleButtonOnAction(ActionEvent event) {
    ToggleButton toggleButton = (ToggleButton) event.getSource();
    toggleButton.setDisable(true); // Once the button has been clicked, we don't want the user to be
                                   // able to click it again
  }

  public void openAboutWindow(ActionEvent event) {
    createDialog("../view/AboutWindow.fxml", "À propos", new AboutWindowController());
  }

  public void openHelpWindow(ActionEvent event) {
    createDialog("../view/HelpWindow.fxml", "Aide", new HelpWindowController());
  }
  
  private void createDialog(String fxmlPath, String windowName, SimpleFXController controller){
    try {
      SimpleFXScene scene =
          new SimpleFXScene(controller.getClass().getResource(fxmlPath),
              controller.getClass().getResource("../view/application.css"),
              controller);

      SimpleFXStage stage =
          new SimpleFXStage(windowName, StageStyle.UTILITY, scene, this.getSimpleFXApplication(),
              this.getSimpleFxStage());
      stage.setOnCloseRequest(new preventStageFromClosing());
      stage.sizeToScene();
      stage.centerOnScreen();
      stage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // TODO: decide whether to have the initialize function set the window name and stuff
  }

  // Prevents user from closing dialog boxes
  private class preventStageFromClosing implements EventHandler<WindowEvent> {
    @Override
    public void handle(WindowEvent event) {
      event.consume();
    }
  };

  @Override
  public void update(Subject<GameTile> sender, GameTile argument) {
    argument.revealGameTile(); // TODO: TEMP
  }

  @FXML public void changeGodModeState() {
    //reveal mines
  }

  @FXML public void startNewGame() {
    populateGameBoard();
    game = new MinesweeperGame(Configuration.selectedGameDifficulty, this);
    timer(); // TODO: change it to start on first click, also change the difficulty in a proper way
  }
}