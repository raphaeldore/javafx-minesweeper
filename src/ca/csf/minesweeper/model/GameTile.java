package ca.csf.minesweeper.model;


public class GameTile extends Subject<GameTile> {

  private boolean isMine = false;
  private int neighboringMineCount = 0;
  private MinesweeperGame game;

  public GameTile(MinesweeperGame game, Observer observer) {
    this.game = game;
    addObserver(observer);
  }

  public int getNeighboringMineCount() {
    return neighboringMineCount;
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

  private void incrementFlagCount() {
    game.incrementFlagCount();
  }

  private void decrementFlagCount() {
    game.decrementFlagCount();
  }

  public void toggleState() {
    if(true){
      
    } else if(true) {
      
    } else {
      
    }
  }

}