import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Rene on 11/03/2015.
 */
public class Screen3Controller implements Initializable, ControlledScreen {

    ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resortImage.setImage(new Image("/img/fjell3.jpg"));
    }



    @FXML private ImageView resortImage;
    @FXML private HBox forecast;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;



}
