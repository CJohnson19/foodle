package com.example.foodle.model;

import com.example.foodle.db.IngredientDB;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pantry implements Serializable {
    private List<Ingredient<?>> ingredients;
    private HashMap<String, Ingredient<?>> ingredientHashMap;

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
        if(!canCookRecipe(recipe)) {
            throw new IllegalArgumentException("Not enough ingredients to cook recipe");
        }
        // We have all the ingredients
        subtractIngredients(recipe.getIngredientList());
        removeEmptyIngredients();
    }

    public List<Ingredient<?>> getIngredients() {
        return this.ingredients;
    }

    public void removeEmptyIngredients() {
        this.ingredients = ingredients.stream()
                .filter(Ingredient::hasNonZeroQuantity)
                .collect(Collectors.toList());
    }

    public void setPantry(Pantry pantry) {
        Pantry pantryCopy = new Pantry(pantry.ingredients);
        this.ingredients = pantryCopy.ingredients;
        this.ingredientHashMap = pantryCopy.ingredientHashMap;
    }

    public boolean canCookRecipe(Recipe recipe) {
        for(Ingredient<?> i : recipe.getIngredientList()) {
            if(!hasEnoughOfIngredient(i)) {
                return false;
            }
        }
        return true;
    }

    public List<Recipe> getCookNowRecipes(List<Recipe> recipes) {
        return recipes.stream().filter(this::canCookRecipe).collect(Collectors.toList());
    }

    public boolean hasEnoughOfIngredient(Ingredient<?> ingredient) {
        Ingredient<?> currIngredient = ingredientHashMap.getOrDefault(ingredient.getTitle(), null);
        if(currIngredient == null) {
            return false;
        }
        return currIngredient.hasMoreQuantityThan(ingredient.getQuantity());
    }

    public void addPantry(Pantry pantry) throws IllegalArgumentException {
        addIngredients(pantry.ingredients);
    }

    public void addIngredients(List<Ingredient<?>> ingredients) throws IllegalArgumentException {
        for(Ingredient<?> i : ingredients) {
            addIngredient(i);
        }
    }

    public void addIngredient(Ingredient<?> ingredient) throws IllegalArgumentException {
        if(ingredientHashMap.containsKey(ingredient.getTitle())) {
            Ingredient<?> currIngredient = ingredientHashMap.get(ingredient.getTitle());
            if(currIngredient!= null && currIngredient.getQuantity().getUnit()
                    .isCompatible(ingredient.getQuantity().getUnit())) {
                currIngredient.addQuantity(ingredient.getQuantity());
                ingredientHashMap.put(ingredient.getTitle(), currIngredient);
            } else {
                throw new IllegalArgumentException("New ingredient is not compatible with old ingredient");
            }
        } else {
            ingredientHashMap.put(ingredient.getTitle(), ingredient);
        }
    }

    public void subtractIngredients(List<Ingredient<?>> ingredients) throws IllegalArgumentException {
        for (Ingredient<?> i : ingredients) {
            this.subtractIngredient(i);
        }
    }

    public void subtractIngredient(Ingredient<?> ingredient) throws IllegalArgumentException {
        if(ingredientHashMap.containsKey(ingredient.getTitle())) {
            Ingredient<?> currIngredient = ingredientHashMap.get(ingredient.getTitle());
            if(currIngredient != null && currIngredient.getQuantity().getUnit()
                    .isCompatible(ingredient.getQuantity().getUnit())) {
                currIngredient.subtractQuantity(ingredient.getQuantity());
            } else {
                throw new IllegalArgumentException("New ingredient is not compatible with old ingredient");
            }
        } else {
            throw new IllegalArgumentException("Ingredient not found in pantry");
        }
    }

    public Ingredient<?> getIngredientByTitle(String title) {
        return ingredientHashMap.get(title);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null) {
            return false;
        }
        if(getClass() != o.getClass()) {
            return false;
        }
        Pantry other = (Pantry) o;
        return Objects.equals(ingredients, other.getIngredients());
    }
}
