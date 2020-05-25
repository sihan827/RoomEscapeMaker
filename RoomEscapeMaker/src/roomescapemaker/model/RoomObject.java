package roomescapemaker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomObject {
	
	public ArrayList<String> imageFileNameList = new ArrayList<String>();
	public Map<String, Boolean> statusList = new HashMap<String, Boolean>();
	private String name;
	private int xScale;
	private int yScale;
	private double xPos;
	private double yPos;
	private boolean ownable;
	private boolean isOwned;
	private boolean visibility;
	
	public RoomObject(String name) {
		this.name = name;
		this.xScale = 100;
		this.yScale = 100;
		this.xPos = 0;
		this.yPos = 0;
		this.ownable = false;
		this.isOwned = false;
		this.visibility = true;
	}
	
	/*
	 *	프로퍼티 기본 설정 메소드 
	 */
	
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
	
	public void addImage(String filename) {
		imageFileNameList.add(filename);
	}
	
	public String removeImage(int index) {
		return imageFileNameList.remove(index);
	}
	
	public String getImage(int index) {
		return imageFileNameList.get(index);
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
	
	/*
	 *	위 메소드를 이용한 오브젝트 관련 설정 메소드 
	 */
	
	void setPos(double x, double y) {
		setXpos(x);
		setYpos(y);
	}
	
	void setScale(int x, int y) {
		setXscale(x);
		setYscale(y);
	}
	
}