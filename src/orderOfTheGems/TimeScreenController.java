// Natalie Assaad
// Order Of The Gems - Time Screen Controller
// Jan. 17, 2019
// This class runs the time screen and shows the player how long it took them to get to each them.
// It also allows the user to access the About screen, Instructions Screen, and Quit the game.

package orderOfTheGems;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This method controls the time screen by handling button action to open the
 * instructions and about screen as well as getting and setting the time it 
 * took the user to collect each gem
 * @author  Natalie Assaad
 * @version 1.0
 * @since   2019-01-18
 */
public class TimeScreenController {
	
	@FXML
	private Text g1Time;
	
	@FXML
	private Text g2Time;
	
	@FXML
	private Text g3Time;
	
	@FXML
	private Text g4Time;
	
	@FXML
	private Text extTime;
	
	private Stage secondaryStage = new Stage();
	private int extraTime = 0;
	
	/**
	   * This method sets the g1Time Text string
	   * @return t An integer passed from another controller
	   */
	public void setG1Time (int t) {
		g1Time.setText(String.valueOf(t));
	}
	
	/**
	   * This method sets the g2Time Text string
	   * @return elapsedTime An integer passed from another controller
	   */
	public void setG2Time (long elapsedTime) {
		g2Time.setText(String.valueOf(elapsedTime));
	}
	
	/**
	   * This method sets the g3Time Text string
	   * @return elapsedTime An integer passed from another controller
	   */
	public void setG3Time (long elapsedTime) {
		g3Time.setText(String.valueOf(elapsedTime));
	}
	
	/**
	   * This method sets the g4Time Text string
	   * @return elapsedTime An integer passed from another controller
	   */
	public void setG4Time (long elapsedTime) {
		g4Time.setText(String.valueOf(elapsedTime));	
	}
	
	/**
	   * This method adds the integer t to private integer extraTime 
	   * and sets the extTime text to the string value of t
	   * @param t An integer passed from another controller
	   */
	public void addExtraTime (int t) {
		extraTime += t;
		extTime.setText(String.valueOf(extraTime));
	}
	
	/**
	   * This method gets the g4Time Text string
	   * @return g4Time.getText() A string displayed on the time screen
	   */
	public String getG4Time() {
		return g4Time.getText();
	}
	
	/**
	   * This method gets the extTime Text string
	   * @return extTime.getText() A string displayed on the time screen
	   */
	public String getExtTime() {
		return extTime.getText();
	}
	
	/**
	* This method handles button input
	* @param evt An Event object that represents an event
	*/
	public void buttonClickHandler (ActionEvent evt) {
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		
		switch(buttonLabel) {
			case("Instructions"): openInstructionsWindow(); break;
			case("About"): openAboutWindow(); break;
			case("Quit"): GameScreenController.quitWindow(); break;
		}	
	}
	
	private void openInstructionsWindow() {
		try {
			// loads the instructions pop up
			Pane Instructions = (Pane)FXMLLoader.load(getClass().getResource("Instructions.fxml"));
					
			// creates a new scene
			Scene instructionsScene = new Scene(Instructions,438,296);

			// adds css to the new scene		
			instructionsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//creates new stage to put scene in
			secondaryStage.setScene(instructionsScene);
			secondaryStage.setResizable(false);
			secondaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void openAboutWindow() {
		try {	
			// loads about window pop up
			Pane About = (Pane)FXMLLoader.load(getClass().getResource("About.fxml"));
				
			// creates a new scene
			Scene aboutScene = new Scene(About,242,253);

			// adds css to the new scene		
			aboutScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//creates new stage to put scene in
			secondaryStage.setScene(aboutScene);
			secondaryStage.setResizable(false);
			secondaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	/**
	* This method closes the current window when a button is pressed
	* @param evt An Event object that represents an event
	*/
	public void closeWindowButtonClickHandler(ActionEvent evt) {
		secondaryStage.close();
	}
	
	/**
	   * This method closes a specific stage
	   * @param evt An Event object that represents an event
	   */
	public void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		System.out.println(source);
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}
	
}
