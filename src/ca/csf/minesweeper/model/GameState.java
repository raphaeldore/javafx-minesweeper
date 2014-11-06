package ca.csf.minesweeper.model;

public class GameState {
  /*
   * 
   * TODO: -nbrOfRevealedTiles
   */

  private int flagCount;
  private GameDifficulty difficulty;
  private GameStates state;
  private int revealedTiles;

  GameState(GameDifficulty difficulty) {
    state = GameStates.PLAYING;
    this.difficulty = difficulty;
    revealedTiles = 0;
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

  public GameStates getState() {
    return state;
  }
  
  public void lose() {
    state = GameStates.LOST;
  }

}