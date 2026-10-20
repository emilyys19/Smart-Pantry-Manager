package com.smartpantrymanager.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

/**
 * AppDatabase - Room database singleton.
 */
@Database(entities = {PantryItem.class, Recipe.class, RecipeIngredient.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;

    public abstract PantryDao pantryDao();
    public abstract RecipeDao recipeDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "smart_pantry.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        AppDatabase innerDatabase = getInstance(context);
                                        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
                                        DatabaseSeeder.seedRecipes(innerDatabase);
                                    });
                                }
                            })
                            .build();

                    if (instance.recipeDao().countHarvestRecipes() == 0) {
                        DatabaseSeeder.seedRecipes(instance);
                    }
                }
            }
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
