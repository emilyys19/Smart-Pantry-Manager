package com.smartpantrymanager.utils;

import android.text.TextUtils;

/**
 * Validation helpers for input validation.
 */
public class ValidationUtils {

    public static boolean isValidName(String name) {
        return !TextUtils.isEmpty(name) && name.trim().length() >= 2;
    }

    public static boolean isValidQuantity(String quantityText) {
        if (TextUtils.isEmpty(quantityText)) return false;
        try {
            double parsedQty = Double.parseDouble(quantityText.trim());
            return parsedQty > 0 && parsedQty < 100000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidExpiryDate(String expiryDate) {
        if (TextUtils.isEmpty(expiryDate)) return true;
        return expiryDate.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static boolean isValidUnit(String unit) {
        return !TextUtils.isEmpty(unit) && unit.trim().length() >= 1;
    }
}
