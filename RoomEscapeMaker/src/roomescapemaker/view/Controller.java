// based on branch controller_implement
package roomescapemaker.view;

import roomescapemaker.MainApp;
import roomescapemaker.model.RoomScene;
import roomescapemaker.model.ObjectStatus;
import roomescapemaker.model.RoomObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import java.lang.ArrayIndexOutOfBoundsException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller implements Initializable{
    
	private ObservableList<RoomScene> sceneList = FXCollections.observableArrayList();
	private Stage fileChooserDialog; 
    private MainApp mainApp;
    // ImageView for status property pane
    private ImageView img;
	
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
    
    /*
     * control for Status choice box
     */
    @FXML
    private ChoiceBox<ObjectStatus> statusChoiceBox;
    
    @FXML
    private Button addStatusBtn;
    
    @FXML
    private Button deleteStatusBtn;
    
    /*
     * TitlePane containers
     */
    @FXML
    private TitledPane propertyPane;
    
    @FXML
    private TitledPane interactionPane;
    
    /*
     * control for showing status property
     */
    @FXML
    private TextField statusNameField;
    
    @FXML 
    private Button imgBrowseBtn;
    
    @FXML
    private BorderPane statusImgPane;
    
    @FXML
    private TextField scaleField;
    
    @FXML
    private TextField xPosField;
    
    @FXML
    private TextField yPosField;
    
    @FXML
    private CheckBox visibleBox;
    
    @FXML
    private CheckBox possessBox;
    
    @FXML
    private Button applyStatusEditBtn;
    
    /*
     * control for Scene list
     */
    @FXML //done!
	private ListView<RoomScene> sceneListView;
    
    @FXML //done!
    private Button addSceneBtn;
    
    @FXML //done!
    private Button deleteSceneBtn;
    
    /*
     * control for Object list
     */
    @FXML //done!
	private ListView<RoomObject> objectListView;
    
    @FXML //done!
    private Button addObjectBtn;
    
    @FXML //done!
    private Button deleteObjectBtn;
    
    
    
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
    	
    	//add scene list listener -> detect changes in sceneList
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
    	
    	//listener for selecting a object
    	objectListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCurrentStatus(newValue)); 
    	
    	//listener for selecting a status
    	statusChoiceBox.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showStatusProperty(newValue));
    
    }
    
    private void showContainedObjects(RoomScene rs) {
    	// clear Status Property pane and Status choice box when changing scene
    	clearStatusProperty();
    	ObservableList<ObjectStatus> clearChoiceBox = FXCollections.observableArrayList();
    	statusChoiceBox.setItems(clearChoiceBox);
    	
    	if (rs != null) {
    		System.out.println(rs.getRoomObjectList().size());
    		objectListView.setItems(rs.getRoomObjectList());
    	} else {
    		return;
    	}
    }
    
    private void showCurrentStatus(RoomObject ro) {
    	if (ro != null) {
    		System.out.println(ro.getObjectName());
    		System.out.println("Current status : "+ ro.getCurrentStatus());
    		statusChoiceBox.setItems(ro.getStatusList());	
    		statusChoiceBox.setValue(ro.getStatus(ro.getCurrentStatus()));
    	} else {
    		return;
    	}
    }
    
    private void showStatusProperty(ObjectStatus os) {
    	if (os != null) {
    		if (propertyPane.isExpanded() == true) {
    			setStatusProperty(os);
    		} else {
    			propertyPane.setExpanded(true);
    			setStatusProperty(os);
    		}
    	} else {
    		return;
    	}
    }
    
    private void setStatusProperty(ObjectStatus os) {
    	statusNameField.setText(os.getStatusName());
    	img = new ImageView();
    	img.setImage(os.getStatusImage());
    	img.setFitHeight(120);
    	img.setPreserveRatio(true);
    	img.fitWidthProperty().bind(statusImgPane.widthProperty());
    	statusImgPane.setCenter(img);
    	scaleField.setText(Integer.toString(os.getScale()));
    	xPosField.setText(Double.toString(os.getXpos()));
    	yPosField.setText(Double.toString(os.getYpos()));
    	visibleBox.setSelected(os.getVisible());
    	possessBox.setSelected(os.getPossess());
   	
    }
    
    private void clearStatusProperty() {
    	statusNameField.clear();
    	statusImgPane.setCenter(null);
    	scaleField.clear();
   		xPosField.clear();
   		yPosField.clear();
   		visibleBox.setSelected(false);
   		possessBox.setSelected(false);
    }
    
    @FXML
    private void onClickApplyStatusChangeBtn(ActionEvent event) {
    	if (statusChoiceBox.getSelectionModel().getSelectedItem() != null) {
    		if (isInputValid()) {
    			statusChoiceBox.getSelectionModel().getSelectedItem().setStatusName(statusNameField.getText());
    			statusChoiceBox.getSelectionModel().getSelectedItem().setImageFile(img.getImage());
    			statusChoiceBox.getSelectionModel().getSelectedItem().setScale(Integer.parseInt(scaleField.getText()));
    			statusChoiceBox.getSelectionModel().getSelectedItem().setXpos(Double.parseDouble(xPosField.getText()));
    			statusChoiceBox.getSelectionModel().getSelectedItem().setYpos(Double.parseDouble(yPosField.getText()));
    			statusChoiceBox.getSelectionModel().getSelectedItem().setVisible(visibleBox.isSelected());
    			statusChoiceBox.getSelectionModel().getSelectedItem().setPossess(possessBox.isSelected());
    			//refresh for object list view
    			objectListView.refresh();
    		}
    		else {
    			//alert message : input value is not valid
    			return;
    		}
    	} else {
    		//alert message : please select a status to edit
    		return;
    	}
    }
    
    private boolean isInputValid() {
		if (statusNameField.getText() == null || statusNameField.getText().length() == 0) {
			return false;
		} else {
			try {
				Integer.parseInt(scaleField.getText());
			} catch (NumberFormatException e) {
				System.out.println("Scale value must be a integer type!");
				return false;
			}
			try {
				Double.parseDouble(xPosField.getText());
			} catch (NumberFormatException e) {
				System.out.println("Coordinate x must be a double type!");
				return false;
			}
			try {
				Double.parseDouble(yPosField.getText());
			} catch (NumberFormatException e) {
				System.out.println("Coordinate y must be a double type!");
				return false;
			}
			return true;
		}		
	}
    
    @FXML 
    private void onClickImgBrowseBtn() {
    	if (statusChoiceBox.getSelectionModel().getSelectedItem() != null) {
    		FileChooser fileChooser = new FileChooser();
    		fileChooser.getExtensionFilters().addAll(
    				new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg","*.png"));
    	
    		fileChooser.setInitialDirectory(new File("."));
    	
    		File selectedFile = fileChooser.showOpenDialog(fileChooserDialog);
    		if (selectedFile == null) {
    			return;	
    		} else {
    			try {
    				img = new ImageView();
    				img.setImage(new Image(selectedFile.toURI().toURL().toString()));
    				img.setFitHeight(120);
    		    	img.setPreserveRatio(true);
    		    	img.fitWidthProperty().bind(statusImgPane.widthProperty());
    		    	statusImgPane.setCenter(img);
    			} catch (MalformedURLException e) {
    				e.printStackTrace();
    				System.out.println("wrong file path url");
    			}
    		}
    	}
    }
    
    @FXML 
    private void onClickAddStatusBtn(ActionEvent event) {
    	if (objectListView.getSelectionModel().getSelectedItem() != null) {
    		ObjectStatus newStatus = new ObjectStatus("new status " 
    				+ objectListView.getSelectionModel().getSelectedItem().getStatusList().size(), null);
    		objectListView.getSelectionModel().getSelectedItem().getStatusList().add(newStatus);	
    	}
    	else {
    		return;
    	}
    }
    
    @FXML 
    private void onClickDeleteStatusBtn(ActionEvent event) {
    	if (statusChoiceBox.getSelectionModel().getSelectedItem() != null) {
    		if (statusChoiceBox.getSelectionModel().getSelectedIndex() == 0) {
    			// alert can't remove first status
    			System.out.println("Cannot remove first status : It is default!");
    		} else {
    			objectListView.getSelectionModel().getSelectedItem().getStatusList().remove(statusChoiceBox.getSelectionModel().getSelectedItem());
    			clearStatusProperty();
    		} 
    	}
    }

    @FXML
    private void onClickAddSceneBtn(ActionEvent event) {

    	RoomScene newRoomScene = new RoomScene(null, null);
    	boolean okClicked = mainApp.showAddSceneStage(newRoomScene);
    	if (okClicked) {
    		sceneList.add(newRoomScene);
    	}
    }
    
  
    @FXML
    private void onClickAddObjectBtn(ActionEvent event) {
    	
    	if(sceneListView.getSelectionModel().getSelectedItem() != null) {
    		RoomObject newRoomObject = new RoomObject();
    		boolean okClicked = mainApp.showAddObjectStage(newRoomObject);
    		if (okClicked) {
    			sceneListView.getSelectionModel().getSelectedItem().getRoomObjectList().add(newRoomObject);
    		}
    	}
    	else return;
    }

    
    
    @FXML
    private void onClickDeleteSceneBtn(ActionEvent event) {
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
    private void onClickDeleteObjectBtn(ActionEvent event) {
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
    	sceneList.get(0).getRoomObject(0).addStatus("isopen", "roomescapemaker/resource/backgrounds/normalRoom.png");
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
