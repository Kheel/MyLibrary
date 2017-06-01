package pl.my.library.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.my.library.database.dao.AuthorDao;
import pl.my.library.database.dbutils.DbManager;
import pl.my.library.database.models.Author;
import pl.my.library.utils.converters.ConverterAuthor;
import pl.my.library.utils.exceptions.ApplicationException;

import java.util.List;


/**
 * Created by Admin on 2017-05-31.
 */
public class AuthorModel {

    private ObjectProperty<AuthorFX> authorFXObjectProperty = new SimpleObjectProperty<>(new AuthorFX());

    private ObservableList<AuthorFX> authorFXObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());
        List<Author> authorList = authorDao.queryForAll(Author.class);
        this.authorFXObservableList.clear();
        authorList.forEach(author ->{
            AuthorFX authorFX = ConverterAuthor.convertToAuthorFX(author);
            this.getAuthorFXObservableList().add(authorFX);
        } );
        DbManager.closeConnectionSource();
    }


    public void saveAuthorInDataBase() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());
        Author author = ConverterAuthor.convertAuthorFXToAuthor(this.getAuthorFXObjectProperty());
        authorDao.createOrUpdate(author);
        DbManager.closeConnectionSource();
        this.init();
    }


    public AuthorFX getAuthorFXObjectProperty() {
        return authorFXObjectProperty.get();
    }

    public ObjectProperty<AuthorFX> authorFXObjectProperty() {
        return authorFXObjectProperty;
    }

    public void setAuthorFXObjectProperty(AuthorFX authorFXObjectProperty) {
        this.authorFXObjectProperty.set(authorFXObjectProperty);
    }

    public ObservableList<AuthorFX> getAuthorFXObservableList() {
        return authorFXObservableList;
    }

    public void setAuthorFXObservableList(ObservableList<AuthorFX> authorFXObservableList) {
        this.authorFXObservableList = authorFXObservableList;
    }
}
