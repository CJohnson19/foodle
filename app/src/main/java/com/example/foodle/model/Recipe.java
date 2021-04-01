package com.example.foodle.model;

import android.widget.Button;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import javax.measure.Quantity;
import javax.measure.quantity.Time;

import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.CompoundQuantity;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

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
    private CompoundQuantity<Time> duration;
    private int image;
    private String details;
    private List<Ingredient<?>> ingredientList;

    public Recipe(int id, String title, String description, CompoundQuantity<Time> duration, int image, String details, List<Ingredient<?>> ingredients) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.image = image;
        this.details = details;
        this.ingredientList = ingredients;
    }

    public Recipe(int id, String title, String description, Quantity<Time> duration, int image, String details, List<Ingredient<?>> ingredients) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = CompoundQuantity.of(duration);
        this.image = image;
        this.details = details;
        this.ingredientList = ingredients;
    }

    public Recipe(int id, String title, String description, String duration, int image, String details, List<Ingredient<?>> ingredients) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = CompoundQuantity.of(Quantities.getQuantity(duration).asType(Time.class));
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

    public CompoundQuantity<Time> getDuration() {
        return duration;
    }

    public String getDurationString() {
        StringBuilder out = new StringBuilder();
        int hours = duration.to(Units.HOUR).getValue().intValue();
        if(hours > 1) {
            out.append(hours);
            out.append(" hours ");
        }
        else if(hours > 0) {
            out.append("1 hour ");
        }
        int minutes = duration.to(Units.MINUTE).subtract(Quantities.getQuantity(hours, Units.HOUR)).getValue().intValue();
        if(minutes > 0) {
            out.append(minutes);
            out.append(" min");
        }
        return out.toString().trim();
    }

    public int getImage() {
        return image;
    }

    public String getDetails() { return details; }

    public List<Ingredient<?>> getIngredientList() {
        return ingredientList;
    }

    public int getDurationMinutes() {
        return duration.to(Units.MINUTE).getValue().intValue();
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
        Recipe other = (Recipe) o;
        return Objects.equals(id, other.getId()) &&
                Objects.equals(title, other.getTitle()) &&
                Objects.equals(description, other.getDescription()) &&
                durationsEqual(other) &&
                Objects.equals(image, other.getImage()) &&
                Objects.equals(details, other.getDetails());
    }

    private boolean durationsEqual(Recipe other) {
        return getDurationMinutes() == other.getDurationMinutes();
    }
}
