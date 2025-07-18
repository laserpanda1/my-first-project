package sia.tacocloud;

import sia.tacocloud.Ingredient;
import sia.tacocloud.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sia.tacocloud.Ingredient.Type;

@Component
public class IngredientByIdConverter implements Converter<String,Ingredient> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}