import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.*;

/**
 * Created by hs on 12/03/15.
 */

public class Resort {

    // wind, distance, degress

    private String name, city, link;
    private double id, distance, wind, latitude, longitude;
    private Forecast[] forecast = new Forecast[5];

    public Resort(String name, String city) {

        this.name = name;
        this.city = city;

    }

    /*
    * Returns true if API call was successful
    * */
    public boolean init() {

        String result;
        String yql;

        try {
            yql = URLEncoder.encode("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + this.getCity() + "\") and u='c'");
            result = httpGet("https://query.yahooapis.com/v1/public/yql?q=" + yql + "&format=json");
            JSONObject json = new JSONObject(result).getJSONObject("query").getJSONObject("results").getJSONObject("channel");
            this.setWind(json.getJSONObject("wind").getDouble("speed"));

            //this.setLatitude(json.getJSONObject("item").getDouble("lat"));
            //this.setLongitude(json.getJSONObject("item").getDouble("long"));


            // ******** Dev

            // ResortModel.setLatLongForID(this.id, this.name, this.getLatitude(), this.getLongitude());

            // ******** Dev end

            JSONArray jsonForecasts = json.getJSONObject("item").getJSONArray("forecast");

            for(int i=0; i<jsonForecasts.length(); i++) {
                JSONObject jsonForecast = jsonForecasts.getJSONObject(i);
                this.forecast[i] = new Forecast(
                        jsonForecast.getInt("code"),
                        jsonForecast.getString("date"),
                        jsonForecast.getString("day"),
                        jsonForecast.getInt("high"),
                        jsonForecast.getInt("low"),
                        jsonForecast.getString("text")
                );
            }
        } catch(JSONException e) {
            //e.printStackTrace();
            return false;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }

        return true;

    }

    public String httpGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        /* Response code isn't 200 for an OK response
        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }
        */

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();
        return sb.toString();
    }

    public Forecast getForecast(int index) {
        return this.forecast[index];
    }

    public Forecast[] getForecast() {
        return this.forecast;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public double getWind() {
        return wind;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setId(double id) {
        this.id = id;
    }
    public double getId() {
        return this.id;
    }
}
