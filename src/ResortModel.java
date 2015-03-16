/**
 * Created by hs on 12/03/15.
 */
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class ResortModel {

    public static void main(String[] args) {

        //Resort resort = new Resort("Gaustablikk", "London");
        //resort.init();

        //System.out.println(resort.getForecast(0).getHigh());

        ResortModel rm = new ResortModel();
        ArrayList<Resort> resorts = rm.getNearbyResorts(51.515977f, (float) -0.0183201);

        for(Resort resort : resorts) {
            System.out.println(resort.getName() + " " + resort.getDistance());
        }


    }

    private Connection c = null;

    public ResortModel() {

        try {
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection("jdbc:sqlite:resources" + File.separator + "snowly_db.sqlite");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public ArrayList<Resort> getAllResorts() {

        Statement statement = null;
        ArrayList<Resort> result = new ArrayList<Resort>();

        try {

            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM resorts;");

            while(rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                Resort resort = new Resort(name, city);
                if(resort.init()) {
                    result.add(resort);
                }

                // Debug
                System.out.println(rs.getString("name") + " is added");
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<Resort> getNearbyResorts(float latitude, float longitude) {


        PreparedStatement statement = null;
        ArrayList<Resort> result = new ArrayList<Resort>();

        try {

            statement = c.prepareStatement("SELECT * FROM resorts");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                Resort resort = new Resort(name, city);
                if(resort.init()) {
                    float resortLong = (float) resort.getLongitude();
                    float resortLat  = (float) resort.getLatitude();

                    double distance = this.distFrom(latitude, longitude, resortLat, resortLong);

                    resort.setDistance(distance);

                    result.add(resort);
                }

                // Debug
                //System.out.println(rs.getString("name") + " is added");
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collections.sort(result, new ResortComparator());
        return result;


    }

    public ArrayList<Resort> getAllResortsByType(int type) {
        return this.getAllResortsByType(type, 51.515977f, (float) -0.0183201); // London
    }

    public ArrayList<Resort> getAllResortsByType(int type, float latitude, float longitude) {

        PreparedStatement statement = null;
        ArrayList<Resort> result = new ArrayList<Resort>();

        try {

            statement = c.prepareStatement("SELECT * FROM resorts WHERE type = ?");
            statement.setInt(1, type);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                Resort resort = new Resort(name, city);
                if(resort.init()) {
                    float resortLong = (float) resort.getLongitude();
                    float resortLat  = (float) resort.getLatitude();

                    double distance = this.distFrom(latitude, longitude, resortLat, resortLong);

                    resort.setDistance(distance);

                    result.add(resort);
                }

                // Debug
                //System.out.println(rs.getString("name") + " is added");
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collections.sort(result, new ResortComparator());
        return result;

    }

    private float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

}
