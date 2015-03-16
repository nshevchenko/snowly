import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;


public class Screen3Controller implements Initializable, ControlledScreen {

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

    @FXML private Label nameLabel;
    @FXML private Label distanceLabel;
    @FXML private Label windLabel;

    @FXML private ImageView mapView;



    private ResortInfo info;
    private Resort currentResort;
    private Forecast[] forecasts;

    private String[] weatherUrls = {"smaller-sun.png", "cloud-snow.png", "cloudy.png", "snowing.png", "sunny.png"};

    ScreensController myController;

    //Sets parent screen.
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }


    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < weatherUrls.length; i++)
            weatherUrls[i] = "img/Weather/" + weatherUrls[i];

        info = (ResortInfo)resources.getObject("resort");
        currentResort = info.getResort();

        resortImage.setImage(new Image(info.imageUrl, 320, 182, false, true));
        nameLabel.setText(currentResort.getName());
        distanceLabel.setText(String.format("Distance: %.2f km", currentResort.getDistance() / 1000.0));
        windLabel.setText("Wind " + currentResort.getWind() + " m/s");

        forecasts = new Forecast[5];
        for (int i = 0; i < forecasts.length; i++)
            forecasts[i] = currentResort.getForecast(i);


        day1.setText(forecasts[0].getDay());
        day2.setText(forecasts[1].getDay());
        day3.setText(forecasts[2].getDay());
        day4.setText(forecasts[3].getDay());
        day5.setText(forecasts[4].getDay());

        temp1.setText(forecasts[0].getAvg().toString());
        temp2.setText(forecasts[1].getAvg().toString());
        temp3.setText(forecasts[2].getAvg().toString());
        temp4.setText(forecasts[3].getAvg().toString());
        temp5.setText(forecasts[4].getAvg().toString());

        weatherImg1.setImage(new Image(weatherUrls[mapWeatherIcons(0)]));
        weatherImg2.setImage(new Image(weatherUrls[mapWeatherIcons(1)]));
        weatherImg3.setImage(new Image(weatherUrls[mapWeatherIcons(2)]));
        weatherImg4.setImage(new Image(weatherUrls[mapWeatherIcons(3)]));
        weatherImg5.setImage(new Image(weatherUrls[mapWeatherIcons(4)]));

        System.out.println(currentResort.getLatitude());
        System.out.println(currentResort.getLongitude());

        Image img = new Image("https://maps.googleapis.com/maps/api/staticmap?center=" + currentResort.getLatitude() + "," + currentResort.getLongitude() + "&zoom=9&size=260x120&maptype=roadmap\">",
                320, 127, false, true);

        mapView.setImage(img);
    }

    public int mapWeatherIcons(int day) {
        int code = forecasts[day].getCode();

        if (code < 26) {
            return 1;
        }
        else if (code < 31) {
            return 2;
        }
        else if (code < 34) {
            return 4;
        }
        else if (code < 37) {
            return 0;
        }
        else {
            return 3;
        }
    }

    //Key listener for keyboard input, to navigate the app.
    public void goToScreen(KeyEvent e) {
        if(e.getCode().toString().equals("UP")) {
            myController.setScreen(ScreensFramework.screenDownID);
        }
    }

}
