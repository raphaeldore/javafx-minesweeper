package ca.csf.minesweeper.model;

/*
 * 
 * Main game loop class
 * 
 */

public class MinesweeperGame {
  GameBoard gameBoard;
  GameState gameState;
  
  MinesweeperGame (GameDifficulty difficulty) {
    gameState = new GameState(difficulty);
    gameBoard = new GameBoard(difficulty.nbrOfRows, difficulty.nbrOfColumns, difficulty.nbrOfMines, this);
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