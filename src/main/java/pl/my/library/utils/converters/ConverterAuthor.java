package pl.my.library.utils.converters;

import pl.my.library.database.models.Author;
import pl.my.library.modelFX.AuthorFX;

/**
 * Created by Admin on 2017-05-31.
 */
public class ConverterAuthor {

    public static Author convertAuthorFXToAuthor(AuthorFX authorFX){

        Author author = new Author();
        author.setId(authorFX.getId());
        author.setName(authorFX.getName());
        author.setSurname(authorFX.getSurname());
        return author;
    }

    public static AuthorFX convertToAuthorFX (Author author){
        AuthorFX authorFX = new AuthorFX();
        authorFX.setId(author.getId());
        authorFX.setName(author.getName());
        authorFX.setSurname(author.getSurname());
        return authorFX;
    }
}
