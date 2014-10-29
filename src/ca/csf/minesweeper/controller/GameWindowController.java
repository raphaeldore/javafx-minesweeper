package ca.csf.minesweeper.controller;

import java.io.Console;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;
import ca.csf.minesweeper.Configuration;
import ca.csf.minesweeper.model.GameBoard;
import ca.csf.minesweeper.model.GameState;
import ca.csf.minesweeper.model.GameState.GameStates;
import ca.csf.minesweeper.model.GameTile;
import ca.csf.minesweeper.model.Observer;
import ca.csf.minesweeper.model.Subject;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;
import ca.csf.simpleFx.events.WindowFocusEvent;

public class GameWindowController extends SimpleFXController implements Initializable,
    Observer<GameTile> {

  private final String resourcesPath = "../../../../resources/";

  private SimpleFXStage parentStage;
  private Timer timer;
  private GameState gameState;
  private Integer timePlayed = new Integer(0);
  private Timeline timeline;
  private ToggleButton toggleButton = new ToggleButton();
  private ToggleButton[][] gameTiles;

  @FXML
  private BorderPane gameWindow;
  @FXML
  private GridPane gameBoard;
  @FXML
  public Button btnPatate;
  @FXML
  Label lblLabel1;


  /*
   * How to add a custom EventHandler: ToggleButton toggleButton = new ToggleButton();
   * toggleButton.setOnMouseReleased(new ToggleButtonEventHandler(0,0));
   */

  public void setStage(SimpleFXStage stage) {
    this.parentStage = stage;
  }

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

  @FXML
  public void havok() {
    startGame();
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

    if (Configuration.currentGameState != GameStates.PAUSE) {
      timePlayed++;
    }

    return timePlayed.toString();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    timer();
    startGame();
  }

  public void startGame() {
    populateGameBoard();
  }

  public void populateGameBoard() {
    ToggleButton[][] gameTiles =
        new ToggleButton[Configuration.selectedGameDifficulty.nbrOfRows][Configuration.selectedGameDifficulty.nbrOfColumns];

    System.out.println(Configuration.selectedGameDifficulty.nbrOfRows);
    System.out.println(Configuration.selectedGameDifficulty.nbrOfColumns);

    for (int i = 0; i < Configuration.selectedGameDifficulty.nbrOfRows; i++) {
      for (int j = 0; j < Configuration.selectedGameDifficulty.nbrOfColumns; j++) {
        ToggleButton gameTile = new ToggleButton();
        gameTiles[i][j] = new ToggleButton("x"); // TODO: TEMP
        gameTiles[i][j].setPrefSize(16, 16);
        gameTiles[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gameTiles[i][j].setOnMouseReleased(new ToggleButtonEventHandler(i, j));
        // gameTiles[i][j].setGraphic(new
        // ImageView(ClassLoader.getSystemResource("/src/resources/QuestionMark.png").toExternalForm()));
        gameBoard.add(gameTiles[i][j], i, j);
      }
    }

    // this.getSimpleFxStage().sizeToScene();


  }

  @Override
  public void update(Subject<GameTile> sender, GameTile argument) {
    argument.revealGameTile(); // TODO: TEMP
  }

}
