package roomescapemaker.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomObject {
	
	public Map<String, Boolean> statusList = new HashMap<String, Boolean>();
	private ArrayList<ObjectStatus> status = new ArrayList<ObjectStatus>();
	private String name;
	private int xScale;
	private int yScale;
	private double xPos;
	private double yPos;
	private boolean ownable;
	private boolean isOwned;
	private boolean visibility;
	
	
	public RoomObject(File defaultStatusImage) {
		this.name = defaultStatusImage.getName();
		this.xScale = 100;
		this.yScale = 100;
		this.xPos = 50;
		this.yPos = 50;
		this.ownable = false;
		this.isOwned = false;
		this.visibility = true;
		
		ObjectStatus defaultStatus = new ObjectStatus(defaultStatusImage);
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getXscale() {
		return xScale;
	}
	
	public void setXscale(int xScale) {
		this.xScale = xScale;
	}
	
	public int getYscale() {
		return yScale;
	}
	
	public void setYscale(int yScale) {
		this.yScale = yScale;
	}
	
	public double getXpos() {
		return xPos;
	}
	
	public void setXpos(double xPos) {
		this.xPos = xPos;
	}
	
	public double getYpos() {
		return yPos;
	}
	
	public void setYpos(double yPos) {
		this.yPos = yPos;
	}
	
	public boolean getOwnable() {
		return ownable;
	}
	
	public void setOwnable(boolean ownable) {
		this.ownable = ownable;
	}

	public boolean getIsOwned() {
		return isOwned;
	}
	
	public void setIsOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}
	
	public boolean getVisibility() {
		return visibility;
	}
	
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	
	public void addStatus(String statusName, boolean status) {
		statusList.put(statusName, status);
	}
	
	public Boolean getStatus(String statusName) {
		return statusList.get(statusName);
	}
	
	public void removeStatus(String statusName) {
		statusList.remove(statusName);
	}
	
	void setPos(double x, double y) {
		setXpos(x);
		setYpos(y);
	}
	
	void setScale(int x, int y) {
		setXscale(x);
		setYscale(y);
	}
	
	public void createStatus() {
		
		
	}
	
	
}