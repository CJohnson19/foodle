package com.example.foodle.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import com.example.foodle.R;
import com.example.foodle.db.IngredientDB;
import com.example.foodle.model.Ingredient;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.measure.Unit;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

import tech.units.indriya.unit.Units;

public class EditIngredientFragment extends DialogFragment {
    Context mContext;
    Ingredient<?> ingredient;

    public EditIngredientFragment(Ingredient<?> ingredient) {
        mContext = getActivity();
        this.ingredient = ingredient;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_edit_ingredient_dialog, null);
        NumberPicker np = (NumberPicker) dialogView.findViewById(R.id.numberPicker);
        np.setMaxValue(100);
        np.setValue(ingredient.getQuantity().getValue().intValue());
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
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

        alertDialogBuilder.setView(dialogView)
                .setPositiveButton(R.string.edit_ingredient_dialog_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditIngredientFragment.this.getDialog().cancel();
                    }
                })
                .setNegativeButton(R.string.edit_ingredient_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditIngredientFragment.this.getDialog().cancel();
                    }
                });
        return alertDialogBuilder.create();
    }
}
