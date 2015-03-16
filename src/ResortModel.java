/**
 * Created by hs on 12/03/15.
 */
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class ResortModel {


    public static void main(String[] args) {

        ResortModel rm = ResortModel.getInstance();
        ArrayList<Resort> resorts = rm.getNearbyResortsByType(1);

        for(Resort resort : resorts) {
            System.out.println(resort.getName());
        }

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

    public static ArrayList<Resort> getNearbyResortsByType(int type) {
        return getNearbyResortsByType(51.522829, -0.0430761, type); // ITL, Queen Mary Campus
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
                Resort resort = new Resort(name, city, latitude, longitude);

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

}
