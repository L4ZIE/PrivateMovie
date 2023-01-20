package bll;

import be.Movie;
import dal.MovieDAO;
import dal.database.SqlServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Filter {
    private MovieDAO movieDAO = new MovieDAO();
    //Search function
    //Looking through the list of all movies
    public ObservableList<Movie> searchMovie(String userSearchInput, String searchOption) throws SQLException, SqlServerException {
        //searching through the list for matching results
        ObservableList<Movie> matchingMovies = FXCollections.observableArrayList();
        ObservableList<Movie> allMovies = movieDAO.getAllMovies();

        for (Movie movie: allMovies) {
            switch(searchOption){
                case "name":
                    if (movie.getName().toLowerCase().contains(userSearchInput.toLowerCase()))
                        matchingMovies.add(movie);
                    break;
                case "cast":
                    if (movie.getCast().toLowerCase().contains(userSearchInput.toLowerCase()))
                        matchingMovies.add(movie);
                    break;
                case "category":
                    if (movie.getGenre().toLowerCase().contains(userSearchInput.toLowerCase()))
                        matchingMovies.add(movie);
                    break;
                }
            }
        return matchingMovies;
    }
}
