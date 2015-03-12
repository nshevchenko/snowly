import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class Screen2Controller implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    private ListView<String> listView;

    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> data = FXCollections.observableArrayList(
                "sdfs", "sdfsdf", "sdfsdf"
        );
        listView.setItems(data);
    }

    //Sets parent screen.
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    //Key listener for keyboard input, to navigate the app.
    public void goToScreen(KeyEvent e) {
        if(e.getCode().toString().equals("ENTER")) {
            myController.setScreen(ScreensFramework.screenResortID);
        }

        if(e.getCode().toString().equals("UP")) {
            myController.setScreen(ScreensFramework.screen1ID);
        }
    }

}

