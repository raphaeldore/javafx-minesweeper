package ca.csf.minesweeper.model;


public class GameTile extends Subject<GameTile> {

  private boolean isMine = false;
  private int neighboringMineCount = 0;
  private MinesweeperGame game;
  private TileState state;
  
  private final int ROW;
  private final int COLUMN;
  
  public GameTile(MinesweeperGame game, Observer<GameTile> observer, int row, int column) {
    this.game = game;
    this.ROW = row;
    this.COLUMN = column;
    addObserver(observer);
    state = TileState.HIDDEN;
  }
  
  public int getROW() {
    return ROW;
  }
  
  public int getCOLUMN() {
    return COLUMN;
  }

  public int getNeighboringMineCount() {
    return neighboringMineCount;
  }

  public boolean revealedGameTileAreaIsClean() {
    boolean isClean = false;
    if (state == TileState.HIDDEN) {
      state = TileState.REVEALED;
      if (isMine) {
        game.lose();
      } else {
        if(neighboringMineCount == 0) {
          isClean = true;
          game.incrementTilesRevealed();
        }
      }
      notifyObservers(this);
    }
    return isClean;
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
    if(state == TileState.FLAGGED){
      decrementFlagCount();
      state = TileState.QUESTIONNED;
    } else if(state == TileState.QUESTIONNED) {
      state = TileState.HIDDEN;
    } else {
      incrementFlagCount();
      state = TileState.FLAGGED;
    }
    notifyObservers(this);
  }
  
  public TileState getState() {
    return state;
  }
  
  public boolean isMine() {
    return isMine;
  }

  public void revealIfMine() {
    if (state != TileState.REVEALED && isMine) {
      if (state == TileState.FLAGGED) {
        decrementFlagCount();
      }
      state = TileState.MINE_REVEALED;
      notifyObservers(this);
    }
  }
  
  public void hideIfReavealedMine() {
    if (state == TileState.MINE_REVEALED) {
      state = TileState.HIDDEN;
      notifyObservers(this);
    }
  }

  //TODO: put functions in order according to public/private/protected
}