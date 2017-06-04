package pl.my.library.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.my.library.modelFX.AuthorFX;
import pl.my.library.modelFX.BookFX;
import pl.my.library.modelFX.CategoryFX;
import pl.my.library.modelFX.ListBooksModel;
import pl.my.library.utils.DialogsUtils;
import pl.my.library.utils.exceptions.ApplicationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

/**
 * Created by Admin on 2017-06-03.
 */
public class ListBooksController {

    @FXML
    private ComboBox categoryComboBox;

    @FXML
    private ComboBox authorComboBox;

    @FXML
    private TableView<BookFX> booksTableView;

    @FXML
    private TableColumn<BookFX,String> titleColumn;

    @FXML
    private TableColumn<BookFX,String> descColumn;

    @FXML
    private TableColumn<BookFX,AuthorFX> authorColumn;

    @FXML
    private TableColumn<BookFX,CategoryFX> categoryColumn;

    @FXML
    private TableColumn<BookFX,Number> ratingColumn;

    @FXML
    private TableColumn<BookFX,String> isbnColumn;

    @FXML
    private TableColumn<BookFX,LocalDate> releaseColumn;

    @FXML
    private TableColumn<BookFX, BookFX> deleteColumn;

    private ListBooksModel listBooksModel;

    //@FXML
    //private TableColumn<BookFX,Double> priceColumn;



    @FXML
    void initialize(){
        this.listBooksModel = new ListBooksModel();
        try {
            this.listBooksModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.categoryComboBox.setItems(this.listBooksModel.getCategoryFXObservableList());
        this.authorComboBox.setItems(this.listBooksModel.getAuthorFXObservableList());

        this.listBooksModel.categoryFXObjectPropertyProperty().bind(this.categoryComboBox.valueProperty());
        this.listBooksModel.authorFXObjectPropertyProperty().bind(this.authorComboBox.valueProperty());

        this.booksTableView.setItems(this.listBooksModel.getBookFXObservableList());
        this.titleColumn.setCellValueFactory(e -> e.getValue().titleProperty());
        this.descColumn.setCellValueFactory(e -> e.getValue().descriptionProperty());
        this.ratingColumn.setCellValueFactory(e -> e.getValue().ratingProperty());
        this.isbnColumn.setCellValueFactory(e -> e.getValue().isbnProperty());
        this.releaseColumn.setCellValueFactory(e -> e.getValue().relaseDateProperty());
        this.titleColumn.setCellValueFactory(e -> e.getValue().titleProperty());
        this.authorColumn.setCellValueFactory(e -> e.getValue().authorFXProperty());
        this.categoryColumn.setCellValueFactory(e -> e.getValue().categoryFXProperty());
        this.deleteColumn.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue()));

        this.deleteColumn.setCellFactory(param -> new TableCell<BookFX, BookFX>(){
            Button button = createDeleteButton();

            @Override
            protected void updateItem(BookFX item, boolean empty) {
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty){
                setGraphic(button);
                button.setOnAction(event -> {
                    try {
                        listBooksModel.deleteBook(item);
                    } catch (ApplicationException e) {
                        DialogsUtils.errorDialog(e.getMessage());
                    }
                });
                }
            }
        });

    }

    private Button createDeleteButton(){
        Button button = new Button();
        Image image = new Image(this.getClass().getResource("/icons/delete-button.png").toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }

    public void filterOnActionComboBox() {
        this.listBooksModel.filterBooksList();

    }

    public void clearCategoryComboBox () {
        this.categoryComboBox.getSelectionModel().clearSelection();
    }

    public void clearAuthorComboBox () {
        this.authorComboBox.getSelectionModel().clearSelection();
    }


}
