import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Rene Birkeland on 08.03.2015.
 */
public class ScreensFramework extends Application {

    public static String screen1ID = "main";
    public static String screen1File = "Snowly-proto.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "hard-packed.fxml";
    public static String screenDownID = "down";
    public static String screenDownFile = "list-view.fxml";


    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screenDownID, ScreensFramework.screenDownFile);

        mainContainer.setScreen(ScreensFramework.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
