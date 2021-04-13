package com.example.foodle.model;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodle.R;
import com.example.foodle.ui.EditIngredientDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{

    /***
     * mCtx -> Context for the layout
     * RecipeList -> Database of all the recipes
     */
    private Context mCtx;
    private FragmentManager fragmentManager;
    private List<Ingredient<?>> ingredientList;
    private PantryViewModel pantryViewModel;
    private boolean isAdd;
    int mExpandedPosition = -1;


    public IngredientAdapter(Context mCtx, List<Ingredient<?>> ingredientList, FragmentManager fragmentManager, PantryViewModel pantryViewModel, boolean isAdd) {
        this.mCtx = mCtx;
        this.ingredientList = ingredientList;
        this.fragmentManager = fragmentManager;
        this.pantryViewModel = pantryViewModel;
        this.isAdd = isAdd;
    }

    /***
     * onCreateViewHolder -> creates the card holder for the recipes
     * onBindViewHolder -> populates one instance with data about a recipe
     * getItemCount -> Implements for extending RecyclerView.Adapter
     */

    @NotNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_ingredients, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        //getting the product of the specified position
        Ingredient<?> ingredient = ingredientList.get(position);
        holder.textViewTitle.setText(ingredient.getTitle());
        holder.textViewShortDesc.setText(ingredient.getDescription());
        holder.textViewQuantity.setText(ingredient.getQuantityString());
        if(isAdd) {
            holder.textViewQuantity.setVisibility(View.GONE);
            holder.actionButton.setImageResource(R.drawable.baseline_add_24);
        } else {
            holder.actionButton.setImageResource(R.drawable.baseline_edit_24);
        }
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(ingredient.getImage()));
        final boolean isExpanded = position==mExpandedPosition;
        holder.details.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.details.setText(ingredient.getDetails());
        holder.actionButton.setOnClickListener(v -> {
            DialogFragment editFragment = new EditIngredientDialogFragment(ingredient, pantryViewModel, isAdd);
            editFragment.show(fragmentManager, "edit");
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(position);
                //notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    /***
     * Class definition of IngredientView holder which contains the attributes for our ingredient cards
     */
    class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewQuantity;
        ImageView imageView;
        TextView details;
        ImageButton actionButton;

        public IngredientViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewDescription);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            imageView = itemView.findViewById(R.id.imageView);
            details = itemView.findViewById(R.id.textDetails);
            actionButton = itemView.findViewById(R.id.actionButton);
        }
    }
}
