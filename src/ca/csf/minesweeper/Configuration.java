package ca.csf.minesweeper;

import ca.csf.minesweeper.model.GameDifficulty;
import ca.csf.minesweeper.model.GameStates;

public class Configuration {
  public static GameDifficulty selectedGameDifficulty = GameDifficulty.BEGINNER;
  public static GameStates currentGameState = GameStates.PLAYING;   // Initial GameState
  
  public static boolean godModeEnabled = false; //TODO: decide whether this is useful (see startGame)
}