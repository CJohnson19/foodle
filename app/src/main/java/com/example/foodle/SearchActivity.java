package com.example.foodle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.foodle.db.IngredientDB;
import com.example.foodle.db.RecipeDB;
import com.example.foodle.model.FilterCategory;
import com.example.foodle.model.FilterOption;
import com.example.foodle.model.Ingredient;
import com.example.foodle.model.IngredientAdapter;
import com.example.foodle.model.Pantry;
import com.example.foodle.model.PantryViewModel;
import com.example.foodle.model.Recipe;
import com.example.foodle.model.RecipeAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class SearchActivity extends AppCompatActivity {
    /**
     * Constant to refer to when sending an intent.
     * Represents which category to filter by, from {@link FilterCategory}.
     */
    public final static String CATEGORY = "CATEGORY";

    /**
     * Constant to refer to when sending an intent.
     * Represents if the page should only present options the user has
     * or if it should be every option.
     */
    public final static String HAVE = "HAVE";

    /**
     * Constant to refer to when sending an intent.
     * Represents if the page should only present options the user has
     * or if it should be every option.
     */
    public final static int RESULT = 10;

    /**
     *
     */
    public final static String PANTRY = "PANTRY";

    /**
     * The recycler view that will present ingredients or
     * recipes in a list.
     */
    private RecyclerView recyclerView;

    /**
     * All {@link FilterOption} of {@link Recipe} type that are displayed
     * to the user through a {@link Chip}.
     */
    private List<FilterOption<Recipe>> recipeFilters;

    /**
     * All {@link FilterOption} of {@link Ingredient} type that are displayed
     * to the user through a {@link Chip}.
     */
    private List<FilterOption<Ingredient<?>>> ingredientFilters;

    /**
     * All recipes to be searched
     */
    private List<Recipe> recipes;

    /**
     * All ingredients to be searched
     */
    private List<Ingredient<?>> ingredients;

    /**
     * Which category to display, either recipes or ingredients.
     */
    private FilterCategory category = FilterCategory.RECIPES;

    /**
     * Query sent through an intent.
     */
    private String query;

    /**
     * Boolean to represent if we are to display only what the user has or not.
     */
    boolean have = false;

    /**
     * Holds pantry
     */
    private PantryViewModel viewModel;

    /**
     * Holds the search widget to be cleared later.
     */
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra(SearchActivity.PANTRY, viewModel.getPantry().getValue());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        viewModel = new ViewModelProvider(this).get(PantryViewModel.class);
        // Get default options
        recipes = RecipeDB.getStandardRecipes();
        recipeFilters = FilterOption.setupRecipeFilters();
        ingredients = IngredientDB.getStandardIngredients();
        ingredientFilters = FilterOption.setupIngredientFilters();

        // set up listeners so when categories are changed, the view is refreshed
        Chip ingredChip = findViewById(R.id.ingredient_chip);
        ingredChip.setOnClickListener((p) -> {
            category = FilterCategory.INGREDIENTS;
            setupFilters();
            performSearch();
        });
        Chip recipeChip = findViewById(R.id.recipe_chip);
        recipeChip.setOnClickListener((p) -> {
            category = FilterCategory.RECIPES;
            setupFilters();
            performSearch();
        });
        recyclerView = (RecyclerView) this.findViewById(R.id.search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            category = (FilterCategory) intent.getSerializableExtra(CATEGORY);
            have = Boolean.parseBoolean(intent.getStringExtra(HAVE));
            Pantry pantry = (Pantry) intent.getSerializableExtra(PANTRY);
            viewModel.getPantry().setValue(pantry);
            setupFilters();
            performSearch();
        }
    }

    /**
     * Checks which chips are currently active and applies all of their filters.
     */
    public void performSearch() {
        ChipGroup categoryChipGroup = findViewById(R.id.category_chip_group);
        category = categoryChipGroup.getCheckedChipId() == R.id.recipe_chip
                ? FilterCategory.RECIPES : FilterCategory.INGREDIENTS;
        if(category == FilterCategory.RECIPES) {
            Predicate<Recipe> filters = (Recipe r) -> r.getTitle().toLowerCase().contains(query)
                    || r.getDescription().toLowerCase().contains(query);
            for(FilterOption<Recipe> filter : recipeFilters) {
                filters = filters.and(filter.getCriteria());
            }
            List<Recipe> recipes_sort = recipes.stream()
                    .filter(filters)
                    .collect(Collectors.toList());
            RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipes_sort, viewModel.getPantry().getValue());
            recyclerView.setAdapter(recipeAdapter);
        } else {
            Predicate<Ingredient<?>> filters = (Ingredient<?> r) -> r.getTitle().toLowerCase().contains(query)
                    || r.getDescription().toLowerCase().contains(query);
            for(FilterOption<Ingredient<?>> filter : ingredientFilters) {
                filters = filters.and(filter.getCriteria());
            }
            List<Ingredient<?>> ingredients_sort = ingredients.stream()
                    .filter(filters)
                    .collect(Collectors.toList());
            IngredientAdapter ingredientAdapter = new IngredientAdapter(this, ingredients_sort, getSupportFragmentManager());

            recyclerView.setAdapter(ingredientAdapter);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Helper function when performSearch() can't be called due to losing
     * scope of the query class variable.
     * @param query the new query to filter by
     */
    public void performSearch(String query) {
        this.query = query;
        performSearch();
    }
    /**
     * Sets up the filters for either the ingredients or recipes by
     * creating a {@link Chip} for each {@link FilterOption} that is
     * relevant. This includes an onClickListener to perform the search
     * over again when a new filter is applied.
     */
    public void setupFilters() {
        ChipGroup filterChipGroup = findViewById(R.id.filter_chip_group);
        filterChipGroup.removeAllViews();
        Chip ingredChip = findViewById(R.id.ingredient_chip);
        Chip recipeChip = findViewById(R.id.recipe_chip);
        if (category == FilterCategory.RECIPES) {
            ingredChip.setChecked(false);
            recipeChip.setChecked(true);
            recipeFilters.forEach((p) -> {
               Chip chip = p.getChip(this);
               chip.setOnClickListener((r) -> {
                   p.toggleActive();
                   performSearch();
               });
               filterChipGroup.addView(chip);
            });
        } else {
            ingredChip.setChecked(true);
            recipeChip.setChecked(false);
            ingredientFilters.forEach((p) -> {
                Chip chip = p.getChip(this);
                chip.setOnClickListener((r) -> {
                    p.toggleActive();
                    performSearch();
                });
                filterChipGroup.addView(chip);
            });
        }
    }

    /**
     * Adds searchview back to the app bar
     * @param menu the toolbar
     * @return boolean representing success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setQuery(this.query, false);
        searchView.setSubmitButtonEnabled(false);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * Override the default {@link SearchView} behavior on submit.
             * Will now do nothing, as we will update on change
             * @param query text to search
             * @return true to signal we have overridden this method
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            /**
             * Overrides the default {@link SearchView} behavior when the text changes.
             * Will now perform another search when change is detected.
             * @param newText text to search.
             * @return true ot signal we have overridden this method.
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
        return true;
    }
}