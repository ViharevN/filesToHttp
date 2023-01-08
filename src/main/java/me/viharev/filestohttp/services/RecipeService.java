package me.viharev.filestohttp.services;

import me.viharev.filestohttp.models.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface RecipeService {
    void addRecipe(Recipe recipe);


    Recipe getRecipeById(Long id);

    Recipe editRecipe(Long id, Recipe recipe);

    void deleteRecipeById(Long id);

    Map<Long, Recipe> getAllRecipes();

}
