package com.smartpantrymanager.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.smartpantrymanager.R;
import com.smartpantrymanager.database.Recipe;
import java.util.ArrayList;
import java.util.List;

// RecipeAdapter - adapter to show suggested recipes in a RecyclerView.

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList = new ArrayList<>();
    private OnRecipeClickListener clickListener;

    public interface OnRecipeClickListener {
        void onRecipeCardClick(Recipe chosenRecipe);
    }

    public void setOnRecipeClickListener(OnRecipeClickListener listener) {
        this.clickListener = listener;
    }

    public void setRecipes(List<Recipe> incomingRecipes) {
        this.recipeList = incomingRecipes != null ? incomingRecipes : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parentGroup, int viewType) {
        View inflatedCard = LayoutInflater.from(parentGroup.getContext())
                .inflate(R.layout.item_recipe, parentGroup, false);
        return new RecipeViewHolder(inflatedCard);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeHolder, int position) {
        Recipe currentRecipe = recipeList.get(position);
        recipeHolder.bindRecipe(currentRecipe);
        recipeHolder.itemView.setOnClickListener(v -> {
            if (clickListener != null) clickListener.onRecipeCardClick(currentRecipe);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView titleLabel, summaryLabel, metaBadge;

        RecipeViewHolder(@NonNull View rowView) {
            super(rowView);
            titleLabel = rowView.findViewById(R.id.recipe_name);
            summaryLabel = rowView.findViewById(R.id.recipe_description);
            metaBadge = rowView.findViewById(R.id.recipe_meta);
        }

        void bindRecipe(Recipe recipe) {
            titleLabel.setText(recipe.getTitle());
            summaryLabel.setText(recipe.getSummary());
            metaBadge.setText(recipe.getCookingDuration() + " - " + recipe.getDifficultyRank() + " - " + recipe.getMealCategory());
        }
    }
}
