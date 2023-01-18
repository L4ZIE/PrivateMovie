package dal;

import be.Category;
import be.Movie;
import dal.database.DatabaseConnector;
import dal.database.SqlServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class CategoryDAO {

    public ObservableList<String>getCategoryName() {
        DatabaseConnector dbConnector = new DatabaseConnector();
        //Opening connection
        try (Connection connection = dbConnector.getConnection()) {
            ObservableList<String> allNames = FXCollections.observableArrayList();

            String sqlGetCategories = "SELECT * FROM Category;";

            Statement statement = connection.createStatement();


            //Executing sql statement to get all movies
            if (statement.execute(sqlGetCategories)) {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();

                //Saving result data as variables
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");

                    //Creating category object


                    //Adding movie object to list of all movies
                    allNames.add(name);
                }
            }
            return allNames;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public static ObservableList<Category> getAllCategories() throws SQLException, SqlServerException {
        //Creating dbConnector instance
        DatabaseConnector dbConnector = new DatabaseConnector();
        //Opening connection
        try (Connection connection = dbConnector.getConnection()) {
            ObservableList<Category> allCategories = FXCollections.observableArrayList();

            String sqlGetCategories = "SELECT * FROM Category;";

            Statement statement = connection.createStatement();


            //Executing sql statement to get all movies
            if (statement.execute(sqlGetCategories)) {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();

                //Saving result data as variables
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");

                    //Creating category object
                    Category category = new Category(name);

                    //Adding movie object to list of all movies
                    allCategories.add(category);
                }
            }
            return allCategories;
        }
    }
    public static void postNewCategory(Category category) throws SQLException{
        //Creating dbConnector instance
        DatabaseConnector dbConnector = new DatabaseConnector();
        try(Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO Category VALUES ("+category.getId()+",'"+category.getName()+"')";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
            }
        }
    }
    public static void removeCategory(int index) throws SQLException{
        //Creating dbConnector instance
        DatabaseConnector dbConnector = new DatabaseConnector();
        try(Connection connection = dbConnector.getConnection()) {
            String sql = "Delete FROM Category where ID=" + index + ';';
            System.out.println(sql);
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Removed correctly");
            }
        }
    }
}
