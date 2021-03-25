package com.example.foodle.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodle.R;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    List<Recipe> recipeList;
    RecyclerView recyclerView;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

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
//        recipeList.add(new Recipe(1,
//                "Authentic Mexican Chicken Soft Tacos",
//                "One of the best authentic mexican recipes out there!",
//                25,
//                R.drawable.tacos));
//
//        recipeList.add(new Recipe(1,
//                "New York Neapolitan Pizza",
//                "Top Chef approved mouthwatering recipe",
//                45,
//                R.drawable.pizza));
        return recipeList;
    }
}