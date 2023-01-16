package dal;

import be.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*Data access object is a class in which the program transforms the data from the database into an instance of the
movie class, also contains function for sending the private instance of the movie to a method in the gui layer that
will display it inside the movie table.
 */
public class MovieDAO {
//Function that opens connection to DB, gets all movies and puts them in a list.
    public ObservableList<Movie> getAllMovies() throws SQLException, SqlServerException {
        //Creating dbConnector instance
        DatabaseConnector dbConnector = new DatabaseConnector();
        //Opening connection
        try (Connection connection = dbConnector.getConnection()) {
            ObservableList<Movie> allMovies = FXCollections.observableArrayList();

            String sqlGetMovies = "SELECT * FROM Movie;";

            Statement statement = connection.createStatement();

            //Executing sql statement to get all movies
            if(statement.execute(sqlGetMovies))
            {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();

                //Saving result data as variables
                while (resultSet.next()){
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("Name");
                    double rating = resultSet.getFloat("IMDB Rating");
                    String genre = resultSet.getString("Genre");
                    String path = resultSet.getString("Path");
                    String cast = resultSet.getString("Cast");
                    String description = resultSet.getString("Description");
                    Date lastView = resultSet.getDate("Last View");

                    //Creating movie object
                    Movie movie = new Movie(name, rating, genre, cast, description);

                    //Adding movie object to list of all movies
                    allMovies.add(movie);
                }
            }
            return allMovies;
        }
    }
}
