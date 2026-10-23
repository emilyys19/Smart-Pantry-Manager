package com.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.smartpantrymanager.database.AppDatabase;
import com.smartpantrymanager.database.Recipe;
import com.smartpantrymanager.database.RecipeIngredient;
import java.util.List;

// RecipeDetailActivity - displays full details for a selected recipe.

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        int recipeId = getIntent().getIntExtra("recipe_id", -1);
        String recipeTitle = getIntent().getStringExtra("recipe_title");

        if (getSupportActionBar() != null && recipeTitle != null) {
            getSupportActionBar().setTitle(recipeTitle);
        }

        if (recipeId == -1) {
            finish();
            return;
        }

        AppDatabase pantryDatabase = AppDatabase.getInstance(this);
        Recipe selectedRecipe = pantryDatabase.recipeDao().fetchHarvestRecipeById(recipeId);
        List<RecipeIngredient> requiredIngredients = pantryDatabase.recipeDao().fetchIngredientsForHarvestRecipe(recipeId);

        if (selectedRecipe == null) {
            finish();
            return;
        }

        TextView titleLabel = findViewById(R.id.detail_name);
        TextView summaryLabel = findViewById(R.id.detail_description);
        TextView metaBadge = findViewById(R.id.detail_meta);
        TextView ingredientsLabel = findViewById(R.id.detail_ingredients);
        TextView stepsLabel = findViewById(R.id.detail_steps);

        titleLabel.setText(selectedRecipe.getTitle());
        summaryLabel.setText(selectedRecipe.getSummary());
        metaBadge.setText(selectedRecipe.getCookingDuration() + " - " + selectedRecipe.getDifficultyRank() + " - " + selectedRecipe.getMealCategory());

        StringBuilder ingredientBuilder = new StringBuilder();
        for (RecipeIngredient oneIngredient : requiredIngredients) {
            ingredientBuilder.append("- ").append(oneIngredient.getIngredientLabel())
                    .append(" - ").append(oneIngredient.getRequiredAmount()).append(" ").append(oneIngredient.getRequiredUnit())
                    .append("\n");
        }
        ingredientsLabel.setText(ingredientBuilder.toString().trim());

        stepsLabel.setText(selectedRecipe.getCookingSteps());

        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
