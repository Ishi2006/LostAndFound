package com.example.lostandfound.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lostandfound.R;
import com.example.lostandfound.models.Item;
import com.example.lostandfound.utils.PreferencesManager;
import java.util.UUID;

public class PostItemActivity extends AppCompatActivity {

    private EditText etTitle, etDescription, etLocation, etContact;
    private Spinner spinnerCategory, spinnerType;
    private PreferencesManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item);

        prefsManager  = new PreferencesManager(this);
        etTitle       = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        etLocation    = findViewById(R.id.et_location);
        etContact     = findViewById(R.id.et_contact);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerType     = findViewById(R.id.spinner_type);

        String[] categories = {"Electronics", "Bags", "Documents", "Jewelry", "Keys", "Clothing", "Other"};
        String[] types      = {"Lost", "Found"};

        spinnerCategory.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories));
        spinnerType.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, types));

        findViewById(R.id.btn_submit).setOnClickListener(v -> submitItem());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void submitItem() {
        String title    = etTitle.getText().toString().trim();
        String desc     = etDescription.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String contact  = etContact.getText().toString().trim();

        if (title.isEmpty() || desc.isEmpty() || location.isEmpty() || contact.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Item item = new Item(
                UUID.randomUUID().toString(),
                title, desc,
                spinnerCategory.getSelectedItem().toString(),
                spinnerType.getSelectedItem().toString().toLowerCase(),
                location,
                prefsManager.getUserName(),
                contact,
                "",
                System.currentTimeMillis(),
                prefsManager.getUserId()
        );

        prefsManager.saveItem(item);
        Toast.makeText(this, "Item posted successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}