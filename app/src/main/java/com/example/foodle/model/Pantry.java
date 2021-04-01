package com.example.foodle.model;

import com.example.foodle.db.IngredientDB;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Pantry implements Serializable {
    private List<Ingredient<?>> ingredients;
    private HashMap<String, Ingredient<?>> ingredientHashMap = new HashMap<>();

    public Pantry(List<Ingredient<?>> ingredients) {
        this.ingredients = ingredients;
        this.ingredientHashMap = new HashMap<>();
        for (Ingredient<?> i : this.ingredients) {
            ingredientHashMap.put(i.getTitle(), i);
        }
    }

    public Pantry() {
        this.ingredients = IngredientDB.getStandardIngredients();
        this.ingredientHashMap = new HashMap<>();
        for (Ingredient<?> i : this.ingredients) {
            ingredientHashMap.put(i.getTitle(), i);
        }
    }

    public void cookRecipe(Recipe recipe) throws IllegalArgumentException {
        for (Ingredient<?> i : recipe.getIngredientList()) {
            try {
                if(!ingredientHashMap.getOrDefault(i.getTitle(), null).hasMoreQuantityThan(i.getQuantity())){
                    throw new IllegalArgumentException("Not enough ingredients");
                }
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("Not enough ingredients");
            }
        }
        // We have all the ingredients
        for (Ingredient<?> i : recipe.getIngredientList()) {
            Ingredient<?> currIngredient = ingredientHashMap.get(i.getTitle());
            currIngredient.subtractQuantity(i.getQuantity());
            ingredientHashMap.put(currIngredient.getTitle(), currIngredient);
        }
        this.ingredients = ingredients.stream()
                .filter(Ingredient::hasNonZeroQuantity)
                .collect(Collectors.toList());
    }

    public List<Ingredient<?>> getIngredients() {
        return this.ingredients;
    }

    public void setPantry(Pantry pantry) {
        Pantry pantryCopy = new Pantry(pantry.ingredients);
        this.ingredients = pantryCopy.ingredients;
        this.ingredientHashMap = pantryCopy.ingredientHashMap;
    }
}
