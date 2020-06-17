package roomescapemaker.model.interaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import roomescapemaker.model.ObjectStatus;
import roomescapemaker.model.RoomObject;

public class InteractionCondition implements Serializable{
	
	private transient ObjectProperty<RoomObject> mainObject = new SimpleObjectProperty<RoomObject>();
	
	private transient ObjectProperty<ConditionAction> mainAction = new SimpleObjectProperty<ConditionAction>();

	private transient IntegerProperty conditionIndex = new SimpleIntegerProperty();
	
	public InteractionCondition(RoomObject object, ConditionAction mainAction) {
		this.mainObject.set(object);
		this.mainAction.set(mainAction);
		this.conditionIndex.set(-1);
	}
	
	public InteractionCondition(RoomObject object, int conditionIndex) {
		this.mainObject.set(object);
		this.mainAction.set(null);
		this.conditionIndex.set(conditionIndex);
	}
	
	public RoomObject getMainObject() {
		return mainObject.get();
	}
	
	public void setMainObject(RoomObject mainObject) {
		this.mainObject.set(mainObject);
	}
	
	public ObjectProperty<RoomObject> mainObjectProperty() {
		return mainObject;
	}
	
	public ConditionAction getMainAction() {
		return mainAction.get();
	}
	
	public void setMainAction(ConditionAction mainAction) {
		this.mainAction.set(mainAction);
	}
	
	public ObjectProperty<ConditionAction> mainActionProperty() {
		return mainAction;
	}
	
	public int getConditionIndex() {
		return conditionIndex.get();
	}
	
	public void setConditionIndex(int conditionIndex) {
		this.conditionIndex.set(conditionIndex);
	}
	
	public IntegerProperty conditionIndexProperty() {
		return conditionIndex;
	}
	
	public ObjectStatus getStatus() {
		return getMainObject().getStatus(getConditionIndex());
	}
	
	public boolean checkCondition() {
		if (conditionIndex.get() == -1) {
			return true;
		} else {
			if (mainObject.get().getCurrentStatus() == conditionIndex.get()){
				return true;
			} else {
				return false;
			}
		}
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException{
		
		System.out.println("start write condition ===" + getConditionIndex());
		oos.defaultWriteObject();
		// writing conditions
		oos.writeObject(mainObject.get());
		oos.writeObject(mainAction.get());
		oos.writeInt(conditionIndex.get());
		
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		
		ois.defaultReadObject();
		mainObject = new SimpleObjectProperty<RoomObject>((RoomObject) ois.readObject());
		mainAction = new SimpleObjectProperty<ConditionAction>((ConditionAction)ois.readObject());
		conditionIndex = new SimpleIntegerProperty(ois.readInt());
		System.out.println("result interaction condition index: " + getConditionIndex());
	}
	
	
}
