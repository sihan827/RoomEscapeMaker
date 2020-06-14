package roomescapemaker.model.interaction;

public enum ConditionAction {
	CLICK("Click Object");
	private String label;
	ConditionAction(String label) {
        this.label = label;
    }
	
	public String toString() {
		return label;
	}
}
