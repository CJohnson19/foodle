package com.example.foodle.model;

import android.content.Context;

import com.example.foodle.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

public class FilterOption {
    private String name;

    public FilterOption(String name) {
        this.name = name;
    }

    public void addFilterOptionToChipGroup(Context context, ChipGroup chipGroup, boolean checked) {
        Chip chip = new Chip(context);
        ChipDrawable drawable = ChipDrawable.createFromAttributes(context, null, 0, R.style.Widget_MaterialComponents_Chip_Filter);
        chip.setChipDrawable(drawable);

        chip.setText(this.name);
        chip.setChipBackgroundColorResource(R.color.grey);
        chip.setChecked(checked);

        chipGroup.addView(chip);
    }
}
