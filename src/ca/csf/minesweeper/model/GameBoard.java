package ca.csf.minesweeper.model;

import java.util.Random;

/*
 * 
 * Represents a grid of GameTile instances.
 */

public class GameBoard extends Subject<GameBoard> {

  private GameTile[][] tiles;
  // TODO: Accept Configuration.GameDifficulty enum
  public GameBoard(int rowSize, int columnSize, int mineCount) {
    tiles = new GameTile[rowSize][columnSize];
    for (int i = 0; i < columnSize; i++) {
      for (int j = 0; j < rowSize; j++) {
        tiles[j][i] = new GameTile();
      }
    }
    
    while (mineCount > 0) {
      Random random = new Random();
      int row = random.nextInt(rowSize - 1);
      int column = random.nextInt(columnSize - 1);

      if (tiles[row][column].setAsMine()) {
        incrementExistingTile(row - 1, column - 1);
        incrementExistingTile(row - 1, column);
        incrementExistingTile(row, column - 1);
        incrementExistingTile(row + 1, column - 1);
        incrementExistingTile(row + 1, column + 1);
        incrementExistingTile(row - 1, column + 1);
        incrementExistingTile(row + 1, column);
        incrementExistingTile(row, column + 1);
        mineCount--;
      }
    }

  }

  public void incrementExistingTile(int row, int column) {
    if (row >= 0 && column >= 0) {
      tiles[row][column].incrementNeighboringMineCount();
    }
  }

  public void revealTile(int row, int column) {
    tiles[row][column].revealGameTile();
  }
  
}