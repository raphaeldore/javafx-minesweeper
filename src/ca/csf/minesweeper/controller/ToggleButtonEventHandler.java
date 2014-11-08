package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.resourcesPath;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ca.csf.minesweeper.Configuration;
import ca.csf.minesweeper.model.MinesweeperGame;

public class ToggleButtonEventHandler implements EventHandler<MouseEvent> {

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
    
    if (Configuration.godModeEnabled && event.getEventType() == MouseEvent.MOUSE_ENTERED && game.getIfTileIsMineAtPos(ROW, COLUMN) == true ) {
      ToggleButton sourceButton = (ToggleButton) event.getSource();
      sourceButton.setGraphic(new ImageView(resourcesPath + "Mine.png"));
    }
  }
}