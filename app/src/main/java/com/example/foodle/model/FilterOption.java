package com.example.foodle.model;

import android.content.Context;

import com.example.foodle.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * FilterOption represents a possible option to filter by in a search.
 * This can be a {@link Recipe} or {@link Ingredient} specification.
 *
 * @see Recipe
 * @see Ingredient
 * @see Filterable
 */
public class FilterOption<T extends Filterable> {
    /**
     * Text to display on the filter
     */
    private final String name;
    /**
     * A Function that takes a {@link Filterable} and will return
     * a boolean based on whether or not it fulfills some criteria.
     */
    private final Predicate<T> criteria;

    /**
     * Boolean to represent if the filter is active.
     */
    private boolean isActive;


    /**
     * A possible filter a user may use in a potentially non-exclusive manner.
     * @param name Text to display when presented to the user.
     * @param criteria A function that will determine if a {@link Filterable} fulfills the filter.
     * @param checked Whether or not the filter should be applied by default.
     */
    public FilterOption(String name, Predicate<T> criteria, boolean checked) {
        this.name = name;
        this.criteria = criteria;
        this.isActive = checked;
    }

    /**
     * Creates a {@link Chip} to represent this filter option.
     * Will be checked based on isActive, and will have text of name
     * @param context The calling context
     */
    public Chip getChip(Context context) {
        Chip chip = new Chip(context);
        ChipDrawable drawable = ChipDrawable.createFromAttributes(context, null, 0, R.style.Widget_MaterialComponents_Chip_Filter);
        chip.setChipDrawable(drawable);

        chip.setText(this.name);
        chip.setChipBackgroundColorResource(R.color.grey);
        chip.setChecked(this.isActive);
        return chip;
    }

    /**
     * Used to see if a {@link Filterable} satisfies the requirement by this option.
     * If the chip is not active, it will return a predicate which always resolves to true,
     * otherwise, it will return the real predicate in criteria.
     * @return a {@link Predicate} to be applied to a {@link Filterable}
     */
    public Predicate<T> getCriteria() {
        if(!this.isActive) {
            return (p) -> true;
        } else {
            return criteria;
        }
    }

    /**
     * Toggles internal active tracker
     */
    public void toggleActive() {
        this.isActive = !this.isActive;
    }

    /**
     * Basic mock recipe filters
     * @return a List of {@link FilterOption}s for {@link Recipe}s.
     */
    public static List<FilterOption<Recipe>> setupRecipeFilters() {
        List<FilterOption<Recipe>> filters = new ArrayList<>();
        filters.add(new FilterOption<>("Quick Meals",
                (r) -> r.getDuration() < 30, false));
        filters.add(new FilterOption<>("Mexican",
                (r) -> r.getDescription().toLowerCase().contains("mexican"), false));
        filters.add(new FilterOption<>("Vegetarian",
                (r) -> r.getDescription().toLowerCase().contains("vegetarian"), false));
        filters.add(new FilterOption<>("Pizza",
                (r) -> r.getTitle().toLowerCase().contains("pizza"), false));
        return filters;
    }

    /**
     * Basic mock ingredient filters
     * @return a List of {@link FilterOption}s for {@link Ingredient}s.
     */
    public static List<FilterOption<Ingredient>> setupIngredientFilters() {
        List<FilterOption<Ingredient>> filters = new ArrayList<>();
        filters.add(new FilterOption<>("Vegetables",
                (i) -> i.getDescription().toLowerCase().contains("vegetable"), false));
        filters.add(new FilterOption<>("Meats",
                (i) -> i.getDescription().toLowerCase().contains("meat"), false));
        filters.add(new FilterOption<>("Dairy",
                (i) -> i.getTitle().toLowerCase().contains("cheese")
                        || i.getDescription().toLowerCase().contains("milk"), false));
        return filters;
    }
}
