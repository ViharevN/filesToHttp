package me.viharev.filestohttp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.viharev.filestohttp.services.RecipeService;
import me.viharev.filestohttp.models.Recipe;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Контроллер рецептов",
        description = "CRUD-операции с рецептами")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    @Operation(
            summary = "Добавление рецепта с помощью POST",
            description = "добавляем Рецепт"
    )
    @ApiResponses(
            value = @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            )
    )
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return recipe;
    }

    @GetMapping("/get/by/{id}")
    @Operation(
            summary = "Поиск рецепта",
            description = "Ищем рецепт по его Id"
    )
    @ApiResponses(
            value = @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PutMapping("/edit/{id}")
    @Operation(
            summary = "Изменение рецепта",
            description = "Меняем рецепт по его Id"
    )
    @ApiResponses(
            value = @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт изменен",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    public ResponseEntity<Recipe> editRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        recipeService.editRecipe(id, recipe);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Удаление рецепта",
            description = "Удаляем рецепт по его Id"
    )
    @ApiResponses(
            value = @ApiResponse(
                    responseCode = "200",
                    description = "Удаление рецепта успешно"
            )
    )
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Вывод рецептов",
            description = "Карта всех рецептов"
    )
    @ApiResponses(
            value = @ApiResponse(
                    responseCode = "200",
                    description = "Успешный вывод списка рецептов"
            )
    )
    public Map<Long, Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

}
