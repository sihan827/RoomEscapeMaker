// based on branch controller_implement
package roomescapemaker.view;

import roomescapemaker.MainApp;
import roomescapemaker.model.RoomScene;
import roomescapemaker.model.interaction.ObjectInteraction;
import roomescapemaker.model.ObjectStatus;
import roomescapemaker.model.RoomObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

import java.lang.ArrayIndexOutOfBoundsException;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;

import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller implements Initializable{

    
    private ObservableList<RoomScene> sceneList = FXCollections.observableArrayList();
    private ObservableList<RoomScene> objectList = FXCollections.observableArrayList();

    private ArrayList<ImageView> objectImageView;

    private Stage fileChooserDialog;
    private Stage dirChooserDialog;

    private MainApp mainApp;
    // ImageView for status property pane
    private ImageView img;

    @FXML
    private Pane mainPane;
    
    @FXML
    private ScrollPane mainCanvasScrollPane;
    @FXML
    private Canvas mainCanvas;
    @FXML
    private Pane pane;
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
    private AnchorPane resizeScrollimg;
    
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
	private Stage mainStage;
    
    /*
     * control for Interaction List
     */
	  
    @FXML
	private TableView<ObjectInteraction> interactionTable;
    
	@FXML
	private TableColumn<ObjectInteraction, String> conditionColumn;
	
	@FXML
	private TableColumn<ObjectInteraction, String> resultColumn;
	
	@FXML 
	private Button addInteractionBtn;
	
	@FXML
	private Button deleteInteractionBtn;
    
    private String saveProjectName;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	initTest();
    	objectImageView = new ArrayList<ImageView>();
    	
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
    						imgview.setFitWidth(160);
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
    			
    				
    				//
    				//
    				//
    			    //Start Drag Function!
    				cell.setOnDragDetected((MouseEvent event) -> {
    					System.out.println("objectList Drag Detected");
    					
    					if(cell.getItem() == null)
    						return;
    					
    					Dragboard db = cell.startDragAndDrop(TransferMode.COPY);
    					ClipboardContent content = new ClipboardContent();

    					content.putString(Integer.toString(cell.getIndex()));
    					System.out.println(cell.getItem().getObjectName());
    					db.setContent(content);
    					event.consume();
    					});
    			
    				cell.setOnDragOver(event -> {
    					Dragboard db = event.getDragboard();
    		            if (db.hasString()) {
    		                event.acceptTransferModes(TransferMode.COPY);
    		                System.out.println("Moving!");
    		            }
    		            event.consume();
    		        });
    				
    				cell.setOnDragExited(event -> {
    					Dragboard db = event.getDragboard();
    					if(db.hasString()) {
    						System.out.println("I'm out!");
    					}
    					event.consume();
    				});
    				
    			return cell;
    		}	
    	} );
    	
    	mainPane.setOnDragOver(event ->{
    		Dragboard db = event.getDragboard();
            if (db.hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
                System.out.println("Moving in main!");
            }
            event.consume();
    	});
    	
    	mainPane.setOnDragDropped(new EventHandler<DragEvent>() {
    		public void handle(DragEvent event) {
    			System.out.println("DRagDropp detected");
    			
    			Dragboard db = event.getDragboard();
    			
    			if(db.hasString()) {
    				String objectnum = db.getString();
    				
    				System.out.println("Arrive " + objectnum);
    				objectListView.getItems().get(Integer.parseInt(objectnum)).getStatus(objectListView.getItems().get(Integer.parseInt(objectnum)).getCurrentStatus()).setVisible(true);
    			}
    			event.setDropCompleted(true);
    			event.consume();
    		}
    	});
    	//Drag function END!
    	//
    	//
    	//
    			

    	sceneListView.setItems(sceneList);
    	
		conditionColumn.setCellValueFactory(
				cellData->cellData.getValue().conditionNameProperty());
		resultColumn.setCellValueFactory(
				cellData->cellData.getValue().resultNameProperty());
    	
    	
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
                (observable, oldValue, newValue) -> onClickSceneListView(newValue)); 
    	
    	//listener for selecting a object
    	objectListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCurrentStatus(newValue)); 
    	

    	objectListView.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showInteractionList(newValue));

    	//listener for selecting a status
    	statusChoiceBox.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showStatusProperty(newValue));
    	
       
    }
    
    private void onClickSceneListView(RoomScene rs) {
    	showContainedObjects(rs);
    	
    	reDrawMainCanvas(rs);
    }
    
    
    
    private void clearObjectIVList() {
		
    	mainPane.getChildren().clear();
    	objectImageView.clear();
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
    
    public void showInteractionList(RoomObject ro) {
    	if (ro != null) {
    		interactionTable.setItems(ro.getInteractionList()); 		
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
    			reDrawMainCanvas(sceneListView.getSelectionModel().getSelectedItem());
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
    				+ objectListView.getSelectionModel().getSelectedItem().getStatusList().size(), null, objectListView.getSelectionModel().getSelectedItem().getObjectName());
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
    		reDrawMainCanvas(sceneListView.getSelectionModel().getSelectedItem());
    	}
    }
    
    @FXML
    private void onClickAddObjectBtn(ActionEvent event) {
    	
    	if(sceneListView.getSelectionModel().getSelectedItem() != null) {
    		RoomObject newRoomObject = new RoomObject();
    		boolean okClicked = mainApp.showAddObjectStage(newRoomObject);
    		if (okClicked) {
    			sceneListView.getSelectionModel().getSelectedItem().getRoomObjectList().add(newRoomObject);
    			reDrawMainCanvas(sceneListView.getSelectionModel().getSelectedItem());
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
    			reDrawMainCanvas(sceneListView.getSelectionModel().getSelectedItem());
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
    			reDrawMainCanvas(sceneListView.getSelectionModel().getSelectedItem());
    		} catch(ArrayIndexOutOfBoundsException e){
    			e.printStackTrace();
    			System.out.println("no data in objectlist");
    		}
    	}
    	else return;
    }
    
    @FXML 
    private void onClickAddInteractionBtn(ActionEvent event) {
    	if(objectListView.getSelectionModel().getSelectedItem() != null) {
    		ObjectInteraction newInteraction = new ObjectInteraction();
    		boolean okClicked = mainApp.showInteractionAddStage(newInteraction, sceneList, objectListView.getSelectionModel().getSelectedItem());
    		if (okClicked) {
    			objectListView.getSelectionModel().getSelectedItem().getInteractionList().add(newInteraction);		
    		}
    	}
    	else return;
    }
    
    @FXML
    private void onClickDeleteInteractionBtn(ActionEvent event) {
    	if (interactionTable.getSelectionModel().getSelectedItem() != null) {
    		objectListView.getSelectionModel().getSelectedItem().removeInteraction(
    				interactionTable.getSelectionModel().getSelectedIndex());
    	} else {
    		return;
    	}
    }
    
    void reDrawMainCanvas(RoomScene rs) {
    	
    	System.out.println("draw canvas");
    	clearObjectIVList();
    
    	ImageView bgImg = new ImageView();
    	bgImg.setImage(rs.getBackGroundImage());
    	bgImg.fitHeightProperty().bind(mainCanvasScrollPane.heightProperty());
    	bgImg.setPreserveRatio(true);
        mainPane.getChildren().add(bgImg);
        StackPane.setAlignment(bgImg, Pos.CENTER);
        
        //rescaleRatio = bgImg.getFitHeight() / rs.getBackGroundImage().getHeight();
        //halfbgImgwidth = rs.getBackGroundImage().getWidth() * rescaleRatio / 2;
        
        bgImg.translateXProperty().bind(Bindings.multiply(bgImg.fitHeightProperty(), -rs.getBackGroundImage().getWidth()/rs.getBackGroundImage().getHeight()).add(mainCanvasScrollPane.widthProperty()).divide(2));
        
        for (RoomObject obj : rs.getRoomObjectList()) {
        	ImageView objImage = new ImageView();
        	objImage.setImage(obj.getStatus(obj.getCurrentStatus()).getStatusImage());
        	objImage.translateXProperty().bind(Bindings.divide(bgImg.fitHeightProperty(), rs.getBackGroundImage().getHeight()).multiply(obj.getStatus(obj.getCurrentStatus()).xPosProperty()).add(bgImg.translateXProperty()));
        	objImage.translateYProperty().bind(Bindings.divide(bgImg.fitHeightProperty(), rs.getBackGroundImage().getHeight()).multiply(obj.getStatus(obj.getCurrentStatus()).yPosProperty()));
        	objImage.scaleXProperty().bind(Bindings.divide(bgImg.fitHeightProperty(), rs.getBackGroundImage().getHeight()).multiply(obj.getStatus(obj.getCurrentStatus()).getScale()).divide(100));
        	objImage.scaleYProperty().bind(Bindings.divide(bgImg.fitHeightProperty(), rs.getBackGroundImage().getHeight()).multiply(obj.getStatus(obj.getCurrentStatus()).getScale()).divide(100));
        	objImage.visibleProperty().bind(obj.getStatus(obj.getCurrentStatus()).visibleProperty());;
        	objImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("fuck! I clicked!");
                }
            });

        	objectImageView.add(objImage);
        }
        
         for(ImageView objIV: objectImageView) {
        	 mainPane.getChildren().add(objIV);
         }
    }
    
    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
    
    public void initTest() {
    	sceneList.add(new RoomScene("Scene 1", new Image("roomescapemaker/resource/backgrounds/normalRoom.png")));
    	sceneList.get(0).addRoomObject("books", "roomescapemaker/resource/objects/books.jpg");
    	sceneList.get(0).getRoomObject(0).addStatus("isopen", "roomescapemaker/resource/backgrounds/normalRoom.png");
    	sceneList.get(0).getRoomObject(0).addInteraction(new ObjectInteraction());
    	sceneList.get(0).getRoomObject(0).getObjectInteraction(0).setConditionName("condition 0");
    	sceneList.get(0).getRoomObject(0).getObjectInteraction(0).setResultName("result 0");
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

     
    @FXML
    void onMouseClickedMainPane(MouseEvent event) {
    	System.out.println("mouse click");
    	
    }

    @FXML
    void onMouseEnterMainPane(MouseEvent event) {
    	//System.out.println("mouse enter");
    	
    }
   
    
    
    @FXML
    void onMouseDragObjectListView(MouseEvent event) {
    	System.out.println("mouse Drag");
    }

    
    
    
    @FXML
    void onClickMenuFileSave(ActionEvent event) {
    	
    	//ArrayList<RoomScene> saveList = new ArrayList<RoomScene>(sceneList);
    	
    	//System.out.println("saveList : " + saveList);
    	String chooseTitle = "choose save directory";
        try {
        	DirectoryChooser dirChooser = new DirectoryChooser();
			dirChooser.setInitialDirectory(new File("."));
			dirChooser.setTitle(chooseTitle);
    		File selectedDir = dirChooser.showDialog(dirChooserDialog);
    		
    		RoomScene.setSavePath(selectedDir.getPath()); // set path to save
    		RoomObject.setSavePath(selectedDir.getPath());
    		ObjectStatus.setSavePath(selectedDir.getPath());
    	    
            FileOutputStream fileOut = new FileOutputStream(selectedDir.getAbsoluteFile() + "/MainSceneFileTemp");
            ObjectOutputStream objectOut= new ObjectOutputStream(fileOut);
            
            objectOut.writeObject(new ArrayList<RoomScene>(sceneList));
            
            objectOut.close();
            fileOut.close();
            System.out.println("save complete!!!");
            
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	
        	System.out.println("unable to save!");
		}
    }
    
    @FXML
    void onCLickMenuFileQuit(ActionEvent event) {
        
    	Stage stage = (Stage)menuBar.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onClickMenuFileOpen(ActionEvent event) {
    	
    	System.out.println("load sequence");
		try {
			 // remove existing!!
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("."));
	    	
    		File selectedFile = fileChooser.showOpenDialog(fileChooserDialog);
    		
			FileInputStream fileIn = new FileInputStream(selectedFile);
		    ObjectInputStream objectIn= new ObjectInputStream(fileIn);
		    System.out.println("opening... " + selectedFile.getParentFile().getPath());
		    String openPath = selectedFile.getParentFile().getPath();
		    RoomScene.setOpenPath(openPath); // set path to save
    		RoomObject.setOpenPath(openPath);
    		ObjectStatus.setOpenPath(openPath);
		    sceneList.clear();
		    
		    sceneList = FXCollections.observableArrayList((ArrayList<RoomScene>) objectIn.readObject());
		    
		    objectIn.close();
		    fileIn.close();
		    
		    sceneListView.setItems(sceneList);
		    
		    reDrawMainCanvas(sceneList.get(0));
		    System.out.println("load complete!!!");
		            
		}catch (Exception e) {
					// TODO: handle exception
		  	e.printStackTrace();
		   	System.out.println("unable to Load!");
		}
	
    }
    
}
