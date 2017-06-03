package pl.my.library.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.my.library.modelFX.AuthorFX;
import pl.my.library.modelFX.BookModel;
import pl.my.library.modelFX.CategoryFX;
import pl.my.library.utils.DialogsUtils;
import pl.my.library.utils.exceptions.ApplicationException;

/**
 * Created by Admin on 2017-06-02.
 */
public class BookController {

    @FXML
    private ComboBox<CategoryFX> categoryComboBox;
    @FXML
    private ComboBox<AuthorFX> authorComboBox;
    @FXML
    private Slider ratingSlider;
    @FXML
    private TextField isbnTextField;
    @FXML
    private TextArea descTextArea;
    @FXML
    private DatePicker dateRelasePicker;
    @FXML
    private TextField titleTextField;

    private BookModel bookModel;

    @FXML
    public void initialize() {

        this.bookModel = new BookModel();

        try {
            this.bookModel.init();

        } catch (ApplicationException e ) {
            DialogsUtils.errorDialog(e.getMessage());
        }

        bindings();

    }

    private void bindings() {
        this.categoryComboBox.setItems(this.bookModel.getCategoryFXObservableList());
        this.authorComboBox.setItems(this.bookModel.getAuthorFXObservableList());
        this.bookModel.getBookFXObjectProperty().categoryFXProperty().bind(this.categoryComboBox.valueProperty());
        this.bookModel.getBookFXObjectProperty().authorFXProperty().bind(this.authorComboBox.valueProperty());
        this.bookModel.getBookFXObjectProperty().ratingProperty().bind(this.ratingSlider.valueProperty());
        this.bookModel.getBookFXObjectProperty().isbnProperty().bind(this.isbnTextField.textProperty());
        this.bookModel.getBookFXObjectProperty().descriptionProperty().bind(this.descTextArea.textProperty());
        this.bookModel.getBookFXObjectProperty().titleProperty().bind(this.titleTextField.textProperty());
        this.bookModel.getBookFXObjectProperty().relaseDateProperty().bind(this.dateRelasePicker.valueProperty());
    }

    public void addBookOnAction() {
        try {
            this.bookModel.saveBookInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }
}
