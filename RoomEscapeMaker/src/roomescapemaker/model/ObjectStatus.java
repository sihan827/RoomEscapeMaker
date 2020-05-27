package roomescapemaker.model;


import java.lang.IllegalArgumentException;
import javafx.scene.image.Image;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ObjectStatus {
	
	private final StringProperty statusName = new SimpleStringProperty();
	private final ObjectProperty<Image> statusImage = new SimpleObjectProperty<Image>();
	private final IntegerProperty scale = new SimpleIntegerProperty();
	private final IntegerProperty xPos = new SimpleIntegerProperty();
	private final IntegerProperty yPos = new SimpleIntegerProperty();
	private final BooleanProperty visible = new SimpleBooleanProperty();
	private final BooleanProperty possess = new SimpleBooleanProperty();
	
	public ObjectStatus(String name, String imageFileURL) {
		this.statusName.set(name);
		this.scale.set(100);
		this.xPos.set(0);
		this.yPos.set(0);
		this.visible.set(true);
		this.possess.set(false);
		try {
			this.statusImage.set(new Image(imageFileURL));
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
	
	public void setImageFileName(Image statusImage) {
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
	
	public int getXpos() {
		return xPos.get();
	}
	
	public void setXpos(int xPos) {
		this.xPos.set(xPos);
	}
	
	public IntegerProperty xPosProperty() {
		return xPos;
	}
	
	public int getYpos() {
		return yPos.get();
	}
	
	public void setYpos(int yPos) {
		this.yPos.set(yPos);
	}
	
	public IntegerProperty yPosProperty() {
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
