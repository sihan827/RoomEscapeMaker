// based on branch controller_implement
package roomescapemaker.view;

import roomescapemaker.MainApp;
import roomescapemaker.model.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.File;
import java.lang.IllegalArgumentException;
import java.lang.ArrayIndexOutOfBoundsException;



import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller implements Initializable{
    
	private ObservableList<RoomScene> sceneList = FXCollections.observableArrayList();
	
	@FXML
	private ListView<RoomScene> sceneListView;
	
	@FXML
	private ListView<RoomObject> objectListView;
	
    @FXML
    private Pane pane;
	
	@FXML
    private Canvas mainCanvas;
    
	@FXML
    private Button playBtn;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuFile;

    @FXML
    private MenuItem menuFileClose;

    @FXML
    private Menu menuHelp;
    
    @FXML
    private Button addSceneBtn;
    
    @FXML
    private Button deleteSceneBtn;
    
    @FXML
    private Button addObjectBtn;
    
    @FXML
    private Button deleteObjectBtn;
    
    private Stage mainStage; 
    private MainApp mainApp;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	initTest();
    	sceneListView.setCellFactory(new Callback<ListView<RoomScene>, ListCell<RoomScene>>(){
    		@Override
    		public ListCell<RoomScene> call(ListView<RoomScene> arg0){
    			ListCell<RoomScene> cell = new ListCell<RoomScene>() {
    				@Override
    				protected void updateItem(RoomScene scene, boolean bt1) {
    					super.updateItem(scene, bt1);
    					if (scene != null) {
    						ImageView imgview = new ImageView(scene.getBackGroundImage());
    						imgview.setFitWidth(120);
    						imgview.setFitHeight(72);
    						setGraphic(imgview);
    						setText(scene.getSceneName());
    					}
    					else {
    						setGraphic(null);
    						setText(null);
    					}
    				}
    			};	
    			return cell;
    		}
    	});
    	
    	objectListView.setCellFactory(new Callback<ListView<RoomObject>, ListCell<RoomObject>>(){
    		@Override
    		public ListCell<RoomObject> call(ListView<RoomObject> arg0){
    			ListCell<RoomObject> cell = new ListCell<RoomObject>() {
    				@Override
    				protected void updateItem(RoomObject obj, boolean bt1) {
    					super.updateItem(obj, bt1);
    					if (obj != null) {
    						ImageView imgview = new ImageView(obj.getStatus(0).getStatusImage());
    						imgview.setFitHeight(160);
    						imgview.setPreserveRatio(true);
    						setGraphic(imgview);
    						setText(obj.getObjectName());
    					}
    					else {
    						setGraphic(null);
    						setText(null);
    					}
    				}
    			};
    			
    			return cell;
    		}	
    	});
    	
    	
    	sceneListView.setItems(sceneList);
    	
    	//add scene list listener
    	sceneList.addListener(new ListChangeListener<RoomScene>() {
    		
    		@Override
    		public void onChanged(Change<? extends RoomScene> c) {
    			System.out.println("scene list changed");
    			if(c.next()) {
    				System.out.println(c.getFrom());
    			}
    		}
    	});
    	
    	//listener for selecting a scene
    	sceneListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContainedObjects(newValue));    
    	
    	
    		  
    
    }
    
    private void showContainedObjects(RoomScene rs) {
    	if (rs != null) {
    		System.out.println(rs.getRoomObjectList().size());
    		objectListView.setItems(rs.getRoomObjectList());
    	} else {
    		return;
    	}
    }

    @FXML
    void onClickAddSceneBtn(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    	     new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png")
    	);
    	
    	fileChooser.setInitialDirectory(new File("."));
    	
    	File selectedFile = fileChooser.showOpenDialog(mainStage);
    	try {
    		sceneList.add(new RoomScene("scene X", new Image(selectedFile.toURI().toURL().toString())));
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    		System.out.println("wrong file path url");
    	}
    	
    }
    
    ///////////////////////////////////////////////
    @FXML
    void onClickAddObjectBtn(ActionEvent event) {
    	
    	if(sceneListView.getSelectionModel().getSelectedItem() != null) {
    		FileChooser fileChooser = new FileChooser();
    		fileChooser.getExtensionFilters().addAll(
    				new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png")
    		);
    		fileChooser.setInitialDirectory(new File("."));
    	
    		File selectedFile = fileChooser.showOpenDialog(mainStage);
    		try {
    			sceneListView.getSelectionModel().getSelectedItem().addRoomObject(
    					"object X", selectedFile.toURI().toURL().toString());
    		} catch (MalformedURLException e) {
    			e.printStackTrace();
    			System.out.println("wrong file path url");
    		}
    	}
    	else return;
    }
    ///////////////////////////////////////////////
    
    
    @FXML
    void onClickDeleteSceneBtn(ActionEvent event) {
    	if (sceneListView.getSelectionModel().getSelectedItem() != null) {
    		try {
    			sceneList.remove(sceneListView.getSelectionModel().getSelectedIndex());
    			if (sceneList.size() == 0) {
    				objectListView.getItems().clear();
    			}
    		} catch(ArrayIndexOutOfBoundsException e) {
    			e.printStackTrace();
    			System.out.println("no data in scenelist");
    		}
    	}
    	else return;
    }
    
    @FXML
    void onClickDeleteObjectBtn(ActionEvent event) {
    	if (sceneListView.getSelectionModel().getSelectedItem() != null 
    			&& objectListView.getSelectionModel().getSelectedItem() != null) {
    		try {
    			sceneListView.getSelectionModel().getSelectedItem().removeRoomObject(
    					objectListView.getSelectionModel().getSelectedIndex());
    		} catch(ArrayIndexOutOfBoundsException e){
    			e.printStackTrace();
    			System.out.println("no data in objectlist");
    		}
    	}
    	else return;
    }
    

    @FXML
    void onCLickMenuFileClose(ActionEvent event) {
    	
    	Stage stage = (Stage)(menuBar.getScene().getWindow());
    	stage.close();
    	
    }

    @FXML 
    void playScene(ActionEvent event) {
    	
    	
    }
    
    void redraw() {
    	
    }
    
    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
    
    public void initTest() {
    	sceneList.add(new RoomScene("Scene 1", new Image("roomescapemaker/resource/backgrounds/normalRoom.png")));
    	sceneList.get(0).addRoomObject("books", "roomescapemaker/resource/objects/books.jpg");
    	sceneList.get(0).addRoomObject("computer", "roomescapemaker/resource/objects/computer.png");
    	sceneList.get(0).addRoomObject("fireextinguisher", "roomescapemaker/resource/objects/fireextinguisher.jpg");
    	sceneList.get(0).addRoomObject("greensofa", "roomescapemaker/resource/objects/greensofa.jpg");
    	sceneList.get(0).addRoomObject("light", "roomescapemaker/resource/objects/light.jpg");
    	sceneList.get(0).addRoomObject("radio", "roomescapemaker/resource/objects/radio.jpg");
    	sceneList.get(0).addRoomObject("soccerball", "roomescapemaker/resource/objects/soccerball.jpg");
    	sceneList.get(0).addRoomObject("telephone", "roomescapemaker/resource/objects/telephone.jpg");
    	sceneList.add(new RoomScene("Scene 2", new Image("roomescapemaker/resource/backgrounds/room2.jpg")));
    	sceneList.get(1).addRoomObject("object2", "roomescapemaker/resource/objects/defaultImage.png");
    	sceneList.add(new RoomScene("Scene 3", new Image("roomescapemaker/resource/backgrounds/room3.jpg")));
    	sceneList.get(2).addRoomObject("object3", "roomescapemaker/resource/objects/defaultImage.png");
    	sceneList.add(new RoomScene("Scene 4", new Image("roomescapemaker/resource/backgrounds/room4.jpg")));
    	sceneList.add(new RoomScene("Scene 5", new Image("roomescapemaker/resource/backgrounds/room5.jpg")));
    	sceneList.add(new RoomScene("Scene 6", new Image("roomescapemaker/resource/backgrounds/room6.jpg")));
    	sceneList.add(new RoomScene("Scene 7", new Image("roomescapemaker/resource/backgrounds/room7.jpg")));
    	sceneList.add(new RoomScene("Scene 8", new Image("roomescapemaker/resource/backgrounds/room8.jpg")));
    }

}
