package pl.my.library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by Admin on 2017-04-08.
 */
public class TopMenuButtonsController {

    private MainController mainController;

    @FXML
    public void openLibrary(ActionEvent actionEvent) {
        System.out.println("openLibrary");
    }


    @FXML
    public void openListBooks(ActionEvent actionEvent) {
        System.out.println("openListBooks");
    }


    @FXML
    public void openStatistics(ActionEvent actionEvent) {
        System.out.println("openStatistics");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
