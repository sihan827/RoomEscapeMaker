package roomescapemaker.model.interaction;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
 * 상호작용의 결과로 씬의 이동이 발생할시 사용하는 클래스, 오직 하나만 가능, 실제 씬의 이동 구현은 상위 클래스인 ObjectInteraction에서 발생 
 * 게임오버인지 게임 클리어인지도 이 결과클래스의 bool타입으로 판단됨
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
