package pl.my.library.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.my.library.database.dao.AuthorDao;
import pl.my.library.database.dao.CategoryDao;
import pl.my.library.database.dbutils.DbManager;
import pl.my.library.database.models.Author;
import pl.my.library.database.models.Category;
import pl.my.library.utils.converters.ConverterAuthor;
import pl.my.library.utils.converters.ConverterCategory;
import pl.my.library.utils.exceptions.ApplicationException;

import java.util.List;

/**
 * Created by Admin on 2017-06-02.
 */
public class BookModel {

    private ObjectProperty<BookFX> bookFXObjectProperty = new SimpleObjectProperty<>(new BookFX());
    private ObservableList<CategoryFX> categoryFXObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFX> authorFXObservableList = FXCollections.observableArrayList();
    
    public void init() throws ApplicationException {
    initAuthorList();
    initCategoryList();
    
    }

    private void initCategoryList() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao((DbManager.getConnectionSource()));
        List<Category> categoryList = categoryDao.queryForAll(Category.class);
        categoryFXObservableList.clear();
        categoryList.forEach(c->{
            CategoryFX categoryFX = ConverterCategory.convertToCategoryFX(c);
            categoryFXObservableList.add(categoryFX);
        });
        DbManager.closeConnectionSource();
    }

    private void initAuthorList() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao((DbManager.getConnectionSource()));
        List<Author> authorList = authorDao.queryForAll(Author.class);
        this.authorFXObservableList.clear();
        authorList.forEach(c->{
            AuthorFX authorFX = ConverterAuthor.convertToAuthorFX(c);
            authorFXObservableList.add(authorFX);
        });
        DbManager.closeConnectionSource();
    }

    public ObservableList<CategoryFX> getCategoryFXObservableList() {
        return categoryFXObservableList;
    }

    public void setCategoryFXObservableList(ObservableList<CategoryFX> categoryFXObservableList) {
        this.categoryFXObservableList = categoryFXObservableList;
    }

    public ObservableList<AuthorFX> getAuthorFXObservableList() {
        return authorFXObservableList;
    }

    public void setAuthorFXObservableList(ObservableList<AuthorFX> authorFXObservableList) {
        this.authorFXObservableList = authorFXObservableList;
    }

    public BookFX getBookFXObjectProperty() {
        return bookFXObjectProperty.get();
    }

    public ObjectProperty<BookFX> bookFXObjectPropertyProperty() {
        return bookFXObjectProperty;
    }

    public void setBookFXObjectProperty(BookFX bookFXObjectProperty) {
        this.bookFXObjectProperty.set(bookFXObjectProperty);
    }
}
