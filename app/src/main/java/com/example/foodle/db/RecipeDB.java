package com.example.foodle.db;

import com.example.foodle.R;
import com.example.foodle.model.Ingredient;
import com.example.foodle.model.Recipe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RecipeDB {
    public static List<Recipe> getStandardRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        List<Ingredient> tacoIngredients = new ArrayList<>();
        tacoIngredients.add(new Ingredient(1,
                "Salt",
                "The saltiest of them all!",
                15,
                R.drawable.salt,
                " \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        tacoIngredients.add(new Ingredient(1,
                "Butter",
                "The finest butter ever!",
                15,
                R.drawable.butter,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n"));
        recipeList.add(new Recipe(1,
                "Authentic Mexican Chicken Soft Tacos",
                "One of the best authentic mexican recipes out there!",
                25,
                R.drawable.tacos,
                "Ingredients: \n Salt - 15 units\n Butter - 10 units \n Sugar - 10 units\n Recipe: \nCook butter for 10 minutes \n Add Salt to the pan\n Add Sugar to the pan \n Add Sugar to the pan\nAdd Sugar to the pan  \n Heat the mixture for 15 minutes \n Bake the mixture for 10 minutes\n  Rest in room temperature for 5 minutes \n Add pizza sauce \n Add sugar \n Add basil \n Add Cheese of your choide ",
                tacoIngredients));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "Ingredients: \n Salt - 15 units\n Butter - 10 units \n Sugar - 10 units\n Recipe: \nCook butter for 10 minutes \n Add Salt to the pan \n Add Sugar to the pan \n Add Sugar to the pan\nAdd Sugar to the pan  \n Heat the mixture for 15 minutes \n Bake the mixture for 10 minutes\n  Rest in room temperature for 5 minutes \n Add pizza sauce \n Add sugar \n Add basil \n Add Cheese of your choide ",
                tacoIngredients));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "Ingredients: \n Salt - 15 units\n Butter - 10 units \n Sugar - 10 units\n Recipe: \nCook butter for 10 minutes \n Add Salt to the pan\n Add Sugar to the pan \n Add Sugar to the pan\nAdd Sugar to the pan  \n Heat the mixture for 15 minutes \n Bake the mixture for 10 minutes\n  Rest in room temperature for 5 minutes \n Add pizza sauce \n Add sugar \n Add basil \n Add Cheese of your choide ",
                tacoIngredients));
        return recipeList;
    }
}
