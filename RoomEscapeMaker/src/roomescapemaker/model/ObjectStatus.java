package roomescapemaker.model;


import java.awt.image.BufferedImage;
import java.io.IOException;
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
	
	
	private static final long serialVersionUID = 1L;
	
	private transient final StringProperty statusName = new SimpleStringProperty();
	private transient final ObjectProperty<Image> statusImage = new SimpleObjectProperty<Image>();
	private transient final IntegerProperty scale = new SimpleIntegerProperty();
	private transient final DoubleProperty xPos = new SimpleDoubleProperty();
	private transient final DoubleProperty yPos = new SimpleDoubleProperty();
	private transient final BooleanProperty visible = new SimpleBooleanProperty();
	private transient final BooleanProperty possess = new SimpleBooleanProperty();
	

	public ObjectStatus(String name, Image image) {
		this(name, image, 0, 0);
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
		oos.writeChars(statusName.get());
		BufferedImage sImage = SwingFXUtils.fromFXImage(statusImage.get(), null);
		ImageIO.write(sImage,"PNG", oos);
		oos.writeInt(scale.get());
		oos.writeDouble(xPos.get());
		oos.writeDouble(yPos.get());
		oos.writeBoolean(visible.get());
		oos.writeBoolean(possess.get());
	}

}
