package ca.csf.minesweeper;

import ca.csf.minesweeper.model.GameDifficulty;
import ca.csf.minesweeper.model.GameStates;
import ca.csf.minesweeper.model.HighScore;

/*
 * All constants are defined here. As are all user defined options (Such as the selected
 * difficulty).
 */
public class Configuration {
  public static GameDifficulty selectedGameDifficulty = GameDifficulty.BEGINNER;
  public static GameStates currentGameState = GameStates.PLAYING;   // Initial GameState
  
  public static boolean godModeEnabled = false; //TODO: decide whether this is useful (see startGame)
  public static final HighScore highScores = new HighScore();
}