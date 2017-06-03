package pl.my.library.utils.converters;

import pl.my.library.database.models.Book;
import pl.my.library.modelFX.BookFX;
import pl.my.library.utils.Utils;

/**
 * Created by Admin on 2017-06-03.
 */
public class ConverterBook {

    public static Book convertToBook(BookFX bookFX){
        Book book = new Book();
        book.setId(bookFX.getId());
        book.setTitle(bookFX.getTitle());
        book.setDescription(bookFX.getDescription());
        book.setRating(bookFX.getRating());
        book.setIsbn(bookFX.getIsbn());
        book.setDateRelease(Utils.convertToDate(bookFX.getRelaseDate()));
        book.setAddedDate(Utils.convertToDate(bookFX.getAddedDate()));

        return book;
    }
}
