package roomescapemaker.model.interaction;

import java.io.File;

import javax.sound.sampled.AudioInputStream;

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
public class SceneResult {
	
	private final IntegerProperty targetIndex = new SimpleIntegerProperty();
	private final BooleanProperty isGameOver = new SimpleBooleanProperty();
	private final BooleanProperty isGameClear = new SimpleBooleanProperty();
	private final ObjectProperty<File> soundFile = new SimpleObjectProperty<File>();
	

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
}
