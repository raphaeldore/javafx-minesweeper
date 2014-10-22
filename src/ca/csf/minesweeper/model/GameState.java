package ca.csf.minesweeper.model;

import java.util.concurrent.atomic.AtomicInteger;

public class GameState {
  /*
   * TODO: -Timer -Flags -nbrOfRevealedTiles
   */

  private int flagCount;
  private int timePlayed; // TODO: When the game starts, the JavaFX timer starts (In the GameWindow Controller).

  public static enum GameStates {
    START, PAUSE, PLAYING, WON;
  }

  /**
   * @return the timePlayed
   */
  public int getTimePlayed() {
    return timePlayed;
  }

  /**
   * @param timePlayed the timePlayed to set
   */
  public void incrementTimePlayedByOneSecond() {
    timePlayed++;
  }
  
  

}
