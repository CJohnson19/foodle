package com.example.foodle.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import com.example.foodle.R;

public class EditIngredientFragment extends DialogFragment {
    Context mContext;

    public EditIngredientFragment() {
        mContext = getActivity();
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
        Spinner sp = (Spinner) dialogView.findViewById(R.id.unitSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.unit_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

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
