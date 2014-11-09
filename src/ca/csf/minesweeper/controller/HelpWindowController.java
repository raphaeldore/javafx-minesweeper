package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.resourcesPath;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import ca.csf.simpleFx.SimpleFXController;

public class HelpWindowController extends SimpleFXController implements Initializable {

  @FXML
  WebView webView;
  @FXML
  private Button btnOk;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Font.loadFont(HelpWindowController.class.getResource(resourcesPath + "Enriqueta-Regular.ttf")
        .toExternalForm(), 10);

    Font.loadFont(HelpWindowController.class.getResource(resourcesPath + "Enriqueta-Bold.ttf")
        .toExternalForm(), 10);
    
    btnOk.setOnAction(e -> {
      Stage stage = ((Stage) btnOk.getScene().getWindow());
      stage.close();
  });

    webView.getEngine().load(getClass().getResource(resourcesPath + "Aide.html").toExternalForm());
  }

}
