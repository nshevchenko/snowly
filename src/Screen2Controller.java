import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.fxml.LoadException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Screen2Controller implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    private ListView<Pane> listView;

    @FXML private Pane listViewParent;

    private boolean escapeListFlag;

    public void initialize(URL url, ResourceBundle rb) {
        listView.getStylesheets().add("css/kill-scroll-bar.css");
        listView.setPrefWidth(320);
        escapeListFlag = false;
        //loop
        //data.add(FXMLLoader.load(getClass().getResource("list-view-pane.fxml")));
        ObservableList<Pane> data = FXCollections.observableArrayList();
        ResortData resort = ResortData.getInstance();

            while (resort.hasResort())
                    try {
                        data.add((Pane) FXMLLoader.load(getClass().getResource("list-view-pane.fxml")));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        System.out.println("complete disaster");
                    }
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
            if ((listView.getSelectionModel().getSelectedIndex() == 0))
                if (escapeListFlag)
                    myController.setScreen(ScreensFramework.screen1ID);
                else
                    escapeListFlag = true;

            //myController.setScreen(ScreensFramework.screen1ID);
        }

        if (e.getCode().toString().equals("DOWN")) {
            if (escapeListFlag)
                escapeListFlag = false;
        }
    }

}

