import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Rene Birkeland on 08.03.2015.
 */
public class Screen1Controller implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML private Button toggle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image buttonImg = new Image(getClass().getResourceAsStream("img/btnimg.png"), 59, 27, false, true);
        toggle.setGraphic(new ImageView(buttonImg));
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private Pane pageID1;
    @FXML
    private Pane pageID2;

    @FXML
    private void goToScreen2(KeyEvent e) {
        if(e.getCode().toString().equals("RIGHT")) {

            myController.setScreen(ScreensFramework.screen2ID);
        }
    }

    @FXML
    private void goToDownScreen(KeyEvent e) {
        if(e.getCode().toString().equals("DOWN")) {

            //myController.setScreen(ScreensFramework.screen2ID);
        }
    }

    @FXML
    private void menu(ActionEvent e) {
       System.out.println("Test");
    }
}

