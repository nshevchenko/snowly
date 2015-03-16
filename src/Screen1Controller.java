import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



//Controls the first screen, with the 4 options for different snow types
public class Screen1Controller implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML private Pane screenRoot;
    @FXML private Button toggle;
    @FXML private Pane contentPane;
    @FXML private ObservableList<Node> oldPaneContent;


    @FXML private Pane pageID1;
    @FXML private Pane pageID2;
    @FXML private Pane pageID3;
    @FXML private Pane pageID4;

    @FXML private Pane menuPane;
    @FXML private Button menuCloseButton;

    private String[] tabsFXML = {"powder_tab.fxml", "hard-packed.fxml", "machine-prep.fxml", "terrain-park.fxml", "terrain-park.fxml"};
    private final int TAB_COUNT = 4;
    private int currentTab;

    public void initialize(URL url, ResourceBundle rb) {
        oldPaneContent = FXCollections.observableArrayList();
        try {
            contentPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("powder_tab.fxml")));
        } catch( IOException e) {
            System.out.println(e.toString());
        }
        menuPane.setVisible(false);
    }

    //Sets parent screen.
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

    //Key listener for keyboard input, to navigate the app.
    @FXML
    private void goToScreen(KeyEvent e) {
        if(e.getCode().toString().equals("RIGHT")) {
            if (currentTab < TAB_COUNT - 1)
                currentTab++;

        }

        if (e.getCode().toString().equals("LEFT")) {
            if (currentTab > 0)
                currentTab--;
        }

        if(e.getCode().toString().equals("DOWN")) {
            ResortData.getInstance().setSnow(currentTab);
            myController.loadScreen(ScreensFramework.screenDownID, ScreensFramework.screenDownFile);
            myController.setScreen(ScreensFramework.screenDownID);
        }
        openTab();
    }


    @FXML
    private void pressMenuBtn(ActionEvent e)
    {
        menuPane.setVisible(true);
        System.out.println("Test");
    }

    @FXML
    public void closeMenuBtn(ActionEvent e)
    {
        menuPane.setVisible(false);
    }

}