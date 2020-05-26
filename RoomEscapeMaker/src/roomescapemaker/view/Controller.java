package roomescapemaker.view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import roomescapemaker.model.RoomObject;
import roomescapemaker.model.RoomScene;

public class Controller implements Initializable{

	private ArrayList<RoomScene> roomScenes = new ArrayList<RoomScene>();
	
	private ObservableList<RoomScene> roomScenesObservable = FXCollections.observableArrayList(roomScenes);
	
	private ArrayList<RoomObject> displayedRoomObjects = new ArrayList<RoomObject>();
	
	
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuFileNew;

    @FXML
    private MenuItem menuFileOpen;

    @FXML
    private MenuItem menuFileSave;

    @FXML
    private MenuItem menuFileSaveAs;

    @FXML
    private MenuItem menuFileImportImage;

    @FXML
    private MenuItem menuFileQuit;

    @FXML
    private Menu menuHelp;

    @FXML
    private MenuItem menuHelpAbout;

    @FXML
    private Button addSceneBtn;

    @FXML
    private Button deleteSceneBtn;

    @FXML
    private ListView<?> sceneListView;

    @FXML
    private Canvas mainCanvas;

    @FXML
    private Button addObjectBtn;

    @FXML
    private TilePane objectTilePane;

    @FXML
    private Button objectStatusAddImageBtn;

    @FXML
    private CheckBox objectVisibleCB;

    private Stage mainStage; 
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	System.out.println("fxml initialized!!");
        
    	
    	// room listener
    	roomScenesObservable.addListener(new ListChangeListener<RoomScene>() {
			
    		@Override
			public void onChanged(Change<? extends RoomScene> c) {
				// TODO Auto-generated method stub
				System.out.println("list changed!!");
				if(c.next()) {
					System.out.println(c.getFrom());
				}
				
				
				
				
				
			}
    	});
    	
    	
    	
    	
        initTestSequence();
	}
    
    @FXML
    void onCLickMenuFileQuit(ActionEvent event) {
        
    	Stage stage = (Stage)(menuBar.getScene().getWindow());
    	mainStage = stage;
    	stage.close();
    	
    }

    @FXML
    void onClickAddObjectBtn(ActionEvent event) {
    	
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    	     new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png")
    	);
    	File selectedFile = fileChooser.showOpenDialog(mainStage);
    	
    	
    	
    }

    @FXML
    void onClickAddSceneBtn(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    	     new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png")
    	);
    	File selectedFile = fileChooser.showOpenDialog(mainStage);
    	
    	
    }

    @FXML
    void onClickDeleteSceneBtn(ActionEvent event) {

    }

    @FXML
    void onClickObjectStatusAddImageBtn(ActionEvent event) {

    }

    @FXML
    void onToggleObjectVisibleCB(ActionEvent event) {

    }

    void checkScene() { // check which scene is selected
    	
    }
    
    
    
    
    
    ///////==========================================/////////
	public void initTestSequence() {
		// create dummy scene
		File dummy = new File("src/roomescapemaker/resources/dummy_room.jpg");
		System.out.println(dummy.getAbsolutePath());
		
		RoomScene dumscene = new RoomScene(dummy);
		roomScenesObservable.add(dumscene);
	}

    
	
	//================================================//
	

	
	
}
