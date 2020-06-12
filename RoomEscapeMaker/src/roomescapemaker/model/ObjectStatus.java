package roomescapemaker.model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ObjectStatus implements Serializable{
	
	
	private transient static final long serialVersionUID = 1L;
	
	private transient StringProperty statusName = new SimpleStringProperty();
	private transient ObjectProperty<Image> statusImage = new SimpleObjectProperty<Image>();
	private transient IntegerProperty scale = new SimpleIntegerProperty();
	private transient DoubleProperty xPos = new SimpleDoubleProperty();
	private transient DoubleProperty yPos = new SimpleDoubleProperty();
	private transient BooleanProperty visible = new SimpleBooleanProperty();
	private transient BooleanProperty possess = new SimpleBooleanProperty();
	private transient String objectName;
	private transient static String savePath;

	public ObjectStatus(String name, Image image) {
		this(name, image, 0, 0);
	}
	public ObjectStatus(String name, Image image, String obName) {
		this(name, image, 0, 0);
		objectName = obName;
	}
	
	public ObjectStatus(String name, Image image, double xPos, double yPos) {
		this.statusName.set(name);
		this.scale.set(100);
		this.xPos.set(xPos);
		this.yPos.set(yPos);
		this.visible.set(true);
		this.possess.set(false);
		try {
			this.statusImage.set(image);
		} catch(IllegalArgumentException e){
			e.printStackTrace();
			System.out.println("fail to call image");
		}
	}
	
	public ObjectStatus(String objName, String statName, Image image, double xPos, double yPos) {
		this.statusName.set(statName);
		this.scale.set(100);
		this.xPos.set(xPos);
		this.yPos.set(yPos);
		this.visible.set(true);
		this.possess.set(false);
		try {
			this.statusImage.set(image);
		} catch(IllegalArgumentException e){
			e.printStackTrace();
			System.out.println("fail to call image");
		}
		this.objectName = objName;
	}
	
	
	public String getStatusName() {
		return statusName.get();
	}
	
	public void setStatusName(String statusName) {
		this.statusName.set(statusName);
	}
	
	public StringProperty statusNameProperty() {
		return statusName;
	}
	
	public Image getStatusImage() {
		return statusImage.get();
	}
	
	public void setImageFile(Image statusImage) {
		this.statusImage.set(statusImage);
	}
	
	public ObjectProperty<Image> statusImageProperty() {
		return statusImage;
	}
	
	public int getScale() {
		return scale.get();
	}
	
	public void setScale(int scale) {
		this.scale.set(scale);
	}
	
	public IntegerProperty scaleProperty() {
		return scale;
	}
	
	public double getXpos() {
		return xPos.get();
	}
	
	public void setXpos(double xPos) {
		this.xPos.set(xPos);
	}
	
	public DoubleProperty xPosProperty() {
		return xPos;
	}
	
	public double getYpos() {
		return yPos.get();
	}
	
	public void setYpos(double yPos) {
		this.yPos.set(yPos);
	}
	
	public DoubleProperty yPosProperty() {
		return yPos;
	}
	
	public boolean getVisible() {
		return visible.get();
	}
	
	public void setVisible(boolean visible) {
		this.visible.set(visible);
	}
	
	public BooleanProperty visibleProperty() {
		return visible;
	}
	
	public boolean getPossess() {
		return possess.get();
	}
	
	public void setPossess(boolean possess) {
		this.possess.set(possess);
	}
	
	public BooleanProperty possessProperty() {
		return possess;
	}
	
	/*
	 * showing ChoiceBox 
	 */
	@Override
	public String toString() {
		return getStatusName();
	}

	
	// for saving objects
	
	private void writeObject(ObjectOutputStream oos) throws IOException{
		
		oos.defaultWriteObject();
		oos.writeObject(statusName.get());
		
		BufferedImage osImage = SwingFXUtils.fromFXImage(statusImage.get(), null);
		File writePath = new File(getSavePath() + "/objects/");
		writePath.mkdir();
		File outImage = new File(getSavePath() + "/objects/" + getObjectName() +"_"+ getStatusName() + ".png"); // make empty file
		ImageIO.write(osImage,"png", outImage); // write image to file
		
		oos.writeInt(scale.get());
		oos.writeDouble(xPos.get());
		oos.writeDouble(yPos.get());
		oos.writeBoolean(visible.get());
		oos.writeBoolean(possess.get());
		
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
	
		ois.defaultReadObject();
		statusName = new SimpleStringProperty((String)ois.readObject());
		BufferedImage sImage = ImageIO.read(ois);
		statusImage = new SimpleObjectProperty<Image>();
		Image fxImage = SwingFXUtils.toFXImage(sImage,null);
		statusImage.set(fxImage);
		scale = new SimpleIntegerProperty(ois.readInt());
		xPos = new SimpleDoubleProperty(ois.readDouble());
		yPos = new SimpleDoubleProperty(ois.readDouble());
		visible = new SimpleBooleanProperty(ois.readBoolean());
		possess = new SimpleBooleanProperty(ois.readBoolean());
		
		
	}

	public static String getSavePath() {
		return savePath;
	}

	public static void setSavePath(String savePath) {
		ObjectStatus.savePath = savePath;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
}
