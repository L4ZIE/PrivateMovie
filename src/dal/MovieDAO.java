package dal;

import be.Movie;

/*Data access object is a class in which the program transforms the data from the database into an instance of the
movie class, also contains function for sending the private instance of the movie to a method in the gui layer that
will display it inside the movie table.
 */
public class DataAccessObject {
    //"Static" keyword to be removed when testing is finished
    private static Movie movieInstance = new Movie("Placeholder", 2, "Thriller", "Dwayne Johnson",
            "Placeholder movie for testing.");

    //"Static" keyword to be removed when testing is finished
    public static Movie GetMovie(){
        return movieInstance;
    }
}
