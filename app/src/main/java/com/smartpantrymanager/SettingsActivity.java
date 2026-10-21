package com.smartpantrymanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.smartpantrymanager.database.AppDatabase;

/**
 * SettingsActivity - manages user preferences and data clearance.
 */
public class SettingsActivity extends AppCompatActivity {

    private AppDatabase pantryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.settings_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        pantryDatabase = AppDatabase.getInstance(this);

        Switch expirySwitch = findViewById(R.id.switch_expiry);
        Switch unitsSwitch = findViewById(R.id.switch_units);
        MaterialButton clearButton = findViewById(R.id.btn_clear_data);

        SharedPreferences pantryPrefs = getSharedPreferences("smart_pantry_prefs", MODE_PRIVATE);

        expirySwitch.setChecked(pantryPrefs.getBoolean("expiry_alert", true));
        unitsSwitch.setChecked(pantryPrefs.getBoolean("metric_choice", true));

        expirySwitch.setOnCheckedChangeListener((switchView, isChecked) -> {
            pantryPrefs.edit().putBoolean("expiry_alert", isChecked).apply();
            String feedback = isChecked ? "Expiry alerts enabled" : "Expiry alerts disabled";
            Toast.makeText(this, feedback, Toast.LENGTH_SHORT).show();
        });

        unitsSwitch.setOnCheckedChangeListener((unitView, metricChecked) -> {
            pantryPrefs.edit().putBoolean("metric_choice", metricChecked).apply();
            String unitFeedback = metricChecked ? "Metric units (g, ml)" : "Imperial units (oz, fl oz)";
            Toast.makeText(this, unitFeedback, Toast.LENGTH_SHORT).show();
        });

        clearButton.setOnClickListener(clearTap -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.confirm_clear_title)
                    .setMessage(R.string.confirm_clear_msg)
                    .setPositiveButton(R.string.yes, (dialog, which) -> {
                        pantryDatabase.pantryDao().purgeAllVaultEntries();
                        Toast.makeText(this, "Pantry cleared", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_settings);
        bottomNav.setOnItemSelectedListener(selectedItem -> {
            int selectedId = selectedItem.getItemId();
            if (selectedId == R.id.nav_settings) return true;
            if (selectedId == R.id.nav_pantry) {
                startActivity(new Intent(this, PantryListActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            if (selectedId == R.id.nav_recipes) {
                startActivity(new Intent(this, SuggestedRecipesActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }
}
