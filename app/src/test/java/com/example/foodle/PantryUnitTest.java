package com.example.foodle;

import com.example.foodle.db.IngredientDB;
import com.example.foodle.model.Ingredient;
import com.example.foodle.model.Pantry;
import com.example.foodle.model.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

import tech.units.indriya.quantity.Quantities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class PantryUnitTest {
    @Before
    public void setUp() {
        IngredientDB.initializeIngredientDB();
    }
    @Test
    public void PantryEqualityReturnsTrue() {
        Pantry p1 = new Pantry();
        Pantry p2 = new Pantry();
        assertEquals(p1, p2);
    }
    @Test
    public void PantryEqualityReturnsFalse() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient<Volume>(1, "Test", "Desc", "1 L", 1, "desc"));
        Pantry p1 = new Pantry();
        Pantry p2 = new Pantry(ingredients);
        assertNotEquals(p1, p2);
    }
    @Test
    public void PantrySetsUpStandardIngredients() {
        Pantry p = new Pantry();
        assertEquals(IngredientDB.getStandardIngredients(), p.getIngredients());
    }
    @Test
    public void PantryRemovesEmptyIngredients() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient<Volume>(1, "Test", "Desc", "0 L", 1, "desc"));
        Pantry p = new Pantry(ingredients);
        p.removeEmptyIngredients();
        assert(p.getIngredients().isEmpty());
    }
    @Test
    public void PantryCanCookReturnsTrueWhenEnoughIngredients() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient<Volume>(1, "Test", "Desc", "1 L", 1, "desc"));
        Pantry p = new Pantry(ingredients);
        Recipe r = new Recipe(1, "Title", "desc", "45 min", 1, "det", ingredients);
        assert(p.canCookRecipe(r));
    }
    @Test
    public void PantryCanCookReturnsFalseWhenNotEnoughIngredients() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient<Volume>(1, "Test", "Desc", "0 L", 1, "desc"));
        Pantry p = new Pantry(ingredients);
        ingredients.add(new Ingredient<Volume>(1, "Test 2", "Desc", "1 L", 1, "desc"));
        Recipe r = new Recipe(1, "Title", "desc", "45 min", 1, "det", ingredients);
        assertFalse(p.canCookRecipe(r));
    }
    @Test
    public void PantryHasEnoughReturnsTrueOnSingleIngredient() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        ingredients.add(i1);
        Pantry p = new Pantry(ingredients);
        assert(p.hasEnoughOfIngredient(i1));
    }
    @Test
    public void PantryHasEnoughReturnsFalseOnSingleIngredient() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        ingredients.add(i1);
        Pantry p = new Pantry(ingredients);
        Ingredient<Volume> i2 = new Ingredient<>(1, "Test 2", "Desc", "1 L", 1, "desc");
        assertFalse(p.hasEnoughOfIngredient(i2));
    }
    @Test(expected = IllegalArgumentException.class)
    public void PantryCookThrowsExceptionWhenNotEnoughIngredients() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        ingredients.add(i1);
        Pantry p = new Pantry(ingredients);
        Ingredient<Volume> i2 = new Ingredient<>(1, "Test 2", "Desc", "1 L", 1, "desc");
        ingredients.add(i2);
        Recipe r = new Recipe(1, "Title", "desc", "45 min", 1, "det", ingredients);
        p.cookRecipe(r);
    }
    @Test
    public void PantrySubtractIngredientWorks() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        ingredients.add(i1);
        Pantry p = new Pantry(ingredients);
        p.subtractIngredient(i1);
        assert(!p.getIngredientByTitle(i1.getTitle()).hasNonZeroQuantity());
    }
    @Test
    public void PantryAddIngredientWorks() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        Quantity<Volume> expected = i1.getQuantity().add(i1.getQuantity());
        ingredients.add(i1);
        Pantry p = new Pantry(ingredients);
        p.addIngredient(i1);
        assert(p.getIngredientByTitle(i1.getTitle()).getQuantity().asType(Volume.class).isEquivalentTo(expected));
    }
    @Test
    public void PantryAddIngredientsWorks() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        Ingredient<Volume> i2 = new Ingredient<>(1, "Test 2", "Desc", "2 L", 1, "desc");
        ingredients.add(i1);
        ingredients.add(i2);
        Quantity<Volume> expected1 = i1.getQuantity().add(i1.getQuantity());
        Quantity<Volume> expected2 = i2.getQuantity().add(i2.getQuantity());
        Pantry p = new Pantry(ingredients);
        p.addIngredients(ingredients);
        assert(p.getIngredientByTitle(i1.getTitle()).getQuantity().asType(Volume.class).isEquivalentTo(expected1));
        assert(p.getIngredientByTitle(i2.getTitle()).getQuantity().asType(Volume.class).isEquivalentTo(expected2));
    }
    @Test
    public void PantrySubtractIngredientsWorks() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        Ingredient<Volume> i2 = new Ingredient<>(1, "Test 2", "Desc", "2 L", 1, "desc");
        ingredients.add(i1);
        ingredients.add(i2);
        Quantity<Volume> expected1 = i1.getQuantity().subtract(i1.getQuantity());
        Quantity<Volume> expected2 = i2.getQuantity().subtract(i2.getQuantity());
        Pantry p = new Pantry(ingredients);
        p.subtractIngredients(ingredients);
        assert(p.getIngredientByTitle(i1.getTitle()).getQuantity().asType(Volume.class).isEquivalentTo(expected1));
        assert(p.getIngredientByTitle(i2.getTitle()).getQuantity().asType(Volume.class).isEquivalentTo(expected2));
    }
    @Test(expected = IllegalArgumentException.class)
    public void PantrySubtractIngredientThrowsIllegalArgumentWhenIncompatible() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        Ingredient<Mass> incompatible = new Ingredient<>(1, "Test", "Desc", "2 lb", 1, "desc");
        ingredients.add(i1);
        Pantry p = new Pantry(ingredients);
        p.subtractIngredient(incompatible);
    }
    @Test(expected = IllegalArgumentException.class)
    public void PantrySubtractIngredientThrowsIllegalArgumentWhenNotPresent() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        Ingredient<Volume> notPresent = new Ingredient<>(1, "Test 2", "Desc", "2 L", 1, "desc");
        ingredients.add(i1);
        Pantry p = new Pantry(ingredients);
        p.subtractIngredient(notPresent);
    }
    @Test
    public void PantrySubtractIngredientGoesToZero() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        Ingredient<Volume> biggerQuantity = new Ingredient<>(1, "Test", "Desc", "2 L", 1, "desc");
        Quantity<Volume> expected = Quantities.getQuantity("0 L").asType(Volume.class);
        ingredients.add(i1);
        Pantry p = new Pantry(ingredients);
        p.subtractIngredient(biggerQuantity);
        assert(expected.isEquivalentTo(p.getIngredientByTitle(i1.getTitle()).getQuantity().asType(Volume.class)));
    }
    @Test
    public void PantryCookRecipeRemovesEmpty() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient<Volume>(1, "Test", "Desc", "1 L", 1, "desc"));
        Pantry p = new Pantry(ingredients);
        Recipe r = new Recipe(1, "Title", "desc", "45 min", 1, "det", ingredients);
        p.cookRecipe(r);
        assert(p.getIngredients().isEmpty());
    }
    @Test
    public void PantryGetCookNowRecipesReturnsValidRecipes() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        Recipe r = new Recipe(1, "Title", "desc", "45 min", 1, "det", ingredients);
        ingredients.add(i1);
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(r);
        Pantry p = new Pantry(ingredients);
        assertEquals(recipes, p.getCookNowRecipes(recipes));
    }
    @Test
    public void PantryGetCookNowRecipesRemovesInvalidRecipes() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        List<Ingredient<?>> ingredients2 = new ArrayList<>();
        Ingredient<Volume> i1 = new Ingredient<>(1, "Test", "Desc", "1 L", 1, "desc");
        Ingredient<Volume> biggerQuantity = new Ingredient<>(1, "Test", "Desc", "2 L", 1, "desc");
        ingredients.add(i1);
        ingredients2.add(biggerQuantity);
        Recipe r = new Recipe(1, "Title", "desc", "45 min", 1, "det", ingredients2);
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(r);
        Pantry p = new Pantry(ingredients);
        assert(p.getCookNowRecipes(recipes).isEmpty());
    }
}
