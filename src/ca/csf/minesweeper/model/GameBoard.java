package ca.csf.minesweeper.model;

import java.util.Random;

/*
 * 
 * Represents a grid of GameTile instances.
 */

public class GameBoard extends Subject<GameBoard> {

  private GameTile[][] tiles;

  public GameBoard(int rowSize, int columnSize, int mineCount) {
    tiles = new GameTile[rowSize][columnSize];
    for (int i = 0; i < columnSize; i++) {
      for (int j = 0; j < rowSize; j++) {
        tiles[j][i] = new GameTile();
      }
    }


    while (mineCount > 0) {
      Random random = new Random();
      int x = random.nextInt(rowSize - 1);
      int y = random.nextInt(columnSize - 1);

      if (tiles[x][y].setAsMine()) {
        incrementExistingTile(x - 1, y - 1);
        incrementExistingTile(x - 1, y);
        incrementExistingTile(x, y - 1);
        incrementExistingTile(x + 1, y - 1);
        incrementExistingTile(x + 1, y + 1);
        incrementExistingTile(x - 1, y + 1);
        incrementExistingTile(x + 1, y);
        incrementExistingTile(x, y + 1);
        mineCount--;
      }
    }

  }

  public void incrementExistingTile(int row, int column) {
    if (row >= 0 && column >= 0) {
      tiles[row][column].incrementNeighboringMineCount();
    }
  }

}
