package roomescapemaker;

import roomescapemaker.view.Controller;
import roomescapemaker.view.GameController;
import roomescapemaker.model.RoomObject;
import roomescapemaker.model.RoomScene;
import roomescapemaker.model.interaction.ObjectInteraction;
import roomescapemaker.view.AddInteractionController;
import roomescapemaker.view.AddObjectController;
import roomescapemaker.view.AddSceneController;
import java.io.IOException;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("RoomExcapeMaker");
        this.primaryStage.setFullScreen(false);
        initRootLayout();
	}
	
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Overview.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout, Color.BEIGE);
			primaryStage.setScene(scene);
			primaryStage.show();
			Controller controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No fxml file named \"Overview.fxml\" in view pakage!");
		}
	}
	

	public boolean showAddSceneStage(RoomScene newRoomScene) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Add_Scene.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage addSceneStage = new Stage();
			addSceneStage.setTitle("Add New Scene");
			addSceneStage.initModality(Modality.WINDOW_MODAL);
			addSceneStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			addSceneStage.setScene(scene);
			
			AddSceneController controller = loader.getController();
			controller.setDialogStage(addSceneStage);
			controller.setScene(newRoomScene);
			
			addSceneStage.showAndWait();
			
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No fxml file named \"Add_Scene.fxml\" in view pakage!");
			return false;
		}

	}
	
	public boolean showAddObjectStage(RoomObject newRoomObject) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Add_Object.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage addObjectStage = new Stage();
			addObjectStage.setTitle("Add New Object to Scene");
			addObjectStage.initModality(Modality.WINDOW_MODAL);
			addObjectStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			addObjectStage.setScene(scene);
			
			AddObjectController controller = loader.getController();
			controller.setDialogStage(addObjectStage);
			controller.setObject(newRoomObject);
			
			addObjectStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No fxml file named \"Add_Object.fxml\" in view pakage!");
			return false;
		}
	}
	
	public boolean showInteractionAddStage(ObjectInteraction newInteraction, ObservableList<RoomScene> sceneList, RoomObject ro) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Interaction_Add.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage interactionAddStage = new Stage();
			interactionAddStage.setTitle("Add New Interaction");
			interactionAddStage.initModality(Modality.WINDOW_MODAL);
			interactionAddStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			interactionAddStage.setScene(scene);
			
			AddInteractionController controller = loader.getController();
			controller.setDialogStage(interactionAddStage);
			controller.setInteraction(newInteraction);
			controller.setSceneList(sceneList);
			controller.setRoomObject(ro);
			
			interactionAddStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No fxml file named \"Interaction_Add.fxml\" in view pakage!");
			return false;
		}
	}
	
	public boolean showGameSimulationStage(ObservableList<RoomScene> sceneList) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GameWindow.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage gameSimulationStage = new Stage();
			gameSimulationStage.setTitle("Game Simulation");
			gameSimulationStage.initModality(Modality.WINDOW_MODAL);
			gameSimulationStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			gameSimulationStage.setScene(scene);
			
			GameController controller = loader.getController();
			controller.setDialogStage(gameSimulationStage);
			controller.setSceneList(sceneList);
			controller.reDrawMainCanvas(sceneList.get(0));
			
			gameSimulationStage.showAndWait();
			
			
			
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No fxml file named \"GameWindow.fxml\" in view pakage!");
			return false;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
