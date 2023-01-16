package be;

import dal.MovieDAO;
import dal.database.SqlServerException;
import javafx.collections.ObservableList;


import java.sql.SQLException;

//Class for sending data from DAO to GUI.
public class DataRoute {
    MovieDAO movieDAO = new MovieDAO();

    //Function that sends all movies as a list from DAO to GUI.
    public ObservableList<Movie> routeMovie() throws SQLException, SqlServerException {
        return movieDAO.getAllMovies();
    }
}
