package ca.csf.minesweeper.model;

import java.util.ArrayList;

/*
 * 
 * Represents a grid of GameTile instances.
 * 
 */

public class GameBoard extends Subject<GameBoard> {
  
  ArrayList<GameTile> tiles;
  
  public GameBoard() {
    tiles = new ArrayList<GameTile>();
  }

}
