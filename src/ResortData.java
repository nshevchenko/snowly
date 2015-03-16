import java.util.ArrayList;
import java.util.List;

/**
 * Created by filipt on 12/03/15.
 */
public class ResortData {

    private static ResortData instance;

    public static ResortData getInstance() {
        if (instance == null)
            instance = new ResortData();

        return instance;
    }

    List<ResortInfo> information;
    private int counter;

    public ResortData() {

        information = new ArrayList<ResortInfo>();

        information.add(new ResortInfo("Gaustablikk", -6, 5, 62, "img/fjell3.jpg"));
        information.add(new ResortInfo("A place", -10, 8, 90, "img/hafjell.png"));
        information.add(new ResortInfo("Hehe", 1, 1, 1, "img/alpine-slopes.jpg"));
        counter = 0;
    }

    public boolean hasResort() {
        if (counter >= information.size())
           return false;
        else
            return true;

    }

    public ResortInfo getResort() {
        return information.get(counter++);
    }

    public void reset() {
        counter = 0;
    }



}
