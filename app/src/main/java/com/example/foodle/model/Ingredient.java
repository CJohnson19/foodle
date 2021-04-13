package com.example.foodle.model;

import java.io.Serializable;
import java.text.DecimalFormat;
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

    /**
     * Sets current quantity to itself plus `quantity`.
     * @param quantity Quantity to add to this
     */
    public void addQuantity(Quantity<?> quantity) {
        this.quantity = this.quantity.add((Quantity<T>) quantity);
    }

    /**
     * Sets current quantity to itself minus `quantity`.
     * If parameter `quantity` is greater than current quantity, clamps to 0.
     * @param quantity Quantity to subtract from this
     */
    public void subtractQuantity(Quantity<?> quantity) {
        this.quantity = this.quantity.subtract((Quantity<T>) quantity);
        if(this.quantity.getValue().doubleValue() < 0) {
            this.quantity = this.quantity.add(this.quantity.negate());
        }
    }

    /**
     * Returns true if there is at least some amount of ingredient
     * @return boolean representing greater than 0 relationship
     */
    public boolean hasNonZeroQuantity() {
        return this.quantity.getValue().doubleValue() > 0;
    }

    /**
     * Returns true if `this` has equal or greater quantity than `quantity`.
     * @param quantity Quantity to compare to `this`
     * @return boolean representing greater than or equal relationship
     */
    public boolean hasMoreQuantityThan(Quantity<?> quantity) {
        return this.quantity.subtract((Quantity<T>)quantity).getValue().intValue() >= 0;
    }

    /**
     * Returns true if `this` has equal or greater quantity than `ingredient`'s quantity
     * @param ingredient ingredient's quantity to compare to `this`
     * @return boolean representing greater than or equal relationship
     */
    public boolean hasMoreQuantityThan(Ingredient<?> ingredient) {
        return hasMoreQuantityThan(ingredient.getQuantity());
    }

    /**
     * Gets formatted string for an ingredient's quantity
     * @return String with quantity truncated to 2 decimal points
     */
    public String getQuantityString() {
        DecimalFormat df = new DecimalFormat("0.##");
        double amount = this.quantity.getValue().doubleValue();
        return String.format("%s %s", df.format(amount), quantity.getUnit().toString());
    }

    /**
     * Gets formatted string for an ingredient's quantity with unit `unit`
     * @return String with quantity truncated to 2 decimal points after converting to `unit`
     */
    public String getQuantityStringWithUnit(Unit<T> unit) {
        double amount = this.quantity.to(unit).getValue().doubleValue();
        return String.format("%.2f %s", amount, unit.toString());
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
