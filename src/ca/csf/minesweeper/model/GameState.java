package ca.csf.minesweeper.model;

import java.util.concurrent.atomic.AtomicInteger;

public class GameState extends Subject<MinesweeperGame>{
  /*
   * TODO: -Timer -Flags -nbrOfRevealedTiles
   */

  private int flagCount;
  private int timePlayed; // TODO: When the game starts, the JavaFX timer starts (In the GameWindow
                          // Controller).
  private GameDifficulty difficulty;

  GameState(GameDifficulty difficulty) {
    this.difficulty = difficulty;
  }

  public int getFlagCount() {
    return flagCount;
  }

  public GameDifficulty getDifficulty() {
    return difficulty;
  }

  public void incrementFlagCount() {
    flagCount++;
  }

  public void decrementFlagCount() {
    flagCount--;
  }

  public static enum GameStates {
    START, PAUSE, PLAYING, WON;
  }

  /**
   * @return the timePlayed
   */
  public int getTimePlayed() {
    return timePlayed;
  }

}