package ca.csf.minesweeper;

import javafx.stage.StageStyle;
import ca.csf.minesweeper.controller.GameWindowController;
import ca.csf.simpleFx.SimpleFXApplication;
import ca.csf.simpleFx.SimpleFXApplicationLauncher;
import ca.csf.simpleFx.SimpleFXScene;
import ca.csf.simpleFx.SimpleFXStage;

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
      simpleFXStage.setResizable(false);
      simpleFXStage.setTitle("Démineur");
      simpleFXStage.show();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
