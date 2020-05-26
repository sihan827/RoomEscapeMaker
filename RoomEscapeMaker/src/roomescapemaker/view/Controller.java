package roomescapemaker.view;

import roomescapemaker.MainApp;
import roomescapemaker.model.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import java.lang.IllegalArgumentException;
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
    
    private Stage mainStage; 
    
    @FXML
    private Button addSceneBtn;
    
    private MainApp mainApp;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	sceneList.clear();
    	sceneList.add(new RoomScene("Scene 1", new Image("roomescapemaker/resource/normalRoom.png")));
    	sceneList.add(new RoomScene("Scene 2", new Image("roomescapemaker/resource/normalRoom.png")));
    	sceneList.add(new RoomScene("Scene 3", new Image("roomescapemaker/resource/normalRoom.png")));
    	sceneList.add(new RoomScene("Scene 4", new Image("roomescapemaker/resource/normalRoom.png")));
    	sceneList.add(new RoomScene("Scene 5", new Image("roomescapemaker/resource/normalRoom.png")));
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
    }
    
    @FXML
    void onClickAddSceneBtn(ActionEvent event) throws MalformedURLException {

    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    	     new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png")
    	);
    	File selectedFile = fileChooser.showOpenDialog(mainStage);
    	
    	sceneList.add(new RoomScene("scene X", new Image(selectedFile.toURI().toURL().toString())));
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
