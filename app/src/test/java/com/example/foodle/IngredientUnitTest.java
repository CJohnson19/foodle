package com.example.foodle;

import com.example.foodle.db.IngredientDB;
import com.example.foodle.model.Ingredient;

import org.junit.Before;
import org.junit.Test;

import javax.measure.Quantity;
import javax.measure.format.MeasurementParseException;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

import systems.uom.common.Imperial;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import static org.junit.Assert.assertFalse;
import static systems.uom.common.USCustomary.CUP;
import static systems.uom.common.USCustomary.FLUID_OUNCE;
import static systems.uom.common.USCustomary.LITER;
import static systems.uom.common.USCustomary.OUNCE;
import static systems.uom.common.USCustomary.PINT;
import static systems.uom.common.USCustomary.POUND;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static systems.uom.common.USCustomary.TABLESPOON;

public class IngredientUnitTest {
    @Before
    public void setUp() {
        IngredientDB.initializeIngredientDB();
    }
    @Test
    public void IngredientEqualityReturnsTrue() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(1, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        Ingredient<Mass> j = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        assertEquals(i, j);
    }
    @Test
    public void IngredientEqualityReturnsFalseWithDifferentTitle() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(1, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        Ingredient<Mass> j = new Ingredient<Mass>(1, "Test2", "Test", testQuantity, 1, "Test");
        assertNotEquals(i, j);
    }
    @Test
    public void IngredientEqualityReturnsFalseWithDifferentQuantity() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(1, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        Ingredient<Mass> j = new Ingredient<Mass>(1, "Test2", "Test", testQuantity, 1, "Test");
        j.setQuantity(Quantities.getQuantity(10, POUND));
        assertNotEquals(i, j);
    }
    @Test
    public void EqualityOverDifferentUnitTypesReturnsFalse() {
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", "1 lb", 1, "Test");
        Ingredient<Volume> j = new Ingredient<Volume>(1, "Test", "Test", "1 cup", 1, "Test");
        assertNotEquals(i, j);
    }
    @Test
    public void IngredientReturnsQuantityString() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(1, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        assertEquals("1 lb",i.getQuantityString());
    }
    @Test
    public void IngredientAddsQuantitySameUnit() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(10, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        i.addQuantity(Quantities.getQuantity(10, POUND));
        assertEquals(Quantities.getQuantity(20, POUND), i.getQuantity());
    }
    @Test
    public void IngredientAddsQuantityDifferentUnit() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(10, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        i.addQuantity(Quantities.getQuantity(16, OUNCE));
        assertEquals(Quantities.getQuantity(11, POUND), i.getQuantity());
    }
    @Test
    public void IngredientSubtractsQuantitySameUnit() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(10, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        i.subtractQuantity(Quantities.getQuantity(1, POUND));
        assertEquals(Quantities.getQuantity(9, POUND), i.getQuantity());
    }
    @Test
    public void IngredientSubtractsQuantityDifferentUnit() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(10, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        i.subtractQuantity(Quantities.getQuantity(16, OUNCE));
        assertEquals(Quantities.getQuantity(9, POUND), i.getQuantity());
    }
    @Test
    public void IngredientRecognizesEmpty() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(0, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        assert(i.hasNonZeroQuantity());
    }
    @Test
    public void IngredientRecognizesComparisonSameUnit() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(10, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        Quantity<Mass> testQuantity2 = Quantities.getQuantity(8, POUND);
        Ingredient<Mass> j = new Ingredient<Mass>(1, "Test", "Test", testQuantity2, 1, "Test");
        assert(i.hasMoreQuantityThan(j.getQuantity()));
        assertFalse(j.hasMoreQuantityThan(i.getQuantity()));
    }
    @Test
    public void IngredientRecognizesComparisonDifferentUnit() {
        Quantity<Mass> testQuantity = Quantities.getQuantity(10, POUND);
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", testQuantity, 1, "Test");
        Quantity<Mass> testQuantity2 = Quantities.getQuantity(8, OUNCE);
        Ingredient<Mass> j = new Ingredient<Mass>(1, "Test", "Test", testQuantity2, 1, "Test");
        assert(i.hasMoreQuantityThan(j.getQuantity()));
        assertFalse(j.hasMoreQuantityThan(i.getQuantity()));
    }
    @Test
    public void IngredientStringConstructorPound() {
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", "1 lb", 1, "Test");
        Quantity<Mass> expected = Quantities.getQuantity(1, POUND);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientStringConstructorOunce() {
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", "1 oz", 1, "Test");
        Quantity<Mass> expected = Quantities.getQuantity(1, OUNCE);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientStringConstructorOunceLong() {
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", "1 ounce", 1, "Test");
        Quantity<Mass> expected = Quantities.getQuantity(1, OUNCE);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientFlOzAliasAdded() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 floz", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, FLUID_OUNCE);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientTbspAliasAdded() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 tbsp", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, TABLESPOON);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientCupAliasAdded() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 cup", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, CUP);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientPintAliasAdded() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 pt", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, PINT);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientPintAliasAddedLong() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 pint", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, PINT);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientQuartAliasAdded() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 qt", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, Imperial.QUART);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientQuartAliasAddedLong() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 quart", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, Imperial.QUART);
        assertEquals(expected, i.getQuantity());
    }
    @Test(expected = MeasurementParseException.class)
    public void IngredientWithInvalidUnitThrowsMeasurementParseException() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 fooobar", 1, "Test");
    }
    @Test
    public void IngredientLitersConstructor() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 L", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, LITER);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientLitersAliasAddedLong() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 liter", 1, "Test");
        Quantity<Volume> expected = Quantities.getQuantity(1, LITER);
        assertEquals(expected, i.getQuantity());
    }
    @Test
    public void IngredientVolumeCompatibleWithLitre() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 liter", 1, "Test");
        assert(i.getQuantity().getUnit().isCompatible(Units.LITRE));
    }
    @Test
    public void IngredientMassIncompatibleWithLitre() {
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", "1 lb", 1, "Test");
        assertFalse(i.getQuantity().getUnit().isCompatible(Units.LITRE));
    }
    @Test
    public void IngredientMassCompatibleWithKG() {
        Ingredient<Mass> i = new Ingredient<Mass>(1, "Test", "Test", "1 lb", 1, "Test");
        assert(i.getQuantity().getUnit().isCompatible(Units.KILOGRAM));
    }
    @Test
    public void IngredientVolumeIncompatibleWithKG() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 liter", 1, "Test");
        assertFalse(i.getQuantity().getUnit().isCompatible(Units.KILOGRAM));
    }
    @Test
    public void IngredientComparisonWorks() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 liter", 1, "Test");
        Ingredient<Volume> j = new Ingredient<Volume>(1, "Test", "Test", "2 liter", 1, "Test");
        assert(j.hasMoreQuantityThan(i.getQuantity()));
    }
    @Test
    public void IngredientComparisonReturnsFalse() {
        Ingredient<Volume> i = new Ingredient<Volume>(1, "Test", "Test", "1 liter", 1, "Test");
        Ingredient<Volume> j = new Ingredient<Volume>(1, "Test", "Test", "2 liter", 1, "Test");
        assertFalse(i.hasMoreQuantityThan(j.getQuantity()));
    }
}
