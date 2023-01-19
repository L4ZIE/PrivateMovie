package bll;

import be.Category;
import dal.CatMovDAO;
import dal.CategoryDAO;
import dal.database.SqlServerException;

import java.sql.SQLException;

public class IdGen {

    private static int movieId = 0;


    private static int categoryId;

    static {
        try {

            categoryId = CategoryDAO.getAllCategories().size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (SqlServerException e) {
            throw new RuntimeException(e);
        }
    }

    private static int CatMovId = CatMovDAO.getCatMov().size();

    public static int createCatMovId() {
        CatMovId++;
        return CatMovId;
    }



    public static int createMovieId() {
        movieId++;
        return movieId;
    }

    public static int createCategoryId() {
        categoryId++;
        return categoryId;
    }

}
