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
    
	@FXML
	private ListView<RoomScene> sceneListView;
	private ObservableList<RoomScene> sceneList = FXCollections.observableArrayList();
	private ObservableList<RoomObject> objectList;
	private ObservableList<ObjectStatus> statusList;
	
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
    
    ////////////////////////////
    @FXML
    private Button addObjectBtn;
    ////////////////////////////
    
    private Stage mainStage; 
    private MainApp mainApp;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	sceneList.clear();
    	sceneList.add(new RoomScene("Scene 1", new Image("roomescapemaker/resource/backgrounds/normalRoom.png")));
    	sceneList.add(new RoomScene("Scene 2", new Image("roomescapemaker/resource/backgrounds/normalRoom.png")));
    	sceneList.add(new RoomScene("Scene 3", new Image("roomescapemaker/resource/backgrounds/normalRoom.png")));
    	sceneList.add(new RoomScene("Scene 4", new Image("roomescapemaker/resource/backgrounds/normalRoom.png")));
    	sceneList.add(new RoomScene("Scene 5", new Image("roomescapemaker/resource/backgrounds/normalRoom.png")));
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
    	
    	
    	sceneListView.setItems(sceneList);
    	
    	//add scene list listener
    	sceneList.addListener(new ListChangeListener<RoomScene>() {
    		
    		@Override
    		public void onChanged(Change<? extends RoomScene> c) {
    			System.out.println("list changed");
    			if(c.next()) {
    				System.out.println(c.getFrom());
    			}
    		}
    	});
    	/////////////////////////////////////////
    	//listener for selecting a scene
    	sceneListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContainedObjects(newValue));    	
    	/////////////////////////////////////////
    }
    /////////////////////////////////////
    private void showContainedObjects(RoomScene rs) {
        if (rs == null) {
            
           
        } else {
           System.out.println(rs.getRoomObjectList().size());
            
        }
    }
    /////////////////////////////////////
    @FXML
    void onClickAddSceneBtn(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    	     new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png")
    	);
    	///////////////////////////////////////////////
    	fileChooser.setInitialDirectory(new File("./RoomEscapeMaker/src/roomescapemaker/resource/backgrounds"));
    	///////////////////////////////////////////////
    	File selectedFile = fileChooser.showOpenDialog(mainStage);
    	try {
    		sceneList.add(new RoomScene("scene X", new Image(selectedFile.toURI().toURL().toString())));
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    		System.out.println("wrong file path url");
    	}
    	
    	///////////////////////////////////////////
    	sceneList.get(sceneList.size() - 1).clearRoomObject();
    	///////////////////////////////////////////
    }
    
    ///////////////////////////////////////////////
    @FXML
    void onClickAddObjectBtn(ActionEvent event) {
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    	     new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png")
    	);
    	fileChooser.setInitialDirectory(new File("./RoomEscapeMaker/src/roomescapemaker/resource/objects"));
    	
    	File selectedFile = fileChooser.showOpenDialog(mainStage);
    }
    ///////////////////////////////////////////////
    
    
    @FXML
    void onClickDeleteSceneBtn(ActionEvent event) {
    	try {
    		sceneList.remove(sceneListView.getSelectionModel().getSelectedIndex());
    	} catch(ArrayIndexOutOfBoundsException e) {
    		e.printStackTrace();
    		System.out.println("no data in scenelist");
    	}
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
    

}
