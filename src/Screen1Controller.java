import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sun.jvm.hotspot.oops.NarrowOopField;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Group 6 on 08.03.2015.
 */
public class Screen1Controller implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML private Button toggle;
    @FXML private Pane contentPane;
    @FXML private ObservableList<Node> oldPaneContent;

    @FXML private Pane pageID1;
    @FXML private Pane pageID2;
    @FXML private Pane pageID3;
    @FXML private Pane pageID4;

    String[] tabsFXML = {"powder_tab.fxml", "hard-packed.fxml", "machine-prep.fxml", "terrain-park.fxml", "terrain-park.fxml"};

    
    private final int TAB_COUNT = 4;
    private int currentTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        oldPaneContent = FXCollections.observableArrayList();
        try {
            contentPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("powder_tab.fxml")));
        } catch( IOException e) {
            System.out.println(e.toString());
        }
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    private void openTab()
    {
        oldPaneContent.setAll(contentPane.getChildren());
        contentPane.getChildren().clear();
        try {
            contentPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource(tabsFXML[currentTab])));
        } catch( IOException e) {
            System.out.println(e.toString());
        }

        deactivatePageIDs();
        switch (currentTab){
            case 0:
                pageID1.setVisible(true);
                break;
            case 1:
                pageID2.setVisible(true);
                break;
            case 2:
                pageID3.setVisible(true);
                break;
            case 3:
                pageID4.setVisible(true);
                break;
        }
    }

    private void deactivatePageIDs()
    {
        pageID1.setVisible(false);
        pageID2.setVisible(false);
        pageID3.setVisible(false);
        pageID4.setVisible(false);
    }

    @FXML
    private void goToScreen2(KeyEvent e) {
        if(e.getCode().toString().equals("RIGHT")) {
            if (currentTab > TAB_COUNT - 1)
                currentTab = 0;
            else
                currentTab++;
        }

        if (e.getCode().toString().equals("LEFT")) {
            if (currentTab <= 0)
                currentTab = TAB_COUNT - 1;
            else
                currentTab--;
        }
        openTab();
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

