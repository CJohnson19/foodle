package com.example.foodle.model;

import java.io.Serializable;
import java.util.Objects;

import javax.measure.Quantity;
import javax.measure.Unit;

import tech.units.indriya.format.SimpleQuantityFormat;


public class Ingredient<T extends Quantity<T>> implements Filterable, Serializable {
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
    private Quantity<T> quantity;
    private int image;
    private String details;

    public Ingredient(int id, String title, String description, Quantity<T> quantity, int image, String details) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
        this.details = details;
    }

    public Ingredient(int id, String title, String description, String quantity, int image, String details) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = (Quantity<T>) SimpleQuantityFormat.getInstance().parse(quantity);
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

    public Quantity<T> getQuantity() {
        return this.quantity;
    }

    public Quantity<T> getQuantityWithUnit(Unit<T> unit) {
        return quantity.to(unit);
    }

    public void setQuantity(Quantity<?> quantity) {
        this.quantity = (Quantity<T>) quantity;
    }

    public void addQuantity(Quantity<T> quantity) {
        this.quantity = this.quantity.add(quantity);
    }

    public void subtractQuantity(Quantity<?> quantity) {
        this.quantity = this.quantity.subtract((Quantity<T>) quantity);
    }

    public boolean hasNonZeroQuantity() {
        return this.quantity.getValue().doubleValue() > 0;
    }

    public boolean hasMoreQuantityThan(Quantity<?> quantity) {
        return this.quantity.subtract((Quantity<T>)quantity).getValue().intValue() >= 0;
    }

    public String getQuantityString() {
        return this.quantity.toString();
    }

    public String getQuantityStringWithUnit(Unit<T> unit) {
        return this.quantity.to(unit).toString();
    }

    public int getImage() {
        return image;
    }

    public String getDetails() { return details; }

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
        try {
            Ingredient<T> other = (Ingredient<T>) o;
            return Objects.equals(id, other.getId()) &&
                    Objects.equals(title, other.getTitle()) &&
                    Objects.equals(description, other.getDescription()) &&
                    quantity.isEquivalentTo( other.getQuantity()) &&
                    Objects.equals(image, other.getImage()) &&
                    Objects.equals(details, other.getDetails());
        } catch(Exception e) {
            return false;
        }
    }
}
