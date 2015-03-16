import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by filipt on 12/03/15.
 */
public class ListViewPaneController implements Initializable{

    @FXML
    private ImageView resortImage;
    @FXML
    private Label resortName;

    @FXML
    private Label record1;
    @FXML
    private Label record2;
    @FXML
    private Label record3;

    @Override
    public void initialize(URL pointless, ResourceBundle alsoPointless) {

            ResortInfo current = ResortData.getInstance().getResort();
            Image coverImage = new Image(current.imageUrl, 302, 150, false, true);
            resortImage.setImage(coverImage);
            resortName.setText(current.name);
            record1.setText("Distance: " + current.distance + " km");
            record3.setText("Wind: " + current.wind + " m/s");
            record2.setText("Degrees: " + current.degrees + " °C");

    }


}
