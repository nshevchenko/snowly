import java.util.Comparator;

/**
 * Created by hs on 15/03/15.
 */
public class ResortComparator implements Comparator<Resort> {
    @Override
    public int compare(Resort o1, Resort o2) {
        if(o1.getDistance() == o2.getDistance()) {
            return 0;
        }

        if(o1.getDistance() > o2.getDistance()) {
            return 1;
        }
        return -1;
    }
}
