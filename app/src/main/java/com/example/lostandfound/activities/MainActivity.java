package com.example.lostandfound.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.lostandfound.R;
import com.example.lostandfound.fragments.HomeFragment;
import com.example.lostandfound.fragments.ProfileFragment;
import com.example.lostandfound.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        FloatingActionButton fab = findViewById(R.id.fab_post);

        loadFragment(new HomeFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment;
            int id = item.getItemId();
            if (id == R.id.nav_home)    fragment = new HomeFragment();
            else if (id == R.id.nav_search)  fragment = new SearchFragment();
            else                              fragment = new ProfileFragment();
            return loadFragment(fragment);
        });

        fab.setOnClickListener(v ->
                startActivity(new Intent(this, PostItemActivity.class)));
    }

    private boolean loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        return true;
    }
}