package roomescapemaker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import roomescapemaker.model.interaction.ObjectInteraction;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RoomObject implements Serializable{
	
	
	private transient static final long serialVersionUID = 1L;
	

	private transient StringProperty objectName = new SimpleStringProperty();
	private transient IntegerProperty currentStatus = new SimpleIntegerProperty();
	private transient ObservableList<ObjectStatus> statusList = FXCollections.observableArrayList();
  private transient ObservableList<ObjectInteraction> interactionList = FXCollections.observableArrayList();
	private transient static String savePath;

	
	public RoomObject() {
		this.objectName.set(null);
		this.statusList.add(new ObjectStatus("default", null, getObjectName()));
		this.currentStatus.set(0);
	}
	
	public RoomObject(String obName) {
		this.objectName.set(obName);
		this.statusList.add(new ObjectStatus("default", null, getObjectName()));
		this.currentStatus.set(0);
	}
	
	public RoomObject(String objectName, String defaultImageURL) {
		this.objectName.set(objectName);
		this.statusList.add(new ObjectStatus("default", new Image(defaultImageURL), getObjectName()));
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
	

	private void writeObject(ObjectOutputStream oos) throws IOException{
	
		oos.defaultWriteObject();
		oos.writeObject(objectName.get());
		oos.writeObject(savePath);
		oos.writeInt(currentStatus.get());
		oos.writeInt(statusList.size());
		oos.writeObject(new ArrayList<ObjectStatus>(statusList));
		
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		
		ois.defaultReadObject();
		objectName = new SimpleStringProperty((String)ois.readObject());
		savePath = (String)ois.readObject();
		System.out.println("objectread===" + getObjectName() + ", " + getSavePath());
		
		currentStatus = new SimpleIntegerProperty(ois.readInt());
		System.out.println("curstatus: " + currentStatus.get());
		int statusSize = ois.readInt();
		System.out.println("stat size: " + statusSize);
		
		statusList = FXCollections.observableArrayList((ArrayList<ObjectStatus>) ois.readObject());
		
	}

	public static String getSavePath() {
		return savePath;
	}

	public static void setSavePath(String savePath) {
		RoomObject.savePath = savePath;
	}
		

	public void addInteraction(ObjectInteraction interaction) {
		interactionList.add(interaction);
	}
	
	public ObjectInteraction getObjectInteraction(int index) {
		return interactionList.get(index);
	}
	
	public void removeInteraction(int index) {
		interactionList.remove(index);
	}
	
	public ObservableList<ObjectInteraction> getInteractionList(){
		return interactionList;
	}
	
	/*
	 * showing ChoiceBox 
	 */
	@Override
	public String toString() {
		return getObjectName();
	}
}