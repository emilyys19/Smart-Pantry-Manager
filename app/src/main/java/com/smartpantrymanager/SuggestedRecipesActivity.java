package com.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartpantrymanager.adapters.RecipeAdapter;
import com.smartpantrymanager.database.AppDatabase;
import com.smartpantrymanager.database.PantryItem;
import com.smartpantrymanager.database.Recipe;
import com.smartpantrymanager.database.RecipeIngredient;
import com.smartpantrymanager.utils.IngredientMatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SuggestedRecipesActivity - displays strictly matched recipes and almost-there recipes.
 */
public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView strictRecycler;
    private RecyclerView almostThereRecycler;
    private TextView emptyMessage, almostThereHeading;
    private RecipeAdapter strictAdapter;
    private RecipeAdapter almostThereAdapter;
    private AppDatabase pantryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.suggested_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        pantryDatabase = AppDatabase.getInstance(this);

        strictRecycler = findViewById(R.id.recipes_recycler);
        almostThereRecycler = findViewById(R.id.almost_recycler);
        emptyMessage = findViewById(R.id.empty_recipes_view);
        almostThereHeading = findViewById(R.id.almost_title);

        strictAdapter = new RecipeAdapter();
        strictRecycler.setLayoutManager(new LinearLayoutManager(this));
        strictRecycler.setAdapter(strictAdapter);

        almostThereAdapter = new RecipeAdapter();
        almostThereRecycler.setLayoutManager(new LinearLayoutManager(this));
        almostThereRecycler.setAdapter(almostThereAdapter);

        RecipeAdapter.OnRecipeClickListener cardClick = chosenRecipe -> {
            Intent detailIntent = new Intent(SuggestedRecipesActivity.this, RecipeDetailActivity.class);
            detailIntent.putExtra("recipe_id", chosenRecipe.getId());
            detailIntent.putExtra("recipe_title", chosenRecipe.getTitle());
            startActivity(detailIntent);
        };

        strictAdapter.setOnRecipeClickListener(cardClick);
        almostThereAdapter.setOnRecipeClickListener(cardClick);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_recipes);
        bottomNav.setOnItemSelectedListener(selectedItem -> {
            int selectedId = selectedItem.getItemId();
            if (selectedId == R.id.nav_recipes) return true;
            if (selectedId == R.id.nav_pantry) {
                startActivity(new Intent(this, PantryListActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            if (selectedId == R.id.nav_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {
        List<PantryItem> pantryStock = pantryDatabase.pantryDao().fetchAllVaultEntries();
        List<Recipe> allRecipes = pantryDatabase.recipeDao().fetchAllHarvestRecipes();

        Map<Integer, List<RecipeIngredient>> ingredientLookup = new HashMap<>();
        for (Recipe recipe : allRecipes) {
            ingredientLookup.put(recipe.getId(), pantryDatabase.recipeDao().fetchIngredientsForHarvestRecipe(recipe.getId()));
        }

        List<Recipe> strictMatches = IngredientMatcher.getStrictMatches(
                allRecipes, ingredientLookup, pantryStock);

        List<Recipe> almostThereRecipes = IngredientMatcher.getAlmostThereMatches(
                allRecipes, ingredientLookup, pantryStock);

        strictAdapter.setRecipes(strictMatches);
        almostThereAdapter.setRecipes(almostThereRecipes);

        if (strictMatches.isEmpty()) {
            emptyMessage.setVisibility(View.VISIBLE);
            strictRecycler.setVisibility(View.GONE);
            emptyMessage.setText(getString(R.string.no_match_message) + "\n\n" + getString(R.string.no_match_hint));
        } else {
            emptyMessage.setVisibility(View.GONE);
            strictRecycler.setVisibility(View.VISIBLE);
        }

        if (almostThereRecipes.isEmpty()) {
            almostThereHeading.setVisibility(View.GONE);
            almostThereRecycler.setVisibility(View.GONE);
        } else {
            almostThereHeading.setVisibility(View.VISIBLE);
            almostThereRecycler.setVisibility(View.VISIBLE);
        }
    }
}
