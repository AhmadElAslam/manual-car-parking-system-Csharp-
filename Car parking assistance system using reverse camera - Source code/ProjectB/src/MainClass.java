import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainClass extends Application {
	JOptionPane frame;
	public static String ip;
	public static String fxmlDir = "fxml/";

	public static void main(String args[]) {
		try {
			ip = args[0];
		} catch (Exception e) {

		}
		launch(args);
	}
/**
 * this method is the start method to load the Main Page of the applications
 * 
 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
			Scene scene = new Scene(root);	Image icon = new Image("/PNG/Logo.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("ParkingAssistance");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
