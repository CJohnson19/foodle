package com.example.foodle.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.foodle.R;
import com.example.foodle.db.IngredientDB;
import com.example.foodle.model.Ingredient;
import com.example.foodle.model.Pantry;
import com.example.foodle.model.PantryViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.stream.Collectors;

import javax.measure.Quantity;
import javax.measure.Unit;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

public class EditIngredientDialogFragment extends DialogFragment {
    Context mContext;
    Ingredient<?> ingredient;
    boolean isAdd;
    PantryViewModel pantryViewModel;

    public EditIngredientDialogFragment(Ingredient<?> ingredient, PantryViewModel pantryViewModel, boolean isAdd) {
        mContext = getActivity();
        this.ingredient = ingredient;
        this.pantryViewModel = pantryViewModel;
        this.isAdd = isAdd;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_edit_ingredient_dialog, null);
        NumberPicker np = (NumberPicker) dialogView.findViewById(R.id.numberPicker);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        if(isAdd) {
            np.setValue(0);
        } else {
            np.setValue(ingredient.getQuantity().getValue().intValue());
        }
        Spinner sp = (Spinner) dialogView.findViewById(R.id.unitSpinner);
        List<Pair<Unit<?>,String>> values;
        // Check if volume or mass based ingredient
        if(ingredient.getQuantity().getUnit().isCompatible(Units.LITRE)){
            values = IngredientDB.getVolumeUnits();
        } else {
            values = IngredientDB.getMassUnits();
        }
        List<String> labels = values.stream().map(v -> v.second).collect(Collectors.toList());
        List<Unit<?>> units = values.stream().map(v -> v.first).collect(Collectors.toList());
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                labels);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        int pos = units.indexOf(ingredient.getQuantity().getUnit());
        sp.setSelection(pos == -1 ? 0 : pos);
        sp.setSelected(true);

        if(isAdd) {
            alertDialogBuilder.setView(dialogView)
                    .setPositiveButton(R.string.add_ingredient_dialog_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Quantity<?> newQuantity = (Quantity<?>)Quantities.getQuantity(np.getValue(), units.get(sp.getSelectedItemPosition()));
                            ingredient.setQuantity(newQuantity);
                            pantryViewModel.getPantry().getValue().addIngredient(ingredient);
                            pantryViewModel.setPantry(pantryViewModel.getPantry().getValue());
                            String displayText = String.format("%s of %s added to your pantry", ingredient.getQuantityString(), ingredient.getTitle());

                            Snackbar addSnackbar = Snackbar.make(getActivity().findViewById(R.id.coordinator_layout), displayText, Snackbar.LENGTH_SHORT);
                            addSnackbar.setAction("UNDO", v -> {
                                pantryViewModel.getPantry().getValue().subtractIngredient(ingredient);
                                pantryViewModel.setPantry(pantryViewModel.getPantry().getValue());
                            });
                            addSnackbar.show();
                            EditIngredientDialogFragment.this.getDialog().dismiss();
                        }
                    })
                    .setNegativeButton(R.string.add_ingredient_dialog_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditIngredientDialogFragment.this.getDialog().cancel();
                        }
                    });
        } else {
            alertDialogBuilder.setView(dialogView)
                    .setPositiveButton(R.string.edit_ingredient_dialog_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Quantity<?> oldQuantity = ingredient.getQuantity();
                            String oldQuantityString = ingredient.getQuantityString();
                            ingredient.setQuantity((Quantity<?>)Quantities.getQuantity(np.getValue(), units.get(sp.getSelectedItemPosition())));
                            pantryViewModel.getPantry().setValue(pantryViewModel.getPantry().getValue());
                            String displayText = String.format("%s changed from %s to %s", ingredient.getTitle(), oldQuantityString, ingredient.getQuantityString());

                            Snackbar editSnackbar = Snackbar.make(getActivity().findViewById(R.id.coordinator_layout), displayText, Snackbar.LENGTH_SHORT);
                            editSnackbar.setAction("UNDO", v -> {
                                ingredient.setQuantity(oldQuantity);
                                pantryViewModel.getPantry().setValue(pantryViewModel.getPantry().getValue());
                            });
                            editSnackbar.show();
                            EditIngredientDialogFragment.this.getDialog().dismiss();
                        }
                    })
                    .setNegativeButton(R.string.edit_ingredient_dialog_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditIngredientDialogFragment.this.getDialog().cancel();
                        }
                    });
        }
        return alertDialogBuilder.create();
    }
}
