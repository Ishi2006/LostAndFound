package com.example.lostandfound.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lostandfound.R;
import com.example.lostandfound.models.Item;
import com.example.lostandfound.utils.PreferencesManager;

public class EditItemActivity extends AppCompatActivity {

    private EditText etTitle, etDescription, etLocation;
    private PreferencesManager prefsManager;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        prefsManager  = new PreferencesManager(this);
        item          = (Item) getIntent().getSerializableExtra("item");
        etTitle       = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        etLocation    = findViewById(R.id.et_location);

        etTitle.setText(item.getTitle());
        etDescription.setText(item.getDescription());
        etLocation.setText(item.getLocation());

        findViewById(R.id.btn_update).setOnClickListener(v -> {
            item.setTitle(etTitle.getText().toString().trim());
            item.setDescription(etDescription.getText().toString().trim());
            item.setLocation(etLocation.getText().toString().trim());
            prefsManager.updateItem(item);
            Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
            finish();
        });

        findViewById(R.id.btn_delete).setOnClickListener(v -> {
            prefsManager.deleteItem(item.getId());
            Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
            finish();
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() { finish(); return true; }
}