package ca.csf.minesweeper.model;

/*
 * 
 * Main game loop class
 */

public class MinesweeperGame {
  GameBoard gameBoard;
  GameState gameState;

  public MinesweeperGame(GameDifficulty difficulty, Observer<GameTile> observer) {
    gameState = new GameState(difficulty);
    // gameBoard = new GameBoard(difficulty.nbrOfRows, difficulty.nbrOfColumns,
    // difficulty.nbrOfMines, this, observer );
    gameBoard = new GameBoard(9, 9, 10, this, observer);
  }

  public void revealTile(int row, int column) {
    gameBoard.revealTileArea(row, column);
  }
  
  public void toggleTileState(int row, int column) {
    gameBoard.toggleTileState(row, column);
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

  public void lose() {
    gameState.lose();
  }

  public GameStates getGameState() {
    return gameState.getState();
  }

  public void incrementTilesRevealed() {
    gameState.incrementTilesRevealed();
  }
  
}