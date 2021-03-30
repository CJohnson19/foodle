package com.example.foodle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PreferencesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_preferences, container, false);
        Button FaqButton = view.findViewById(R.id.FAQbutton);
        FaqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFAQ(v);
            }
        });
        Button RestrictionButton = view.findViewById(R.id.RestrictionButton);
        RestrictionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRestrictions(v);
            }
        });
        return view;
    }

    public void getFAQ(View view){
        Intent intent = new Intent(getActivity(), FaqActivity.class);
        startActivity(intent);
    }

    public void getRestrictions(View view){
        Intent intent = new Intent(getActivity(), RestrictionsActivity.class);
        startActivity(intent);
    }
}
