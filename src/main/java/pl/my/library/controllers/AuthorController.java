package pl.my.library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.my.library.modelFX.AuthorFX;
import pl.my.library.modelFX.AuthorModel;
import pl.my.library.utils.DialogsUtils;
import pl.my.library.utils.exceptions.ApplicationException;

/**
 * Created by Admin on 2017-05-31.
 */
public class AuthorController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Button addButton;

    @FXML
    private TableView<AuthorFX> authorTableView;

    @FXML
    private TableColumn<AuthorFX, String> nameColumn;

    @FXML
    private TableColumn<AuthorFX, String> surnameColumn;

    @FXML
    private MenuItem deleteMenuItem;

    private AuthorModel authorModel;

    public void initialize() {

        this.authorModel = new AuthorModel();
        try {
            this.authorModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

        bindings();
        bindingsTableView();

    }

    private void bindingsTableView() {
        this.authorTableView.setItems(this.authorModel.getAuthorFXObservableList());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());

        this.nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        this.authorTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        this.authorModel.setAuthorFXObjectPropertyEdit(newValue);
        } );
    }

    private void bindings() {
        this.authorModel.authorFXObjectProperty().get().nameProperty().bind(this.nameTextField.textProperty());
        this.authorModel.authorFXObjectProperty().get().surnameProperty().bind(this.surnameTextField.textProperty());
        this.addButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty().or(this.surnameTextField.textProperty().isEmpty()));
        this.deleteMenuItem.disableProperty().bind(this.authorTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    public void addAuthorOnAction() {
        try {
            this.authorModel.saveAuthorInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.nameTextField.clear();
        this.surnameTextField.clear();
    }

    public void onEditCommitName(TableColumn.CellEditEvent<AuthorFX, String> authorFXStringCellEditEvent) {
        this.authorModel.getAuthorFXObjectPropertyEdit().setName(authorFXStringCellEditEvent.getNewValue());
        updateInDatabase();
    }


    public void onEditCommitSurname(TableColumn.CellEditEvent<AuthorFX, String> authorFXStringCellEditEvent) {
        this.authorModel.getAuthorFXObjectPropertyEdit().setSurname(authorFXStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    private void updateInDatabase() {
        try {
            this.authorModel.saveAuthorEditInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public void deleteAuthorOnAction(ActionEvent actionEvent) {
        try {
            this.authorModel.deleteAuthorInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }
}

