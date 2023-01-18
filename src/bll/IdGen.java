package bll;

import dal.CatMovDAO;

public class IdGen {

    private static int movieId = 0;


    private static int categoryId = 0;
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
