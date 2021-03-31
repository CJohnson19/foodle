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

import com.example.foodle.db.IngredientDB;
import com.example.foodle.model.Ingredient;
import com.example.foodle.model.IngredientAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddIngredientFragment extends Fragment {

    List<Ingredient<?>> ingredientList;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addingredient, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ingredientList = IngredientDB.getStandardIngredients();
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), ingredientList, getChildFragmentManager());
        recyclerView.setAdapter(ingredientAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }

    /**
     * To Use Later
     * @param someFragment
     */
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
