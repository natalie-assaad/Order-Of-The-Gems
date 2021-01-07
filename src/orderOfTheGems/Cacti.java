// Natalie Assaad
// Order Of The Gems - Cacti Class
// Jan. 17, 2019
// This class draws a rect behind each cacti on the canvas and gets the boundary for each rect

package orderOfTheGems;

//imports
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * This method controls the rect hidden behind the cacti that are objects that, when it collides with the pirate 
 * (the object the user controls) decreases the extra points earned by two
 * @author  Natalie Assaad
 * @version 1.0
 * @since   2019-01-18
 */
public class Cacti {
    
    @FXML
    private Canvas gameCanvas;
    
    @FXML
    private GraphicsContext gc;
    
    private int rectWidth = 45;
	private int rectHeight = 45;
        
    /** initializes public integer variable */
	public int numCacti = 4;
	
	/** initializes public integer array */
    public int[] xValues = {90, 180, 404, 494};
    /** initializes public integer array */
    public int[] yValues = {91, 136, 452, 497};
    
	/**
	   * This method is a constructor method that takes the graphics context and the canvas
	   * set by the programmer and sets it equal to the relevant objects initialized at the start of the program.
	   * @param gc The graphics context calls to a Canvas using a buffer
	   * @param canvas The canvas is an image that can be drawn on using a set of graphics commands provided by a GraphicsContext
	   */
    public Cacti (GraphicsContext gc, Canvas canvas) {
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
	   * This method displays the object on the canvas at the specified x and y coordinates
	   */
	public void drawCacti(double x, double y) {
		this.gc.fillRect(x, y, rectWidth, rectHeight);
	}
	
    /**
	   * This method gets the boundary of the object
	   * @return Rectangle2D The Rectangle2D is used to describe the bounds of an object
	   */
    public Rectangle2D getBoundary(int i) {
		return new Rectangle2D(this.xValues[i], this.yValues[i], rectWidth, rectHeight);
    }
}
