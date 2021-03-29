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
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
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
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n",
                tacoIngredients));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n",
                tacoIngredients));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n",
                tacoIngredients));
        return recipeList;
    }
}
