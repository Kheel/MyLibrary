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

    public void bindings() {
        this.categoryComboBox.setItems(this.bookModel.getCategoryFXObservableList());
        this.authorComboBox.setItems(this.bookModel.getAuthorFXObservableList());
        
        this.authorComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().authorFXProperty());
        this.categoryComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().categoryFXProperty());
        this.titleTextField.textProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().titleProperty());
        this.descTextArea.textProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().descriptionProperty());
        this.ratingSlider.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().ratingProperty());
        this.isbnTextField.textProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().isbnProperty());
        this.dateRelasePicker.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().relaseDateProperty());

    }

    public void addBookOnAction() {
        try {
            this.bookModel.saveBookInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public BookModel getBookModel() {
        return bookModel;
    }
}
