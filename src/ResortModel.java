/**
 * Created by hs on 12/03/15.
 */
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class ResortModel {

    public static void main(String[] args) {

        //Resort resort = new Resort("Gaustablikk", "London");
        //resort.init();

        //System.out.println(resort.getForecast(0).getHigh());

        ResortModel rm = new ResortModel();
        ArrayList<Resort> resorts = rm.getAllResorts();

        for(Resort resort : resorts) {
            System.out.println(resort.getName() + " " + resort.getForecast()[0].getHigh());

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

}
