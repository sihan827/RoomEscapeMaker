package roomescapemaker.model;


import java.lang.IllegalArgumentException;
import javafx.scene.image.Image;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ObjectStatus {
	
	private final StringProperty statusName = new SimpleStringProperty();
	private final ObjectProperty<Image> statusImage = new SimpleObjectProperty<Image>();
	private final IntegerProperty scale = new SimpleIntegerProperty();
	private final DoubleProperty xPos = new SimpleDoubleProperty();
	private final DoubleProperty yPos = new SimpleDoubleProperty();
	private final BooleanProperty visible = new SimpleBooleanProperty();
	private final BooleanProperty possess = new SimpleBooleanProperty();
	
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
	
	
}
