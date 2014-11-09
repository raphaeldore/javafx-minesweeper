package ca.csf.minesweeper.model;

/*
 * 
 * Main game loop class
 */

public class MinesweeperGame {
  private GameBoard gameBoard;
  private GameState gameState;

  public MinesweeperGame(GameDifficulty difficulty, Observer<GameTile> observer) {
    gameState = new GameState(difficulty, this);
    gameBoard = new GameBoard(difficulty, this, observer );
  }
  
  public void win() {
    gameBoard.setMinesAsFlags();
  }

  public void revealTile(int row, int column) {
    gameBoard.revealTileArea(row, column);
  }
    
  public void toggleTileState(int row, int column) {
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
    gameBoard.revealMines();  
  }

  public GameStates getGameState() {
    return gameState.getState();
  }

  public void incrementTilesRevealed() {
    gameState.incrementTilesRevealed();
  }
  
  public void hideMines() {
    gameBoard.hideMines();
  }
  
  public void revealMines() {
    gameBoard.revealMines();  
  }

  public int getFlagCount() {
    return gameState.getFlagCount();
  }
}