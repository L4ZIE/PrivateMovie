package gui.controllers;


import be.DataRoute;
import be.Movie;

import dal.database.SqlServerException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField keywordsTextField;

    @FXML
    private TableView<Movie> movieTableView;


    @FXML
    private TableColumn<Movie, String> nameTableColumn;

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
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (SqlServerException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateTable() throws SQLException, SqlServerException {
        DataRoute dataRoute = new DataRoute();
        ObservableList<Movie> allMovies = dataRoute.routeMovie();

        //Displaying movies as table view rows
            movieTableView.setItems(allMovies);
    }
}