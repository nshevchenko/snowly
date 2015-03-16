import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.xml.stream.Location;

/**
 * Created by nik on 13/03/15.
 */
public class GoogleMaps {

    private WebView googleMap;
    private double latitude, longitude;

    public GoogleMaps(WebView map, double latitude, double longitude)
    {
        googleMap = map;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public void init()
    {
        try {
            WebEngine webEngine = googleMap.getEngine();
            webEngine.loadContent("<html>\n"
                    + "<body>"
                    + "<img draggable=\"false\" id=\"img_prop\" src=\"https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=9&size=260x120&maptype=roadmap\">"
                    + "</body>\n"
                    + "</html>");
        } catch(NullPointerException e) {
            System.out.println("asd");
        }
    }
}
