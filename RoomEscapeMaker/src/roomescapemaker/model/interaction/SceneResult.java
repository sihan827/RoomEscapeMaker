package roomescapemaker.model.interaction;


import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.sun.glass.ui.CommonDialogs.Type;
import com.sun.media.sound.WaveFileReader;
import com.sun.media.sound.WaveFileWriter;

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
	
	
	private static final long serialVersionUID = 2L;
	private transient ObjectProperty<File> soundFile = new SimpleObjectProperty<File>();
	private transient IntegerProperty targetIndex = new SimpleIntegerProperty();
	private transient BooleanProperty isGameOver = new SimpleBooleanProperty();
	private transient BooleanProperty isGameClear = new SimpleBooleanProperty();
	private transient static String audioPath;
	private transient BooleanProperty isAudioAvailable = new SimpleBooleanProperty();
	private transient String resultName;
	
	public SceneResult(int targetIndex, String resultName) {
		this.targetIndex.set(targetIndex);
		this.isGameOver.set(false);
		this.isGameClear.set(false);
		soundFile.set(null);
		isAudioAvailable.set(false);
		this.setResultName(resultName);
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
		if(isAudioAvailable.get()) {
			return soundFile.get();
		}
		return null;
	}
	
	public void setSoundFile(File soundFile) {
		this.soundFile.set(soundFile);
		isAudioAvailable.set(true);
	}
	
	public ObjectProperty<File> soundFileProperty(){
		return soundFile;
	}

		
	private void writeObject(ObjectOutputStream oos) throws IOException{
		
		System.out.println("start scene result write ===" + getTargetIndex());
		oos.defaultWriteObject();
		// writing conditions
		oos.writeInt(targetIndex.get());
		oos.writeObject(resultName);
		oos.writeBoolean(isGameOver.get());
		oos.writeBoolean(isGameClear.get());
		oos.writeBoolean(isAudioAvailable.get());
		//write soundfile!
		
		if(isAudioAvailable.get()) {
			System.out.println("write audio @ " + getAudioPath());
			File writePath = new File(getAudioPath() + "/audio/");
			writePath.mkdir();
			File outAudio = new File(getAudioPath() + "/audio/" + getResultName() + "_" + getTargetIndex()+".wav");
			System.out.println("out file @ " + getAudioPath() + "/audio/" + getResultName() + "_" + getTargetIndex()+".wav");
			AudioFileFormat.Type fileType;
			
			try {
				fileType = AudioSystem.getAudioFileFormat(soundFile.get()).getType();
				if (AudioSystem.isFileTypeSupported(fileType)) {
					  AudioSystem.write(AudioSystem.getAudioInputStream(soundFile.get()), fileType, outAudio);
					}
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("write sound complete!");
		}
		System.out.println("scene result write complete isgameover: " + getIsGameOver() + "isgameclear: " + getIsGameClear());
		
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		
		ois.defaultReadObject();
		targetIndex= new SimpleIntegerProperty(ois.readInt());
		resultName = (String)ois.readObject();
		isGameOver = new SimpleBooleanProperty(ois.readBoolean());
		isGameClear = new SimpleBooleanProperty(ois.readBoolean());
		isAudioAvailable = new SimpleBooleanProperty(ois.readBoolean());
		
		System.out.println("audio = " + isAudioAvailable.get());
		if(isAudioAvailable.get()) {
			File readPath = new File(getAudioPath() + "/audio/" + getResultName() +"_"+ getTargetIndex() + ".wav");	
			soundFile = new SimpleObjectProperty<File>(readPath);
			System.out.println("read audio file");
		}
		else {
			soundFile = new SimpleObjectProperty<File>();
		}
		System.out.println("scene result read complete: " + getTargetIndex());
	}

	public static String getAudioPath() {
		return audioPath;
	}

	public static void setAudioPath(String audioPath) {
		SceneResult.audioPath = audioPath;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

}
