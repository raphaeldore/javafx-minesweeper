package ca.csf.minesweeper.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GameTileTest {

  private GameTile testTile;
  
  @Before
  public void setUp() throws Exception {
    testTile = new GameTile(new MinesweeperGame(GameDifficulty.BEGINNER, null), null, 0, 0);
  }
  // Passing null as the observer means that all the methods that call notifyObservers would crash if the
  // observer would have really been added to the list. Having no observers on the list also means that the
  // notifyObserver will not crash due to having either null of a fake GameWindowController which would not
  // have all of the necessary UI elements to update and who would then cause a crash.

  @Test
  public void onExteriorCreationTileIsNotMined(){
    assertEquals(false, testTile.isMine());
  }
  
  @Test
  public void onExteriorCreationTileCanBeSetMined(){
    assertEquals(true, testTile.setAsMine());
  }
  
  @Test
  public void minedTileCannotBeSetMinedAgain(){
    testTile.setAsMine();
    assertEquals(false, testTile.setAsMine());
  }
  
  @Test
  public void setAsMineMakesTileMined(){
    testTile.setAsMine();
    assertEquals(true, testTile.isMine());
  } 
  
  @Test
  public void setMinesAsFlagPutsFlagIfMine(){
    testTile.setAsMine();
    testTile.setMineAsFlags();
    assertEquals(TileState.FLAGGED, testTile.getState());
  }
  
  @Test
  public void setWrongFlagAsCrossSetCrossMineIfFlaggedAndNotMined(){
    testTile.toggleState();
    testTile.setWrongFlagAsCross();
    assertEquals(TileState.CROSSED_MINE, testTile.getState());
  }
  
  @Test
  public void setWrongFlagAsCrossDoesNotSetCrossMineIfFlaggedAndMined(){
    testTile.toggleState();
    testTile.setAsMine();
    testTile.setWrongFlagAsCross();
    assertEquals(TileState.FLAGGED, testTile.getState());
  }
  
  @Test
  public void setWrongFlagAsCrossDoesNotSetCrossMineIfNotFlagged(){
    testTile.setWrongFlagAsCross();
    assertEquals(TileState.HIDDEN, testTile.getState());
  }
  
  @Test
  public void toggleStateOnceSetsFlagged(){
    testTile.toggleState();
    assertEquals(TileState.FLAGGED, testTile.getState());
  }
  
  @Test
  public void initialTileIsHidden(){
    assertEquals(TileState.HIDDEN, testTile.getState());
  }
  
  @Test
  public void toggleStateTwiceSetsQuestionned(){
    testTile.toggleState();
    testTile.toggleState();
    assertEquals(TileState.QUESTIONNED, testTile.getState());
  }
  
  @Test
  public void toggleStateThreeTimesSetsHiddenAgain(){
    testTile.toggleState();
    testTile.toggleState();
    testTile.toggleState();
    assertEquals(TileState.HIDDEN, testTile.getState());
  }
  
  @Test
  public void neighbouringMineCountForNewTileIsZero(){
    assertEquals(0, testTile.getNeighboringMineCount());
  }
  
  @Test
  public void incrementNeighbouringMineCountAugmentsCountByOne(){
    testTile.incrementNeighboringMineCount();
    assertEquals(1, testTile.getNeighboringMineCount());
  }
  
  @Test
  public void ifNeighbouringMineCountIsZeroAndIsNotMineRevealedGameTileAreaIsCleanReturnsTrue(){
    assertEquals(true, testTile.revealedGameTileAreaIsClean());
  }
  
  @Test
  public void ifNeighbouringMineCountIsNotZeroThenRevealedGameAreaIsCleanReturnsFalse(){
    testTile.incrementNeighboringMineCount();
    assertEquals(false, testTile.revealedGameTileAreaIsClean());
  }
  
  @Test
  public void ifIsMineThenRevealedGameAreaIsCleanReturnsFalse(){
    testTile.setAsMine();
    assertEquals(false, testTile.revealedGameTileAreaIsClean());
  }
  
  @Test
  public void afterRevealedGameAreaIsCleanStateIsRevealed(){
    testTile.revealedGameTileAreaIsClean();
    assertEquals(TileState.REVEALED, testTile.getState());
  }
  
  @Test
  public void revealIfMineSetStateToMINE_SHOWNIfTileIsMined(){
    testTile.setAsMine();
    testTile.revealIfMine();
    assertEquals(TileState.MINE_REVEALED, testTile.getState());
  }

  @Test
  public void revealIfMineLeavesStateAtHIDDENIfTileIsNotMined(){
    testTile.revealIfMine();
    assertEquals(TileState.HIDDEN, testTile.getState());
  }
  
}