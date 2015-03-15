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

    private String name, city;
    private double distance, wind;
    private Forecast[] forecast = new Forecast[5];

    public Resort(String name, String city) {

        this.name = name;
        this.city = city;

    }

    /*
    * Returns true if API call was successful
    * */
    public boolean init() {

        String result = null;

        String yql = URLEncoder.encode("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + this.getCity() + "\") and u='c'");

        try {
            result = httpGet("https://query.yahooapis.com/v1/public/yql?q=" + yql + "&format=json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject(result).getJSONObject("query").getJSONObject("results").getJSONObject("channel");
        this.wind = json.getJSONObject("wind").getDouble("speed");
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

        return (result != null) ? true : false;

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

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }
}
