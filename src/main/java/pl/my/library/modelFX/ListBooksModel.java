package pl.my.library.modelFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.my.library.database.dao.BookDao;
import pl.my.library.database.models.Book;
import pl.my.library.utils.converters.ConverterBook;
import pl.my.library.utils.exceptions.ApplicationException;

import java.util.List;

/**
 * Created by Admin on 2017-06-03.
 */
public class ListBooksModel {

    private ObservableList<BookFX> bookFXObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryForAll(Book.class);
        books.forEach(book ->{
            this.bookFXObservableList.add(ConverterBook.convertToBookFX(book));
        });
    }

    public ObservableList<BookFX> getBookFXObservableList() {
        return bookFXObservableList;
    }
}
