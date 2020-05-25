package roomescapemaker.model;

import java.util.ArrayList;

public class RoomScene {
	public ArrayList<RoomObject> roomObjectList = new ArrayList<RoomObject>();
	public String backGroundImageFileName;
	
	public RoomScene() {
		this("");
	}
	
	public RoomScene(String backGroundImageFileName) {
		this.backGroundImageFileName = backGroundImageFileName;
    
	}
	
}
