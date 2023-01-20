package gui;

import dal.database.SqlServerException;
import gui.controllers.MoviesearchController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;


public class PrivateMovie extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException, SqlServerException {
        //Creating controller instance
                Parent root = FXMLLoader.load(getClass().getResource("view/PrivateMovieView.fxml"));
                primaryStage.setScene(new Scene(root));
                primaryStage.setResizable(false);
                primaryStage.centerOnScreen();
                primaryStage.setTitle("Movies");
                primaryStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2503/2503508.png"));
                primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}