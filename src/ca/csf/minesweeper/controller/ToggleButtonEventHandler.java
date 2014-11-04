package ca.csf.minesweeper.controller;

import static ca.csf.minesweeper.controller.ControllerConsts.*;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ToggleButtonEventHandler implements EventHandler<MouseEvent> {

  private int column;
  private int row;
  private int counter = 1;

  public ToggleButtonEventHandler(int column, int row) {
    this.column = column;
    this.row = row;
  }

  @Override
  public void handle(MouseEvent event) {
    if (event.isPrimaryButtonDown()) {

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

      if (counter % 3 == 0) {
        sourceButton.setGraphic(null);
      } else if (counter % 3 == 1) {
        sourceButton.setGraphic(new ImageView(IMAGE_FLAG));
      } else {
        sourceButton.setGraphic(new ImageView(IMAGE_QUESTION_MARK));
      }

      counter++;
    }
  }

}