package roomescapemaker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;


public class RoomScene {
	
	public final StringProperty sceneName = new SimpleStringProperty();
	public final StringProperty backGroundImageFileName = new SimpleStringProperty();
	private ObservableList<RoomObject> roomObjectList = FXCollections.observableArrayList();
	
	public RoomScene(String sceneName, String backGroundImageFileName) {
		this.sceneName.set(sceneName);
		this.backGroundImageFileName.set(backGroundImageFileName);
	}
	
	public String getSceneName() {
		return sceneName.get();
	}
	
	public void setSceneName(String sceneName) {
		this.sceneName.set(sceneName);
	}
	
	public StringProperty sceneNameProperty() {
		return sceneName;
	}
	
	public String getBackGroundImageFileName() {
		return backGroundImageFileName.get();
	}
	
	public void setBackGroundImageFileName(String backGroundImageFileName) {
		this.backGroundImageFileName.set(backGroundImageFileName);
	}
	
	public StringProperty backGroundImageFileNameProperty() {
		return backGroundImageFileName;
	}
	
	public ObservableList<RoomObject> getRoomObjectList(){
		return roomObjectList;
	}
	
	public void addRoomObject(String objectName) {
		roomObjectList.add(new RoomObject(objectName));
	}
	
	public RoomObject getRoomObject(int index) {
		return roomObjectList.get(index);
	}
	
	public void removeRoomObject(int index) {
		roomObjectList.remove(index);
	}
	
}
