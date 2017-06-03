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

    public static BookFX convertToBookFX (Book book) {
        BookFX bookFX= new BookFX();
        bookFX.setId(book.getId());
        bookFX.setTitle(book.getTitle());
        bookFX.setDescription(book.getDescription());
        bookFX.setRating(book.getRating());
        bookFX.setIsbn(book.getIsbn());
        bookFX.setRelaseDate(Utils.convertToLocalDate(book.getDateRelease()));
        bookFX.setAuthorFX(ConverterAuthor.convertToAuthorFX(book.getAuthor()));
        bookFX.setCategoryFX(ConverterCategory.convertToCategoryFX(book.getCategory()));

        return bookFX;
    }
}
