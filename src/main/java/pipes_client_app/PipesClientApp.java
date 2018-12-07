package pipes_client_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PipesClientApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = new File("src/main/java/pipes_client_app/view/MainWindow.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Pipes Game");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }
}
