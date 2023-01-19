package gui.controllers;

import be.DataRoute;
import be.Movie;
import dal.MovieDAO;
import dal.database.SqlServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.time.temporal.ChronoUnit;


public class DeleteMoviePopupController implements Initializable {
    @FXML
    private Button removeButton, closeButton;
    @FXML
    private ListView<String> deleteMovieList;
    ObservableList<Movie> selectedMovies = FXCollections.observableArrayList();
    DataRoute dataRoute = new DataRoute();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataRoute dataRoute = new DataRoute();
        try {
            ObservableList<Movie> allMovies = dataRoute.routeMovie();

            //checks if movie has low rating and hasn't been played in two years
            for (Movie movie:allMovies) {
                LocalDate lastView = LocalDate.parse(movie.getLastView().toString());
                LocalDate timeNow = LocalDate.now();
                long daysBetween = Duration.between(lastView.atTime(0, 0), timeNow.atTime(0, 0)).toDays();
                if (Integer.parseInt(movie.getPersonalRating()) < 6 && daysBetween > 730) {
                    selectedMovies.add(movie);
                    deleteMovieList.getItems().add(movie.getName());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (SqlServerException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void removeMovies() throws SQLException {
        for (Movie movie : selectedMovies) {
            MovieDAO.removeMovie(movie.getName());
        }
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
