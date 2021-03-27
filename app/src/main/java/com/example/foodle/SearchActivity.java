package com.example.foodle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.foodle.model.FilterCategory;
import com.example.foodle.model.FilterOption;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import kotlin.NotImplementedError;

public class SearchActivity extends AppCompatActivity {
    public final static String CATEGORY = "CATEGORY";
    public final static String HAVE = "HAVE";
    RecyclerView recyclerView;
    List<FilterOption> filterOptionList;
    FilterCategory category = FilterCategory.RECIPES;
    boolean have = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            category = (FilterCategory) intent.getSerializableExtra(CATEGORY);
            have = Boolean.parseBoolean(intent.getStringExtra(HAVE));
            setupFilters();
            performSearch(query);
        }
    }

    public void performSearch(String query) {
        ChipGroup categoryChipGroup = findViewById(R.id.category_chip_group);
        category = categoryChipGroup.getCheckedChipId() == R.id.recipe_chip
                ? FilterCategory.RECIPES : FilterCategory.INGREDIENTS;
    }

    public void setupFilters() {
        // Ingredient chip is enabled by default, so we only change it if
        // the user wants recipes
        if (category == FilterCategory.RECIPES) {
            Chip ingredChip = findViewById(R.id.ingredient_chip);
            ingredChip.setChecked(false);
            Chip recipeChip = findViewById(R.id.recipe_chip);
            recipeChip.setChecked(true);
        }
        ChipGroup filterChipGroup = findViewById(R.id.filter_chip_group);
        for(int i = 0; i < 10; i++) {
            FilterOption option = new FilterOption("Filter " + i);
            option.addFilterOptionToChipGroup(this, filterChipGroup, false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}