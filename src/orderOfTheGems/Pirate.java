// Natalie Assaad
// Order Of The Gems - Pirate
// Jan. 17, 2019
// This class hands sprite movement and collision

package orderOfTheGems;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This method controls the player by handling movement and collision with a variety of objects
 * @author  Natalie Assaad
 * @version 1.0
 * @since   2019-01-18
 */
public class Pirate extends AnchorPane { // extends and uses AnchorPane class
	
	// Character configurations
	private ImageView imageView;
    private int count = 2;
    private int columns = 3;
    private int offsetX = 0;
    private int offsetY = 40;
    private int width = 48; // size of character
    private int height = 44; // size of the character
    
    Rectangle removeRect = null;
    SpriteAnimation animation;
	
    /**
	   * This method is a constructor method that takes the imageView
	   * set by the programmer and gets a viewport of it.  The method also 
	   * calls the SpriteAnimation class and adds the imageView to the AnchorPanes children
	   * @param imageView The ImageView is a Node used for painting images loaded with Image class.
	   */
	public Pirate (ImageView imageView) {
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height)); // sprite is drawn
		animation = new SpriteAnimation(imageView, Duration.millis(300), count, columns, offsetX, offsetY, width, height);
		getChildren().addAll(imageView);
	}

	/**
	   * This method moves the players x coordinate
	   * @param x The x value is the horizontal value in a pair of coordinates
	   */
	public void moveX(int x) {
		boolean right = x>0?true:false;
		for (int i=0; i < Math.abs(x); i++) { // using x and y coordinate and using math to figure out where it is going to happen
			if(right) {
				this.setTranslateX(this.getTranslateX() + 1);
			} else {
				this.setTranslateX(this.getTranslateX() - 1);
			}
		}
	}
	
	/**
	   * This method moves the players y coordinate
	   * @param y The y value is the vertical value in a pair of coordinates
	   */
	public void moveY(int y) {
		boolean down = y>0?true:false;
		for (int i=0; i < Math.abs(y); i++) { // using x and y coordinate and using math to figure out where it is going to happen
			if(down) {
				this.setTranslateY(this.getTranslateY() + 1);
			} else {
				this.setTranslateY(this.getTranslateY() - 1);
			}
		}
	}
	
	private Rectangle2D getBoundary() {
		return new Rectangle2D(this.getTranslateX(), this.getTranslateY(), width, height);
	}
	
	/**
	   * This method determines whether or not the object player collided with the bonus gem object
	   * @param bGem The bonus gem object
	   * @return collide Collide is a boolean that informs the program whether or not the user has collided with another object
	   */
	public boolean bonusGemCollision (BonusGem bGem) {
		boolean collide = bGem.getBoundary().intersects(this.getBoundary());
		return collide;
	}
	
	/**
	   * This method determines whether or not the object player collided with one of the gem objects
	   * @param gem The gem object
	   * @param i The index of the gem
	   * @return collide Collide is a boolean that informs the program whether or not the user has collided with another object
	   */
	public boolean gemCollision (Gem gem, int i) {
		boolean collide = gem.getBoundary(i).intersects(this.getBoundary());
		return collide;
	}
	
	/**
	   * This method determines whether or not the object player collided with one of the cactus objects
	   * @param cactus The cactus object
	   * @param i The index of the cactus
	   * @return collide Collide is a boolean that informs the program whether or not the user has collided with another object
	   */
	public boolean cactusCollision (Cacti cactus, int i) {
		boolean collide = cactus.getBoundary(i).intersects(this.getBoundary());
		return collide;
	}

	/**
	   * This method determines whether or not the object player collided with the circle node
	   * @param cXPos The circles x position
	   * @param cYPos The circles y position
	   * @return collide Collide is a boolean that informs the program whether or not the user has collided with another object
	   */
	public boolean circleCollision(double cXPos, double cYPos) {
		boolean collide;
		if (this.getBoundary().contains(cXPos, cYPos)) {
			collide = true;
			return collide;
		}
		collide = false;
		return collide;
	}
	
	/**
	   * This method moves the player back if they hit a bad object
	   */
	public void hitBadObject() {
		this.moveX(-50); // sends sprite back if it hits a bad object
		this.moveY(50);
	}
	
}
