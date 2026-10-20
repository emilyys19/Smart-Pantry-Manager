package com.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartpantrymanager.adapters.PantryAdapter;
import com.smartpantrymanager.database.AppDatabase;
import com.smartpantrymanager.database.PantryItem;

import java.util.List;

/**
 * PantryListActivity - main pantry screen displaying pantry items in a RecyclerView.
 */
public class PantryListActivity extends AppCompatActivity {

    private RecyclerView pantryRecycler;
    private PantryAdapter pantryAdapter;
    private TextView emptyPlaceholder;
    private AppDatabase pantryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.pantry_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        pantryDatabase = AppDatabase.getInstance(this);

        pantryRecycler = findViewById(R.id.pantry_recycler);
        emptyPlaceholder = findViewById(R.id.empty_view);
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);

        pantryAdapter = new PantryAdapter();
        pantryRecycler.setLayoutManager(new LinearLayoutManager(this));
        pantryRecycler.setAdapter(pantryAdapter);

        pantryAdapter.setOnPantryItemClickListener(item -> {
            Intent editIntent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
            editIntent.putExtra("pantry_item_id", item.getId());
            editIntent.putExtra("pantry_item_name", item.getName());
            editIntent.putExtra("pantry_item_amount", item.getAmount());
            editIntent.putExtra("pantry_item_unit", item.getUnit());
            editIntent.putExtra("pantry_item_expiry", item.getExpiryDate());
            startActivity(editIntent);
        });

        pantryAdapter.setOnPantryItemLongClickListener(item -> showDeleteDialog(item));

        fabAdd.setOnClickListener(v -> {
            Intent createIntent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
            startActivity(createIntent);
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_pantry);
        bottomNav.setOnItemSelectedListener(selectedItem -> {
            int selectedId = selectedItem.getItemId();
            if (selectedId == R.id.nav_pantry) return true;
            if (selectedId == R.id.nav_recipes) {
                startActivity(new Intent(this, SuggestedRecipesActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            if (selectedId == R.id.nav_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryVault();
    }

    private void loadPantryVault() {
        List<PantryItem> vaultEntries = pantryDatabase.pantryDao().fetchAllVaultEntries();
        pantryAdapter.setPantryItems(vaultEntries);

        if (vaultEntries.isEmpty()) {
            emptyPlaceholder.setVisibility(View.VISIBLE);
            pantryRecycler.setVisibility(View.GONE);
        } else {
            emptyPlaceholder.setVisibility(View.GONE);
            pantryRecycler.setVisibility(View.VISIBLE);
        }
    }

    private void showDeleteDialog(PantryItem item) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm_delete_title)
                .setMessage("Delete " + item.getName() + "?")
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    pantryDatabase.pantryDao().deletePantryVaultEntry(item);
                    Toast.makeText(this, R.string.msg_deleted, Toast.LENGTH_SHORT).show();
                    loadPantryVault();
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu optionsMenu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, optionsMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem selectedOption) {
        if (selectedOption.getItemId() == R.id.action_add) {
            startActivity(new Intent(this, AddEditIngredientActivity.class));
            return true;
        }
        if (selectedOption.getItemId() == R.id.action_clear) {
            confirmClearAll();
            return true;
        }
        return super.onOptionsItemSelected(selectedOption);
    }

    private void confirmClearAll() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm_clear_title)
                .setMessage(R.string.confirm_clear_msg)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    pantryDatabase.pantryDao().purgeAllVaultEntries();
                    loadPantryVault();
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}
