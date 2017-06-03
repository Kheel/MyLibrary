package pl.my.library.modelFX;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Created by Admin on 2017-06-02.
 */
public class BookFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<CategoryFX> categoryFX = new SimpleObjectProperty<>();
    private ObjectProperty<AuthorFX> authorFX = new SimpleObjectProperty<>();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> relaseDate = new SimpleObjectProperty<>();
    private SimpleStringProperty isbn = new SimpleStringProperty();
    private IntegerProperty rating = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty(LocalDate.now());

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public CategoryFX getCategoryFX() {
        return categoryFX.get();
    }

    public ObjectProperty<CategoryFX> categoryFXProperty() {
        return categoryFX;
    }

    public void setCategoryFX(CategoryFX categoryFX) {
        this.categoryFX.set(categoryFX);
    }

    public AuthorFX getAuthorFX() {
        return authorFX.get();
    }

    public ObjectProperty<AuthorFX> authorFXProperty() {
        return authorFX;
    }

    public void setAuthorFX(AuthorFX authorFX) {
        this.authorFX.set(authorFX);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public SimpleStringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public int getRating() {
        return rating.get();
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public LocalDate getRelaseDate() {
        return relaseDate.get();
    }

    public ObjectProperty<LocalDate> relaseDateProperty() {
        return relaseDate;
    }

    public void setRelaseDate(LocalDate relaseDate) {
        this.relaseDate.set(relaseDate);
    }

    public LocalDate getAddedDate() {
        return addedDate.get();
    }

    public ObjectProperty<LocalDate> addedDateProperty() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate.set(addedDate);
    }

    @Override
    public String toString() {
        return "BookFX{" +
                "id=" + id +
                ", categoryFX=" + categoryFX +
                ", authorFX=" + authorFX +
                ", title=" + title +
                ", description=" + description +
                ", relaseDate=" + relaseDate +
                ", isbn=" + isbn +
                ", rating=" + rating +
                ", addedDate=" + addedDate +
                '}';
    }
}
