import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class GUI extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private Stage primaryStage;
    private Pane rootLayout;

    public void start(final Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Snowly");
        System.out.println("******");
        initRootLayout();
    }

    @FXML
    private GridPane gridPane;

    public void initRootLayout() {

            try {

           /*// Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("Snowly-proto.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();*/


                Parent root = FXMLLoader.load(getClass().getResource("Snowly-proto.fxml"));
                primaryStage.setTitle("Snowly");
                //primaryStage.setResizable(false);
                primaryStage.setScene(new Scene(root));
                primaryStage.show();

                System.out.println(gridPane);
            } catch(IOException e) {
                e.printStackTrace();
            }
    }

    public void handle(KeyEvent e) {

        System.out.println("Test");
        e.consume();
    }


}



