package roomescapemaker.model.interaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import roomescapemaker.model.ObjectStatus;
import roomescapemaker.model.RoomObject;

public class ObjectResult implements Serializable{
	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = 1L;
	private transient ObjectProperty<RoomObject> targetObject = new SimpleObjectProperty<RoomObject>();
	private transient IntegerProperty targetIndex = new SimpleIntegerProperty();
	
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
	
	
	private void writeObject(ObjectOutputStream oos) throws IOException{	
		System.out.println("start scene result write ===" + getTargetIndex());
		oos.defaultWriteObject();
		// writing conditions
		oos.writeObject(targetObject.get());
		oos.writeInt(targetIndex.get());
		
		System.out.println("object result write complete targetobject: " + getTargetObject().getObjectName());
		
	}
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		
		ois.defaultReadObject();
		targetObject= new SimpleObjectProperty<RoomObject>((RoomObject)ois.readObject());
		targetIndex = new SimpleIntegerProperty(ois.readInt());
		System.out.println("object result read complete targetobject : " + getTargetObject().getObjectName());
	}
	
	
}
