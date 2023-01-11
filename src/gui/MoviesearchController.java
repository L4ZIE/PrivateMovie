package gui;

import be.Movie;
import dal.DataAccessObject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MoviesearchController {

    @FXML
    private TableColumn<?, ?> castTableColumn;

    @FXML
    private TableColumn<?, ?> descriptionTableColumn;

    @FXML
    private TableColumn<?, ?> genreTableColumn;

    @FXML
    private TableColumn<?, ?> imdbTableColumn;

    @FXML
    private TextField keywordsTextField;

    @FXML
    private TableView<Movie> movieTableView;

    @FXML
    private TableColumn<?, ?> nameTableColumn;

    /*
    Function that updates the list with the new movie object created from data fetched from the database.
     */
    public void UpdateTable(){
        Movie selectedMovie = DataAccessObject.GetMovie();
        movieTableView.getItems().add(selectedMovie);
    }
}