package roomescapemaker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RoomObject implements Serializable{
	
	
	private transient static final long serialVersionUID = 1L;
	
	private transient final StringProperty objectName = new SimpleStringProperty();
	private transient final IntegerProperty currentStatus = new SimpleIntegerProperty();
	private transient ObservableList<ObjectStatus> statusList = FXCollections.observableArrayList();
	
	public RoomObject() {
		this.objectName.set(null);
		this.statusList.add(new ObjectStatus("default", null));
		this.currentStatus.set(0);
	}
	
	public RoomObject(String objectName, String defaultImageURL) {
		this.objectName.set(objectName);
		this.statusList.add(new ObjectStatus("default", new Image(defaultImageURL)));
		this.currentStatus.set(0);

	}
	
	public String getObjectName() {
		return objectName.get();
	}
	
	public void setObjectName(String objectName) {
		this.objectName.set(objectName);
	}
	
	public StringProperty objectNameProperty() {
		return objectName;
	}
	
	public int getCurrentStatus() {
		return currentStatus.get();
	}
	
	public void setCurrentStatus(int currentStatus) {
		this.currentStatus.set(currentStatus);
	}
	
	public IntegerProperty currentStatusProperty() {
		return currentStatus;
	}
	
	public ObservableList<ObjectStatus> getStatusList(){
		return statusList;
	}
	
	public void addStatus(String statusName, String imageFileURL) {
		statusList.add(new ObjectStatus(statusName, new Image(imageFileURL)));
	}
	
	public ObjectStatus getStatus(int index) {
		return statusList.get(index);
	}
	
	public void removeStatus(int index) {
		statusList.remove(index);
	}
	
	// for serialization
	
	private void writeObject(ObjectOutputStream oos) throws IOException{
	
		oos.defaultWriteObject();
		oos.writeChars(objectName.get());
		oos.writeInt(currentStatus.get());
		oos.writeObject(new ArrayList<ObjectStatus>(statusList));
	}
	
}