package gui.controllers;

import dal.CategoryDAO;
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

public class RemoveCategoryController implements Initializable {
    @FXML
    public Button removeButton, closeButton;
    @FXML
    public ComboBox categoriesBox;

    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriesBox.setItems(FXCollections.observableArrayList(categoryDAO.getCategoryName()));
        try {
            categoriesBox.setVisibleRowCount(categoryDAO.getAllCategories().size());
        } catch (SQLException | SqlServerException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void removeCategory(ActionEvent actionEvent) throws SQLException {
        int selectedIndex = categoriesBox.getSelectionModel().getSelectedIndex()+1;
        CategoryDAO.removeCategory(selectedIndex);
        categoriesBox.setItems(FXCollections.observableArrayList(categoryDAO.getCategoryName()));
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
