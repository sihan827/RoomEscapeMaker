package roomescapemaker.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.sun.xml.internal.fastinfoset.stax.events.ReadIterator;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class RoomScene implements Serializable{


	private transient static final long serialVersionUID = 1L;
	
	public transient StringProperty sceneName = new SimpleStringProperty();
	public transient ObjectProperty<Image> backGroundImage = new SimpleObjectProperty<Image>();
	private transient ObservableList<RoomObject> roomObjectList = FXCollections.observableArrayList();

	public RoomScene(String sceneName, Image backGroundImage) {
		this.sceneName.set(sceneName);
		this.backGroundImage.set(backGroundImage);
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
	
	// for serialization
	
	private void writeObject(ObjectOutputStream oos) throws IOException{
		
		oos.defaultWriteObject();
		oos.writeObject(sceneName.get());
		BufferedImage bImage = SwingFXUtils.fromFXImage(backGroundImage.get(), null);
		
		ImageIO.write(bImage,"png", oos);
		oos.writeInt(roomObjectList.size());
		oos.writeObject(new ArrayList<RoomObject>(roomObjectList));
		
		
	}
	
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
	
		ois.defaultReadObject();
		sceneName = new SimpleStringProperty((String)ois.readObject());
		System.out.println(sceneName.get());
		BufferedImage bImage = ImageIO.read(ois);
		backGroundImage = new SimpleObjectProperty<Image>();
		Image bgfxImage = SwingFXUtils.toFXImage(bImage,null);
		backGroundImage.set(bgfxImage);
		int objectSize = ois.readInt();
		System.out.println(objectSize);
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
		
}
