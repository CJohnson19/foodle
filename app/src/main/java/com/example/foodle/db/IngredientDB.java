package com.example.foodle.db;

import com.example.foodle.R;
import com.example.foodle.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientDB {
    public static List<Ingredient> getStandardIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1,
                "Salt",
                "The saltiest of them all!",
                25,
                R.drawable.salt,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        ingredients.add(new Ingredient(1,
                "Butter",
                "The finest butter ever!",
                45,
                R.drawable.butter,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n"));
        ingredients.add(new Ingredient(1,
                "Sugar",
                "The sweetest of them all!",
                45,
                R.drawable.sugar,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        ingredients.add(new Ingredient(1,
                "Bananas",
                "The best of them all!",
                45,
                R.drawable.bananas,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        ingredients.add(new Ingredient(1,
                "Milk",
                "The finest milk ever!",
                45,
                R.drawable.milk,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        ingredients.add(new Ingredient(1,
                "Cheese",
                "Cheesiest of them all!",
                45,
                R.drawable.cheese,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        return ingredients;
    }
}
