//Imports
package com.smartpantrymanager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.smartpantrymanager.database.AppDatabase;
import com.smartpantrymanager.database.PantryItem;
import com.smartpantrymanager.utils.ValidationUtils;

// Screen for adding a new pantry item or editing an existing one.

public class AddEditIngredientActivity extends AppCompatActivity {

    private TextInputEditText nameEntry, amountEntry, expiryEntry;
    private TextInputLayout nameWrapper, amountWrapper;
    private Spinner unitPicker;
    private Button saveButton, cancelButton;

    private AppDatabase pantryDatabase;
    private PantryItem editingItem = null;
    private boolean isEditingExisting = false;
    private int editingId = -1;

    // Measurement units for the dropdown spinner
    private final String[] availableUnits = {"g", "kg", "ml", "l", "pcs", "tsp", "tbsp", "cup", "oz"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        // Setup toolbar with back button functionality
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Initialize local database instance
        pantryDatabase = AppDatabase.getInstance(this);

        // Bind UI views
        nameEntry = findViewById(R.id.input_name);
        amountEntry = findViewById(R.id.input_quantity);
        expiryEntry = findViewById(R.id.input_expiry);
        unitPicker = findViewById(R.id.spinner_unit);
        nameWrapper = findViewById(R.id.layout_name);
        amountWrapper = findViewById(R.id.layout_quantity);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);

        // Populate unit dropdown spinner with adapter
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, availableUnits);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitPicker.setAdapter(unitAdapter);

        // Check if we passed an item ID, which means we are editing instead of adding fresh
        if (getIntent().hasExtra("pantry_item_id")) {
            isEditingExisting = true;
            editingId = getIntent().getIntExtra("pantry_item_id", -1);
            String incomingName = getIntent().getStringExtra("pantry_item_name");
            double incomingAmount = getIntent().getDoubleExtra("pantry_item_amount", 0);
            String incomingUnit = getIntent().getStringExtra("pantry_item_unit");
            String incomingExpiry = getIntent().getStringExtra("pantry_item_expiry");

            // Fill form fields with existing item data
            nameEntry.setText(incomingName);
            amountEntry.setText(String.valueOf(incomingAmount));
            if (incomingExpiry != null) expiryEntry.setText(incomingExpiry);

            // Select the correct unit in the spinner
            for (int unitIndex = 0; unitIndex < availableUnits.length; unitIndex++) {
                if (availableUnits[unitIndex].equalsIgnoreCase(incomingUnit)) {
                    unitPicker.setSelection(unitIndex);
                    break;
                }
            }

            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(R.string.edit_ingredient);
            saveButton.setText(R.string.save);

            editingItem = pantryDatabase.pantryDao().fetchVaultEntryById(editingId);
        } else {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(R.string.add_ingredient);
        }

        // Button click listeners for saving or cancelling
        saveButton.setOnClickListener(v -> attemptSave());
        cancelButton.setOnClickListener(v -> finish());
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    // Validates form input and saves or updates the pantry item in database
    private void attemptSave() {
        String enteredName = nameEntry.getText() != null ? nameEntry.getText().toString().trim() : "";
        String enteredAmountText = amountEntry.getText() != null ? amountEntry.getText().toString().trim() : "";
        String chosenUnit = (String) unitPicker.getSelectedItem();
        String enteredExpiry = expiryEntry.getText() != null ? expiryEntry.getText().toString().trim() : "";

        nameWrapper.setError(null);
        amountWrapper.setError(null);

        boolean formValid = true;

        // Make sure name and quantity meet requirements
        if (!ValidationUtils.isValidName(enteredName)) {
            nameWrapper.setError(getString(R.string.validation_name_required));
            formValid = false;
        }
        if (!ValidationUtils.isValidQuantity(enteredAmountText)) {
            amountWrapper.setError(getString(R.string.validation_quantity_required));
            formValid = false;
        }
        if (!ValidationUtils.isValidExpiryDate(enteredExpiry)) {
            expiryEntry.setError("Use YYYY-MM-DD or leave empty");
            formValid = false;
        }

        if (!formValid) return;

        double parsedAmount = Double.parseDouble(enteredAmountText);

        // Update database record if editing, otherwise insert a brand new entry
        if (isEditingExisting && editingItem != null) {
            editingItem.setName(enteredName);
            editingItem.setAmount(parsedAmount);
            editingItem.setUnit(chosenUnit);
            editingItem.setExpiryDate(enteredExpiry);
            pantryDatabase.pantryDao().updatePantryVaultEntry(editingItem);
            Toast.makeText(this, R.string.msg_updated, Toast.LENGTH_SHORT).show();
        } else {
            PantryItem newItem = new PantryItem(enteredName, parsedAmount, chosenUnit, enteredExpiry, System.currentTimeMillis());
            pantryDatabase.pantryDao().insertPantryVaultEntry(newItem);
            Toast.makeText(this, R.string.msg_saved, Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
