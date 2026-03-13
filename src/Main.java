import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Player player = new Player(500);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        primaryStage.setTitle("Farm My Farm");
        primaryStage.setScene(new Scene(root, 1250, 750));
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}