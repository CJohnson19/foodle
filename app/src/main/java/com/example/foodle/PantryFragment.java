package com.example.foodle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodle.db.IngredientDB;
import com.example.foodle.model.Ingredient;
import com.example.foodle.model.IngredientAdapter;
import com.example.foodle.model.Pantry;
import com.example.foodle.model.PantryViewModel;
import com.example.foodle.model.Recipe;

import java.util.List;

public class PantryFragment extends Fragment {
    List<Ingredient<?>> ingredientList;
    RecyclerView recyclerView;
    private PantryViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pantry, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.pantryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ingredientList = IngredientDB.getStandardIngredients();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PantryViewModel.class);
        viewModel.getPantry().observe(getViewLifecycleOwner(), pantry -> {
            // Update the list UI
            ingredientList = pantry.getIngredients();
            updatePantry();
        });
        updatePantry();
    }


    public void updatePantry() {
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), ingredientList, getChildFragmentManager());
        recyclerView.setAdapter(ingredientAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
