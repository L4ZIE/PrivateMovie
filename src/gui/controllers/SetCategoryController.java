package gui.controllers;

import be.CatMov;
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

    public void submitBtn(ActionEvent actionEvent) throws SQLException {

        int catIndex = categoriesBox.getSelectionModel().getSelectedIndex()+1;
        int movIndex = MoviesBox.getSelectionModel().getSelectedIndex()+1;

        CatMov catMov=new CatMov(catIndex,movIndex);

        CatMovDAO.postNewCatMov(catMov);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
