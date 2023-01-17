package gui.controllers;


import be.DataRoute;
import be.Filter;
import be.Movie;

import be.PlayerFunctions;
import dal.database.SqlServerException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MoviesearchController implements Initializable {

    @FXML
    private TableColumn<Movie, String> castTableColumn;

    @FXML
    private TableColumn<Movie, String> descriptionTableColumn;

    @FXML
    private TableColumn<Movie, String> genreTableColumn;

    @FXML
    private TableColumn<Movie, Double> imdbTableColumn;

    @FXML
    private TextField movieSearchBox;

    @FXML
    private TableView<Movie> movieTableView;


    @FXML
    private TableColumn<Movie, String> nameTableColumn;

    @FXML
    private Button playMovieButton;

    PlayerFunctions playerFunctions = new PlayerFunctions();

    Filter filter = new Filter();

    DataRoute dataRoute = new DataRoute();

    /*
    Function that updates the list with all movie objects created from data fetched from the database.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // initialization method for tableview

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        imdbTableColumn.setCellValueFactory(new PropertyValueFactory<>("IMDB"));
        genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        castTableColumn.setCellValueFactory(new PropertyValueFactory<>("Cast"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));

        try {
            updateTable(dataRoute.routeMovie());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (SqlServerException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateTable(ObservableList<Movie> selectedMovies) throws SQLException, SqlServerException {
        //Displaying movies as table view rows
            movieTableView.setItems(selectedMovies);
    }


    //Function that calls media player methods on button click
    public void playButtonOnPress(){
        if (movieTableView.getSelectionModel().getSelectedItem() != null){
            Movie selectedMovie = movieTableView.getSelectionModel().getSelectedItem();
            playerFunctions.playVideo(selectedMovie.getPath());
        }
    }
    //Sends user input to the search function
    public void searchForName() throws SQLException, SqlServerException {
        updateTable(filter.searchMovie(movieSearchBox.getText(), "name"));
    }
    public void searchForCast() throws SQLException, SqlServerException {
        updateTable(filter.searchMovie(movieSearchBox.getText(), "cast"));
    }
    public void searchForCategory() throws SQLException, SqlServerException {
        updateTable(filter.searchMovie(movieSearchBox.getText(), "category"));
    }
}