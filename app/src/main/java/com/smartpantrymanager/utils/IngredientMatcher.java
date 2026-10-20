package com.smartpantrymanager.utils;

import com.smartpantrymanager.database.PantryItem;
import com.smartpantrymanager.database.Recipe;
import com.smartpantrymanager.database.RecipeIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IngredientMatcher - logic for strict recipe ingredient matching.
 */
public class IngredientMatcher {

    public static String normalizeIngredientName(String rawName) {
        if (rawName == null) return "";
        String cleanedName = rawName.toLowerCase().trim().replaceAll("\\s+", " ");

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
