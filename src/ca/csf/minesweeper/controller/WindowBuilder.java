package ca.csf.minesweeper.controller;

import java.io.IOException;

import javafx.stage.StageStyle;
import ca.csf.simpleFx.SimpleFXApplication;
import ca.csf.simpleFx.SimpleFXController;
import ca.csf.simpleFx.SimpleFXScene;
import ca.csf.simpleFx.SimpleFXStage;

/*
 * @author Raphaël Doré
 * 
 * 
 */
public class WindowBuilder extends SimpleFXController {

  SimpleFXApplication simpleFXApplication;
  SimpleFXStage simpleFXStage;
  SimpleFXScene scene;
  String fxmlPath;
  String windowName;
  SimpleFXController controller;

  public WindowBuilder() {}

  public SimpleFXStage buildStage() {

    try {
      setSimpleFxScene();
      return new SimpleFXStage(windowName, StageStyle.UTILITY, scene, simpleFXApplication,
          simpleFXStage);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  public WindowBuilder fxmlPath(String fxmlPath) {
    this.fxmlPath = fxmlPath;
    return this;
  }

  public WindowBuilder windowName(String windowName) {
    this.windowName = windowName;
    return this;
  }

  public WindowBuilder simpleFXController(SimpleFXController controller) {
    this.controller = controller;
    return this;
  }

  public WindowBuilder simpleFXApplication(SimpleFXApplication simpleFXApplication) {
    this.simpleFXApplication = simpleFXApplication;
    return this;
  }

  public WindowBuilder SimpleFXStage(SimpleFXStage simpleFXStage) {
    this.simpleFXStage = simpleFXStage;
    return this;
  }

  private void setSimpleFxScene() throws IOException {
    scene =
        new SimpleFXScene(controller.getClass().getResource(fxmlPath), controller.getClass()
            .getResource("../view/application.css"), controller);
  }

}
