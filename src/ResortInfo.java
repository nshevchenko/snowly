public class ResortInfo {

    private Resort data;

    String imageUrl;

    public ResortInfo(Resort resort, String imageUrl) {
        data = resort;
        this.imageUrl = imageUrl;
    }

    public Resort getResort() {
        return data;
    }
}

