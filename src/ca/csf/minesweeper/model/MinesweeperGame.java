package ca.csf.minesweeper.model;

/*
 * 
 * Main game loop class
 * 
 */

public class MinesweeperGame {
  GameBoard gameBoard;
  GameState gameState;
  
  MinesweeperGame (int difficulty) { // TODO: replace this int with difficulty enum
    gameState = new GameState();
    gameBoard = new GameBoard();
  }
  
  public void revealTile(int row, int column) {
    gameBoard.revealTile(row, column);
  }
  public static void main(String[] args) {
   // GameBoard gameBoard = new GameBoard<GameTile>();

  }
}
