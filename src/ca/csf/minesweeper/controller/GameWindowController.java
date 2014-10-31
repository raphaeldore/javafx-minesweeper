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
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import ca.csf.minesweeper.Configuration;
import ca.csf.minesweeper.model.GameState;
import ca.csf.minesweeper.model.GameState.GameStates;
import ca.csf.minesweeper.model.GameTile;
import ca.csf.minesweeper.model.Observer;
import ca.csf.minesweeper.model.Subject;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.SimpleFXScene;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.dialogs.SimpleFXDialogChoiceSet;
import ca.csf.simpleFx.dialogs.SimpleFXDialogIcon;
import ca.csf.simpleFx.dialogs.SimpleFXDialogResult;
import ca.csf.simpleFx.dialogs.SimpleFXDialogs;

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
  @FXML
  Button btnAboutWindow;
  @FXML
  Button btnHelpWindow;


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
    btnAboutWindow.setOnAction(this::openAboutWindow);
    btnHelpWindow.setOnAction(this::openHelpWindow);
    timer();
    startGame();
  }

  public void startGame() {
    populateGameBoard();
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
    try {
      SimpleFXScene scene =
          new SimpleFXScene(AboutWindowController.class.getResource("../view/AboutWindow.fxml"),
              AboutWindowController.class.getResource("../view/application.css"),
              new AboutWindowController());

      SimpleFXStage stage =
          new SimpleFXStage("About", StageStyle.UTILITY, scene, this.getSimpleFXApplication(),
              this.getSimpleFxStage());
      
      stage.setOnCloseRequest(new preventStageFromClosing());
      stage.setResizable(false);
      stage.centerOnScreen();
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openHelpWindow(ActionEvent event) {
    try {
      SimpleFXScene scene =
          new SimpleFXScene(HelpWindowController.class.getResource("../view/HelpWindow.fxml"),
              HelpWindowController.class.getResource("../view/application.css"),
              new HelpWindowController());

      SimpleFXStage stage =
          new SimpleFXStage("Help!", StageStyle.UTILITY, scene, this.getSimpleFXApplication(),
              this.getSimpleFxStage());
      stage.setOnCloseRequest(new preventStageFromClosing());
      stage.centerOnScreen();
      stage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
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

}
