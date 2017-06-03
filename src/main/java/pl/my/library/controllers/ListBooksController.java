package pl.my.library.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.my.library.modelFX.AuthorFX;
import pl.my.library.modelFX.BookFX;
import pl.my.library.modelFX.CategoryFX;
import pl.my.library.modelFX.ListBooksModel;
import pl.my.library.utils.DialogsUtils;
import pl.my.library.utils.exceptions.ApplicationException;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Created by Admin on 2017-06-03.
 */
public class ListBooksController {

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

        this.booksTableView.setItems(this.listBooksModel.getBookFXObservableList());
        this.titleColumn.setCellValueFactory(e -> e.getValue().titleProperty());
        this.descColumn.setCellValueFactory(e -> e.getValue().descriptionProperty());
        this.ratingColumn.setCellValueFactory(e -> e.getValue().ratingProperty());
        this.isbnColumn.setCellValueFactory(e -> e.getValue().isbnProperty());
        this.releaseColumn.setCellValueFactory(e -> e.getValue().relaseDateProperty());
        this.titleColumn.setCellValueFactory(e -> e.getValue().titleProperty());
        this.authorColumn.setCellValueFactory(e -> e.getValue().authorFXProperty());
        this.categoryColumn.setCellValueFactory(e -> e.getValue().categoryFXProperty());

    }



}
