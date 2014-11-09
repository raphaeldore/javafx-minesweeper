package ca.csf.minesweeper.model;

import java.util.Random;

import ca.csf.minesweeper.Configuration;

/*
 * 
 * Represents a grid of GameTile instances.
 */

public class GameBoard {

  private GameDifficulty difficulty;
  private GameTile[][] tiles;

  public GameBoard(GameDifficulty gameDifficulty, MinesweeperGame game, Observer<GameTile> observer) {
    difficulty = gameDifficulty;
    int mineCount = difficulty.nbrOfMines;
    tiles = new GameTile[difficulty.nbrOfRows][difficulty.nbrOfColumns];
    for (int i = 0; i < difficulty.nbrOfColumns; i++) {
      for (int j = 0; j < difficulty.nbrOfRows; j++) {
        tiles[j][i] = new GameTile(game, observer, j, i);
      }
    }

    while (mineCount > 0) {
      Random random = new Random();
      int row = random.nextInt(difficulty.nbrOfRows - 1);
      int column = random.nextInt(difficulty.nbrOfColumns - 1);

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

  public void setMinesAsFlags() {
    for (int i = 0; i < difficulty.nbrOfRows; i++) {
      for (int j = 0; j < difficulty.nbrOfColumns; j++) {
        tiles[i][j].setMinesAsFlags();
      }
    }
  }

  private void incrementExistingTile(int row, int column) {
    if (row >= 0 && column >= 0) {
      tiles[row][column].incrementNeighboringMineCount();
    }
  }

  public void revealTileArea(int row, int column) {
    if (row >= 0 && column >= 0 && row <= difficulty.nbrOfRows - 1 && column <= difficulty.nbrOfColumns - 1) {
      if (tiles[row][column].revealedGameTileAreaIsClean()) {
        revealTileArea(row - 1, column - 1);
        revealTileArea(row - 1, column);
        revealTileArea(row, column - 1);
        revealTileArea(row + 1, column - 1);
        revealTileArea(row + 1, column + 1);
        revealTileArea(row - 1, column + 1);
        revealTileArea(row + 1, column);
        revealTileArea(row, column + 1);
      }
    }
  }

  public void toggleTileState(int row, int column) {
    tiles[row][column].toggleState();
  }

  public void revealMines() {
    for (int i = 0; i < difficulty.nbrOfRows; i++) {
      for (int j = 0; j < difficulty.nbrOfColumns; j++) {
        tiles[i][j].revealIfMine();
      }
    }
  }

  public void hideMines() {
    for (int i = 0; i < difficulty.nbrOfRows; i++) {
      for (int j = 0; j < difficulty.nbrOfColumns; j++) {
        tiles[i][j].hideIfReavealedMine();
      }
    }
  }

}