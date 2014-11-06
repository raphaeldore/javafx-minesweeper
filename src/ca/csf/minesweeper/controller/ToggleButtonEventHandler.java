package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.*;
import ca.csf.minesweeper.model.MinesweeperGame;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ToggleButtonEventHandler implements EventHandler<MouseEvent> {

  private final int COLUMN;
  private final int ROW;
  private int counter = 1;
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
      ToggleButton sourceButton = (ToggleButton) event.getSource();
      
      /* @formatter:off */
      
            /*
             *  Here we cycle between tree states. We use modular arithmetic to achieve this (https://en.wikipedia.org/wiki/Modular_arithmetic)
             *  For example, imagine the following clock (or use http://www.shodor.org/interactivate/activities/ClockArithmetic/ and create a clock of size 3):
             *    
                _.'_____`._
              .'.-'  0  `-.`.
             /,'           `.\
            //       |       \\
           ;;        |        ::
           ||        O        ||
           ::  2           1  ;;
            \\               //
             \`.           ,'/
              '.`-.__ __.-'.'
               ((-._____.-))
               _))       ((_
              '--'       '--'
            
             *  If we advance the time by two places (0 + 2), the clock hand will rest a 2 (as we would expect)
             *  But if we advance the time by 3 places (0 + 3), the clock hand will rest a 0. Thus it looped on itself.
             *  
             *  Very simple concept, very easy to implement, and is perfect for out situation.
             */
      
      /* @formatter:on */

//      if (counter % 3 == 0) {
//        sourceButton.setGraphic(null);
//      } else if (counter % 3 == 1) {
//        sourceButton.setGraphic(new ImageView(IMAGE_FLAG));
//      } else {
//        sourceButton.setGraphic(new ImageView(IMAGE_QUESTION_MARK));
//      }
//
//      counter++;
    }
  }

}