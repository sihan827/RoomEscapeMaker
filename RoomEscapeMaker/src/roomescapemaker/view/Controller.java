package roomescapemaker.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Controller {

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
    void onCLickMenuFileClose(ActionEvent event) {
    	
    	Stage stage = (Stage)(menuBar.getScene().getWindow());
    	stage.close();
    	
    }

    @FXML
    void playScene(ActionEvent event) {

    }

}
