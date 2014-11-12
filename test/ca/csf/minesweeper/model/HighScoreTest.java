package ca.csf.minesweeper.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/*
 * Warning: When this class is run with infinitest, the HighScores.xml used during normal play is
 * overwrited! To solve this problem we have a special Run Configuration for the HighScoreTest class
 * in the project root directory, "HighScoreTest.launch", which is used when running JUnit normally.
 * 
 * Note: We disabled infinitest for all the classes in our project, see the file infinitest.filters
 * in the root directory.
 */

public class HighScoreTest {

  HighScore highScores;

  @Before
  public void setUp() throws Exception {
    highScores = new HighScore();
  }

  @Test
  public void whenHighScoreInstanceCreated_andHighScoreXMLDoesNotExist_thenHighScoresXMLFileIsCreated() {
    File file = new File("HighScores.xml");
    if (file.exists()) {
      file.delete();
    }
    highScores = new HighScore();
    boolean fileExists = new File("HighScores.xml").exists();
    assertTrue(fileExists);
  }

  @Test
  public void whenHighScoreInstanceCreated_andHighScoreXMLExists_thenExistingHighScoresXMLFileisPreserved()
      throws IOException {

    File originalFile = new File("HighScores.xml");
    File originalFileBackUp = new File("HighScoresBackup.xml");

    FileUtils.copyFile(originalFile, originalFileBackUp);

    @SuppressWarnings("unused")
    // When the constructor is called it loads/creates the file on the disk
    // There is nothing else to do with the instance in this case
    HighScore highScores = new HighScore();
    File originalFileAfterHighScoreInitialization = new File("HighScores.xml");

    boolean filesAreSame =
        FileUtils.contentEquals(originalFileBackUp, originalFileAfterHighScoreInitialization);

    assertTrue("Files are different", filesAreSame);
  }

  @Test
  public void whenAddingAHighScore_thenHighScoreIsAdded() {
    highScores.setHighScore(GameDifficulty.BEGINNER, "Test", 10);

    ArrayList<String> scoreToCompare = new ArrayList<String>(Arrays.asList("Test", "10"));
    ArrayList<String> score = highScores.getHighestScoreForDifficulty(GameDifficulty.BEGINNER);

    assertEquals(scoreToCompare, score);
  }

  @Test
  public void whenHighScoresAreDeleted_thenHighScoreFileIsResetToInitialState() throws IOException {
    File initialState = new File("HighScores_clean.xml");

    highScores.setHighScore(GameDifficulty.BEGINNER, "John Snow", 123);
    highScores.setHighScore(GameDifficulty.INTERMEDIATE, "Ginette Renault", 532);
    highScores.setHighScore(GameDifficulty.EXPERT, "Johny Capala", 312);

    highScores.deleteHighScores();
    File highScoresFileAfterReset = new File("HighScores.xml");

    boolean filesAreSame = FileUtils.contentEquals(initialState, highScoresFileAfterReset);

    assertTrue("Files are different!", filesAreSame);
  }

  @Test
  public void whenComparingAWorstScoreThanTheHighScoreForDifficulty_thenReturnFalse() {
    highScores.setHighScore(GameDifficulty.BEGINNER, "John Snow", 123);
    boolean isNotHighestScoreForDifficulty =
        highScores.isHighestScoreForDifficulty(GameDifficulty.BEGINNER, 400);
    assertFalse(isNotHighestScoreForDifficulty);
  }

  @Test
  public void whenComparingABetterScoreThanTheHighScoreForDifficulty_thenReturnTrue() {
    highScores.setHighScore(GameDifficulty.BEGINNER, "John Snow", 123);
    boolean isHighestScoreForDifficulty = highScores.isHighestScoreForDifficulty(GameDifficulty.BEGINNER, 100);
    assertTrue(isHighestScoreForDifficulty);
  }

  @Test
  public void whenSavingAScore_AndSavingToTheHardDrive_ThenDataStillExistsWhenFileIsLoaded() {
    highScores.deleteHighScores();
    highScores.setHighScore(GameDifficulty.EXPERT, "Johny Capala", 312);
    highScores.saveHighScores();

    ArrayList<String> scoreToCompare = new ArrayList<String>(Arrays.asList("Johny Capala", "312"));

    HighScore sameHighScores = new HighScore();

    assertEquals(scoreToCompare, sameHighScores.getHighestScoreForDifficulty(GameDifficulty.EXPERT));
  }
  
  @AfterClass
  public static void cleanUp() {
    File file = new File("HighScores.xml");
    File fileBackup = new File("HighScoresBackup.xml");
    if (file.exists()) {
      file.delete();
    }

    if (fileBackup.exists()) {
      fileBackup.delete();
    }
  }
}
