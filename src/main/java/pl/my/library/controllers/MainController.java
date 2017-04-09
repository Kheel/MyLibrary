package pl.my.library.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

/**
 * Created by Admin on 2017-04-06.
 */
public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    private void initialize(){
    topMenuButtonsController.setMainController(this);
    }

}
