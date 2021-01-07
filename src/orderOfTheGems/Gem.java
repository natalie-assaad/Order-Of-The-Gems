// Natalie Assaad
// Order Of The Gems - Gem Class
// Jan. 17, 2019
// This class draws the gems on the canvas and gets each gems individual boundary

package orderOfTheGems;

//imports
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This method controls the gems which are objects that, when one collides with the pirate (the object
 * the user controls) is collected and allows the player to either move and head to collect the next gem
 * or does nothing if the user is collecting the gems in the incorrect order.
 * @author  Natalie Assaad
 * @version 1.0
 * @since   2019-01-18
 */
public class Gem {
    
    @FXML
    private Canvas gameCanvas;
    
    @FXML
    private GraphicsContext gc;
    
    private String gemImageName = "/resources/Gem2.png";
	private Image gem = new Image(gemImageName);
	
	/** initializes public integer variable */
	public int numGems = 4;
	
	/** initializes public integer array */
    public int[] xValues = {142, 450, 420, 100};
    /** initializes public integer array */
    public int[] yValues = {487, 520, 100, 50};
    
	/**
	   * This method is a constructor method that takes the graphics context and the canvas
	   * set by the programmer and sets it equal to the relevant objects initialized at the start of the program.
	   * @param gc The graphics context calls to a Canvas using a buffer
	   * @param canvas The canvas is an image that can be drawn on using a set of graphics commands provided by a GraphicsContext
	   */
    public Gem (GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
    }
    
    /**
	   * This method returns the x value
	   * @return xValues The x value is the horizontal value in a pair of coordinates
	   */
    public int[] getXValues() {
    	return xValues;
    }
    
    /**
   	* This method returns the y value
   	* @return yValues The y value is the vertical value in a pair of coordinates
   	*/
    public int[] getYValues() {
    	return yValues;
    }
    
    /**
	   * This method returns the width value
	   * @return image.getWidth() The width value is the measurement or extent of something from side to side
	   */
    public double getWidth() {
    	return gem.getWidth();
    }
    
    /**
	   * This method returns the height value
	   * @return image.getHeight() The height value is the measurement or extent of something from top to bottom
	   */
    public double getHeight() {
    	return gem.getHeight();
    }
    
    /**
	   * This method returns the string of the image name
	   * @return imageName The imageName is a string that contains the route used to access an image
	   */
    public String getImageName() {
      return gemImageName;
    }

    /**
	   * This method sets the string of the imageName to the provided value
	   * @param imageName The imageName is a string that contains the route used to access an image
	   */
    public void setImageName(String imageName) {
      this.gemImageName = imageName;
    }
    
    /**
	   * This method displays the object on the canvas at the specified x and y coordinates
	   */
	public void drawGems(double x, double y) {
		this.gc.drawImage(this.gem, x, y);
	}
	
    /**
	   * This method gets the boundary of the object
	   * @return Rectangle2D The Rectangle2D is used to describe the bounds of an object
	   */
    public Rectangle2D getBoundary(int i) {
		return new Rectangle2D(this.xValues[i], this.yValues[i], this.gem.getWidth(), this.gem.getHeight());
    }
    
}
