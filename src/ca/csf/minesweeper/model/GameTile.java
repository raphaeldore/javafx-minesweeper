package ca.csf.minesweeper.model;


public class GameTile extends Subject<GameTile> {

  private boolean isMine = false;
  private int neighboringMineCount = 0;

  public GameTile() {

  }

  public void revealGameTile() {

  }

  public boolean setAsMine() {
    if (isMine) {
      return false;
    } else {
      isMine = true;
      return true;
    }
  }

  public void incrementNeighboringMineCount() {
    neighboringMineCount++;
  }
}
