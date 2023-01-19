package gui.controllers;

import be.Category;
import be.Movie;
import dal.MovieDAO;
import dal.database.SqlServerException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {

    @FXML
    private TextField movieName, movieCast, movieDescription, fileName;
    @FXML
    private Button cancelButton, submitButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Instantiate MovieDAO to access the post method
    }

    //When submit button is clicked, if input is valid, creates new movie and sends it to the post method in movieDAO
    public void submitMovie() throws SQLException, SqlServerException {
        if (!movieName.getText().isBlank() && !movieCast.getText().isBlank() && !movieDescription.getText().isBlank() &&
            !fileName.getText().isBlank()){
             Movie newMovie;
             MovieDAO movieDAO=new MovieDAO();
            if (movieDAO.getAllMovies().size()>0) {
                 newMovie = new Movie(movieDAO.getAllMovies().get(movieDAO.getAllMovies().size() - 1).getId() + 1,movieName.getText(), 0.00, "", "src/dal/moviefiles/" + fileName.getText(),
                        movieCast.getText(), movieDescription.getText(), null, null);
            }else{
                 newMovie = new Movie(1,movieName.getText(), 0.00, "", "src/dal/moviefiles/" + fileName.getText(),
                         movieCast.getText(), movieDescription.getText(), null, null);
            }
            MovieDAO.postNewMovie(newMovie);
            }
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        }

    public void close(){

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
