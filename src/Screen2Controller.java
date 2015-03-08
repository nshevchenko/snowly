import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Rene Birkeland on 08.03.2015.
 */
public class Screen2Controller implements Initializable, ControlledScreen {

    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private Pane pageID1;
    @FXML
    private Pane pageID2;

    @FXML
    private void goToScreen1(KeyEvent e) {
        if(e.getCode().toString().equals("LEFT")) {
            myController.setScreen(ScreensFramework.screen1ID);
        }
    }
}

