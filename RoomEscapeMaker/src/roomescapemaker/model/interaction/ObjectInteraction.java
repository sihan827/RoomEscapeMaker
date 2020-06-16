package roomescapemaker.model.interaction;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import roomescapemaker.model.RoomObject;

public class ObjectInteraction {
	
	// condition
	private final StringProperty conditionName = new SimpleStringProperty();
	private final ObjectProperty<InteractionCondition> primaryCondition = new SimpleObjectProperty<InteractionCondition>();
	private ObservableList<InteractionCondition> secondaryConditionList = FXCollections.observableArrayList();
	
	// result
	private final StringProperty resultName = new SimpleStringProperty();
	private final ObjectProperty<SceneResult> sceneChangeResult = new SimpleObjectProperty<SceneResult>();
	private ObservableList<ObjectResult> objectResultList = FXCollections.observableArrayList();
	
	public ObjectInteraction() {
		primaryCondition.set(null);
		sceneChangeResult.set(null);
	}
	
	public String getConditionName() {
		return conditionName.get();
	}
	
	public void setConditionName(String conditionName) {
		this.conditionName.set(conditionName);
	}
	
	public StringProperty conditionNameProperty() {
		return conditionName;
	}
	
	public String getResultName() {
		return resultName.get();
	}
	
	public void setResultName(String resultName) {
		this.resultName.set(resultName);
	}
	
	public StringProperty resultNameProperty() {
		return resultName;
	}
	
	public InteractionCondition getPrimaryCondition() {
		return primaryCondition.get();
	}
	
	public void setPrimaryCondition(InteractionCondition primaryCondition) {
		this.primaryCondition.set(primaryCondition);
	}
	
	public ObjectProperty<InteractionCondition> primaryConditionProperty(){
		return primaryCondition;
	}
	
	public SceneResult getSceneChangeResult() {
		return sceneChangeResult.get();
	}
	
	public void setSceneChangeResult(SceneResult sceneChangeResult) {
		this.sceneChangeResult.set(sceneChangeResult);
	}
	
	public ObjectProperty<SceneResult> sceneChangeResultProperty(){
		return sceneChangeResult;
	}
	
	public void addSecondaryCondition(InteractionCondition newCondition) {
		secondaryConditionList.add(newCondition);
	}
	
	public InteractionCondition getSecondaryCondition(int index) {
		return secondaryConditionList.get(index);
	}
	
	public void removeSecondaryCondition(int index) {
		secondaryConditionList.remove(index);
	}
	
	public ObservableList<InteractionCondition> getSecondaryConditionList(){
		return secondaryConditionList;
	}
	
	public void addObjectResult(ObjectResult newObjectResult) {
		objectResultList.add(newObjectResult);
	}
	
	public ObjectResult getObjectResult(int index) {
		return objectResultList.get(index);
	}
	
	public void removeObjectResult(int index) {
		objectResultList.remove(index);
	}
	
	public ObservableList<ObjectResult> getObjectResultList(){
		return objectResultList;
	}
	
	public int getTargetSceneIndex() {
		return sceneChangeResult.get().getTargetIndex();
	}
	
	public boolean checkValidAction() {
		int count = 0;
		for (InteractionCondition ic : secondaryConditionList) {
			if (ic.getMainObject() == primaryCondition.get().getMainObject()) { 
				if (ic.getConditionIndex() == primaryCondition.get().getMainObject().getCurrentStatus()) {
					return true;
				} else {
					count++;
				}
			}
		}
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkSecondaryConditionList() {
		if (secondaryConditionList.size() == 0) {
			return true;
		} else {
			for (InteractionCondition condition : secondaryConditionList) {
				if (condition.checkCondition()) {
					continue;
				} else {
					return false;
				}
			}
			return true;
		} 
	}
	
	public boolean checkPossessedCondition(RoomObject usingItem) {
		boolean hasPossessCondition = false;
		RoomObject ro = new RoomObject();
		if (secondaryConditionList.size() == 0) {
			return true;
		} else {
			for (InteractionCondition condition : secondaryConditionList) {
				if (condition.getMainObject().getStatus(condition.getConditionIndex()).getPossess() == true) {
					ro = condition.getMainObject();
					hasPossessCondition = true;
				} else {
					continue;
				}
			}
			if (hasPossessCondition) {
				if (ro == usingItem) {
					return true;
				} else {
				return false;
				} 
			} else {
				return true;
			}
		}
	}
	
	public void executeObjectResultList() {
		if (objectResultList.size() == 0) {
			return;
		} else {
			for (ObjectResult result : objectResultList) {
				result.executeObjectResult();
			}
		}
	}
}
