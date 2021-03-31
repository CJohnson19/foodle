package com.example.foodle.db;

import com.example.foodle.R;
import com.example.foodle.model.Ingredient;
import com.example.foodle.model.Recipe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Time;
import javax.measure.quantity.Volume;


import tech.units.indriya.quantity.CompoundQuantity;
import tech.units.indriya.quantity.Quantities;

import static systems.uom.common.USCustomary.CUP;
import static systems.uom.common.USCustomary.GALLON_LIQUID;
import static systems.uom.common.USCustomary.MINUTE;
import static systems.uom.common.USCustomary.OUNCE;
import static systems.uom.common.USCustomary.POUND;
import static systems.uom.common.USCustomary.TABLESPOON;

public class RecipeDB {
    public static List<Recipe> getStandardRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        List<Ingredient<?>> tacoIngredients = new ArrayList<>();
        Quantity<Volume> saltQuantity = Quantities.getQuantity(10, TABLESPOON);
        tacoIngredients.add(new Ingredient<Volume>(10,
                "Salt",
                "The saltiest of them all!",
                saltQuantity,
                R.drawable.salt,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        Quantity<Volume> butterQuantity = Quantities.getQuantity(1, CUP);
        tacoIngredients.add(new Ingredient<Volume>(1,
                "Butter",
                "The finest butter ever!",
                butterQuantity,
                R.drawable.butter,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n"));

        Quantity<Volume> sugarQuantity = Quantities.getQuantity(2, CUP);
        tacoIngredients.add(new Ingredient<Volume>(1,
                "Sugar",
                "The sweetest of them all!",
                sugarQuantity,
                R.drawable.sugar,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        CompoundQuantity<Time> tacoCookTime = CompoundQuantity.of(Quantities.getQuantity(25, MINUTE));
        recipeList.add(new Recipe(1,
                "Authentic Mexican Chicken Soft Tacos",
                "One of the best authentic mexican recipes out there!",
                tacoCookTime,
                R.drawable.tacos,
                "Ingredients: \n Salt - 15 units\n Butter - 10 units \n Sugar - 10 units\n Recipe: \nCook butter for 10 minutes \n Add Salt to the pan\n Add Sugar to the pan \n Add Sugar to the pan\nAdd Sugar to the pan  \n Heat the mixture for 15 minutes \n Bake the mixture for 10 minutes\n  Rest in room temperature for 5 minutes \n Add pizza sauce \n Add sugar \n Add basil \n Add Cheese of your choide ",
                tacoIngredients));

        CompoundQuantity<Time> pizzaCookTime = CompoundQuantity.of(Quantities.getQuantity(45, MINUTE));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                pizzaCookTime,
                R.drawable.pizza,
                "Ingredients: \n Salt - 15 units\n Butter - 10 units \n Sugar - 10 units\n Recipe: \nCook butter for 10 minutes \n Add Salt to the pan \n Add Sugar to the pan \n Add Sugar to the pan\nAdd Sugar to the pan  \n Heat the mixture for 15 minutes \n Bake the mixture for 10 minutes\n  Rest in room temperature for 5 minutes \n Add pizza sauce \n Add sugar \n Add basil \n Add Cheese of your choide ",
                tacoIngredients));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                pizzaCookTime,
                R.drawable.pizza,
                "Ingredients: \n Salt - 15 units\n Butter - 10 units \n Sugar - 10 units\n Recipe: \nCook butter for 10 minutes \n Add Salt to the pan\n Add Sugar to the pan \n Add Sugar to the pan\nAdd Sugar to the pan  \n Heat the mixture for 15 minutes \n Bake the mixture for 10 minutes\n  Rest in room temperature for 5 minutes \n Add pizza sauce \n Add sugar \n Add basil \n Add Cheese of your choide ",
                tacoIngredients));
        return recipeList;
    }
}
