package com.smartpantrymanager.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    long insertHarvestRecipe(Recipe recipe);

    @Insert
    void insertHarvestIngredients(List<RecipeIngredient> ingredients);

    @Query("SELECT * FROM recipes ORDER BY title ASC")
    List<Recipe> fetchAllHarvestRecipes();

    @Query("SELECT * FROM recipes WHERE id = :recipeId LIMIT 1")
    Recipe fetchHarvestRecipeById(int recipeId);

    @Query("SELECT * FROM recipe_ingredients WHERE recipeId = :recipeId")
    List<RecipeIngredient> fetchIngredientsForHarvestRecipe(int recipeId);

    @Query("SELECT COUNT(*) FROM recipes")
    int countHarvestRecipes();

    @Query("DELETE FROM recipes")
    void purgeAllHarvestRecipes();

    @Query("DELETE FROM recipe_ingredients")
    void purgeAllHarvestIngredients();
}
