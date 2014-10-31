package ca.csf.minesweeper;

import ca.csf.minesweeper.model.GameState;
import ca.csf.minesweeper.model.GameState.GameStates;
import ca.csf.minesweeper.model.GameDifficulty;

/*
 * All constants are defined here. As are all user defined options (Such as the selected
 * difficulty).
 */
public class Configuration {
  // TODO: Enum TileState {Normal, Flag, QuestionMark}
  // public static final nbrOfTiles

  public static GameDifficulty selectedGameDifficulty = GameDifficulty.BEGINNER; // Default
                                                                                 // GameDifficulty
  
  public static GameStates currentGameState = GameState.GameStates.START;        // Initial GameState

}