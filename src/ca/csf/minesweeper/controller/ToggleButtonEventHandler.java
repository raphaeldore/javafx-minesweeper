package ca.csf.minesweeper.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ca.csf.minesweeper.model.MinesweeperGame;

class ToggleButtonEventHandler implements EventHandler<MouseEvent> {

  private final int COLUMN;
  private final int ROW;
  private final MinesweeperGame game;

  public ToggleButtonEventHandler(int row, int column, MinesweeperGame game) {
    COLUMN = column;
    ROW = row;
    this.game = game;
  }

  @Override
  public void handle(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) {
        game.revealTile(ROW, COLUMN);
        
    } else if (event.getButton() == MouseButton.SECONDARY) {      
      game.toggleTileState(ROW, COLUMN);
    }
  }
}