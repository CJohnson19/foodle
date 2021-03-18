package com.example.foodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFrag = null;

                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFrag = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            selectedFrag = new SearchFragment();
                            break;
                        case R.id.nav_add:
                            selectedFrag = new AddFragment();
                            break;
                        case R.id.nav_pantry:
                            selectedFrag = new PantryFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFrag = new PreferencesFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFrag).commit();
                    // return clicked item
                    return true;
                }
            };
}