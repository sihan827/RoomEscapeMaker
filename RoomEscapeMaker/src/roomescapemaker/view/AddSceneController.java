package roomescapemaker.view;

import roomescapemaker.model.RoomScene;

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



public class AddSceneController implements Initializable{
	
	@FXML
	private TextField sceneNameField;
	
	@FXML
	private Label filePathLabel;
	
	@FXML
	private Button browseImageFileBtn;
	
	@FXML
	private Button addSceneBtn;
	
	@FXML
	private Button cancelBtn;
	
	private Stage addSceneStage;
	
	private Stage fileChooserDialog;
	
	private boolean okClicked = false;
	
	private RoomScene scene;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setDialogStage(Stage addSceneStage) {
		this.addSceneStage = addSceneStage;
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
	private void onClickAddSceneBtn(ActionEvent event) {
		if (isInputValid()) {
			scene.setSceneName(sceneNameField.getText());
			scene.setBackGroundImage(new Image(filePathLabel.getText()));
			okClicked = true;
			addSceneStage.close();
		}
	}
	
	@FXML 
	private void onClickCancelBtn() {
		addSceneStage.close();
	}
	
	private boolean isInputValid() {
		if (sceneNameField.getText() == null || sceneNameField.getText().length() == 0) {
			return false;
		} else if (filePathLabel.getText() == null || filePathLabel.getText().length() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setScene(RoomScene newRoomScene) {
		this.scene = newRoomScene;
	}
	
	
}
