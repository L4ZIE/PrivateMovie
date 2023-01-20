package gui.controllers;


import be.*;

import bll.Filter;
import dal.CatMovDAO;
import dal.CategoryDAO;
import dal.MovieDAO;
import dal.database.SqlServerException;
import gui.PrivateMovie;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;



public class MoviesearchController implements Initializable {

   @FXML
   public TableView<Category> categoryTableView;
  @FXML
  public TableColumn<Category, String> categoryTableColumn;

  @FXML
  public TableColumn<Movie, Date> lastViewTableColumn;
    @FXML
    private TableColumn<Movie, String> castTableColumn;

    @FXML
    private TableColumn<Movie, String> descriptionTableColumn;
    @FXML
    public TableColumn <Movie, String> personalRatingTableColumn;

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
        lastViewTableColumn.setCellValueFactory(new PropertyValueFactory<>("LastView"));
        genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        castTableColumn.setCellValueFactory(new PropertyValueFactory<>("Cast"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        personalRatingTableColumn.setCellValueFactory(new PropertyValueFactory<>("PersonalRating"));

        DataRoute dataRoute = new DataRoute();

        try {
            ObservableList<Movie> allMovies = dataRoute.routeMovie();
            updateMovieTable(allMovies);
            testForDeletableMovies();
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

    public void playMovie() throws SQLException {
        if (movieTableView.getSelectionModel().getSelectedItem() != null) {
            Movie selectedMovie = movieTableView.getSelectionModel().getSelectedItem();
            MovieDAO.updateWatchTime(selectedMovie.getName());
            playerFunctions.playVideo(selectedMovie.getPath());
        }
    }
    public void openAddCategory() throws IOException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/AddCategory.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddCategory = new Stage();
       // listOfStages.add(stageAddSong);
        stageAddCategory.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2503/2503508.png"));
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
        stageAddCategory.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2503/2503508.png"));
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
        updateMovieTable(MovieDAO.getAllMovies());
    }


    public void openSetCategory() throws IOException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/SetCategory.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddCategory = new Stage();
        // listOfStages.add(stageAddSong);
        stageAddCategory.setTitle("Set a category");
        stageAddCategory.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2503/2503508.png"));
        stageAddCategory.setScene(scene);
        stageAddCategory.show();
        stageAddCategory.setResizable(false);
    }
    public void setCategory() throws IOException{
        openSetCategory();
    }


    public void  getCategory(MouseEvent mouseEvent) throws SQLException, SqlServerException {

        if (categoryTableView.getSelectionModel().getSelectedItem() != null) {
            //Getting selected category name
            String categorySelection = categoryTableView.getSelectionModel().getSelectedItem().getName();

            //Getting the category from the database to get the CategoryID
            Category cat = CategoryDAO.getAllCategories().filtered(category -> category.getName().equals(categorySelection)).get(0);

            //Filtering all catMovs to get akk movies related to this Category
            ObservableList<CatMov> catMovs = CatMovDAO.getCatMov().filtered(catMov -> catMov.getCatID() == cat.getId());

            //Setting the Ids od the related movies
            List<Integer> ids = new ArrayList<>();

            for (CatMov e : catMovs) {
                ids.add(e.getMovID());
            }

            //Filtering all the movies from the database
            ObservableList<Movie> movies = MovieDAO.getAllMovies().filtered(movie -> ids.contains(movie.getId()));

            //Displaying the table
            movieTableView.setItems(movies);
        }
    }
    public void openSetPersonalRating() throws IOException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/SetPersonalRating.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddRating = new Stage();
        // listOfStages.add(stageAddSong);
        stageAddRating.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2503/2503508.png"));
        stageAddRating.setTitle("Set a personal rating");
        stageAddRating.setScene(scene);
        stageAddRating.show();
        stageAddRating.setResizable(false);
    }

    public void setPersonalRating() throws IOException {
        openSetPersonalRating();
    }
    public void openAddMovie() throws IOException, SQLException, SqlServerException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/AddMovie.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddMovie = new Stage();
        stageAddMovie.setTitle("Add a movie");
        stageAddMovie.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2503/2503508.png"));
        stageAddMovie.setScene(scene);
        stageAddMovie.show();
        stageAddMovie.setResizable(false);
        updateMovieTable(dataRoute.routeMovie());
    }

    public void openRemoveMovie() throws IOException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/RemoveMovie.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageRemoveMovie = new Stage();
        stageRemoveMovie.setTitle("Remove a movie");
        stageRemoveMovie.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2503/2503508.png"));
        stageRemoveMovie.setScene(scene);
        stageRemoveMovie.show();
        stageRemoveMovie.setResizable(false);
    }

    public void openDeleteMoviePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(PrivateMovie.class.getResource("view/DeleteMoviePopup.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageOpenPopup = new Stage();
        stageOpenPopup.setScene(scene);
        stageOpenPopup.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2503/2503508.png"));
        stageOpenPopup.setAlwaysOnTop(true);
        stageOpenPopup.show();
        stageOpenPopup.setResizable(false);
    }

    public void testForDeletableMovies(){
        DataRoute dataRoute = new DataRoute();
        try {
            ObservableList<Movie> allMovies = dataRoute.routeMovie();
            //checks if movie has low rating and hasn't been played in two years
            for (Movie movie:allMovies) {
                if (movie.getLastView() != null) {
                    LocalDate lastView = LocalDate.parse(movie.getLastView().toString());
                    LocalDate timeNow = LocalDate.now();
                    long daysBetween = Duration.between(lastView.atTime(0, 0), timeNow.atTime(0, 0)).toDays();
                    if (Integer.parseInt(movie.getPersonalRating()) < 6 && daysBetween > 730) {
                        openDeleteMoviePopup();
                    }
                }
            }
        } catch (SQLException | SqlServerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}