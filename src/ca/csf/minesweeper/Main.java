package ca.csf.minesweeper;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import ca.csf.minesweeper.controller.GameWindowController;
import ca.csf.minesweeper.model.GameStates;
import ca.csf.simpleFx.SimpleFXApplication;
import ca.csf.simpleFx.SimpleFXApplicationLauncher;
import ca.csf.simpleFx.SimpleFXScene;
import ca.csf.simpleFx.SimpleFXStage;
import ca.csf.simpleFx.events.WindowFocusEvent;

public class Main extends SimpleFXApplication {
  public static void main(String[] args) {
    SimpleFXApplicationLauncher.startSimpleFXApplication(Main.class, args);
  }

  public void start() {
    try {
      SimpleFXScene simpleFXScene =
          new SimpleFXScene(GameWindowController.class.getResource("../view/GameWindow.fxml"),
              GameWindowController.class.getResource("../view/application.css"),
              new GameWindowController());

      SimpleFXStage simpleFXStage =
          new SimpleFXStage("Démineur", StageStyle.DECORATED, simpleFXScene, this);
      simpleFXStage.centerOnScreen();
      simpleFXStage.setOnFocusChanged(new EventHandler<WindowFocusEvent>() {

        @Override
        public void handle(WindowFocusEvent event) {
          if (simpleFXStage.isFocused()) {
            System.out.println("FOCUS");
            if (Configuration.currentGameState == GameStates.PAUSE) {
              Configuration.currentGameState = GameStates.PLAYING;
            }
          } else {
            System.out.println("NO FOCUS");
            Configuration.currentGameState = GameStates.PAUSE;
          }
        }
      });
      simpleFXStage.getInternalJavaFXStage().getIcons().add(new Image("file:src/resources/icon.png"));
      simpleFXStage.setResizable(false);
      simpleFXStage.setTitle("Démineur");
      simpleFXStage.show();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
