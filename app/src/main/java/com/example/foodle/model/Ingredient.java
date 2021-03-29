package com.example.foodle.model;

import android.widget.Button;

public class Ingredient implements Filterable {
    /***
     * Attributes
     *
     * id -> unique id for each recipe
     * title -> Name of the dish
     * description -> A short description of the dish
     * quantity -> How many units are present
     * image -> image of the food
     */
    private int id;
    private String title;
    private String description;
    private int quantity;
    private int image;
    private String details;

    public Ingredient(int id, String title, String description, int quantity, int image, String details) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
        this.details = details;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public String getDetails() { return details; }
}
