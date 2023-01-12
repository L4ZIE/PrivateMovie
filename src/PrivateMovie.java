import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class PrivateMovie extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("Test.fxml"));
            primaryStage.setTitle("Movies");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}