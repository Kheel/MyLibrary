package pl.my.library.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.my.library.database.dao.AuthorDao;
import pl.my.library.database.dao.BookDao;
import pl.my.library.database.dao.CategoryDao;
import pl.my.library.database.models.Author;
import pl.my.library.database.models.Book;
import pl.my.library.database.models.Category;
import pl.my.library.utils.converters.ConverterAuthor;
import pl.my.library.utils.converters.ConverterBook;
import pl.my.library.utils.converters.ConverterCategory;
import pl.my.library.utils.exceptions.ApplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Admin on 2017-06-03.
 */
public class ListBooksModel {

    private ObservableList<BookFX> bookFXObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFX> authorFXObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFX> categoryFXObservableList = FXCollections.observableArrayList();

    private ObjectProperty<AuthorFX> authorFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFX> categoryFXObjectProperty = new SimpleObjectProperty<>();

    private List<BookFX> bookFXList = new ArrayList<>();


    public void init() throws ApplicationException {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryForAll(Book.class);
        bookFXList.clear();
        books.forEach(book ->{
            this.bookFXList.add(ConverterBook.convertToBookFX(book));
        });
        this.bookFXObservableList.setAll(bookFXList);
        
        initAuthors();
        
        initCategory();
    }

    public void filterBooksList() {
        if(getAuthorFXObjectProperty() !=null && getCategoryFXObjectProperty() !=null) {
            filterPredicate(predicateAuthor().and(predicateCategory()));
        }
        else if(getCategoryFXObjectProperty() !=null) {
            filterPredicate(predicateCategory());
        }
        else if(getAuthorFXObjectProperty() !=null){
            filterPredicate(predicateAuthor());
        }
        else {
            this.bookFXObservableList.setAll(this.bookFXList);
        }
    }

    private void initCategory() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.queryForAll(Category.class);
        this.categoryFXObservableList.clear();
        categoryList.forEach(author ->{
            CategoryFX categoryFX = ConverterCategory.convertToCategoryFX(author);
            this.categoryFXObservableList.add(categoryFX);
        } );
    }

    private Predicate<BookFX> predicateCategory() {
        Predicate<BookFX> predicate = bookFX -> bookFX.getCategoryFX().getId() == getCategoryFXObjectProperty().getId();
        return predicate;
    }

    private Predicate<BookFX> predicateAuthor() {
        Predicate<BookFX> predicate = bookFX -> bookFX.getAuthorFX().getId() == getAuthorFXObjectProperty().getId();
        return predicate;
    }

    private void filterPredicate(Predicate<BookFX> predicate) {
        List<BookFX> newList = bookFXList.stream().filter(predicate).collect(Collectors.toList());
        this.bookFXObservableList.setAll(newList);

    }


    private void initAuthors() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        this.authorFXObservableList.clear();
        authorList.forEach(author ->{
            AuthorFX authorFX = ConverterAuthor.convertToAuthorFX(author);
            this.authorFXObservableList.add(authorFX);
        } );
    }

    public ObservableList<BookFX> getBookFXObservableList() {
        return bookFXObservableList;
    }

    public void setBookFXObservableList(ObservableList<BookFX> bookFXObservableList) {
        this.bookFXObservableList = bookFXObservableList;
    }

    public ObservableList<AuthorFX> getAuthorFXObservableList() {
        return authorFXObservableList;
    }

    public void setAuthorFXObservableList(ObservableList<AuthorFX> authorFXObservableList) {
        this.authorFXObservableList = authorFXObservableList;
    }

    public ObservableList<CategoryFX> getCategoryFXObservableList() {
        return categoryFXObservableList;
    }

    public void setCategoryFXObservableList(ObservableList<CategoryFX> categoryFXObservableList) {
        this.categoryFXObservableList = categoryFXObservableList;
    }

    public AuthorFX getAuthorFXObjectProperty() {
        return authorFXObjectProperty.get();
    }

    public ObjectProperty<AuthorFX> authorFXObjectPropertyProperty() {
        return authorFXObjectProperty;
    }

    public void setAuthorFXObjectProperty(AuthorFX authorFXObjectProperty) {
        this.authorFXObjectProperty.set(authorFXObjectProperty);
    }

    public CategoryFX getCategoryFXObjectProperty() {
        return categoryFXObjectProperty.get();
    }

    public ObjectProperty<CategoryFX> categoryFXObjectPropertyProperty() {
        return categoryFXObjectProperty;
    }

    public void setCategoryFXObjectProperty(CategoryFX categoryFXObjectProperty) {
        this.categoryFXObjectProperty.set(categoryFXObjectProperty);
    }

    public void deleteBook(BookFX bookFX) throws ApplicationException {
        BookDao bookDao= new BookDao();
        bookDao.deleteById(Book.class, bookFX.getId());
        init();
    }
}
