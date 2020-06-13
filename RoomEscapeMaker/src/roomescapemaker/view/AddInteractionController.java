package roomescapemaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import roomescapemaker.model.ObjectStatus;
import roomescapemaker.model.RoomObject;
import roomescapemaker.model.RoomScene;
import roomescapemaker.model.interaction.ConditionAction;
import roomescapemaker.model.interaction.InteractionCondition;
import roomescapemaker.model.interaction.ObjectInteraction;
import roomescapemaker.model.interaction.ObjectResult;
import roomescapemaker.model.interaction.SceneResult;

public class AddInteractionController implements Initializable{
	
	/*
	 * condition controls
	 */
	@FXML
	private TextField conditionNameField;
	
	@FXML
	private ChoiceBox<ConditionAction> objectActionBox;
	
	@FXML
	private TableView<InteractionCondition> objectConditionTable;
	
	@FXML
	private TableColumn<InteractionCondition, String> objectNameColumn;
	
	@FXML
	private TableColumn<InteractionCondition, String> statusNameColumn;
	
	@FXML 
	private ChoiceBox<RoomScene> conditionSceneBox;
	
	@FXML
	private ChoiceBox<RoomObject> conditionObjectBox;
	
	@FXML
	private ChoiceBox<ObjectStatus> conditionStatusBox;
	
	@FXML
	private Button conditionAddBtn;
	
	@FXML
	private Button conditionDeleteBtn;
	
	/*
	 * result control
	 */
	@FXML
	private TextField resultNameField;
	
	@FXML
	private ChoiceBox<RoomScene> sceneResultBox;
	
	@FXML
	private TableView<ObjectResult> objectResultTable;
	
	@FXML
	private TableColumn<ObjectResult, String> resultObjectNameColumn;
	
	@FXML
	private TableColumn<ObjectResult, String> resultStatusNameColumn;
	
	@FXML 
	private ChoiceBox<RoomScene> resultSceneBox;
	
	@FXML
	private ChoiceBox<RoomObject> resultObjectBox;
	
	@FXML
	private ChoiceBox<ObjectStatus> resultStatusBox;
	
	@FXML
	private Button resultAddBtn;
	
	@FXML
	private Button resultDeleteBtn;
	
	/*
	 * whole control buttons
	 */
	@FXML
	private Button okBtn;
	
	@FXML
	private Button cancelBtn;
	
	/*
	 * controller
	 */
	private Stage addInteractionStage;
	
	private boolean okClicked = false;
	
	private ObjectInteraction interaction;
	
	private ObservableList<RoomScene> sceneList;
	
	private RoomObject roomObject;
	
	public AddInteractionController() {
		
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
		objectNameColumn.setCellValueFactory(
				cellData->cellData.getValue().getMainObject().objectNameProperty());
		statusNameColumn.setCellValueFactory(
				cellData->cellData.getValue().getStatus().statusNameProperty());
		
		objectActionBox.getItems().setAll(ConditionAction.values());
		
		resultObjectNameColumn.setCellValueFactory(
				cellData->cellData.getValue().getTargetObject().objectNameProperty());
		resultStatusNameColumn.setCellValueFactory(
				cellData->cellData.getValue().getStatus().statusNameProperty());
		
		conditionSceneBox.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showConditionObjectBox(newValue));
		
		conditionObjectBox.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showConditionStatusBox(newValue));
		
		resultSceneBox.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showResultObjectBox(newValue));
		
		resultObjectBox.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showResultStatusBox(newValue));
		
	}
	
	public void showConditionObjectBox(RoomScene scene) {
		conditionObjectBox.setItems(scene.getRoomObjectList());
	}
	
	public void showConditionStatusBox(RoomObject roomObject) {
		if (conditionObjectBox.getValue() != null) {
			conditionStatusBox.setItems(roomObject.getStatusList());
		} else {
			ObservableList<ObjectStatus> clearChoiceBox = FXCollections.observableArrayList();
			conditionStatusBox.setItems(clearChoiceBox);
		}
	}
	
	public void showResultObjectBox(RoomScene scene) {
		resultObjectBox.setItems(scene.getRoomObjectList());
	}
	
	public void showResultStatusBox(RoomObject roomObject) {
		if (resultObjectBox.getValue() != null) {
			resultStatusBox.setItems(roomObject.getStatusList());
		} else {
			ObservableList<ObjectStatus> clearChoiceBox = FXCollections.observableArrayList();
			resultStatusBox.setItems(clearChoiceBox);
		}
	}
	
	@FXML
	private void onClickConditionAddBtn(ActionEvent event) {
		if (conditionSceneBox.getSelectionModel().getSelectedItem() != null &&
				conditionObjectBox.getSelectionModel().getSelectedItem() != null &&
				conditionStatusBox.getSelectionModel().getSelectedItem() != null) {
			interaction.addSecondaryCondition(new InteractionCondition(conditionObjectBox.getValue(), 
					conditionStatusBox.getSelectionModel().getSelectedIndex()));
		} else {
			return;
		}
	}
	
	@FXML
	private void onClickConditionDeleteBtn(ActionEvent event) {
		if (objectConditionTable.getSelectionModel().getSelectedItem() != null) {
			interaction.removeSecondaryCondition(objectConditionTable.getSelectionModel().getSelectedIndex());
		} else {
			return;
		}
	}
	
	@FXML
	private void onClickResultAddBtn(ActionEvent event) {
		if (resultSceneBox.getSelectionModel().getSelectedItem() != null &&
				resultObjectBox.getSelectionModel().getSelectedItem() != null &&
				resultStatusBox.getSelectionModel().getSelectedItem() != null) {
			interaction.addObjectResult(new ObjectResult(resultObjectBox.getValue(), 
					resultStatusBox.getSelectionModel().getSelectedIndex()));
		} else {
			return;
		}
	}
	
	@FXML
	private void onClickResultDeleteBtn(ActionEvent event) {
		if (objectResultTable.getSelectionModel().getSelectedItem() != null) {
			interaction.removeObjectResult(objectResultTable.getSelectionModel().getSelectedIndex());
		} else {
			return;
		}
	}
	
	@FXML
	private void onClickOkBtn(ActionEvent event) {
		if (isInputValid()) {
			interaction.setConditionName(conditionNameField.getText());
			interaction.setPrimaryCondition(new InteractionCondition(roomObject, objectActionBox.getValue()));
			interaction.setResultName(resultNameField.getText());
			interaction.setSceneChangeResult(new SceneResult(sceneResultBox.getSelectionModel().getSelectedIndex()));
			okClicked = true;
			addInteractionStage.close();
		} else {
			return;
		}
	}
	
	@FXML 
	private void onClickCancelBtn() {
		addInteractionStage.close();
	}
	
	private boolean isInputValid() {
		if (conditionNameField.getText() == null || conditionNameField.getText().length() == 0) {
			System.out.println("condition name is blank!");
			return false;
		} else if (objectActionBox.getValue() == null) {
			System.out.println("you have to choose one of condition object action!");
			return false;
		} else if (resultNameField.getText() == null || resultNameField.getText().length() == 0) {
			System.out.println("result name is blank!");
			return false;
		} else if (sceneResultBox.getValue() == null) {
			System.out.println("you have to choose one of result scene!");
			return false;
		} else {
			return true;
		}
	}
	
	public void setDialogStage(Stage addInteractionStage) {
		this.addInteractionStage = addInteractionStage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setInteraction(ObjectInteraction newInteraction) {
		this.interaction = newInteraction;
		objectConditionTable.setItems(interaction.getSecondaryConditionList());
		objectResultTable.setItems(interaction.getObjectResultList());
	}
	
	public void setSceneList(ObservableList<RoomScene> sceneList) {
		this.sceneList = sceneList;
		conditionSceneBox.setItems(sceneList);
		sceneResultBox.setItems(sceneList);
		resultSceneBox.setItems(sceneList);
	}
	
	public void setRoomObject(RoomObject ro) {
		this.roomObject = ro;
	}
	
}
