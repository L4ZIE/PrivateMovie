package gui.controllers;

import be.Category;
import dal.CategoryDAO;
import dal.database.SqlServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCategoryController implements Initializable {

    @FXML
    private TextField categoryName;

    @FXML
    private Button closeButton;

    @FXML
    private Button sumbitButton;

    CategoryDAO categoryDAO = new CategoryDAO();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sumbitCategory(ActionEvent actionEvent) throws SQLException, SqlServerException {

        Category category;
        if (categoryDAO.getAllCategories().size()>0) {
             category = new Category(categoryDAO.getAllCategories().get(categoryDAO.getAllCategories().size() - 1).getId() + 1, categoryName.getText());
        }else {
             category = new Category( 1, categoryName.getText());

        }

        categoryDAO.postNewCategory(category);
        Stage stage = (Stage) sumbitButton.getScene().getWindow();
        stage.close();
    }
    public void close(){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
