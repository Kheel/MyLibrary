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

    public void addBook(ActionEvent actionEvent) {

        if(toggleButtons.getSelectedToggle()!=null)
        {
            toggleButtons.getSelectedToggle().setSelected(false);
        }

        mainController.setCenter("/fxml/AddBook.fxml");


    }
}
