package roomescapemaker.model.interaction;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import roomescapemaker.model.ObjectStatus;
import roomescapemaker.model.RoomObject;

public class InteractionCondition {
	
	private final ObjectProperty<RoomObject> mainObject = new SimpleObjectProperty<RoomObject>();
	
	private final ObjectProperty<ConditionAction> mainAction = new SimpleObjectProperty<ConditionAction>();

	private final IntegerProperty conditionIndex = new SimpleIntegerProperty();
	
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
	
	
}
