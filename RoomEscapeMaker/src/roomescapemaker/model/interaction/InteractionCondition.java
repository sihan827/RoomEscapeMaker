package roomescapemaker.model.interaction;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import roomescapemaker.model.RoomObject;

public class InteractionCondition {
	//조건의 주체 오브젝트
	private final ObjectProperty<RoomObject> mainObject = new SimpleObjectProperty<RoomObject>();
	//조건의 주체에서 적용될 액션 객체 -> enum 타입이며 조건의 주 오브젝트에만 적용, 오직 1개
	private final ObjectProperty<ConditionAction> mainAction = new SimpleObjectProperty<ConditionAction>();
	//조건에서 비교할 상태의 인덱스 값
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
