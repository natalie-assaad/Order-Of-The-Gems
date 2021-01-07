// Natalie Assaad
// Order Of The Gems - Bonus Gem Class
// Jan. 17, 2019
// This class draws the bonus gem on the canvas and gets it boundary

package orderOfTheGems;

//imports
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This method controls the bonus gem which is an object that, when it collides with the pirate (the object
 * the user controls) increases the extra points earned by three
 * @author  Natalie Assaad
 * @version 1.0
 * @since   2019-01-18
 */
public class BonusGem {
    
    @FXML
    private Canvas gameCanvas;
    
    @FXML
    private GraphicsContext gc;
    
    private double x = 300;
    private double y = 300;
    
    private String bonusGemImageName = "/resources/Gem.png";
	private Image bonusGem = new Image(bonusGemImageName);
	
	/** initializes public integer variable */
	public int bGemNum = 1;
    
	/**
	   * This method is a constructor method that takes the graphics context and the canvas
	   * set by the programmer and sets it equal to the relevant objects initialized at the start of the program.
	   * @param gc The graphics context calls to a Canvas using a buffer
	   * @param canvas The canvas is an image that can be drawn on using a set of graphics commands provided by a GraphicsContext
	   */
    public BonusGem (GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
    }
    
    /**
	   * This method returns the x value
	   * @return x The x value is the horizontal value in a pair of coordinates
	   */
    public double getX() {
    	return x;
    }

    /**
	   	* This method sets the x value to the provided value
	   	* @param x The x value is the horizontal value in a pair of coordinates
	   	*/
    public void setX(double x) {
    	this.x = x;
    }

    /**
	   	* This method returns the y value
	   	* @return y The y value is the vertical value in a pair of coordinates
	   	*/
    public double getY() {
    	return y;
    }

    /**
	   	* This method sets the y value to the provided value
	   	* @param y The y value is the vertical value in a pair of coordinates
	   	*/
    public void setY(double y) {
    	this.y = y;
    }
    
    /**
	   * This method returns the width value
	   * @return image.getWidth() The width value is the measurement or extent of something from side to side
	   */
    public double getWidth() {
    	return bonusGem.getWidth();
    }
    
    /**
	   * This method returns the height value
	   * @return image.getHeight() The height value is the measurement or extent of something from top to bottom
	   */
    public double getHeight() {
    	return bonusGem.getHeight();
    }
    
    /**
	   * This method returns the string of the image name
	   * @return imageName The imageName is a string that contains the route used to access an image
	   */
    public String getImageName() {
      return bonusGemImageName;
    }

    /**
	   * This method sets the string of the imageName to the provided value
	   * @param imageName The imageName is a string that contains the route used to access an image
	   */
    public void setImageName(String imageName) {
      this.bonusGemImageName = imageName;
    }
    
    /**
	   * This method displays the object on the canvas at the specified x and y coordinates
	   */
	public void drawBonusGem() {
		this.gc.drawImage(this.bonusGem, this.x, this.y);
	}
    /**
	   * This method gets the boundary of the object
	   * @return Rectangle2D The Rectangle2D is used to describe the bounds of an object
	   */
    public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.bonusGem.getWidth(), this.bonusGem.getHeight());
    }
}
