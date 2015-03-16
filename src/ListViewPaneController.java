import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;


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
        Resort resort = current.getResort();
        Image coverImage = new Image(current.imageUrl, 302, 150, false, true);
        resortImage.setImage(coverImage);
        resortName.setText(resort.getName());
        record1.setText(String.format("Distance: %.2f km", resort.getDistance() / 1000.0));
        record3.setText("Wind: " + resort.getWind() + " m/s");
        record2.setText("Degrees: " + (resort.getForecast(0).getHigh() + resort.getForecast(0).getLow()) / 2 + " °C");

    }


}

