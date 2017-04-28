package pl.my.library.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import pl.my.library.database.dao.CategoryDao;
import pl.my.library.database.dbutils.DbManager;
import pl.my.library.database.models.Category;
import pl.my.library.database.dao.CommonDao;
import pl.my.library.utils.converters.ConverterCategory;
import pl.my.library.utils.exceptions.ApplicationException;

import java.util.List;
import java.util.Observable;

/**
 * Created by Admin on 2017-04-26.
 */
public class CategoryModel {

    private ObservableList<CategoryFX> categoryList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFX> category = new SimpleObjectProperty<>();
    private TreeItem<String> root = new TreeItem<>();

    public void init() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        List<Category> categories = categoryDao.queryForAll(Category.class);
        initCategoryList(categories);
        initRoot(categories);
        DbManager.closeConnectionSource();
    }

    private void initRoot(List<Category> categories) {
        this.root.getChildren().clear();
        categories.forEach(c->{
            TreeItem<String> categoryItem = new TreeItem<>(c.getName());
            c.getBooks().forEach(b->{
                categoryItem.getChildren().add(new TreeItem<>(b.getTitle()));
            });
            root.getChildren().add(categoryItem);
        });
    }

    private void initCategoryList(List<Category> categories) {
        this.categoryList.clear();
        categories.forEach(c->{
            CategoryFX categoryFX = ConverterCategory.convertToCategoryFX(c);
            this.categoryList.add(categoryFX);
        });
    }

    public void deleteCategoryById() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        categoryDao.deleteById(Category.class, category.getValue().getId());
        DbManager.closeConnectionSource();
        init();
    }

    public void saveCategoryInDataBase (String name) throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.createOrUpdate(category);
        DbManager.closeConnectionSource();
        init();
    }

    public void updateCategoryInDataBase() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category tempCategory = categoryDao.findById(Category.class , getCategory().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.createOrUpdate(tempCategory);
        DbManager.closeConnectionSource();
        init();
    }

    public CategoryFX getCategory() {
        return category.get();
    }

    public void setCategory(CategoryFX category) {
        this.category.set(category);
    }

    public ObservableList<CategoryFX> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<CategoryFX> categoryList) {
        this.categoryList = categoryList;
    }

    public ObjectProperty<CategoryFX> categoryProperty() {
        return category;
    }

    public TreeItem<String> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }
}
