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
        //ArrayList<Resort> resorts = rm.getNearbyResorts(51.515977f, (float) -0.0183201);

        ResortModel foo = ResortModel.getInstance();
        ArrayList<Resort> resorts = foo.getNearbyResortsByType(0,0,1);

        for(Resort resort : resorts) {
            System.out.println("|-----------------------------------------------------------------");
            System.out.println("|   " + resort.getName());
            System.out.println("|   " + resort.getCity());
            System.out.println("|-----------------------------------------------------------------");
        }

        System.out.println("Total: " + resorts.size());

/*
        ResortModel rm = ResortModel.getInstance();
        ArrayList<Resort> resorts = rm.getNearbyResortsByType(51.515977, -0.0183201, 1);
        for(Resort resort : resorts) {
            System.out.println("|-----------------------------------------------------------------");
            System.out.println("|   " + resort.getName());
            System.out.println("|   " + resort.getCity());
            System.out.println("|-----------------------------------------------------------------");
        }

        System.out.println("Total: " + resorts.size());
*/
    }

    private static Connection c = null;
    private static ResortModel singleton = null;

    public static ResortModel getInstance() {

        if(singleton == null) {
            singleton = new ResortModel();
        }

        return singleton;
    }

    private ResortModel() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:resources" + File.separator + "snowly_db.sqlite");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }

    /*
    * Development Purposes.
    * All calls for Resorts will be made with coordinates of the user with getAllNearbyResorts(lat, long);
    * */
    public static ArrayList<Resort> getAllResorts() {

        Statement statement = null;
        ArrayList<Resort> result = new ArrayList<Resort>();

        try {

            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM resorts500;");

            while (rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("address");
                Resort resort = new Resort(name, city);
                //DEBUG
                resort.setId(rs.getDouble("id"));
                if (resort.init()) {
                    result.add(resort);
                }
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<Resort> getNearbyResortsByType(double latitude, double longitude, int type) {

        PreparedStatement statement = null;
        ArrayList<Resort> result = new ArrayList<Resort>();
        ArrayList<Resort> result2 = new ArrayList<Resort>();

        try {
            statement = c.prepareStatement("SELECT * FROM resorts500 WHERE type = ?");
            statement.setInt(1, type);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("address");
                double resortLat = rs.getDouble("latitude");
                double resortLong = rs.getDouble("longitude");
                Resort resort = new Resort(name, city);

                resort.setDistance(distFrom((float) latitude, (float) longitude, (float) resortLat, (float) resortLong));

                result.add(resort);
            }

            Collections.sort(result, new ResortComparator());

            for(int i=0; i < result.size(); i++) {

                if(i<15) {
                    if(result.get(i).init()) {
                        result2.add(result.get(i));
                    }
                } else {
                    return result2;
                }
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result2;

    }

    public static ArrayList<Resort> getNearbyResorts(float latitude, float longitude) {

        PreparedStatement statement = null;
        ArrayList<Resort> result = new ArrayList<Resort>();

        try {

            statement = c.prepareStatement("SELECT * FROM resorts LIMIT 20");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                Resort resort = new Resort(name, city);
                if(resort.init()) {
                    float resortLong = (float) resort.getLongitude();
                    float resortLat  = (float) resort.getLatitude();

                    double distance = distFrom(latitude, longitude, resortLat, resortLong);

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

    public static ArrayList<Resort> getAllResortsByType(int type) {
        return getAllResortsByType(type, 51.515977f, (float) -0.0183201); // London
    }

    public static ArrayList<Resort> getAllResortsByType(int type, float latitude, float longitude) {

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

                    double distance = distFrom(latitude, longitude, resortLat, resortLong);

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

    /*
    * Stackoverflow
    * */
    private static float distFrom(float lat1, float lng1, float lat2, float lng2) {
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

    /*
    * Development Purposes
    * */
    public static void setLatLongForID(double id, String name, double latitude, double longitude) {

        try {
            PreparedStatement stmt = c.prepareStatement("UPDATE resorts500 SET latitude = ?, longitude = ? WHERE id = ?");
            stmt.setDouble(1, latitude);
            stmt.setDouble(2, longitude);
            stmt.setDouble(3, id);
            stmt.execute();
            stmt.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public static void setRandomTypes() {

        try {

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM resorts500");

            int count = 1;

            while(rs.next()) {

                if(count == 4) {
                    count = 1;
                } else {
                    count++;
                }

                double id = rs.getDouble("id");

                PreparedStatement s = c.prepareStatement("UPDATE resorts500 SET type = ? WHERE id = ?");
                s.setInt(1, count);
                s.setDouble(2, id);
                s.execute();
                s.close();

                System.out.println("[" + count + "] " + rs.getString("name"));

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
