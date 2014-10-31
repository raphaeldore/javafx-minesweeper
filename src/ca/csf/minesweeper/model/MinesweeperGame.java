package ca.csf.minesweeper.model;

/*
 * 
 * Main game loop class
 * 
 */

public class MinesweeperGame {
  GameBoard gameBoard;
  GameState gameState;
  
public MinesweeperGame (GameDifficulty difficulty, Observer observer) {
    gameState = new GameState(difficulty, observer);
    gameBoard = new GameBoard(difficulty.nbrOfRows, difficulty.nbrOfColumns, difficulty.nbrOfMines, this, observer);
  }
  
  public void revealTile(int row, int column) {
    gameBoard.revealTile(row, column);
  }

  public void alternateReminder(int row, int column) {
    gameBoard.toggleTileState(row, column);
  }
  
  public void incrementFlagCount() {
    gameState.incrementFlagCount();
  }

  public void decrementFlagCount() {
    gameState.decrementFlagCount();
  }
  
}