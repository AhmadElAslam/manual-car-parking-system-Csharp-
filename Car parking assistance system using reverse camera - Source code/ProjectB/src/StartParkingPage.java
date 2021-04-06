import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/*
 * All event handlers of the Parking Search page.
 */
public class StartParkingPage implements Initializable
{
	@FXML
	private Text AngleText;
	@FXML
	private Text DirectionText;
	@FXML
	private Text DistanceText;
	@FXML
	private Text ParkText;
	
	@FXML
	private Button Back;
	@FXML
	private Button StartButton;
	
	@FXML
	private MediaView mv;
	private MediaPlayer mp;
	private Media me;
	@FXML
	private MediaView mv1;
	private MediaPlayer mp1;
	private Media me1;
	
	public void initialize(URL lovation, ResourceBundle resources) 
	{
		/*
		 * Setting up the Media player for the Camera View video
		 */
		String path = new File("src/media/Car.mp4").getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);
		mv.setMediaPlayer(mp);

		/*
		 * Setting up the Media player for the Map view video
		 */
		String path1 = new File("src/media/Map.mp4").getAbsolutePath();
		me1 = new Media(new File(path1).toURI().toString());
		mp1 = new MediaPlayer(me1);
		mv1.setMediaPlayer(mp1);
		StartButton.setDisable(true);
		
		mp.setAutoPlay(true);
		mp1.setAutoPlay(true);
		
		/*
		 * Setting up a TimeLine and Keyframes for the changes of the instructions
		 */
		Timeline timeline = new Timeline();		
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0),event1));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(16500),event2));
		timeline.play();
	}
	
	/*Instructions changes by Timeline and Keyframes - First Maneuver instructions*/
	EventHandler event1 = new EventHandler()
	{
		@Override
		public void handle(Event event1) {
			ParkText.setVisible(true);
			ParkText.setStyle("-fx-text-fill: red;");
			AngleText.setVisible(true);
			DirectionText.setVisible(true);
			DistanceText.setVisible(true);
			
			ParkText.setText("No spot found");
			AngleText.setText("-");
			DirectionText.setText("-");
			DistanceText.setText("-");
		}
	};
	EventHandler event2 = new EventHandler()
	{
		@Override
		public void handle(Event event2) {
			StartButton.setDisable(false);
			ParkText.setFill(Color.YELLOW);
			ParkText.setText("Park Found !");
			mp.pause();
			mp1.pause();

		}
	};
	
	/*
	 * After clicking the Start button, it starts giving instructions by time.
	 */
	public void StartClick(ActionEvent event) throws IOException {
		StartButton.setVisible(false);	
		AngleText.setText("115° Right ->");
		DirectionText.setFill(Color.WHITE);
		DirectionText.setText("Reverse");
		DistanceText.setFill(Color.rgb(236,202,0));
		DistanceText.setText("3.6 Meters");
		Timeline timeline1 = new Timeline();
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(1000),OneSecond));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(2000),TwoSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(4000),FourSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(6000),SixSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(8000),EightSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(10000),TenSeconds));
		
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(10600),event3)); // Second Maneuver instructions
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(12000),TwelveSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(13600),ThirteenSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(15600),FifteenSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(17600),SeventeenSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(19600),NineteenSeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(21600),TwentyoneSeconds));

		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(27000),event4)); // Third Maneuver instructions
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(33000),ThirtySeconds));
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(34000),ThirtyoneSeconds));
		
		timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(35000),event5)); // Stop instruction
		timeline1.play();
	}
	
	/*
	 * After clicking the Start button, it starts giving instructions by time.
	 */
	EventHandler OneSecond = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			mp.play();
			mp1.play();
		}
	};
	EventHandler TwoSeconds = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,155,0));
			DistanceText.setText("2.8 Meters");
		}
	};
	EventHandler FourSeconds = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,83,0));
			DistanceText.setText("2.1 Meters");
		}
	};
	EventHandler SixSeconds = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,36,0));
			DistanceText.setText("1.4 Meters");
		}
	};
	EventHandler EightSeconds = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,0,0));
			DistanceText.setText("0.9 Meters");
		}
	};
	EventHandler TenSeconds = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(255,0,0));
			DistanceText.setText("0.2 Meters");
		}
	};

	EventHandler event3 = new EventHandler()
	{
		@Override
		public void handle(Event event3) {
			
			//ParkText.setText("Test End");
			AngleText.setFill(Color.LIGHTBLUE);
			AngleText.setText("<- 115° Left");
			DirectionText.setText("Reverse");
			DistanceText.setFill(Color.rgb(236,202,0));
			DistanceText.setText("3.6 Meters");
			mp.pause();
			mp1.pause();
		}
	};
	EventHandler TwelveSeconds = new EventHandler()
	{
		@Override
		public void handle(Event event3) {
			mp.play();
			mp1.play();
		}
	};
	EventHandler ThirteenSeconds = new EventHandler()
	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,155,0));
			DistanceText.setText("2.8 Meters");
		}
	};
	EventHandler FifteenSeconds = new EventHandler()
	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,83,0));
			DistanceText.setText("2.2 Meters");
		}
	};
	EventHandler SeventeenSeconds = new EventHandler()
	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,0,0));
			DistanceText.setText("2.0 Meters");
		}
	};
	EventHandler NineteenSeconds = new EventHandler()
	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(255,0,0));
			DistanceText.setText("0.8 Meters");
		}
	};
	EventHandler TwentyoneSeconds = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(255,0,0));
			DistanceText.setText("0.5 Meters");
		}
	};
	
	EventHandler event4 = new EventHandler()
	{
		@Override
		public void handle(Event event4) {
			
			//ParkText.setText("Test End");
			AngleText.setText("0°");
			DirectionText.setFill(Color.GREEN);
			DirectionText.setText("Forward");
			DistanceText.setFill(Color.rgb(236,83,0));
			DistanceText.setText("1.8 Meters");
		}
	};
	EventHandler ThirtySeconds = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,36,0));
			DistanceText.setText("0.8 Meters");
		}
	};
	EventHandler ThirtyoneSeconds = new EventHandler()

	{
		@Override
		public void handle(Event event3) {
			DistanceText.setFill(Color.rgb(236,0,0));
			DistanceText.setText("0.2 Meters");
		}
	};
	
	EventHandler event5 = new EventHandler()
	{
		@Override
		public void handle(Event event3) {
			
			ParkText.setStyle("-fx-font-size: 12px;");
			ParkText.setText("Stop - Successfuly Parked");
			AngleText.setText("-");
			DirectionText.setText("-");
			DistanceText.setText("-");
		}
	};
	
	/*
	 * The event handler for the Back button in the Parking Search page.
	 */
	public void BackClick(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("ParkingAssistance");
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.show();
		((Node) event.getSource()).getScene().getWindow().hide();
	}
	
}