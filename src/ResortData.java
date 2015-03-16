import java.util.ArrayList;
import java.util.List;

public class ResortData {

    private static ResortData instance;

    public static ResortData getInstance() {
        if (instance == null)
            instantiate();
        return instance;
    }

    public static void instantiate() {
        instance = new ResortData();
    }

    private int counter;
    private int snowType;
    private String[] imgUrls = {"img/fjell3.jpg", "img/hafjell.png", "img/alpine-slopes.jpg", "img/resort4.jpg"};

    private ArrayList<ArrayList<ResortInfo>> information;

    public ResortData() {
        counter = 0;
        information = new ArrayList<ArrayList<ResortInfo>>(4);

        for (int i = 0; i < 4; i++)
            information.add(new ArrayList<ResortInfo>(15));

        ResortModel model = ResortModel.getInstance();
        setLists(model);

    }

    private void setLists(ResortModel model) {
        for (int i = 0; i < 4; i++) {
            ArrayList<Resort> resorts = model.getNearbyResortsByType(51.5f, -0.12f, i + 1);
            ArrayList<ResortInfo> current = information.get(i);

            for (int k = 0, j = 0; k < resorts.size(); k++, j++) {
                if (j == 4)
                    j = 0;

                current.add(new ResortInfo(resorts.get(k), imgUrls[j]));
            }
        }
    }

    public void setSnow(int snow) {
        snowType = snow;
        reset();
    }

    public boolean hasResort() {
        if (counter >= information.get(snowType).size())
            return false;
        else
            return true;

    }

    public ResortInfo getResort() {
        return information.get(snowType).get(counter++);
    }

    public void reset() {
        counter = 0;
    }

}

