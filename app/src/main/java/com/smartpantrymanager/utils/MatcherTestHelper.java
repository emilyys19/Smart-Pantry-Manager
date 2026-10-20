package com.smartpantrymanager.utils;

import com.smartpantrymanager.database.PantryItem;
import com.smartpantrymanager.database.Recipe;

import java.util.Arrays;
import java.util.List;

/**
 * MatcherTestHelper - helper to run self checks on matcher logic.
 */
public class MatcherTestHelper {

    public static void runSelfCheck() {
        PantryItem tomatoes = new PantryItem("Tomatoes", 500, "g", null, 0);
        PantryItem pasta = new PantryItem("pasta", 200, "g", null, 0);
        List<PantryItem> pantrySample = Arrays.asList(tomatoes, pasta);

        Recipe quickPasta = new Recipe("Test Pasta", "desc", "steps", "10 mins", "Easy", "Dinner");

        assert IngredientMatcher.normalizeIngredientName("Tomatoes").equals("tomato") : "Plural handling failed";
        assert IngredientMatcher.normalizeIngredientName("pasta").equals("pasta") : "Singular corrupted";
        assert IngredientMatcher.normalizeIngredientName("  TOMATOES  ").equals("tomato") : "Case trim failed";
        assert IngredientMatcher.normalizeIngredientName("potatoes").equals("potato") : "oes handling failed";
        assert IngredientMatcher.normalizeIngredientName("eggs").equals("egg") : "Simple plural failed";

        assert UnitConverter.isCompatible("g", "kg") : "Weight units should be compatible";
        assert UnitConverter.isCompatible("ml", "tsp") : "Volume units should be compatible";
        assert !UnitConverter.isCompatible("g", "pcs") : "Weight vs count not compatible";
        assert UnitConverter.getBaseQuantity(1, "kg") == 1000.0 : "kg to g failed";
        assert UnitConverter.getBaseQuantity(2, "tsp") == 10.0 : "tsp to ml failed";

        System.out.println("Matcher self-checks passed.");
    }
}
