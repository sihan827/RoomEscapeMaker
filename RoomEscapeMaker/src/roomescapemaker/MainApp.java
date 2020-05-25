package roomescapemaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {

    
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/Overview.fxml"));

	        primaryStage.setTitle("RoomExcapeMaker");
	        primaryStage.setFullScreen(false);
	        //Group root = new Group();

	        Scene scene = new Scene(root, Color.BEIGE);
	        primaryStage.setScene(scene);


	        //primaryStage.setVisible(true);
	        primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// not needed!!
	public void initRootLayout() {
		
		try {
            
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail!!!");
			System.out.println(e);
		} 
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
