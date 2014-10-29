package ca.csf.minesweeper;

import ca.csf.minesweeper.model.GameState;
import ca.csf.minesweeper.model.GameState.GameStates;

/*
 * All constants are defined here. As are all user defined options (Such as the selected
 * difficulty).
 */
public class Configuration {
  // TODO: Enum TileState {Normal, Flag, QuestionMark}
  // public static final nbrOfTiles

  public static GameDifficulty selectedGameDifficulty = GameDifficulty.BEGINNER; // Default
                                                                                 // GameDifficulty
  public static GameStates currentGameState = GameState.GameStates.START;

  public static enum GameDifficulty {
    BEGINNER("Débutant", 9, 9, 10), INTERMEDIATE("Intermédiaire", 16, 16, 40), EXPERT("Expert", 30,
        16, 99);

    public final String difficultyName;
    public final int nbrOfColumns;
    public final int nbrOfRows;
    public final int nbrOfMines;

    private GameDifficulty(String difficultyName, int nbrOfRows, int nbrOfColumns, int nbrOfMines) {
      this.difficultyName = difficultyName;
      this.nbrOfRows = nbrOfRows;
      this.nbrOfColumns = nbrOfColumns;
      this.nbrOfMines = nbrOfMines;
    }
  }

}
