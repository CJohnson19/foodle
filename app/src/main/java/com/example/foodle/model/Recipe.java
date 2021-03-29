package com.example.foodle.model;

import android.widget.Button;

import java.util.List;

public class Recipe implements Filterable {
    /***
     * Attributes
     *
     * id -> unique id for each recipe
     * title -> Name of the dish
     * description -> A short description of the dish
     * duration -> Cooking time
     * image -> image of the food
     */
    private int id;
    private String title;
    private String description;
    private int duration;
    private int image;
    private String details;
    private List<Ingredient> ingredientList;

    public Recipe(int id, String title, String description, int duration, int image, String details, List<Ingredient> ingredients) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.image = image;
        this.details = details;
        this.ingredientList = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public int getImage() {
        return image;
    }

    public String getDetails() { return details; }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

}
