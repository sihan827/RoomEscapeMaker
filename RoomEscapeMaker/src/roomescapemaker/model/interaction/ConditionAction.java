package roomescapemaker.model.interaction;

import java.io.Serializable;

public enum ConditionAction implements Serializable{
	CLICK("Click Object");
	private String label;
	ConditionAction(String label) {
        this.label = label;
    }
	
	public String toString() {
		return label;
	}
}
