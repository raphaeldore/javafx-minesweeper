package ca.csf.minesweeper.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ToggleButtonEventHandler implements EventHandler<MouseEvent> {

  private int column;
  private int row;

  public ToggleButtonEventHandler(int column, int row) {
    this.column = column;
    this.row = row;
  }

  @Override
  public void handle(MouseEvent event) {
    if (event.isPrimaryButtonDown()) {
      //TODO: Implement what happens when left click
    } else if (event.isSecondaryButtonDown()) {
      //TODO: Implement what happens when right click
    }
  }

}
