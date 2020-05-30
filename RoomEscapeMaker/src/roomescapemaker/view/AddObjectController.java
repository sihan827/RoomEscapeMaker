package roomescapemaker.view;

import roomescapemaker.model.RoomObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class AddObjectController implements Initializable{
	
	@FXML
	private TextField objectNameField;
	
	@FXML
	private Label filePathLabel;
	
	@FXML
	private Button browseImageFileBtn;
	
	@FXML
	private TextField xPosField;
	
	@FXML
	private TextField yPosField;
	
	@FXML
	private Button addObjectBtn;
	
	@FXML
	private Button cancelBtn;
	
	private Stage addObjectStage;
	
	private Stage fileChooserDialog;
	
	private boolean okClicked = false;
	
	private RoomObject object;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setDialogStage(Stage addObjectStage) {
		this.addObjectStage = addObjectStage;
	}
	
	@FXML
	private void onClickBrowseImageFileBtn(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    	     new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png")
    	);
    	
    	fileChooser.setInitialDirectory(new File("."));
    	
    	File selectedFile = fileChooser.showOpenDialog(fileChooserDialog);
    	if (selectedFile == null) {
    		return;	
    	} else {
    		try {
    			filePathLabel.setText((selectedFile.toURI().toURL().toString()));
    		} catch (MalformedURLException e) {
    			e.printStackTrace();
    			System.out.println("wrong file path url");
    		}
    	}
	}
	
	@FXML
	private void onClickAddObjectBtn(ActionEvent event) {
		if (isInputValid()) {
			object.setObjectName(objectNameField.getText());
			object.getStatus(0).setImageFile(new Image(filePathLabel.getText()));
			object.getStatus(0).setXpos(Double.parseDouble(xPosField.getText()));
			object.getStatus(0).setYpos(Double.parseDouble(yPosField.getText()));
			okClicked = true;
			addObjectStage.close();
		}
	}
	
	@FXML 
	private void onClickCancelBtn() {
		addObjectStage.close();
	}
	
	private boolean isInputValid() {
		boolean isDouble;
		if (objectNameField.getText() == null || objectNameField.getText().length() == 0) {
			return false;
		} else if (filePathLabel.getText() == null || filePathLabel.getText().length() == 0) {
			return false;
		} else if (xPosField.getText() == null || xPosField.getText().length() == 0) {
			isDouble = false;
			try {
				Double.parseDouble(xPosField.getText());
				isDouble = true;
			} catch(NumberFormatException e){
				System.out.println("Coordinate x must be a double type!");
			}
			if (isDouble == false) 
				return false;
			else {
				if (yPosField.getText() == null || yPosField.getText().length() == 0){
					isDouble = false;
					try {
						Double.parseDouble(yPosField.getText());
						isDouble = true;
					} catch(NumberFormatException e){
						System.out.println("Coordinate y must be a double type!");
					}
					if (isDouble == false) 
						return false;
					else 
						return true;
				}
				else 
					return true;
			}		
		} else 
			return true;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setObject(RoomObject newRoomObject) {
		this.object = newRoomObject;
	}
	
	
}