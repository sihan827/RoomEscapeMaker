package roomescapemaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	
	private Stage addInteractionStage;
	
	private boolean okClicked = false;
	
	private ObjectInteraction interaction;
	
	private ObservableList<RoomScene> sceneList;
	
	public AddInteractionController() {
		
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
		objectNameColumn.setCellValueFactory(
				cellData->cellData.getValue().getMainObject().objectNameProperty());
		statusNameColumn.setCellValueFactory(
				cellData->cellData.getValue().getStatus().statusNameProperty());
		
		conditionSceneBox.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showConditionObjectBox(newValue));
		
		conditionObjectBox.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showConditionStatusBox(newValue));
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
	
	public void setDialogStage(Stage addInteractionStage) {
		this.addInteractionStage = addInteractionStage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setInteraction(ObjectInteraction newInteraction) {
		this.interaction = newInteraction;
	}
	
	public void setSceneList(ObservableList<RoomScene> sceneList) {
		this.sceneList = sceneList;
		conditionSceneBox.setItems(sceneList);
	}
	
}
