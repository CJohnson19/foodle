package com.example.foodle.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PantryViewModel extends ViewModel {
    private final MutableLiveData<Pantry> pantry = new MutableLiveData<Pantry>();
    public MutableLiveData<Pantry> getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
        pantry.setPantry(pantry);
    }
}
