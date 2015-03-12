/**
 * Created by hs on 12/03/15.
 */
public class ResortModel {

    public static void main(String[] args) {

        Resort resort = new Resort("Gaustablikk", "London");
        resort.init();

        System.out.println(resort.getForecast(0).getHigh());

    }

}
