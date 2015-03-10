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

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Rene Birkeland on 08.03.2015.
 */
public class Screen1Controller implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML private Button toggle;

    @FXML private Pane contentPane;

    @FXML private ObservableList<Node> oldPaneContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        oldPaneContent = FXCollections.observableArrayList();
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private Pane pageID1;
    @FXML
    private Pane pageID2;

    //Which screen we are on
    int screenNr = 0;

    @FXML
    private void goToScreen2(KeyEvent e) {
        if(e.getCode().toString().equals("RIGHT")) {

            System.out.println("right");
            //myController.setScreen(ScreensFramework.screen2ID);
            try {
                oldPaneContent.setAll(contentPane.getChildren());
                contentPane.getChildren().clear();
                contentPane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("hard-packed.fxml")));
                pageID1.setVisible(false);
                pageID2.setVisible(true);
                screenNr++;
                //contentPane = FXMLLoader.load(getClass().getResource("hard-packed.fxml"));
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.println("Error loading other pane.");
            }
        }

        if (e.getCode().toString().equals("LEFT") || screenNr < 0) {
           try {
               screenNr--;
               contentPane.getChildren().clear();
               contentPane.getChildren().setAll(oldPaneContent);
               pageID1.setVisible(true);
               pageID2.setVisible(false);

           } catch (NullPointerException npe) {

           }
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

