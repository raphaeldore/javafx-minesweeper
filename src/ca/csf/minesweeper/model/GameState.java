package ca.csf.minesweeper.model;

public class GameState {

  private int flagCount;
  private GameDifficulty difficulty;
  private GameStates state;
  private int revealedTiles;
  private MinesweeperGame game;

  GameState(GameDifficulty difficulty, MinesweeperGame game) {
    state = GameStates.PLAYING;
    this.difficulty = difficulty;
    revealedTiles = 0;
    this.game = game;
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

  public void incrementTilesRevealed() {
    revealedTiles++;
    if (revealedTiles == (difficulty.nbrOfColumns * difficulty.nbrOfRows) - difficulty.nbrOfMines) {
      state = GameStates.WON;
      game.win();
    }
  }
}