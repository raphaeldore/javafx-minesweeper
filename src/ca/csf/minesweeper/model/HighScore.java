package ca.csf.minesweeper.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class HighScore {
  Hashtable<String, ArrayList<String>> scores;

  public HighScore() {
    scores = new Hashtable<String, ArrayList<String>>();

    if (!new File("HighScores.xml").exists()) {
      initializeFile();
    } else {
      loadFile();
    }
  }

  // Usually this is a bad idea...But I really have no choice, since XMLDecoder returns an object
  // and I need a HashTable
  @SuppressWarnings("unchecked")
  private void loadFile() {
    XMLDecoder d;
    try {
      d = new XMLDecoder(new BufferedInputStream(new FileInputStream("HighScores.xml")));
      Object result = d.readObject();
      this.scores = (Hashtable<String, ArrayList<String>>) result;
      d.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err
          .println("An error occured while reading the file HighScores.xml. It seems corrupted. The file will be reset.");
      initializeFile();
    }


  }

  public void deleteHighScores() {
    initializeFile();
  }

  private void initializeFile() {
    scores.put("Débutant", new ArrayList<String>(Arrays.asList("", "999")));
    scores.put("Intermédiaire", new ArrayList<String>(Arrays.asList("", "999")));
    scores.put("Expert", new ArrayList<String>(Arrays.asList("", "999")));

    saveHighScores();
  }

  public void setHighScore(GameDifficulty difficulty, String playerName, int time) {
    scores.put(difficulty.difficultyName, new ArrayList<String>(Arrays.asList(playerName, Integer.toString(time))));
    saveHighScores();
  }

  public boolean isHighestScoreForDifficulty(GameDifficulty difficulty, int time) {
    ArrayList<String> highestScore = scores.get(difficulty.difficultyName);
    return time < Integer.parseInt(highestScore.get(1));
  }

  public void saveHighScores() {
    FileOutputStream fos;
    try {
      fos = new FileOutputStream("HighScores.xml");
      XMLEncoder e = new XMLEncoder(fos);
      e.writeObject(scores);
      e.close();
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
  }
  
  public ArrayList<String> getHighestScoreForDifficulty(GameDifficulty difficulty) {
    return scores.get(difficulty.difficultyName);
  }
}
