package me.viharev.filestohttp.services;

import me.viharev.filestohttp.models.Ingredient;

import java.util.Map;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredientById(Long id);

    Ingredient editIngredientById(Long id, Ingredient ingredient);

    void deleteIngredientById(Long id);

    Map<Long, Ingredient> getAllIngredients();
}
