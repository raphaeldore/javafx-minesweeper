package ca.csf.minesweeper.model;

public class GameState {
  /*
   * 
   * TODO: -Timer -Flags -nbrOfRevealedTiles
   */

  private int flagCount;
  private int timePlayed; // TODO: When the game starts, the JavaFX timer starts (In the GameWindow
                          // Controller).
  private GameDifficulty difficulty;
  private GameStates state;

  GameState(GameDifficulty difficulty) {
    state = GameStates.PLAYING;
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

  public GameStates getState() {
    return state;
  }

  /**
   * @return the timePlayed
   */
  public int getTimePlayed() {
    return timePlayed;
  }
  
  public void lose() {
    //TODO: state = LOST
  }

}