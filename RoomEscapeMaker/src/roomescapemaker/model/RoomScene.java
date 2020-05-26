package roomescapemaker.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RoomScene {
	public ArrayList<RoomObject> roomObjectList = new ArrayList<RoomObject>();
	public String backGroundImageFileName;
	private BufferedImage sceneImage;
	
	
	public RoomScene(File simage) {
		
		try {
			sceneImage = ImageIO.read(simage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			File directory = new File("./");
			System.out.println(directory.getAbsolutePath());
			System.out.println("fail to call image");
		}
	}
	
	public RoomScene(String backGroundImageFileName) {
		this.backGroundImageFileName = backGroundImageFileName;
    
	}
	
}
