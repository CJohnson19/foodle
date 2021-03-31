package com.example.foodle;

import com.example.foodle.db.IngredientDB;
import com.example.foodle.model.Ingredient;
import com.example.foodle.model.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static systems.uom.common.USCustomary.POUND;

public class RecipeUnitTest {
    private List<Ingredient<?>> getIngredients() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Quantity<Mass> i1Mass = Quantities.getQuantity(1, POUND);
        Quantity<Mass> i2Mass = Quantities.getQuantity(1, POUND);
        ingredients.add(new Ingredient<Mass>(1, "Test1", "Here", i1Mass, 1, "Desc"));
        ingredients.add(new Ingredient<Mass>(1, "Test2", "Here", i2Mass, 1, "Desc"));
        return ingredients;
    }

    @Before
    public void setUp() {
        IngredientDB.initializeIngredientDB();
    }

    @Test
    public void RecipeEqualityReturnsTrue() {
        Recipe r1 = new Recipe(1, "Recipe 1", "Desc 1", "45 min", 1, "Details", getIngredients());
        Recipe r2 = new Recipe(1, "Recipe 1", "Desc 1", Quantities.getQuantity(45, Units.MINUTE), 1, "Details", getIngredients());
        assertEquals(r1, r2);
    }

    @Test
    public void RecipeEqualityReturnsFalseWithDifferentDurations() {
        Recipe r1 = new Recipe(1, "Recipe 1", "Desc 1", Quantities.getQuantity(40, Units.MINUTE), 1, "Details", getIngredients());
        Recipe r2 = new Recipe(1, "Recipe 1", "Desc 1", Quantities.getQuantity(45, Units.MINUTE), 1, "Details", getIngredients());
        assertNotEquals(r1, r2);
    }

    @Test
    public void RecipeDurationStringMinutes() {
        Recipe r1 = new Recipe(1, "Recipe 1", "Desc 1", Quantities.getQuantity(45, Units.MINUTE), 1, "Details", getIngredients());
        assertEquals("45 min", r1.getDurationString());
    }

    @Test
    public void RecipeDurationStringOneHour() {
        Recipe r1 = new Recipe(1, "Recipe 1", "Desc 1", Quantities.getQuantity(60, Units.MINUTE), 1, "Details", getIngredients());
        assertEquals("1 hour", r1.getDurationString());
    }
    @Test
    public void RecipeDurationStringOneHourAndMin() {
        Recipe r1 = new Recipe(1, "Recipe 1", "Desc 1", Quantities.getQuantity(65, Units.MINUTE), 1, "Details", getIngredients());
        assertEquals("1 hour 5 min", r1.getDurationString());
    }
    @Test
    public void RecipeDurationStringMultipleHours() {
        Recipe r1 = new Recipe(1, "Recipe 1", "Desc 1", Quantities.getQuantity(120, Units.MINUTE), 1, "Details", getIngredients());
        assertEquals("2 hours", r1.getDurationString());
    }
    @Test
    public void RecipeDurationStringMultipleHoursAndMin() {
        Recipe r1 = new Recipe(1, "Recipe 1", "Desc 1", Quantities.getQuantity(125, Units.MINUTE), 1, "Details", getIngredients());
        assertEquals("2 hours 5 min", r1.getDurationString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void RecipeDurationStringThrowsError() {
        Recipe r1 = new Recipe(1, "Recipe 1", "Desc 1", "100 foobar", 1, "Details", getIngredients());
    }
}
