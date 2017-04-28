package pl.my.library.utils.converters;

import pl.my.library.database.models.Category;
import pl.my.library.modelFX.CategoryFX;

/**
 * Created by Admin on 2017-04-28.
 */
public class ConverterCategory {

    public static CategoryFX convertToCategoryFX(Category category){
        CategoryFX categoryFX = new CategoryFX();
        categoryFX.setId(category.getId());
        categoryFX.setName(category.getName());
        return categoryFX;
    }
}
