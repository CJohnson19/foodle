package com.example.foodle;

import android.Manifest;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodle.model.FilterCategory;
import com.example.foodle.model.Pantry;
import com.example.foodle.model.PantryViewModel;
import com.example.foodle.ui.AddMethodFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FilterCategory category = FilterCategory.RECIPES;
    private boolean have = true;
    private PantryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
        viewModel = new ViewModelProvider(this).get(PantryViewModel.class);
        viewModel.getPantry().setValue(new Pantry());
        viewModel.getPantry().observe(this, pantry -> {
            System.out.println("Change detected in main activity");
        });

        int cameraPermission = this.checkSelfPermission(Manifest.permission.CAMERA);
        int REQUEST_PERMISSION = 100;
        if(cameraPermission != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_PERMISSION
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchActivity.class)));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(true);
        Context activity = getApplicationContext();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchIntent = new Intent(activity, SearchActivity.class);
                searchIntent.setAction(Intent.ACTION_SEARCH);
                searchIntent.putExtra(SearchManager.QUERY, query);
                searchIntent.putExtra(SearchActivity.CATEGORY, category);
                searchIntent.putExtra(SearchActivity.HAVE, have ? "true" : "false");
                searchIntent.putExtra(SearchActivity.PANTRY, viewModel.getPantry().getValue());
                startActivity(searchIntent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFrag = null;

                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFrag = new HomeFragment();
                            category = FilterCategory.RECIPES;
                            have = true;
                            break;
                        case R.id.nav_search:
                            selectedFrag = new SearchFragment();
                            category = FilterCategory.RECIPES;
                            have = false;
                            break;
                        case R.id.nav_add:
                            //dispatchTakePictureIntent();
                            selectedFrag = new AddMethodFragment();
                            break;
                        case R.id.nav_pantry:
                            selectedFrag = new PantryFragment();
                            category = FilterCategory.INGREDIENTS;
                            have = true;
                            break;
                        case R.id.nav_profile:
                            selectedFrag = new PreferencesFragment();
                            category = FilterCategory.RECIPES;
                            have = false;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFrag).commit();
                    // return clicked item
                    return true;
                }
            };

}