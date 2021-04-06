import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/*
 * The event handler for the Back button in the About page
 */
public class AboutPage 
{
	@FXML 
	private Button Back;
	
	@FXML
	private TextArea Text;
	
	public void BackClick(ActionEvent event) throws IOException 
	{
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
