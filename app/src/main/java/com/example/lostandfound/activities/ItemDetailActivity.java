package com.example.lostandfound.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lostandfound.R;
import com.example.lostandfound.models.Item;
import com.example.lostandfound.utils.PreferencesManager;

public class ItemDetailActivity extends AppCompatActivity {

    private PreferencesManager prefsManager;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        prefsManager = new PreferencesManager(this);
        item = (Item) getIntent().getSerializableExtra("item");

        if (item == null) { finish(); return; }

        ((TextView) findViewById(R.id.tv_title)).setText(item.getTitle());
        ((TextView) findViewById(R.id.tv_description)).setText(item.getDescription());
        ((TextView) findViewById(R.id.tv_category)).setText(item.getCategory());
        ((TextView) findViewById(R.id.tv_location)).setText(item.getLocation());
        ((TextView) findViewById(R.id.tv_contact)).setText(item.getContactName());
        ((TextView) findViewById(R.id.tv_type)).setText(
                item.getType().toUpperCase());

        Button btnCall = findViewById(R.id.btn_call);
        Button btnEdit = findViewById(R.id.btn_edit);
        Button btnMarkResolved = findViewById(R.id.btn_mark_resolved);

        // Show edit/delete only to item owner
        boolean isOwner = item.getPostedByUserId().equals(prefsManager.getUserId());
        btnEdit.setVisibility(isOwner ? View.VISIBLE : View.GONE);
        btnMarkResolved.setVisibility(isOwner ? View.VISIBLE : View.GONE);

        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + item.getContactPhone()));
            startActivity(intent);
        });

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditItemActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        });

        btnMarkResolved.setOnClickListener(v -> {
            item.setResolved(true);
            prefsManager.updateItem(item);
            Toast.makeText(this, "Marked as resolved!", Toast.LENGTH_SHORT).show();
            finish();
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() { finish(); return true; }
}