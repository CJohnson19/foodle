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

import com.example.foodle.model.Recipe;
import com.example.foodle.model.RecipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Recipe> recipeList;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /**
         * Set up Recycler View
         */

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recipeList = populateRecipes();
        RecipeAdapter recipeAdapter = new RecipeAdapter(getContext(), recipeList);
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    /***
     * Populates the recipe list with the database of recipe
     * @return recipeList
     */
    private List<Recipe> populateRecipes() {
        ArrayList<Recipe> recipeList = new ArrayList<>();
        recipeList.add(new Recipe(1,
                "Authentic Mexican Chicken Soft Tacos",
                "One of the best authentic mexican recipes out there!",
                25,
                R.drawable.tacos,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));

        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\nLOrem Ipsum Lorem Imputs \n"));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        recipeList.add(new Recipe(1,
                "New York Neapolitan Pizza",
                "Top Chef approved mouthwatering recipe",
                45,
                R.drawable.pizza,
                "LOrem Ipsum Lorem Imputs \n sdflsdfj sdkfjsdfj sdlkfj\n"));
        return recipeList;
    }
}
