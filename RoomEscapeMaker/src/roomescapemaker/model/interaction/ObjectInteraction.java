package roomescapemaker.model.interaction;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObjectInteraction {
	
	// 조건
	private final StringProperty conditionName = new SimpleStringProperty();
	private final ObjectProperty<InteractionCondition> primaryCondition = new SimpleObjectProperty<InteractionCondition>();
	private ObservableList<InteractionCondition> secondaryConditionList = FXCollections.observableArrayList();
	
	// 결과
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
	
	public int getTargetSceneIndex() {
		return sceneChangeResult.get().getTargetIndex();
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
