package com.example.foodle.model;

/**
 * An interface to allow some polymorphism in {@link FilterOption}. No content here now, but
 * {@link Ingredient} and {@link Recipe} will extend this to limit {@link FilterOption} to only
 * these classes.
 *
 * @see Ingredient
 * @see Recipe
 * @see FilterOption
 */
public interface Filterable {
}
