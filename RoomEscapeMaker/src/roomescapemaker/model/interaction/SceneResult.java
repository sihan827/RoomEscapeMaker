package roomescapemaker.model.interaction;


import java.io.File;

import javax.sound.sampled.AudioInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


/*
 * scene change
 * game status included
 */
public class SceneResult implements Serializable{
	


	private transient ObjectProperty<File> soundFile = new SimpleObjectProperty<File>();
	private transient IntegerProperty targetIndex = new SimpleIntegerProperty();
	private transient BooleanProperty isGameOver = new SimpleBooleanProperty();
	private transient BooleanProperty isGameClear = new SimpleBooleanProperty();

	public SceneResult(int targetIndex) {
		this.targetIndex.set(targetIndex);
		this.isGameOver.set(false);
		this.isGameClear.set(false);
		soundFile.set(null);
	}	
	
	public int getTargetIndex() {
		return targetIndex.get();
	}
	
	public void setTargetIndex(int targetIndex) {
		this.targetIndex.set(targetIndex);
	}
	
	public IntegerProperty targetIndexProperty() {
		return targetIndex;
	}
	
	public boolean getIsGameOver() {
		return isGameOver.get();
	}
	
	public void setIsGameOver(boolean isGameOver) {
		this.isGameOver.set(isGameOver);
	}
	
	public BooleanProperty isGameOverProperty() {
		return isGameOver;
	}
	
	public boolean getIsGameClear() {
		return isGameClear.get();
	}
	
	public void setIsGameClear(boolean isGameClear) {
		this.isGameClear.set(isGameClear);
	}
	
	public BooleanProperty isGameClearProperty() {
		return isGameClear;
	}

	public File getSoundFile() {
		return soundFile.get();
	}
	
	public void setSoundFile(File soundFile) {
		this.soundFile.set(soundFile);
	}
	
	public ObjectProperty<File> soundFileProperty(){
		return soundFile;
	}

		
	private void writeObject(ObjectOutputStream oos) throws IOException{
		
		System.out.println("start scene result write ===" + getTargetIndex());
		oos.defaultWriteObject();
		// writing conditions
		oos.writeInt(targetIndex.get());
		oos.writeBoolean(isGameOver.get());
		oos.writeBoolean(isGameClear.get());
		
		System.out.println("scene result write complete isgameover: " + getIsGameOver() + "isgameclear: " + getIsGameClear());
		
		
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		
		ois.defaultReadObject();
		targetIndex= new SimpleIntegerProperty(ois.readInt());
		isGameOver = new SimpleBooleanProperty(ois.readBoolean());
		isGameClear = new SimpleBooleanProperty(ois.readBoolean());
		System.out.println("scene result read complete: " + getTargetIndex());
	}
}
