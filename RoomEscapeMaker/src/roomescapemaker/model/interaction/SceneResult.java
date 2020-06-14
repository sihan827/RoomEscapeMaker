package roomescapemaker.model.interaction;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
 * scene change
 * game status included
 */
public class SceneResult {
	
	private final IntegerProperty targetIndex = new SimpleIntegerProperty();
	private final BooleanProperty isGameOver = new SimpleBooleanProperty();
	private final BooleanProperty isGameClear = new SimpleBooleanProperty();
	

	public SceneResult(int targetIndex) {
		this.targetIndex.set(targetIndex);
		this.isGameOver.set(false);
		this.isGameClear.set(false);
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
}
