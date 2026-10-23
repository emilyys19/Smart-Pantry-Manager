package com.smartpantrymanager.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RecipeDao_Impl implements RecipeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Recipe> __insertionAdapterOfRecipe;

  private final EntityInsertionAdapter<RecipeIngredient> __insertionAdapterOfRecipeIngredient;

  private final SharedSQLiteStatement __preparedStmtOfPurgeAllHarvestRecipes;

  private final SharedSQLiteStatement __preparedStmtOfPurgeAllHarvestIngredients;

  public RecipeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecipe = new EntityInsertionAdapter<Recipe>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `recipes` (`id`,`title`,`summary`,`cookingSteps`,`cookingDuration`,`difficultyRank`,`mealCategory`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Recipe entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getSummary() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSummary());
        }
        if (entity.getCookingSteps() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCookingSteps());
        }
        if (entity.getCookingDuration() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCookingDuration());
        }
        if (entity.getDifficultyRank() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDifficultyRank());
        }
        if (entity.getMealCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getMealCategory());
        }
      }
    };
    this.__insertionAdapterOfRecipeIngredient = new EntityInsertionAdapter<RecipeIngredient>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `recipe_ingredients` (`id`,`recipeId`,`ingredientLabel`,`requiredAmount`,`requiredUnit`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final RecipeIngredient entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRecipeId());
        if (entity.getIngredientLabel() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getIngredientLabel());
        }
        statement.bindDouble(4, entity.getRequiredAmount());
        if (entity.getRequiredUnit() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRequiredUnit());
        }
      }
    };
    this.__preparedStmtOfPurgeAllHarvestRecipes = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM recipes";
        return _query;
      }
    };
    this.__preparedStmtOfPurgeAllHarvestIngredients = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM recipe_ingredients";
        return _query;
      }
    };
  }

  @Override
  public long insertHarvestRecipe(final Recipe recipe) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfRecipe.insertAndReturnId(recipe);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertHarvestIngredients(final List<RecipeIngredient> ingredients) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRecipeIngredient.insert(ingredients);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void purgeAllHarvestRecipes() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfPurgeAllHarvestRecipes.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfPurgeAllHarvestRecipes.release(_stmt);
    }
  }

  @Override
  public void purgeAllHarvestIngredients() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfPurgeAllHarvestIngredients.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfPurgeAllHarvestIngredients.release(_stmt);
    }
  }

  @Override
  public List<Recipe> fetchAllHarvestRecipes() {
    final String _sql = "SELECT * FROM recipes ORDER BY title ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "summary");
      final int _cursorIndexOfCookingSteps = CursorUtil.getColumnIndexOrThrow(_cursor, "cookingSteps");
      final int _cursorIndexOfCookingDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "cookingDuration");
      final int _cursorIndexOfDifficultyRank = CursorUtil.getColumnIndexOrThrow(_cursor, "difficultyRank");
      final int _cursorIndexOfMealCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "mealCategory");
      final List<Recipe> _result = new ArrayList<Recipe>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Recipe _item;
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpSummary;
        if (_cursor.isNull(_cursorIndexOfSummary)) {
          _tmpSummary = null;
        } else {
          _tmpSummary = _cursor.getString(_cursorIndexOfSummary);
        }
        final String _tmpCookingSteps;
        if (_cursor.isNull(_cursorIndexOfCookingSteps)) {
          _tmpCookingSteps = null;
        } else {
          _tmpCookingSteps = _cursor.getString(_cursorIndexOfCookingSteps);
        }
        final String _tmpCookingDuration;
        if (_cursor.isNull(_cursorIndexOfCookingDuration)) {
          _tmpCookingDuration = null;
        } else {
          _tmpCookingDuration = _cursor.getString(_cursorIndexOfCookingDuration);
        }
        final String _tmpDifficultyRank;
        if (_cursor.isNull(_cursorIndexOfDifficultyRank)) {
          _tmpDifficultyRank = null;
        } else {
          _tmpDifficultyRank = _cursor.getString(_cursorIndexOfDifficultyRank);
        }
        final String _tmpMealCategory;
        if (_cursor.isNull(_cursorIndexOfMealCategory)) {
          _tmpMealCategory = null;
        } else {
          _tmpMealCategory = _cursor.getString(_cursorIndexOfMealCategory);
        }
        _item = new Recipe(_tmpTitle,_tmpSummary,_tmpCookingSteps,_tmpCookingDuration,_tmpDifficultyRank,_tmpMealCategory);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Recipe fetchHarvestRecipeById(final int recipeId) {
    final String _sql = "SELECT * FROM recipes WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, recipeId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "summary");
      final int _cursorIndexOfCookingSteps = CursorUtil.getColumnIndexOrThrow(_cursor, "cookingSteps");
      final int _cursorIndexOfCookingDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "cookingDuration");
      final int _cursorIndexOfDifficultyRank = CursorUtil.getColumnIndexOrThrow(_cursor, "difficultyRank");
      final int _cursorIndexOfMealCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "mealCategory");
      final Recipe _result;
      if (_cursor.moveToFirst()) {
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpSummary;
        if (_cursor.isNull(_cursorIndexOfSummary)) {
          _tmpSummary = null;
        } else {
          _tmpSummary = _cursor.getString(_cursorIndexOfSummary);
        }
        final String _tmpCookingSteps;
        if (_cursor.isNull(_cursorIndexOfCookingSteps)) {
          _tmpCookingSteps = null;
        } else {
          _tmpCookingSteps = _cursor.getString(_cursorIndexOfCookingSteps);
        }
        final String _tmpCookingDuration;
        if (_cursor.isNull(_cursorIndexOfCookingDuration)) {
          _tmpCookingDuration = null;
        } else {
          _tmpCookingDuration = _cursor.getString(_cursorIndexOfCookingDuration);
        }
        final String _tmpDifficultyRank;
        if (_cursor.isNull(_cursorIndexOfDifficultyRank)) {
          _tmpDifficultyRank = null;
        } else {
          _tmpDifficultyRank = _cursor.getString(_cursorIndexOfDifficultyRank);
        }
        final String _tmpMealCategory;
        if (_cursor.isNull(_cursorIndexOfMealCategory)) {
          _tmpMealCategory = null;
        } else {
          _tmpMealCategory = _cursor.getString(_cursorIndexOfMealCategory);
        }
        _result = new Recipe(_tmpTitle,_tmpSummary,_tmpCookingSteps,_tmpCookingDuration,_tmpDifficultyRank,_tmpMealCategory);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<RecipeIngredient> fetchIngredientsForHarvestRecipe(final int recipeId) {
    final String _sql = "SELECT * FROM recipe_ingredients WHERE recipeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, recipeId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfRecipeId = CursorUtil.getColumnIndexOrThrow(_cursor, "recipeId");
      final int _cursorIndexOfIngredientLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredientLabel");
      final int _cursorIndexOfRequiredAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "requiredAmount");
      final int _cursorIndexOfRequiredUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "requiredUnit");
      final List<RecipeIngredient> _result = new ArrayList<RecipeIngredient>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final RecipeIngredient _item;
        final int _tmpRecipeId;
        _tmpRecipeId = _cursor.getInt(_cursorIndexOfRecipeId);
        final String _tmpIngredientLabel;
        if (_cursor.isNull(_cursorIndexOfIngredientLabel)) {
          _tmpIngredientLabel = null;
        } else {
          _tmpIngredientLabel = _cursor.getString(_cursorIndexOfIngredientLabel);
        }
        final double _tmpRequiredAmount;
        _tmpRequiredAmount = _cursor.getDouble(_cursorIndexOfRequiredAmount);
        final String _tmpRequiredUnit;
        if (_cursor.isNull(_cursorIndexOfRequiredUnit)) {
          _tmpRequiredUnit = null;
        } else {
          _tmpRequiredUnit = _cursor.getString(_cursorIndexOfRequiredUnit);
        }
        _item = new RecipeIngredient(_tmpRecipeId,_tmpIngredientLabel,_tmpRequiredAmount,_tmpRequiredUnit);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int countHarvestRecipes() {
    final String _sql = "SELECT COUNT(*) FROM recipes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
