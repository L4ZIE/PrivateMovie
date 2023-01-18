package dal;

import be.CatMov;
import be.Category;
import be.Movie;
import dal.database.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CatMovDAO {



    public static ObservableList<CatMov> getCatMov() {
        DatabaseConnector dbConnector = new DatabaseConnector();
        //Opening connection
        try (Connection connection = dbConnector.getConnection()) {
            ObservableList<CatMov> allCatMov = FXCollections.observableArrayList();

            String sqlGetCategories = "SELECT * FROM CatMov;";

            Statement statement = connection.createStatement();


            //Executing sql statement to get all movies
            if (statement.execute(sqlGetCategories)) {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();

                //Saving result data as variables
                while (resultSet.next()) {
                    int catId = resultSet.getInt("CategoryID");
                    int movId = resultSet.getInt("MovieID");


                    //Creating category object
                    CatMov catMov=new CatMov(catId,movId);


                    //Adding movie object to list of all movies
                    allCatMov.add(catMov);
                }
            }
            return allCatMov;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeCatMov(int catID,int movId) throws SQLException{
        //Creating dbConnector instance
        DatabaseConnector dbConnector = new DatabaseConnector();
        try(Connection connection = dbConnector.getConnection()) {
            String sql = "Delete FROM CatMov where CategoryID=" + catID + "and MovieID="+movId+';';
            System.out.println(sql);
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Removed correctly");
            }
        }
    }

    public static void postNewCatMov(CatMov catMov) throws SQLException{
        //Creating dbConnector instance
        DatabaseConnector dbConnector = new DatabaseConnector();
        try(Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO CatMov VALUES ("+catMov.getCatMovID()+ " ,"+ catMov.getCatID()+",'"+catMov.getMovID()+"')";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
            }
        }
    }
}
