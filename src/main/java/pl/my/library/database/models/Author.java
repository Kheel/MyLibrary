package pl.my.library.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Admin on 2017-04-26.
 */
@DatabaseTable(tableName = "AUTHOR")
public class Author implements BaseModel {

    public Author() {
    }

    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String nameAndSurname;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public ForeignCollection<Book> getBooks() {
        return books;
    }

    public void setBooks(ForeignCollection<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", nameAndSurname='" + nameAndSurname + '\'' +
                ", books=" + books +
                '}';
    }
}
