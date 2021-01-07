// Natalie Assaad
// Order Of The Gems - Sprite Animatoin
// Jan. 17, 2019
// This program runs the interpoalte method that hands the sprite animation

package orderOfTheGems;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This method controls the playable character sprites animation by changing the location
 * of the sprite sheets viewport depending on the input from the GameScreenController.java
 * @author  Natalie Assaad
 * @version 1.0
 * @since   2019-01-18
 */
public class SpriteAnimation extends Transition { // extends and uses Transition class

	private final ImageView imageView;
	private int count;
	private int columns;
	private int offsetX;
	private int offsetY;
	private int width;
	private int height;
	
	/**
	   * This method is a constructor method that takes in input and applies it to the sprite animation accordingly
	   * @param imageView The ImageView is a Node used for painting images loaded with Image class.
	   * @param duration A time-based amount of time
	   * @param count The number of images used in the sprite animation
	   * @param columns A vertical division of a page, text, image, etc.
	   * @param offsetX The x value is the horizontal value in a pair of coordinates
	   * @param offsetY The y value is the vertical value in a pair of coordinates
	   * @param width The width value is the measurement or extent of something from side to side
	   * @param height The height value is the measurement or extent of something from top to bottom
	   */
	public SpriteAnimation(
			ImageView imageView,
			Duration duration,
			int count,
			int columns,
			int offsetX,
			int offsetY,
			int width,
			int height
	){
		this.imageView = imageView;
		this.count = count;
		this.columns = columns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		
		// methods
		setCycleDuration(duration); // sets cycle duration
		setCycleCount(Animation.INDEFINITE); // how many cycles in animation
		setInterpolator(Interpolator.LINEAR); // Built-in interpolator that provides linear time interpolation.
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
	}
	
	/**
   	* This method sets the offsetY value to the provided value
   	* @param y The y value is the vertical value in a pair of coordinates
   	*/
	public void setOffsetY(int y) {
		this.offsetY = y;
	}
	
	/**
   	* This method sets the offsetX value to the provided value
   	* @param x The x value is the horizontal value in a pair of coordinates
   	*/
	public int getOffsetX() {
		return offsetX;
	}
	
	/**
   	* This method sets the count value to the provided value
   	* @param c The number of images used in the sprite animation
   	*/
	public void setCount(int c) {
		this.count = c;
	}
	
	// for the Transition extension
	
//	The method interpolate() has to be provided by implementations of Transition. While a Transition is running, 
//	this method is called in every frame. The parameter defines the current position with the animation. At the start, 
//	the fraction will be 0.0 and at the end it will be 1.0. How the parameter increases, depends on the interpolator, 
//	e.g. if the interpolator is Interpolator.LINEAR, the fraction will increase linear. This method must not be called by the user directly.

//  Overrides: interpolate(...) in Transition
//  Parameters:
//  frac The relative position
	
	protected void interpolate (double frac) {
		final int index = Math.min((int)Math.floor(count*frac), count-1);
		final int x = (index%columns)*width+offsetX;
		final int y = (index/columns)*height+offsetY;
		imageView.setViewport(new Rectangle2D(x, y, width, height));
	}
}
