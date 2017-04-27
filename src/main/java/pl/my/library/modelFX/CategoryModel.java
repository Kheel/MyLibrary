package pl.my.library.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.my.library.database.dao.CategoryDao;
import pl.my.library.database.dbutils.DbManager;
import pl.my.library.database.models.Category;
import pl.my.library.database.dao.CommonDao;

import java.util.List;
import java.util.Observable;

/**
 * Created by Admin on 2017-04-26.
 */
public class CategoryModel {

    private ObservableList<CategoryFX> categoryList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFX> category = new SimpleObjectProperty<>();

    public void init() {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        List<Category> categories = categoryDao.queryForAll(Category.class);
        this.categoryList.clear();
        categories.forEach(c->{
            CategoryFX categoryFX = new CategoryFX();
            categoryFX.setId(c.getId());
            categoryFX.setName(c.getName());
            this.categoryList.add(categoryFX);
        });
        DbManager.closeConnectionSource();
    }

    public void deleteCategoryById(){
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        categoryDao.deleteById(Category.class, category.getValue().getId());
        DbManager.closeConnectionSource();
        init();
    }

    public void saveCategoryInDataBase (String name) {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.createOrUpdate(category);
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

}
