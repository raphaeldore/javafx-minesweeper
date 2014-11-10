package ca.csf.minesweeper.controller;

import java.io.InputStream;

import ca.csf.minesweeper.model.HighScore;
import javafx.scene.image.Image;

final class ControllerConsts {

	// Path to images
	public static final String resourcesPath = "/resources/";
	public static final String defaultName = "NoName";
	public static final HighScore highScores = new HighScore();

	public static final Image IMAGE_ONE_MINE = loadImage("1.png");
	public static final Image IMAGE_TWO_MINES = loadImage("2.png");
	public static final Image IMAGE_THREE_MINES = loadImage("3.png");
	public static final Image IMAGE_FOUR_MINES = loadImage("4.png");
	public static final Image IMAGE_FIVE_MINES = loadImage("5.png");
	public static final Image IMAGE_SIX_MINES = loadImage("6.png");
	public static final Image IMAGE_SEVEN_MINES = loadImage("7.png");
	public static final Image IMAGE_EIGHT_MINES = loadImage("8.png");
	public static final Image IMAGE_FLAG = loadImage("Flag.png");
	public static final Image IMAGE_MINE = loadImage("Mine.png");
	public static final Image IMAGE_MINE_CROSS = loadImage("MineCross.png"); // TODO: This is never used
	public static final Image IMAGE_MINE_RED = loadImage("MineRed.png");
	public static final Image IMAGE_QUESTION_MARK = loadImage("QuestionMark.png");
	public static final Image IMAGE_SMILE_DEAD = loadImage("Smile_Dead.png");
	public static final Image IMAGE_SMILE_HAPPY = loadImage("Smile_Happy.png");
	public static final Image IMAGE_SMILE_NORMAL = loadImage("Smile_Normal.png");
	public static final Image IMAGE_SMILE_WORRY = loadImage("Smile_Worry.png"); // TODO: This is never used

	private static Image loadImage(String path) {
		InputStream resource = ControllerConsts.class
				.getResourceAsStream(resourcesPath + path);
		
		return new Image(resource);
	}

}
