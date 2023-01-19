package gui.controllers;

import dal.CategoryDAO;
import dal.MovieDAO;
import dal.database.SqlServerException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveMovieController implements Initializable {
    MovieDAO movieDAO = new MovieDAO();
    @FXML
    public Button removeButton, closeButton;
    @FXML
    public ComboBox movieBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movieBox.setItems(FXCollections.observableArrayList(movieDAO.getMovieName()));
        try {
            movieBox.setVisibleRowCount(movieDAO.getAllMovies().size());
        } catch (SQLException | SqlServerException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    //Calls remove movie from table function from MovieDAO and closes the popup window
    public void removeMovie() throws SQLException {
        String selectedIndex = movieBox.getSelectionModel().getSelectedItem().toString();
        movieDAO.removeMovie(selectedIndex);
        movieBox.setItems(FXCollections.observableArrayList(movieDAO.getMovieName()));
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
