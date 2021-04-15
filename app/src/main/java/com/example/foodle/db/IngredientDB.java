package com.example.foodle.db;

import android.util.Pair;

import com.example.foodle.R;
import com.example.foodle.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.AmountOfSubstance;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

import systems.uom.common.USCustomary;
import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import static systems.uom.common.Imperial.QUART;
import static systems.uom.common.USCustomary.*;

public class IngredientDB {
    /**
     * This is important because it adds aliases to the service
     * we use to parse strings into quantities.
     */
    public static void initializeIngredientDB() {
        SimpleUnitFormat.getInstance().alias(TABLESPOON, "tbsp");
        SimpleUnitFormat.getInstance().alias(TEASPOON, "tsp");
        SimpleUnitFormat.getInstance().alias(CUP, "cup");
        SimpleUnitFormat.getInstance().alias(PINT, "pt");
        SimpleUnitFormat.getInstance().alias(PINT, "pint");
        SimpleUnitFormat.getInstance().alias(QUART, "qt");
        SimpleUnitFormat.getInstance().alias(QUART, "quart");
        SimpleUnitFormat.getInstance().alias(FLUID_OUNCE, "floz");
        SimpleUnitFormat.getInstance().alias(OUNCE, "ounce");
        SimpleUnitFormat.getInstance().alias(LITER, "liter");
    }
    public static List<Ingredient<?>> getStandardIngredients() {
        List<Ingredient<?>> ingredients = new ArrayList<>();
        Quantity<Volume> saltQuantity = Quantities.getQuantity(25, TABLESPOON);
        ingredients.add(new Ingredient<Volume>(10,
                "Salt",
                "The saltiest of them all!",
                saltQuantity,
                R.drawable.salt,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        Quantity<Volume> butterQuantity = Quantities.getQuantity(2, CUP);
        ingredients.add(new Ingredient<Volume>(1,
                "Butter",
                "The finest butter ever!",
                butterQuantity,
                R.drawable.butter,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n"));

        Quantity<Volume> sugarQuantity = Quantities.getQuantity(1, CUP);
        ingredients.add(new Ingredient<Volume>(1,
                "Sugar",
                "The sweetest of them all!",
                sugarQuantity,
                R.drawable.sugar,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        Quantity<Mass> bananaAmount = Quantities.getQuantity(2, POUND);
        ingredients.add(new Ingredient<Mass>(1,
                "Bananas",
                "The best of them all!",
                bananaAmount,
                R.drawable.bananas,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        Quantity<Volume> milkAmount = Quantities.getQuantity(2, GALLON_LIQUID);
        ingredients.add(new Ingredient<Volume>(1,
                "Milk",
                "The finest milk ever!",
                milkAmount,
                R.drawable.milk,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        Quantity<Mass> cheeseAmount = Quantities.getQuantity(2, OUNCE);
        ingredients.add(new Ingredient<Mass>(1,
                "Cheese",
                "Cheesiest of them all!",
                cheeseAmount,
                R.drawable.cheese,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        Quantity<Mass> chickenAmount = Quantities.getQuantity(2, POUND);
        ingredients.add(new Ingredient<Mass>(1,
                "Chicken",
                "Chicken breasts, legs, etc.",
                cheeseAmount,
                R.drawable.chicken,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        return ingredients;
    }

    public static List<Pair<Unit<?>, String>> getMassUnits() {
        List<Pair<Unit<?>,String>> out = new ArrayList<>();
        out.add(new Pair<>(Units.KILOGRAM, "kg"));
        out.add(new Pair<>(POUND, "lb"));
        out.add(new Pair<>(OUNCE, "oz"));
        out.add(new Pair<>(Units.GRAM, "g"));
        return out;
    }
    public static List<Pair<Unit<?>, String>> getVolumeUnits() {
        List<Pair<Unit<?>,String>> out = new ArrayList<>();
        out.add(new Pair<>(Units.LITRE, "L"));
        out.add(new Pair<>(CUP, "cup"));
        out.add(new Pair<>(FLUID_OUNCE, "fl oz"));
        out.add(new Pair<>(TABLESPOON, "Tbsp"));
        out.add(new Pair<>(PINT, "pt"));
        out.add(new Pair<>(QUART, "qt"));
        out.add(new Pair<>(GALLON_LIQUID, "Gal"));
        return out;
    }
}
