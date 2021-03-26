package com.example.foodle;

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


public class AddFragment extends Fragment {

    List<Ingredient> ingredientList;
    RecyclerView recyclerView;

    private AddViewModel addViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add, container, false);
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

        ingredientList = populateRecipes();
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), ingredientList);
        recyclerView.setAdapter(ingredientAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    /***
     * Populates the recipe list with the database of recipe
     * @return recipeList
     */
    private List<Ingredient> populateRecipes() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
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
        return ingredientList;
    }
}
