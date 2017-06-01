package pl.my.library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

/**
 * Created by Admin on 2017-04-08.
 */
public class TopMenuButtonsController {

    public static final String FXML_STATISTICS_FXML = "/fxml/Statistics.fxml";
    public static final String FXML_LIBRARY_FXML = "/fxml/Library.fxml";
    public static final String FXML_LISTBOOKS_FXML = "/fxml/ListBooks.fxml";
    public static final String FXML_ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    public static final String FXML_ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String FXML_ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void openLibrary(ActionEvent actionEvent) {
        mainController.setCenter(FXML_LIBRARY_FXML);
    }


    @FXML
    public void openListBooks(ActionEvent actionEvent) {
        mainController.setCenter(FXML_LISTBOOKS_FXML);
    }


    @FXML
    public void openStatistics(ActionEvent actionEvent) {
        mainController.setCenter(FXML_STATISTICS_FXML);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void resetToggleButtons() {
        if(toggleButtons.getSelectedToggle()!=null)
        {
            toggleButtons.getSelectedToggle().setSelected(false);
        }
    }

    @FXML
    public void addBook(ActionEvent actionEvent) {

        resetToggleButtons();

        mainController.setCenter(FXML_ADD_BOOK_FXML);

    }

    @FXML
    public void addCategory(ActionEvent actionEvent) {

        resetToggleButtons();

        mainController.setCenter(FXML_ADD_CATEGORY_FXML);
    }

    @FXML
    public void addAuthor(ActionEvent actionEvent) {

        resetToggleButtons();

        mainController.setCenter(FXML_ADD_AUTHOR_FXML);
    }
}
