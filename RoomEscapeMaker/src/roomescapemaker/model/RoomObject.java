package roomescapemaker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class RoomObject {
	
	private final StringProperty objectName = new SimpleStringProperty();
	private final IntegerProperty currentStatus = new SimpleIntegerProperty();
	private ObservableList<ObjectStatus> statusList = FXCollections.observableArrayList();
	
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
	
}