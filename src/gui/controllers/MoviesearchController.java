package gui.controllers;


import be.Category;
import be.DataRoute;
import be.Filter;
import be.Movie;

import be.PlayerFunctions;
import dal.database.SqlServerException;
import gui.PrivateMovie;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class MoviesearchController implements Initializable {

   @FXML
   public TableView<Category> categoryTableView;
  @FXML
  public TableColumn<Category, String> categoryTableColumn;
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

       // initialization method for Movies TableView

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        imdbTableColumn.setCellValueFactory(new PropertyValueFactory<>("IMDB"));
        genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        castTableColumn.setCellValueFactory(new PropertyValueFactory<>("Cast"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));

        DataRoute dataRoute = new DataRoute();

        try {
            ObservableList<Movie> allMovies = dataRoute.routeMovie();
            updateMovieTable(allMovies);
        } catch (SQLException | SqlServerException e) {

            throw new RuntimeException(e);
        }

        // initialization method for Categories TableView
        categoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        try {
            updateCategoryTable();
        } catch (SQLException | SqlServerException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateMovieTable(ObservableList<Movie> selectedMovies) throws SQLException, SqlServerException {

        //Displaying movies as table view rows
            movieTableView.setItems(selectedMovies);

    }


    public void updateCategoryTable() throws SQLException, SqlServerException{
        DataRoute dataRoute = new DataRoute();
        ObservableList<Category> allCategories = dataRoute.routeCategory();
        System.out.println(allCategories);
        categoryTableView.setItems(allCategories);
    }

    public void searchForName() throws SQLException, SqlServerException {
        updateMovieTable(filter.searchMovie(movieSearchBox.getText(), "name"));
    }
    public void searchForCast() throws SQLException, SqlServerException {
        updateMovieTable(filter.searchMovie(movieSearchBox.getText(), "cast"));
    }
    public void searchForCategory() throws SQLException, SqlServerException {
        updateMovieTable(filter.searchMovie(movieSearchBox.getText(), "category"));
    }

    public void openAddCategory() throws IOException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/AddCategory.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddCategory = new Stage();
       // listOfStages.add(stageAddSong);
        stageAddCategory.setTitle("Add a category");
        stageAddCategory.setScene(scene);
        stageAddCategory.show();
        stageAddCategory.setResizable(false);
    }
    public void addCategory() throws IOException{
        openAddCategory();
    }
    public void openRemoveCategory() throws IOException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/RemoveCategory.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddCategory = new Stage();
        // listOfStages.add(stageAddSong);
        stageAddCategory.setTitle("Remove a category");
        stageAddCategory.setScene(scene);
        stageAddCategory.show();
        stageAddCategory.setResizable(false);
    }
    public  void removeCategory() throws IOException{
        openRemoveCategory();
    }

    public void refreshTable() throws SQLException, SqlServerException {
        updateCategoryTable();
        //updateMovieTable();
    }


    public void openSetCategory() throws IOException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/SetCategory.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddCategory = new Stage();
        // listOfStages.add(stageAddSong);
        stageAddCategory.setTitle("Set a category");
        stageAddCategory.setScene(scene);
        stageAddCategory.show();
        stageAddCategory.setResizable(false);
    }
    public void setCategory() throws IOException{
        openSetCategory();

    }

    public void playMovie(){
        playerFunctions.playVideo(movieTableView.getSelectionModel().getSelectedItem().getPath());
    }

    public void openAddMovie() throws IOException, SQLException, SqlServerException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/AddMovie.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddCategory = new Stage();
        stageAddCategory.setTitle("Add a movie");
        stageAddCategory.setScene(scene);
        stageAddCategory.show();
        stageAddCategory.setResizable(false);
        updateMovieTable(dataRoute.routeMovie());
    }
}