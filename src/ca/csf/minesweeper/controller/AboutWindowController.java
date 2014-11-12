package ca.csf.minesweeper.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ca.csf.simpleFx.SimpleFXController;

public class AboutWindowController extends SimpleFXController implements Initializable {

  @FXML
  Button btnOk;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    btnOk.setOnAction(e -> {
      Stage stage = ((Stage) btnOk.getScene().getWindow());
      stage.close();
    });
  }

}
