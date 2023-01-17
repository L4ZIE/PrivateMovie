package gui;

import be.PlayerFunctions;
import dal.database.SqlServerException;
import gui.controllers.MoviesearchController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class PrivateMovie extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, SqlServerException {
        //Creating controller instance
            MoviesearchController moviesearchController = new MoviesearchController();

                Parent root = FXMLLoader.load(getClass().getResource("view/Test.fxml"));
                primaryStage.setTitle("Movies");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}