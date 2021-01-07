// Natalie Assaad
// Order Of The Gems - End Screen Controller
// Jan. 17, 2019
// This class controls the end screen by handling user input on the Split Menu Button, allowing the user to either quit or play again.  
// It also displays the total time it took for the user to collect the gems, the extra time, and the overall time.

package orderOfTheGems;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This method controls the end screen by handling user input and displaying
 * the total time, extra time, and final time.
 * @author  Natalie Assaad
 * @version 1.0
 * @since   2019-01-18
 */
public class EndScreenController {
	
	@FXML
	private Text totalTimeText;
	
	@FXML
	private Text extraTimeText;
	
	@FXML
	private Text overallTimeText;
	
	@FXML
	private SplitMenuButton menuButton;

	private int totalTime;
	private int extraTime;
	
	private Stage gameStage = new Stage();
	
	/**
	* This method handles menu input
	* @param evt An Event object that represents an event
	*/
	public void menuClickHandler(ActionEvent evt) {
		MenuItem clickedMenu = (MenuItem) evt.getTarget();
		String menuLabel = clickedMenu.getText();
		
		switch(menuLabel) {
		case("Play Again"): openGameScreen(gameStage); break;
		case("Quit"): GameScreenController.quitWindow(); break;
		}
	}

	private void openGameScreen(Stage gameStage) {
		try {
//			// loads about window pop up
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
//			AnchorPane root = (AnchorPane)loader.load();
//			
//			// creates a new scene
//			Scene gameScene = new Scene(root,600,600);
//
//			// adds css to the new scene		
//			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			GameScreenController gameScreen = loader.getController();
//		
//			// calls methods to get / display final times
//			//gameScreen.start(gameStage);
////			ManagementFactory.getRuntimeMXBean().getInputArguments();
////			String[] mainCommand = System.getProperty("sun.java.command").split(" ");
//			gameScreen.main(null);
//
//			//creates new stage to put scene in
//			gameStage.setScene(gameScene);
//			gameStage.setResizable(false);
//			gameStage.show();
////			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
////			AnchorPane root = (AnchorPane)loader.load();
////			Scene scene = new Scene(root,600,600);
////			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
////			
////			GameScreenController gameScreen = loader.getController();
//////			gameStage = new Stage();
////			
////			// calls methods to get / display final times
//////			gameScreen.start(gameStage);
////			
//////			//creates new stage to put scene in
////			gameStage.setScene(scene);
////			gameStage.setResizable(false);
////			gameStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
   	* This method gets the total time from the GameScreenController and
   	* sets specified text to that String.  It also sets the integer totalTime
   	* to the integer value of totalTimeStr
   	* @param totalTimeStr The total time it took the player to collect all the normal gems
   	*/
	public void getTotalTime(String totalTimeStr) {
		totalTime = Integer.valueOf(totalTimeStr);
		totalTimeText.setText(totalTimeStr);
	}
	
	/**
   	* This method gets the extra time from the GameScreenController and
   	* sets specified text to that string.  It also sets the integer extraTime
   	* to the integer value of bonusTimeStr
   	* @param extTimeStr The extra time that the player alloted by either hitting a cactus, the black circle, or getting the bonus gem
   	*/
	public void getExtTime(String extTimeStr) {
		extraTime = Integer.valueOf(extTimeStr);
		extraTimeText.setText(extTimeStr);
	}
	
	/**
   	* This method caclulates the overall time by adding the extraTime to the totalTime
   	* and then it sets specified text to the string value of that integer
   	*/
	public void setOverallTime() {
		int overallTime = totalTime + extraTime;
		overallTimeText.setText(String.valueOf(overallTime));
	}
}
