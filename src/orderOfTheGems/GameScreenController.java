// Natalie Assaad
// Order Of The Gems - Game Screen Controller
// Jan. 17, 2019
// This class runs the game and opens the time screen that is linked to the game.

package orderOfTheGems;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import orderOfTheGems.TimeScreenController;

/**
 * This method controls the game screen which draws all the images / nodes,
 * plays music, handles user key input calls collision functions, and opens
 * the time screen on the side.
 * @author  Natalie Assaad
 * @version 1.0
 * @since   2019-01-18
 */
public class GameScreenController extends Application {
		
    private TimeScreenController timeCntrl;
    
    private Circle c;
    private boolean circleDrawn;
    
    private Stage timeStage = new Stage();
    private Stage endStage = new Stage();
        
	private Image imagePlayer = new Image(getClass().getResourceAsStream("/resources/Player.png"));
	private ImageView imageViewPlayer = new ImageView(imagePlayer);
	private Pirate player = new Pirate(imageViewPlayer);
	
	private Image background = new Image("/resources/Background.png");

	private String left = "LEFT";
	private String right = "RIGHT";
	private String up = "UP";
	private String down = "DOWN";
	private boolean animationUp = false;
	private ArrayList<String> readInput = new ArrayList<String>();
	private ArrayList<String> input = new ArrayList<String>(); // key input arrayList
	
	private boolean firstGemHit = false; // sets all gem collisions to false at start
	private boolean secondGemHit = false;
	private boolean thirdGemHit = false;
	private boolean fourthGemHit = false;
	private ArrayList<Gem> gemList = new ArrayList<Gem>();
	private int timesScreenDrawn = -1;
	private int gemsToGlow = 4;
	private int timesBGemDrawn = 1;
	
	private ArrayList<Cacti> cactiList = new ArrayList<Cacti>();
	
	private long startTime = 0;
	private long currentTime = 0;
	private long elapsedTime = 0;
	
	/**
	   * This method runs the game
	   * @param primaryStage The primary stage
	   */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		AnchorPane root = new AnchorPane();
		
		// loads song
		java.net.URL song = getClass().getResource("/resources/PirateSong.wav");
		Media media = new Media(song.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		// plays song
		mediaPlayer.play();
		
		// sets up stage and canvas
		root.setPrefSize(600, 600);
		Canvas canvas = new Canvas(600, 600);
		root.getChildren().addAll(canvas, player);
		
		GraphicsContext gc = canvas.getGraphicsContext2D(); // initialize the canvas for 2D drawing
		Scene scene = new Scene(root);
		
		// creates instances of Bonus Gem, Gem, and Cacti class
		BonusGem bGem = new BonusGem(gc, canvas);
		Gem gem = new Gem(gc, canvas);
		Cacti cacti = new Cacti(gc, canvas);
		
		// handles key input on key pressed
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!input.contains(code))
                    input.add(code);
            }
        });

		// handles key input on key released
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (input.contains(code))
                    input.remove(code);
            }
        });
		
		// creates 4 normal gems in arrayList
		for (int i = 0; i < gem.numGems; i++) {
			gemList.add(new Gem(gc, canvas));
		}
		
		// creates 4 cacti in arrayList
		for (int i = 0; i < cacti.numCacti; i++) {
			cactiList.add(new Cacti(gc, canvas));
		}
		
		// loads and opens Time Screen
		openTimeScreen(timeStage);
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle (long now) {
												
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // refreshes screen
								
				// draws rectangle around cacti and detects collision with it
				for (int i = 0; i < cacti.numCacti; i++) {
					cactiList.get(i).drawCacti(cacti.xValues[i], cacti.yValues[i]);					
					boolean cactusCollision = player.cactusCollision(cactiList.get(i), i);
					if (cactusCollision) {
						timeCntrl.addExtraTime(-2);
						player.hitBadObject();
					}
				}
				
				// draws background
				gc.drawImage(background, 0, 0);
				
				// displays pattern in which gems must be collected
				gemPattern(gc);
				
				// draws black circle after gem pattern is finished
				if (gemsToGlow == -1) {
					rotateCircle();
					root.getChildren().add(c);
					circleDrawn = true;
					gemsToGlow -= 1;
				}
				
				// detects collision with circle after it is drawn
				if (circleDrawn) {
					double cXPos = 315 + c.getTranslateX();
					double cYPos = 315 + c.getTranslateY();
					boolean circleCollision = player.circleCollision(cXPos, cYPos);
					if (circleCollision) {
						player.hitBadObject();
						timeCntrl.addExtraTime(-1);
					}
				}
									
				// draws & animates player
				drawPlayer();
								
				// draws normal gems and detects collision with each individual gem
				for (int i = 0; i < gem.numGems; i++) {
					gemList.get(i).drawGems(gem.xValues[i], gem.yValues[i]);
					boolean gemCollision = player.gemCollision(gemList.get(i), i);
					if (gemCollision) {
						if (firstGemHit == false && i == gem.numGems -1) {
							startTime = System.currentTimeMillis();
							timeCntrl.setG1Time(0);
							gem.numGems -= 1;
							firstGemHit = true;
						} 
						if (secondGemHit == false && i == gem.numGems -1) {
							currentTime = System.currentTimeMillis();
							elapsedTime = (currentTime - startTime) / 1000;
							timeCntrl.setG2Time(elapsedTime);
							gem.numGems -= 1;
							secondGemHit = true;
						}
						if (thirdGemHit == false && i == gem.numGems -1) {
							currentTime = System.currentTimeMillis();
							elapsedTime = (currentTime - startTime) / 1000;
							timeCntrl.setG3Time(elapsedTime);
							gem.numGems -= 1;
							thirdGemHit = true;
						}
						if (fourthGemHit == false && i == gem.numGems -1) {
							currentTime = System.currentTimeMillis();
							elapsedTime = (currentTime - startTime) / 1000;
							timeCntrl.setG4Time(elapsedTime);
							openEndScreen(endStage);
							gem.numGems -= 1;
							fourthGemHit = true;
							primaryStage.close();
							timeStage.close();
						}
					}
				}
				
				// draws bonus gem and detects collision with the bonus gem
				if (timesBGemDrawn == 1) {
					boolean bonusGemCollision = player.bonusGemCollision(bGem);
					if (!bonusGemCollision) {
						bGem.drawBonusGem();
					} else {
						timeCntrl.addExtraTime(3);
						timesBGemDrawn -= 1;
					}
				}
			}
		};
		timer.start();
		primaryStage.setTitle("Order Of The Gems: Game Screen");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void drawPlayer() {
		readInput = input;
		if (readInput.contains(up) && player.getTranslateY() > -2) {
			player.animation.setOffsetY(132); // sets offset Y of spreadsheet
			player.animation.setCount(2); // sets number of frames
			player.moveY(-2); // sets y translation
			player.animation.play(); // runs animation
			animationUp = true;
		} else if (readInput.contains(down) && player.getTranslateY() < 554) {
			if (animationUp == true) {
				player.animation.setOffsetY(42);
				player.animation.setCount(3);
			}
			player.moveY(2);
			player.animation.play();
		} else if (readInput.contains(right) && player.getTranslateX() < 554 ) {
			player.animation.setOffsetY(42);
			player.animation.setCount(3);
			player.moveX(2);
			player.animation.play();
			animationUp = false;
		} else if (readInput.contains(left) && player.getTranslateX() > -2) {
			player.animation.setOffsetY(87);
			player.animation.setCount(2);
			player.moveX(-2);
			player.animation.play();
			animationUp = false;
		} else {
			player.animation.stop(); // stops animation if no key is pressed
		}
	}
	
	private void rotateCircle() {
		// makes path for circle to follow
		Circle cPath = new Circle(315, 315, 60);
		
		// makes Circle c equal a new circle
		c = new Circle(315,315,10);
		
		// creates instance of PathTransition
		PathTransition transition = new PathTransition();
        transition.setNode(c);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(cPath);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play(); 
	}
	
 	private void delay(int t) {
		// puts gui to sleep
		try {
			TimeUnit.MILLISECONDS.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    private void gemPattern(GraphicsContext gc) {
    	Image glow = new Image(getClass().getResourceAsStream("/resources/Glow.png"));
    	int extraL = 55;
    	int extraH = 45;
    	int timeForGlow = 300;
    	if (timesScreenDrawn < 2) {
			timesScreenDrawn +=1;
		} else {
			if (gemsToGlow == 4) {
				gc.drawImage(glow, 100 - extraL, 50 - extraH); // draws glow behind gem
				delay(timeForGlow); // puts gui to sleep to freeze screen and allow gem to glow for a sufficent amount of time
				gemsToGlow -=1; // subtracts one from number of gems left to glow
			} else if (gemsToGlow == 3) {
				gc.drawImage(glow, 420 - extraL, 100 - extraH);
				delay(timeForGlow);
				gemsToGlow -=1;
			} else if (gemsToGlow == 2) {
				gc.drawImage(glow, 450 - extraL, 520 - extraH);
				delay(timeForGlow);
				gemsToGlow -=1;
			} else if (gemsToGlow == 1) {
				gc.drawImage(glow, 142 - extraL, 487 - extraH);
				delay(timeForGlow);
				gemsToGlow -=1;
			} else if (gemsToGlow == 0) {
				delay(timeForGlow);
				gemsToGlow-=1;
			}
		}
    }
    
    private void openEndScreen(Stage endStage) {
		try {
			// loads fxml file
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreen.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			
			// sets scene and applies css
			Scene scene = new Scene(root,414,401);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// creates instance of endScreenController
			EndScreenController endScreen = loader.getController();
			
			// calls methods to get / display final times
			endScreen.getTotalTime(timeCntrl.getG4Time());
			if (timeCntrl.getExtTime() == "Calculating...") {
				endScreen.getExtTime(timeCntrl.getExtTime());
			} else {
				timeCntrl.addExtraTime(0);
				endScreen.getExtTime(timeCntrl.getExtTime());
			}
			endScreen.setOverallTime();
			
			// sets up stage
			endStage.setScene(scene);
			endStage.setTitle("Order Of The Gems: End Screen");
			endStage.setResizable(false);
			endStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    private void openTimeScreen(Stage timeStage) {
		try {
			// loads fxml file
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TimeScreen.fxml"));
			AnchorPane time = (AnchorPane)loader.load();
			
			// sets up scene and applies css
			Scene timeScene = new Scene(time,300,370);
			timeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			timeScene.getStylesheets().add("application.css");

			// makes instance of timeScreenController equal the controller
			timeCntrl = loader.getController();
			
			// sets up stage
			timeStage.setX(100);
			timeStage.setY(100);
			timeStage.setTitle("Order Of The Gems: Time Screen");
			timeStage.setScene(timeScene);
			timeStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    /**
	   * This method closes the primaryStage
	   */
	public static void quitWindow() {
		Platform.exit(); // closes game
	}
	
	/**
	   * This method launches the main method
	   * @param args The command line arguments
	   */
	public static void main (String args[]) {
		launch(args); // launches game
	}
}