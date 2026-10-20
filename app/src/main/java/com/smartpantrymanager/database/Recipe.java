package com.smartpantrymanager.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Recipe - entity representing a recipe.
 */
@Entity(tableName = "recipes")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String summary;
    private String cookingSteps;       // newline-separated steps
    private String cookingDuration;    // e.g. "20 mins"
    private String difficultyRank;     // Easy / Medium
    private String mealCategory;       // Breakfast, Lunch, Dinner

    @Ignore
    public Recipe() {
    }

    public Recipe(String title, String summary, String cookingSteps,
                  String cookingDuration, String difficultyRank, String mealCategory) {
        this.title = title;
        this.summary = summary;
        this.cookingSteps = cookingSteps;
        this.cookingDuration = cookingDuration;
        this.difficultyRank = difficultyRank;
        this.mealCategory = mealCategory;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getCookingSteps() { return cookingSteps; }
    public void setCookingSteps(String cookingSteps) { this.cookingSteps = cookingSteps; }

    public String getCookingDuration() { return cookingDuration; }
    public void setCookingDuration(String cookingDuration) { this.cookingDuration = cookingDuration; }

    public String getDifficultyRank() { return difficultyRank; }
    public void setDifficultyRank(String difficultyRank) { this.difficultyRank = difficultyRank; }

    public String getMealCategory() { return mealCategory; }
    public void setMealCategory(String mealCategory) { this.mealCategory = mealCategory; }
}
