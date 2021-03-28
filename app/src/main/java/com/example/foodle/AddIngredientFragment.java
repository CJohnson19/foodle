package com.example.foodle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodle.model.Ingredient;
import com.example.foodle.model.IngredientAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddIngredientFragment extends Fragment {

    List<Ingredient> ingredientList;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addingredient, container, false);

        /**
         * Set up Recycler View
         */

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ingredientList = populateIngredients();
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), ingredientList);
        recyclerView.setAdapter(ingredientAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    /***
     * Populates the recipe list with the database of recipe
     * @return recipeList
     */
    private List<Ingredient> populateIngredients() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient(1,
                "Salt",
                "The saltiest of them all!",
                25,
                R.drawable.salt,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        ingredientList.add(new Ingredient(1,
                "Butter",
                "The finest butter ever!",
                45,
                R.drawable.butter,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n"));
        ingredientList.add(new Ingredient(1,
                "Sugar",
                "The sweetest of them all!",
                45,
                R.drawable.sugar,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        ingredientList.add(new Ingredient(1,
                "Bananas",
                "The best of them all!",
                45,
                R.drawable.bananas,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        ingredientList.add(new Ingredient(1,
                "Milk",
                "The finest milk ever!",
                45,
                R.drawable.milk,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        ingredientList.add(new Ingredient(1,
                "Cheese",
                "Cheesiest of them all!",
                45,
                R.drawable.cheese,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        return ingredientList;
    }
}
