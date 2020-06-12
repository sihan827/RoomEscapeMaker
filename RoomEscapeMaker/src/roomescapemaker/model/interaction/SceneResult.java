package roomescapemaker.model.interaction;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
 * ��ȣ�ۿ��� ����� ���� �̵��� �߻��ҽ� ����ϴ� Ŭ����, ���� �ϳ��� ����, ���� ���� �̵� ������ ���� Ŭ������ ObjectInteraction���� �߻� 
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
