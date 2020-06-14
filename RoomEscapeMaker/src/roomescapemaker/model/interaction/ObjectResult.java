package roomescapemaker.model.interaction;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import roomescapemaker.model.ObjectStatus;
import roomescapemaker.model.RoomObject;

public class ObjectResult {
	
	private final ObjectProperty<RoomObject> targetObject = new SimpleObjectProperty<RoomObject>();
	private final IntegerProperty targetIndex = new SimpleIntegerProperty();
	
	public ObjectResult(RoomObject targetObject, int targetIndex) {
		this.targetObject.set(targetObject);
		this.targetIndex.set(targetIndex);
	}
	
	public RoomObject getTargetObject() {
		return targetObject.get();
	}
	
	public void setTargetObject(RoomObject targetObject) {
		this.targetObject.set(targetObject);
	}
	
	public ObjectProperty<RoomObject> targetObjectProperty(){
		return targetObject;
	}
	
	public int getTargetIndex() {
		return targetIndex.get();
	}
	
	public void setTargetIndex(int targetIndex) {
		this.targetIndex.set(targetIndex);
	}
	
	public IntegerProperty targetIndexProperty() {
		return targetIndex;
	}
	
	public ObjectStatus getStatus() {
		return getTargetObject().getStatus(getTargetIndex());
	}
	
	
	public void executeObjectResult() {
		if (getTargetIndex() != -1) {
			targetObject.get().setCurrentStatus(getTargetIndex());
		}
	}
	
}
