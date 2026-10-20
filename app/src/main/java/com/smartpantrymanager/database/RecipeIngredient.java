package com.smartpantrymanager.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * RecipeIngredient - entity linking a Recipe to an ingredient requirement.
 */
@Entity(tableName = "recipe_ingredients",
        foreignKeys = @ForeignKey(entity = Recipe.class,
                parentColumns = "id",
                childColumns = "recipeId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index("recipeId")})
public class RecipeIngredient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int recipeId;
    private String ingredientLabel; // e.g. "tomato"
    private double requiredAmount;  // required quantity
    private String requiredUnit;    // required unit

    @Ignore
    public RecipeIngredient() {
    }

    public RecipeIngredient(int recipeId, String ingredientLabel, double requiredAmount, String requiredUnit) {
        this.recipeId = recipeId;
        this.ingredientLabel = ingredientLabel;
        this.requiredAmount = requiredAmount;
        this.requiredUnit = requiredUnit;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRecipeId() { return recipeId; }
    public void setRecipeId(int recipeId) { this.recipeId = recipeId; }

    public String getIngredientLabel() { return ingredientLabel; }
    public void setIngredientLabel(String ingredientLabel) { this.ingredientLabel = ingredientLabel; }

    public double getRequiredAmount() { return requiredAmount; }
    public void setRequiredAmount(double requiredAmount) { this.requiredAmount = requiredAmount; }

    public String getRequiredUnit() { return requiredUnit; }
    public void setRequiredUnit(String requiredUnit) { this.requiredUnit = requiredUnit; }
}
