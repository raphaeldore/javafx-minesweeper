package ca.csf.minesweeper.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import ca.csf.simpleFx.SimpleFXController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HighScoresWindowController extends SimpleFXController implements Initializable {

  @FXML Button btnOk;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    btnOk.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        Stage stage = ((Stage) btnOk.getScene().getWindow());
        stage.close();
      }
    });
  }
  
  

}
