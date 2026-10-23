package com.smartpantrymanager.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// PantryItem - entity representing an item stored in the pantry.

@Entity(tableName = "pantry_items")
public class PantryItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;              // e.g. "Tomatoes"
    private double amount;            // e.g. 500
    private String unit;              // e.g. "g", "ml", "pcs", "kg"
    private String expiryDate;        // optional YYYY-MM-DD
    private long createdTimestamp;    // timestamp

    @Ignore
    public PantryItem() {
    }

    public PantryItem(String name, double amount, String unit, String expiryDate, long createdTimestamp) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.expiryDate = expiryDate;
        this.createdTimestamp = createdTimestamp;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public long getCreatedTimestamp() { return createdTimestamp; }
    public void setCreatedTimestamp(long createdTimestamp) { this.createdTimestamp = createdTimestamp; }
}
