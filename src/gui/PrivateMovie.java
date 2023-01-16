package gui;

import dal.SqlServerException;
import gui.MoviesearchController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;


public class PrivateMovie extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, SqlServerException {
        //Creating controller instance
            MoviesearchController moviesearchController = new MoviesearchController();

                Parent root = FXMLLoader.load(getClass().getResource("Test.fxml"));
                primaryStage.setTitle("Movies");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}