package roomescapemaker.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import roomescapemaker.model.RoomObject;
import roomescapemaker.model.RoomScene;
import roomescapemaker.model.interaction.ConditionAction;
import roomescapemaker.model.interaction.ObjectInteraction;
import roomescapemaker.model.interaction.ObjectResult;

public class GameController implements Initializable{
	
	@FXML
	private AnchorPane mainStagePane;
	
	@FXML
    private Pane mainPane;
    
    @FXML
    private ScrollPane mainCanvasScrollPane;
    
    @FXML
    private ListView<RoomObject> itemListView;
    
    @FXML
    private BorderPane usingItemPane;
    
    @FXML
    private Button useBtn;
    
    @FXML
    private Button cancelBtn;
    
    private ObservableList<RoomScene> sceneList = FXCollections.observableArrayList();
    private ObservableList<RoomObject> itemList = FXCollections.observableArrayList();
    private ArrayList<ImageView> objectImageView;
    
    private ImageView usingItemIV;
    private RoomObject usingItem;
    
    private boolean isGameOver = false;
    private boolean isGameClear = false;
    private int currentScene = 0;
    private Stage gameSimulationStage;
    
    public GameController() {
    	
    }
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		objectImageView = new ArrayList<ImageView>();
		itemListView.setCellFactory(new Callback<ListView<RoomObject>, ListCell<RoomObject>>(){
    		@Override
    		public ListCell<RoomObject> call(ListView<RoomObject> arg0){
    			ListCell<RoomObject> cell = new ListCell<RoomObject>() {
    				@Override
    				protected void updateItem(RoomObject obj, boolean bt1) {
    					super.updateItem(obj, bt1);
    					if (obj != null) {
    						ImageView imgview = new ImageView(obj.getStatus(obj.getCurrentStatus()).getStatusImage());
    						imgview.setFitWidth(160);
    						imgview.setPreserveRatio(true);
    						setGraphic(imgview);
    					}
    					else {
    						setGraphic(null);
    						setText(null);
    					}
    				}
    			};	
    				
    			return cell;
    		}	
    	} );
		itemListView.setItems(itemList);
	}
	
	private void clearObjectIVList() {
    	mainPane.getChildren().clear();
    	objectImageView.clear();
	}
	
	@FXML
	private void onClickUseBtn(ActionEvent event) {
		if (itemListView.getSelectionModel().getSelectedItem() != null) {
			usingItem = itemListView.getSelectionModel().getSelectedItem();
			usingItemIV = new ImageView();
			usingItemIV.setImage(itemListView.getSelectionModel().getSelectedItem().getStatus(
					itemListView.getSelectionModel().getSelectedItem().getCurrentStatus()).getStatusImage());
			usingItemIV.setFitHeight(133);
			usingItemIV.setPreserveRatio(true);
			usingItemIV.fitWidthProperty().bind(usingItemPane.widthProperty());
	    	usingItemPane.setCenter(usingItemIV);
		}
	}
	
	@FXML
	private void onClickCancelBtn(ActionEvent event) {
		usingItem = null;
		usingItemPane.setCenter(null);
	}
	
	public void reDrawMainCanvas(RoomScene rs) {
    	
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
        	objImage.visibleProperty().bind(obj.getStatus(obj.getCurrentStatus()).visibleProperty());
        	
        	for (ObjectInteraction oi : obj.getInteractionList()) {
        		if (oi.getPrimaryCondition().getMainAction().equals(ConditionAction.CLICK)) {
        			if (oi.checkValidAction()) {
        				objImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
        					@Override
        					public void handle(MouseEvent event) {
        						System.out.println("fuck! I clicked!");
        						if (oi.checkSecondaryConditionList() && oi.checkPossessedCondition(usingItem)) {
        							currentScene = oi.getSceneChangeResult().getTargetIndex();
        							isGameOver = oi.getSceneChangeResult().getIsGameOver();
        							isGameClear = oi.getSceneChangeResult().getIsGameClear();
        							try {
										oi.excuteSoundStream();
									} catch (LineUnavailableException | IOException e) {
										System.out.println("sound file is strange!");
										e.printStackTrace();
									}
        							oi.executeObjectResultList();
        							setPossessedItemToItemList(oi);
        							reDrawMainCanvas(sceneList.get(currentScene));
        						}
        						if (isGameOver) {
        							System.out.println("Game over!");
        							gameSimulationStage.close();
        						}
        						if (isGameClear) {
        							System.out.println("Game clear!");
        							gameSimulationStage.close();
        						}
        					}
        				});
        			}
        		}
        	}

        	objectImageView.add(objImage);
        }
        
         for(ImageView objIV: objectImageView) {
        	 mainPane.getChildren().add(objIV);
         }
    }
	
	public void setPossessedItemToItemList(ObjectInteraction oi) {
		for (ObjectResult or : oi.getObjectResultList()) {
			if (or.getTargetObject().getStatus(or.getTargetIndex()).getPossess() == true && itemList.contains(or.getTargetObject()) == false) {
				itemList.add(or.getTargetObject());
				itemListView.refresh();
			}
		}
	}
	
	public void setSceneList(ObservableList<RoomScene> sceneList) {
		this.sceneList = sceneList;
	}
	
	public void setDialogStage(Stage gameSimulationStage) {
		this.gameSimulationStage = gameSimulationStage;
	}

}
