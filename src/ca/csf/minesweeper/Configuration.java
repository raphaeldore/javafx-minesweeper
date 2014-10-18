package ca.csf.minesweeper;

/*
 * All constants are defined here. As are all user defined options (Such as the selected
 * difficulty).
 */
public class Configuration {
  // TODO: Enum TileState {Normal, Flag, QuestionMark}
  // public static final nbrOfTiles

  public static GameDifficulty selectedGameDifficulty;

  public static enum GameDifficulty {
    Beginner("Débutant", 9, 9, 10), Intermediate("Intermédiaire", 16, 16, 40), Expert("Expert", 30,
        16, 99);

    public final String difficultyName;
    public final int gameBoardWidth;
    public final int gameBoardHeight;
    public final int nbrOfMines;

    private GameDifficulty(String difficultyName, int gameBoardWidth, int gameBoardHeight,
        int nbrOfMines) {
      this.difficultyName = difficultyName;
      this.gameBoardWidth = gameBoardWidth;
      this.gameBoardHeight = gameBoardHeight;
      this.nbrOfMines = nbrOfMines;
    }
  }
}
