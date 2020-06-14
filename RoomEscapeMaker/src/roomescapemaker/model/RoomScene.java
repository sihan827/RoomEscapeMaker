package roomescapemaker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class RoomScene implements Serializable{


	private transient static final long serialVersionUID = 1L;
	
	public transient StringProperty sceneName = new SimpleStringProperty();
	public transient ObjectProperty<Image> backGroundImage = new SimpleObjectProperty<Image>();
	private transient ObservableList<RoomObject> roomObjectList = FXCollections.observableArrayList();
	private transient static String savePath;
	private transient static String openPath;
	
	public RoomScene(String sceneName, Image backGroundImage) {
		this.sceneName.set(sceneName);
		this.backGroundImage.set(backGroundImage);
	}
	
	public String getSceneName() {
		return sceneName.get();
	}
	public static void setSavePath(String path) {
		RoomScene.savePath = path;
	}
	public static String getSavePath() {
		return savePath;
	}
	public void setSceneName(String sceneName) {
		this.sceneName.set(sceneName);
	}
	
	public StringProperty sceneNameProperty() {
		return sceneName;
	}
	
	public Image getBackGroundImage() {
		return backGroundImage.get();
	}
	
	public void setBackGroundImage(Image backGroundImage) {
		this.backGroundImage.set(backGroundImage);
	}
	
	public ObjectProperty<Image> backGroundImageProperty() {
		return backGroundImage;
	}
	
	public ObservableList<RoomObject> getRoomObjectList(){
		return roomObjectList;
	}
	
	public void addRoomObject(String objectName, String defaultImageURL) {
		roomObjectList.add(new RoomObject(objectName, defaultImageURL));
	}
	
	public RoomObject getRoomObject(int index) {
		return roomObjectList.get(index);
	}
	
	public void removeRoomObject(int index) {
		roomObjectList.remove(index);

	}
	
	public void clearRoomObject() { 
		roomObjectList.clear();
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException{
	
		oos.defaultWriteObject();
		oos.writeObject(sceneName.get());
		oos.writeObject(savePath);
		BufferedImage bImage = SwingFXUtils.fromFXImage(backGroundImage.get(), null); // convert first
		
		File writePath = new File(getSavePath() + "/scenes/");
		writePath.mkdir();
		File bgImage = new File(getSavePath() + "/scenes/" + getSceneName() + " background.png"); // make empty file
		ImageIO.write(bImage,"png", bgImage); // write image to file
		
		oos.writeInt(roomObjectList.size()); // write int
		oos.writeObject(new ArrayList<RoomObject>(roomObjectList)); //write arraylist of roomobject
		
	}
	
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
	
		ois.defaultReadObject();
		sceneName = new SimpleStringProperty((String)ois.readObject());
		System.out.println(sceneName.get());
		savePath = (String)ois.readObject();
		System.out.println(getSavePath());
		
		File readPath = new File(getOpenPath() + "/scenes/" + getSceneName() + " background.png");
		BufferedImage bImage = ImageIO.read(readPath);
		backGroundImage = new SimpleObjectProperty<Image>();
		Image bgfxImage = SwingFXUtils.toFXImage(bImage,null);
		backGroundImage.set(bgfxImage); // read Image
		
		int objectSize = ois.readInt();
		System.out.println("object size in scene : " + objectSize);
		
		ArrayList<RoomObject> temp = new ArrayList<RoomObject>();
		
		try {
			temp = (ArrayList<RoomObject>) ois.readObject();
			roomObjectList = FXCollections.observableArrayList(temp);
		}catch (OptionalDataException e) {
			// TODO: handle exception
			System.out.println(e.length + " and " + e.eof);
			e.printStackTrace();
		}
	}

	/*
	 * showing ChoiceBox 
	 */
	@Override
	public String toString() {
		return getSceneName();
	}

	public static String getOpenPath() {
		return openPath;
	}

	public static void setOpenPath(String openPath) {
		RoomScene.openPath = openPath;
	}

	
}
