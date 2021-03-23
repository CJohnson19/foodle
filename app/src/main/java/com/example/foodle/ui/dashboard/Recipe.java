package com.example.foodle.ui.dashboard;

public class Recipe {
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

    public Recipe(int id, String title, String description, int duration, int image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.image = image;
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
}
