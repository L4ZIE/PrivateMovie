package gui.controllers;

import be.CatMov;
import be.Category;
import be.Movie;
import dal.CatMovDAO;
import dal.CategoryDAO;
import dal.MovieDAO;
import dal.database.SqlServerException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SetCategoryController implements Initializable {
    @FXML
    public Button submitBtn, closeButton;
    @FXML
    public ComboBox categoriesBox, MoviesBox;

    CategoryDAO categoryDAO = new CategoryDAO();

    MovieDAO movieDAO = new MovieDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriesBox.setItems(FXCollections.observableArrayList(categoryDAO.getCategoryName()));
        try {
            categoriesBox.setVisibleRowCount(categoryDAO.getAllCategories().size());
        } catch (SQLException | SqlServerException e) {
            throw new RuntimeException(e);
        }
        MoviesBox.setItems(FXCollections.observableArrayList(movieDAO.getMovieName()));
        try {
            MoviesBox.setVisibleRowCount(movieDAO.getAllMovies().size());
        } catch (SQLException | SqlServerException e) {
            throw new RuntimeException(e);
        }
    }

    public void submitBtn(ActionEvent actionEvent) throws SQLException, SqlServerException {

        String catIndex = categoriesBox.getSelectionModel().getSelectedItem().toString();
        String movIndex = MoviesBox.getSelectionModel().getSelectedItem().toString();

        categoryDAO=new CategoryDAO();
        Category cat=categoryDAO.getAllCategories().filtered(category -> category.getName().equals(catIndex)).get(0);
        System.out.println(cat.getId()+""+cat.getName());
        Movie mov=movieDAO.getAllMovies().filtered(movie -> movie.getName().equals(movIndex)).get(0);
        System.out.println(mov.getId()+""+mov.getName());



        CatMov catMov;
        if (CatMovDAO.getCatMov().size()>0) {
             catMov = new CatMov(CatMovDAO.getCatMov().get(CatMovDAO.getCatMov().size() - 1).getCatMovID() + 1, cat.getId(), mov.getId());

        }else {
             catMov = new CatMov( 1, cat.getId(), mov.getId());

        }

        CatMovDAO.postNewCatMov(catMov);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
