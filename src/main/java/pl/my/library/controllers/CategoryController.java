package pl.my.library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.my.library.database.dbutils.DbManager;
import pl.my.library.modelFX.CategoryFX;
import pl.my.library.modelFX.CategoryModel;

/**
 * Created by Admin on 2017-04-26.
 */
public class CategoryController {

    @FXML
    public Button deleteCategoryButton;
    @FXML
    private TextField categoryTextField;
    @FXML
    private Button addCategoryButton;
    @FXML
    private ComboBox<CategoryFX> categoryComboBox;

    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        this.categoryModel.init();
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        initBindings();
    }

    private void initBindings() {
        this.addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }

    public void addCategoryOnAction(ActionEvent actionEvent) {
        categoryModel.saveCategoryInDataBase(categoryTextField.getText());
        categoryTextField.clear();
    }

    public void onActionComboBox(){
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }

    public void deleteCategoryOnAction(ActionEvent actionEvent) {
        this.categoryModel.deleteCategoryById();
    }
}
