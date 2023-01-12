import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MoviesearchController {

    @FXML
    private TableColumn<String, String> castTableColumn;

    @FXML
    private TableColumn<String, String> descriptionTableColumn;

    @FXML
    private TableColumn<String, String> genreTableColumn;

    @FXML
    private TableColumn<Double, Double> imdbTableColumn;

    @FXML
    private TextField keywordsTextField;

    @FXML
    private TableView<?> movieTableView;

    @FXML
    private TableColumn<String, String> nameTableColumn;

}