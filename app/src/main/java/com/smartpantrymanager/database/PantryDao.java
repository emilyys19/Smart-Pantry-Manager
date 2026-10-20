package com.smartpantrymanager.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PantryDao {

    @Insert
    long insertPantryVaultEntry(PantryItem item);

    @Update
    int updatePantryVaultEntry(PantryItem item);

    @Delete
    int deletePantryVaultEntry(PantryItem item);

    @Query("SELECT * FROM pantry_items ORDER BY createdTimestamp DESC")
    List<PantryItem> fetchAllVaultEntries();

    @Query("SELECT * FROM pantry_items WHERE id = :id LIMIT 1")
    PantryItem fetchVaultEntryById(int id);

    @Query("SELECT * FROM pantry_items WHERE LOWER(TRIM(name)) = LOWER(TRIM(:name)) LIMIT 1")
    PantryItem fetchVaultEntryByName(String name);

    @Query("DELETE FROM pantry_items")
    void purgeAllVaultEntries();

    @Query("SELECT COUNT(*) FROM pantry_items")
    int countVaultEntries();
}
