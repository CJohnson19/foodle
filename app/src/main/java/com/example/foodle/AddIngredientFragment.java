package com.example.foodle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodle.ui.dashboard.Recipe;
import com.example.foodle.ui.dashboard.RecipeAdapter;

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
        final Button add = root.findViewById(R.id.cnfrm_ing);
        final Button cancel = root.findViewById(R.id.dny_ing);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new PantryFragment());
            }
        });

        return root;
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
