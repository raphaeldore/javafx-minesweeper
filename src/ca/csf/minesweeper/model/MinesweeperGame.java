package ca.csf.minesweeper.model;

/*
 * Interface between the controllers and the model
 */

public class MinesweeperGame {
  private final GameBoard gameBoard;
  private final GameState gameState;

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
    gameBoard.setWrongFlagsAsCrossed();
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