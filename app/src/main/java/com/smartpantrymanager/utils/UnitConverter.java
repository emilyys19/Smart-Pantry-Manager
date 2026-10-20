package com.smartpantrymanager.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * UnitConverter - converts kitchen measurement units for comparisons.
 */
public class UnitConverter {

    private static final Map<String, Double> weightToGramsMap = new HashMap<>();
    private static final Map<String, Double> volumeToMlMap = new HashMap<>();

    static {
        weightToGramsMap.put("g", 1.0);
        weightToGramsMap.put("gram", 1.0);
        weightToGramsMap.put("grams", 1.0);
        weightToGramsMap.put("kg", 1000.0);
        weightToGramsMap.put("kgs", 1000.0);
        weightToGramsMap.put("kilogram", 1000.0);
        weightToGramsMap.put("oz", 28.35);
        weightToGramsMap.put("lb", 453.592);
        weightToGramsMap.put("lbs", 453.592);

        volumeToMlMap.put("ml", 1.0);
        volumeToMlMap.put("l", 1000.0);
        volumeToMlMap.put("litre", 1000.0);
        volumeToMlMap.put("liter", 1000.0);
        volumeToMlMap.put("tsp", 5.0);
        volumeToMlMap.put("teaspoon", 5.0);
        volumeToMlMap.put("tbsp", 15.0);
        volumeToMlMap.put("tablespoon", 15.0);
        volumeToMlMap.put("cup", 240.0);
        volumeToMlMap.put("cups", 240.0);
        volumeToMlMap.put("fl oz", 29.574);
    }

    public static boolean isWeightUnit(String unit) {
        if (unit == null) return false;
        return weightToGramsMap.containsKey(unit.toLowerCase().trim());
    }

    public static boolean isVolumeUnit(String unit) {
        if (unit == null) return false;
        return volumeToMlMap.containsKey(unit.toLowerCase().trim());
    }

    public static boolean isCountUnit(String unit) {
        if (unit == null) return false;
        String normalized = unit.toLowerCase().trim();
        return normalized.equals("pcs") || normalized.equals("pc") || normalized.equals("piece") ||
                normalized.equals("pieces") || normalized.equals("unit") || normalized.equals("units") ||
                normalized.equals("clove") || normalized.equals("cloves");
    }

    public static boolean isCompatible(String firstUnit, String secondUnit) {
        if (firstUnit == null || secondUnit == null) return false;
        String normFirst = firstUnit.toLowerCase().trim();
        String normSecond = secondUnit.toLowerCase().trim();
        if (normFirst.equals(normSecond)) return true;
        if (isWeightUnit(normFirst) && isWeightUnit(normSecond)) return true;
        if (isVolumeUnit(normFirst) && isVolumeUnit(normSecond)) return true;
        if (isCountUnit(normFirst) && isCountUnit(normSecond)) return true;
        return false;
    }

    public static double getBaseQuantity(double amount, String unit) {
        if (unit == null) return amount;
        String lookupKey = unit.toLowerCase().trim();
        if (weightToGramsMap.containsKey(lookupKey)) {
            return amount * weightToGramsMap.get(lookupKey);
        }
        if (volumeToMlMap.containsKey(lookupKey)) {
            return amount * volumeToMlMap.get(lookupKey);
        }
        return amount;
    }

    public static String toBaseUnit(double amount, String unit) {
        return String.valueOf(getBaseQuantity(amount, unit));
    }
}
