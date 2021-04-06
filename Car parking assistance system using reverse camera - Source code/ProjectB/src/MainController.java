import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/*
 * The event handler for Parking Search,Settings, Connect, Help and About pages.
 */
public class MainController {
@FXML
private Button startParking;
@FXML
private Button settings;
@FXML
private Button help;
@FXML
private Button about;

@FXML
private void StartParkingClick(ActionEvent event) throws IOException {
	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParkingSearchPage.fxml"));
	Parent root = (Parent) fxmlLoader.load();
	Stage stage = new Stage();
	Image icon = new Image("/PNG/CarPark.png");
	stage.getIcons().add(icon);
	stage.setTitle("Parking Search");
	stage.setScene(new Scene(root));
	stage.setResizable(false);
	stage.show();
	((Node) event.getSource()).getScene().getWindow().hide();
}

@FXML
private void SettingsClick(ActionEvent event) throws IOException {
	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingsPage.fxml"));
	Parent root = (Parent) fxmlLoader.load();
	Stage stage = new Stage();
	Image icon = new Image("/PNG/Settings.png");
	stage.getIcons().add(icon);
	stage.setTitle("Settings");
	stage.setScene(new Scene(root));
	stage.setResizable(false);
	stage.show();
	((Node) event.getSource()).getScene().getWindow().hide();
}

@FXML
private void ConnectClick(ActionEvent event) throws IOException {
	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConnectPage.fxml"));
	Parent root = (Parent) fxmlLoader.load();
	Stage stage = new Stage();
	Image icon = new Image("/PNG/Bluetooth.png");
	stage.getIcons().add(icon);
	stage.setTitle("Connect");
	stage.setScene(new Scene(root));
	stage.setResizable(false);
	stage.show();
	((Node) event.getSource()).getScene().getWindow().hide();
}

@FXML
private void HelpClick(ActionEvent event) throws IOException {
	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelpPage.fxml"));
	Parent root = (Parent) fxmlLoader.load();
	Stage stage = new Stage();
	Image icon = new Image("/PNG/Help.png");
	stage.getIcons().add(icon);
	stage.setTitle("Help");
	stage.setScene(new Scene(root));
	stage.setResizable(false);
	stage.show();
	((Node) event.getSource()).getScene().getWindow().hide();
}

@FXML
private void AboutClick(ActionEvent event) throws IOException {
	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutPage.fxml"));
	Parent root = (Parent) fxmlLoader.load();
	Stage stage = new Stage();
	Image icon = new Image("/PNG/About.png");
	stage.getIcons().add(icon);
	stage.setTitle("About");
	stage.setScene(new Scene(root));
	stage.setResizable(false);
	stage.show();
	((Node) event.getSource()).getScene().getWindow().hide();
}

/*
 * Testing button for the calculations of our algorithm
 */

/* 
@FXML
private void ButtonClick(ActionEvent event) throws IOException {
	//Net mynet = new Net();
	//mynet.main1();
	clDriving drive = new clDriving();
	drive.run();
}*/
}
