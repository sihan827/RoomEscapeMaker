package roomescapemaker.model.interaction;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
 * 상호작용의 결과로 씬의 이동이 발생할시 사용하는 클래스, 오직 하나만 가능, 실제 씬의 이동 구현은 상위 클래스인 ObjectInteraction에서 발생 
 */
public class SceneResult {
	
	private final IntegerProperty targetIndex = new SimpleIntegerProperty();
	
	public SceneResult() {
		this(-1);
	}
	
	public SceneResult(int targetIndex) {
		this.targetIndex.set(targetIndex);
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
}
