package roomescapemaker.model.interaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObjectInteraction implements Serializable{
	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = 1L;
	// condition
	private transient StringProperty conditionName = new SimpleStringProperty();
	private transient ObjectProperty<InteractionCondition> primaryCondition = new SimpleObjectProperty<InteractionCondition>();
	private transient ObservableList<InteractionCondition> secondaryConditionList = FXCollections.observableArrayList(); 
	
	// result
	private transient StringProperty resultName = new SimpleStringProperty();
	private transient ObjectProperty<SceneResult> sceneChangeResult = new SimpleObjectProperty<SceneResult>();
	private transient ObservableList<ObjectResult> objectResultList = FXCollections.observableArrayList();
	
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
	
	
	private void writeObject(ObjectOutputStream oos) throws IOException{
		
		System.out.println("start object interation write ===" + getConditionName());
		oos.defaultWriteObject();
		// writing conditions
		oos.writeObject(conditionName.get());
		oos.writeObject(primaryCondition.get());
		oos.writeObject(new ArrayList<InteractionCondition>(secondaryConditionList));
		System.out.println("condition write ===" + getConditionName());
		// writing results
		oos.writeObject(resultName.get());
		oos.writeObject(sceneChangeResult.get());
		oos.writeObject(new ArrayList<ObjectResult>(objectResultList));
		System.out.println("result write : " + getResultName());
		
		
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		
		ois.defaultReadObject();
		conditionName = new SimpleStringProperty((String)ois.readObject());
		primaryCondition = new SimpleObjectProperty<InteractionCondition>((InteractionCondition)ois.readObject());
		secondaryConditionList = FXCollections.observableArrayList((ArrayList<InteractionCondition>) ois.readObject());
		
		System.out.println("condition read ===" + getConditionName());
		
		resultName = new SimpleStringProperty((String)ois.readObject());
		sceneChangeResult = new SimpleObjectProperty<SceneResult>((SceneResult)ois.readObject());
		objectResultList = FXCollections.observableArrayList((ArrayList<ObjectResult>) ois.readObject());
		System.out.println("result read : " + getResultName());
	}
}
