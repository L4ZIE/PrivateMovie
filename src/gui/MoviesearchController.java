package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MoviesearchController {

    @FXML
    private TableColumn<?, ?> castTableColumn;

    @FXML
    private TableColumn<?, ?> descriptionTableColumn;

    @FXML
    private TableColumn<?, ?> genreTableColumn;

    @FXML
    private TableColumn<?, ?> imdbTableColumn;

    @FXML
    private TextField keywordsTextField;

    @FXML
    private TableView<?> movieTableView;

    @FXML
    private TableColumn<?, ?> nameTableColumn;

}