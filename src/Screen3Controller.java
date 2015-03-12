import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class Screen3Controller implements Initializable, ControlledScreen {

    ScreensController myController;

    //Sets parent screen.
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }


    public void initialize(URL location, ResourceBundle resources) {
        resortImage.setImage(new Image("/img/fjell3.jpg"));
        day1.setText("Test");
    }

    //Key listener for keyboard input, to navigate the app.
    public void goToScreen(KeyEvent e) {
        if(e.getCode().toString().equals("UP")) {
            myController.setScreen(ScreensFramework.screenDownID);
        }
    }


    @FXML private ImageView resortImage;

    @FXML private HBox forecast;
    @FXML private Label day1;
    @FXML private Label day2;
    @FXML private Label day3;
    @FXML private Label day4;
    @FXML private Label day5;
    @FXML private Label temp1;
    @FXML private Label temp2;
    @FXML private Label temp3;
    @FXML private Label temp4;
    @FXML private Label temp5;
    @FXML private ImageView weatherImg1;
    @FXML private ImageView weatherImg2;
    @FXML private ImageView weatherImg3;
    @FXML private ImageView weatherImg4;
    @FXML private ImageView weatherImg5;

    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;



}
