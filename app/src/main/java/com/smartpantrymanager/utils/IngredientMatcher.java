package com.smartpantrymanager.utils;

import com.smartpantrymanager.database.PantryItem;
import com.smartpantrymanager.database.Recipe;
import com.smartpantrymanager.database.RecipeIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles matching pantry inventory items against recipe requirements.
 */
public class IngredientMatcher {

    // Cleans and normalizes ingredient names so plurals or slight wording differences match properly
    public static String normalizeIngredientName(String rawName) {
        if (rawName == null) return "";
        String cleanedName = rawName.toLowerCase().trim().replaceAll("\\s+", " ");

        // Basic plural removal to match singular pantry items
        if (cleanedName.endsWith("ies") && cleanedName.length() > 4) {
            cleanedName = cleanedName.substring(0, cleanedName.length() - 3) + "y";
        } else if (cleanedName.endsWith("es") && cleanedName.length() > 4) {
            String withoutEs = cleanedName.substring(0, cleanedName.length() - 2);
            if (cleanedName.endsWith("oes") || cleanedName.endsWith("ses") || cleanedName.endsWith("xes") || cleanedName.endsWith("ches") || cleanedName.endsWith("shes")) {
                cleanedName = withoutEs;
            } else if (cleanedName.endsWith("oes")) {
                cleanedName = withoutEs;
            }
        } else if (cleanedName.endsWith("s") && cleanedName.length() > 2 && !cleanedName.endsWith("ss")) {
            cleanedName = cleanedName.substring(0, cleanedName.length() - 1);
        }

        // Apply any specific synonym mappings
        Map<String, String> synonymLookup = getSynonymMap();
        if (synonymLookup.containsKey(cleanedName)) {
            cleanedName = synonymLookup.get(cleanedName);
        }

        return cleanedName;
    }

    private static Map<String, String> getSynonymMap() {
        Map<String, String> synonymMap = new HashMap<>();
        synonymMap.put("chilli flake", "chilli flakes");
        synonymMap.put("spring onion", "spring onion");
        return synonymMap;
    }

    // Checks if the user has enough quantity of an ingredient in their pantry compared to what a recipe needs
    public static boolean hasEnoughQuantity(double pantryQty, String pantryUnit,
                                           double requiredQty, String requiredUnit) {
        double pantryBaseQty = UnitConverter.getBaseQuantity(pantryQty, pantryUnit);
        double requiredBaseQty = UnitConverter.getBaseQuantity(requiredQty, requiredUnit);

        if (UnitConverter.isCompatible(pantryUnit, requiredUnit)) {
            return pantryBaseQty >= requiredBaseQty;
        }

        if (pantryUnit != null && requiredUnit != null &&
                pantryUnit.trim().equalsIgnoreCase(requiredUnit.trim())) {
            return pantryQty >= requiredQty;
        }

        return false;
    }

    // Determines if the user has all required ingredients in sufficient quantities for a recipe
    public static boolean isStrictMatch(Recipe targetRecipe, List<RecipeIngredient> requiredList,
                                        List<PantryItem> pantryStock) {
        if (requiredList == null || requiredList.isEmpty()) return false;
        if (pantryStock == null || pantryStock.isEmpty()) return false;

        Map<String, PantryItem> pantryMap = new HashMap<>();
        for (PantryItem singleStock : pantryStock) {
            String normPantryName = normalizeIngredientName(singleStock.getName());
            if (!pantryMap.containsKey(normPantryName) || singleStock.getAmount() > pantryMap.get(normPantryName).getAmount()) {
                pantryMap.put(normPantryName, singleStock);
            }
        }

        for (RecipeIngredient oneRequirement : requiredList) {
            String normReq = normalizeIngredientName(oneRequirement.getIngredientLabel());
            PantryItem matchingStock = pantryMap.get(normReq);

            if (matchingStock == null) {
                return false;
            }

            if (!hasEnoughQuantity(matchingStock.getAmount(), matchingStock.getUnit(),
                    oneRequirement.getRequiredAmount(), oneRequirement.getRequiredUnit())) {
                return false;
            }
        }

        return true;
    }

    // Filters recipes that can be made right now with current pantry stock
    public static List<Recipe> getStrictMatches(List<Recipe> allRecipes,
                                                Map<Integer, List<RecipeIngredient>> recipeIngredientLookup,
                                                List<PantryItem> pantryStock) {
        List<Recipe> matchedRecipes = new ArrayList<>();
        for (Recipe oneRecipe : allRecipes) {
            List<RecipeIngredient> ingredientsForThis = recipeIngredientLookup.get(oneRecipe.getId());
            if (isStrictMatch(oneRecipe, ingredientsForThis, pantryStock)) {
                matchedRecipes.add(oneRecipe);
            }
        }
        return matchedRecipes;
    }

    // Finds recipes where the user is missing just one ingredient ("almost there")
    public static List<Recipe> getAlmostThereMatches(List<Recipe> allRecipes,
                                                    Map<Integer, List<RecipeIngredient>> recipeIngredientLookup,
                                                    List<PantryItem> pantryStock) {
        List<Recipe> almostThere = new ArrayList<>();
        for (Recipe oneRecipe : allRecipes) {
            List<RecipeIngredient> ingredientsForThis = recipeIngredientLookup.get(oneRecipe.getId());
            int missingCount = countMissingIngredients(ingredientsForThis, pantryStock);
            if (missingCount == 1) {
                almostThere.add(oneRecipe);
            }
        }
        return almostThere;
    }

    // Counts how many required ingredients are missing from the pantry
    public static int countMissingIngredients(List<RecipeIngredient> requiredIngredients, List<PantryItem> pantryStock) {
        if (requiredIngredients == null) return 0;
        Map<String, PantryItem> pantryMap = new HashMap<>();
        for (PantryItem singleStock : pantryStock) {
            pantryMap.put(normalizeIngredientName(singleStock.getName()), singleStock);
        }
        int missingTally = 0;
        for (RecipeIngredient oneRequirement : requiredIngredients) {
            String normReq = normalizeIngredientName(oneRequirement.getIngredientLabel());
            PantryItem matchingStock = pantryMap.get(normReq);
            if (matchingStock == null || !hasEnoughQuantity(matchingStock.getAmount(), matchingStock.getUnit(), oneRequirement.getRequiredAmount(), oneRequirement.getRequiredUnit())) {
                missingTally++;
            }
        }
        return missingTally;
    }

    // Returns the name of the first missing ingredient for a recipe
    public static String getMissingIngredientName(List<RecipeIngredient> requiredIngredients, List<PantryItem> pantryStock) {
        Map<String, PantryItem> pantryMap = new HashMap<>();
        for (PantryItem singleStock : pantryStock) {
            pantryMap.put(normalizeIngredientName(singleStock.getName()), singleStock);
        }
        for (RecipeIngredient oneRequirement : requiredIngredients) {
            String normReq = normalizeIngredientName(oneRequirement.getIngredientLabel());
            PantryItem matchingStock = pantryMap.get(normReq);
            if (matchingStock == null || !hasEnoughQuantity(matchingStock.getAmount(), matchingStock.getUnit(), oneRequirement.getRequiredAmount(), oneRequirement.getRequiredUnit())) {
                return oneRequirement.getIngredientLabel();
            }
        }
        return "";
    }
}
