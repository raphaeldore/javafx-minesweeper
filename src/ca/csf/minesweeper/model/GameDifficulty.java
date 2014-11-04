package ca.csf.minesweeper.model;

public enum GameDifficulty {
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