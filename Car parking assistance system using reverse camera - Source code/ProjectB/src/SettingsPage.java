import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/*
 * All handlers inside the Settings page
 */
public class SettingsPage {
	ObservableList<String> LanguageList = FXCollections.observableArrayList("English", "Hebrew", "Arabic"); // Language Combobox
	ObservableList<String> UnitList = FXCollections.observableArrayList("cm", "inch"); // Units Combobox

	@FXML
	private ComboBox<String> Languages;
	@FXML
	private ComboBox<String> Units;

	@FXML
	private Button Back;

	@FXML
	private Button Save;

	@FXML
	private TextField Length;
	@FXML
	private TextField Width;
	@FXML
	private Text LengthText;
	@FXML
	private Text WidthText;

	@FXML
	private TextField MinTurnRadius;
	@FXML
	private Text MinTurnRadiusText;

	@FXML
	private TextField MaxSteeringWheel;
	@FXML
	private Text MaxSteeringWheelText;

	@FXML
	private TextField CameraDirection1;
	@FXML
	private TextField CameraDirection2;
	@FXML
	private Text CameraDirection1Text;
	@FXML
	private Text CameraDirection2Text;

	@FXML
	private TextField CameraAngles1;
	@FXML
	private TextField CameraAngles2;
	@FXML
	private Text CameraAngles1Text;
	@FXML
	private Text CameraAngles2Text;

	@FXML
	private TextField CameraPosition1;
	@FXML
	private TextField CameraPosition2;
	@FXML
	private TextField CameraPosition3;
	@FXML
	private Text CameraPosition1Text;
	@FXML
	private Text CameraPosition2Text;
	@FXML
	private Text CameraPosition3Text;

	public static double[] TextFieldArray = { 3.0, 1.5, 4.0, 180, 0.0, 0.0, 70.0, 50.0, -1.5, 0.0, 0.7 }; // Default values in settings
	public int count = 0;
	public int flag = 0;

	@FXML
	public void initialize() {
		Languages.setValue("English");
		Languages.setItems(LanguageList);
		Units.setValue("cm");
		Units.setItems(UnitList);
		/*Assigning default values to the text fields*/
		Length.setText(TextFieldArray[0] + "");
		Width.setText(TextFieldArray[1] + "");
		MinTurnRadius.setText(TextFieldArray[2] + "");
		MaxSteeringWheel.setText(TextFieldArray[3] + "");
		CameraDirection1.setText(TextFieldArray[4] + "");
		CameraDirection2.setText(TextFieldArray[5] + "");
		CameraAngles1.setText(TextFieldArray[6] + "");
		CameraAngles2.setText(TextFieldArray[7] + "");
		CameraPosition1.setText(TextFieldArray[8] + "");
		CameraPosition2.setText(TextFieldArray[9] + "");
		CameraPosition3.setText(TextFieldArray[10] + "");
	}

	/*Car Length input validation - It must be between 1-30 meters (in case of a bus), and it shouldn't be empty*/
	public void LengthValidation() {
		try {
			if ((Length.getText().isEmpty())
					|| ((Double.parseDouble((Length.getText())) < 1.0) || (Double.parseDouble((Length.getText())) > 30.0)))
				throw new NumberFormatException();
			else {
				LengthText.setVisible(false);
				Length.setStyle("");
				TextFieldArray[count] = Double.parseDouble((Length.getText()));
				Length.setText(TextFieldArray[count] + "");
			}

		} catch (NumberFormatException carLengthEx) {
			LengthText.setVisible(true);
			Length.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;
	}
	
	/*Car Width input validation - It must be between 1-3 meters, and it shouldn't be empty*/
	public void WidthValidation() {
		try {
			if ((Width.getText().isEmpty())
					|| ((Double.parseDouble((Width.getText())) < 1.0) || (Double.parseDouble((Width.getText())) > 3.0)))
				throw new NumberFormatException();
			else {
				WidthText.setVisible(false);
				Width.setStyle("");
				TextFieldArray[count] = Double.parseDouble((Width.getText()));
				Width.setText(TextFieldArray[count] + "");
			}

		} catch (NumberFormatException carLengthEx) {
			WidthText.setVisible(true);
			Width.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;
	}
	
	/*Car Minimum Turn Radius input validation - It must be between 0-20 meters, and it shouldn't be empty*/
	public void MinTurnRadiusValidation() {
		try {
			if ((MinTurnRadius.getText().isEmpty()) || ((Double.parseDouble((MinTurnRadius.getText())) < 0.0)
					|| (Double.parseDouble((MinTurnRadius.getText())) > 20.0)))
				throw new NumberFormatException();
			else {
				MinTurnRadiusText.setVisible(false);
				MinTurnRadius.setStyle("");
				TextFieldArray[count] = Double.parseDouble((MinTurnRadius.getText()));
				MinTurnRadius.setText(TextFieldArray[count] + "");
			}

		} catch (NumberFormatException carLengthEx)

		{
			MinTurnRadiusText.setVisible(true);
			MinTurnRadius.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;
	}
	
	/*Car Max Steering Wheel input validation - It must be between 10-360 degrees, and it shouldn't be empty*/
	public void MaxSteeringWheelValidation() {
		try {
			if ((MaxSteeringWheel.getText().isEmpty()) || ((Double.parseDouble((MaxSteeringWheel.getText())) < 10.0)
					|| (Double.parseDouble((MaxSteeringWheel.getText())) > 360.0)))
				throw new NumberFormatException();
			else {
				MaxSteeringWheelText.setVisible(false);
				MaxSteeringWheel.setStyle("");
				TextFieldArray[count] = Double.parseDouble((MaxSteeringWheel.getText()));
				MaxSteeringWheel.setText(TextFieldArray[count] + "");

			}

		} catch (NumberFormatException carLengthEx)

		{
			MaxSteeringWheelText.setVisible(true);
			MaxSteeringWheel.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;
	}
	
	/*Camera Direction input validation - It must be between (-5) - 5 degrees, and it shouldn't be empty*/
	public void CameraDirectionValidation() {
		try {
			if ((CameraDirection1.getText().isEmpty()) || ((Double.parseDouble((CameraDirection1.getText())) < -5.0)
					|| (Double.parseDouble((CameraDirection1.getText())) > 5.0)))
				throw new NumberFormatException();
			else {
				CameraDirection1Text.setVisible(false);
				CameraDirection1.setStyle("");
				TextFieldArray[count] = Double.parseDouble((CameraDirection1.getText()));
				CameraDirection1.setText(TextFieldArray[count] + "");
			}

		} catch (NumberFormatException carLengthEx)

		{
			CameraDirection1Text.setVisible(true);
			CameraDirection1.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}

		count++;

		try {
			if ((CameraDirection2.getText().isEmpty()) || ((Double.parseDouble((CameraDirection2.getText())) < -5.0)
					|| (Double.parseDouble((CameraDirection2.getText())) > 5.0)))
				throw new NumberFormatException();
			else {
				CameraDirection2Text.setVisible(false);
				CameraDirection2.setStyle("");
				TextFieldArray[count] = Double.parseDouble((CameraDirection2.getText()));
				CameraDirection2.setText(TextFieldArray[count] + "");
			}

		} catch (NumberFormatException carLengthEx)

		{
			CameraDirection2Text.setVisible(true);
			CameraDirection2.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;
	}
	
	/*Camera Angles input validation - It must be between 45 - 120 degrees, and it shouldn't be empty*/
	public void CameraAnglesValidation() {
		try {
			if ((CameraAngles1.getText().isEmpty()) || ((Double.parseDouble((CameraAngles1.getText())) < 45.0)
					|| (Double.parseDouble((CameraAngles1.getText())) > 120.0)))
				throw new NumberFormatException();
			else {
				CameraAngles1Text.setVisible(false);
				CameraAngles1.setStyle("");
				TextFieldArray[count] = Double.parseDouble((CameraAngles1.getText()));
				CameraAngles1.setText(TextFieldArray[count] + "");
			}

		} catch (NumberFormatException carLengthEx)

		{
			CameraAngles1Text.setVisible(true);
			CameraAngles1.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;

		try {
			if ((CameraAngles2.getText().isEmpty()) || ((Double.parseDouble((CameraAngles2.getText())) < 45.0)
					|| (Double.parseDouble((CameraAngles2.getText())) > 120.0)))
				throw new NumberFormatException();
			else {
				CameraAngles2Text.setVisible(false);
				CameraAngles2.setStyle("");
				TextFieldArray[count] = Double.parseDouble((CameraAngles2.getText()));
				CameraAngles2.setText(TextFieldArray[count] + "");
			}
		} catch (NumberFormatException carLengthEx)

		{
			CameraAngles2Text.setVisible(true);
			CameraAngles2.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;
	}
	
	/*Camera Position input validation - It must be: X: (-20) - 0 meters, Y: (-1) - 1, Z: 0.2 - 3 and it shouldn't be empty*/
	public void CameraPositionValidation()
	{
		try {
			if ((CameraPosition1.getText().isEmpty()) || ((Double.parseDouble((CameraPosition1.getText())) < -20.0)
					|| (Double.parseDouble((CameraPosition1.getText())) > 0.0)))
				throw new NumberFormatException();
			else {
				CameraPosition1Text.setVisible(false);
				CameraPosition1.setStyle("");
				TextFieldArray[count] = Double.parseDouble((CameraPosition1.getText()));
				CameraPosition1.setText(TextFieldArray[count] + "");
			}
		} catch (NumberFormatException carLengthEx)

		{
			CameraPosition1Text.setVisible(true);
			CameraPosition1.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;

		try {
			if ((CameraPosition2.getText().isEmpty()) || ((Double.parseDouble((CameraPosition2.getText())) < -1.0)
					|| (Double.parseDouble((CameraPosition2.getText())) > 1.0)))
				throw new NumberFormatException();
			else {
				CameraPosition2Text.setVisible(false);
				CameraPosition2.setStyle("");
				TextFieldArray[count] = Double.parseDouble((CameraPosition2.getText()));
				CameraPosition2.setText(TextFieldArray[count] + "");
			}
		} catch (NumberFormatException carLengthEx)

		{
			CameraPosition2Text.setVisible(true);
			CameraPosition2.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
		count++;

		try {
			if ((CameraPosition3.getText().isEmpty()) || ((Double.parseDouble((CameraPosition3.getText())) < 0.2)
					|| (Double.parseDouble((CameraPosition3.getText())) > 3.0)))
				throw new NumberFormatException();
			else {
				CameraPosition3Text.setVisible(false);
				CameraPosition3.setStyle("");
				TextFieldArray[count] = Double.parseDouble((CameraPosition3.getText()));
				CameraPosition3.setText(TextFieldArray[count] + "");
			}
		} catch (NumberFormatException carLengthEx)

		{
			CameraPosition3Text.setVisible(true);
			CameraPosition3.setStyle("-fx-border-color: yellow ; -fx-border-width: 2px ;");
			flag = 1;
		}
	}
	
	/*Setting the Combo boxes*/
	public void LanguageComboBoxSelect() {

		if (Languages.getValue().equals("English")) {
			Languages.setValue("English");
			Languages.setItems(LanguageList);
		} else if (Languages.getValue().equals("Hebrew")) {
			Languages.setValue("Hebrew");
			Languages.setItems(LanguageList);
		} else if (Languages.getValue().equals("Arabic")) {
			Languages.setValue("Arabic");
			Languages.setItems(LanguageList);
		}

		if (Units.getValue().equals("cm")) {
			Units.setValue("cm");
			Units.setItems(UnitList);
		} else if (Units.getValue().equals("inch")) {
			Units.setValue("inch");
			Units.setItems(UnitList);

		}

	}
	
	/*If Save button was clicked, it checks all the text fields, we set a flag=0 and if one of the validation checks was wrong
	  it would turn the flag = 1, else, it will stay 0 until the last check is done, this function then checks if the flag==0, then
	  all the validations are fine and it would go back to the main page with the new/old values (in case of a change)*/
	public void SaveClick(ActionEvent event) throws IOException {

		count = 0;
		flag = 0;
		LengthValidation();
		WidthValidation();
		MinTurnRadiusValidation();
		MaxSteeringWheelValidation();
		CameraDirectionValidation();
		CameraAnglesValidation();
		CameraPositionValidation();

		if (flag == 0) {
			/*for (int i = 0; i < 11; i++) {
				System.out.println(TextFieldArray[i] + " ");
			}*/
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("MainPage");
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
			((Node) event.getSource()).getScene().getWindow().hide();
		}
	}
	
	/*
	 * Gets a copy of the text fields values array 
	 */
	public double[] GetArray() {
		return TextFieldArray.clone();
	}

	/*
	 * Returns to the main page without saving any values in settings
	 */
	public void BackClick(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("MainPage");
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.show();
		((Node) event.getSource()).getScene().getWindow().hide();
	}

}
