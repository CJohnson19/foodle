package com.example.foodle.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodle.R;
import com.example.foodle.ui.EditIngredientFragment;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{

    /***
     * mCtx -> Context for the layout
     * RecipeList -> Database of all the recipes
     */
    private Context mCtx;
    private FragmentManager fragmentManager;
    private List<Ingredient> ingredientList;
    int mExpandedPosition = -1;


    public IngredientAdapter(Context mCtx, List<Ingredient> ingredientList, FragmentManager fragmentManager) {
        this.mCtx = mCtx;
        this.ingredientList = ingredientList;
        this.fragmentManager = fragmentManager;
    }

    /***
     * onCreateViewHolder -> creates the card holder for the recipes
     * onBindViewHolder -> populates one instance with data about a recipe
     * getItemCount -> Implements for extending RecyclerView.Adapter
     */

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_ingredients, null);
        System.out.println("Opening View Holder");
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        //getting the product of the specified position
        Ingredient ingredient = ingredientList.get(position);
        holder.textViewTitle.setText(ingredient.getTitle());
        holder.textViewShortDesc.setText(ingredient.getDescription());
        holder.textViewDuration.setText(String.valueOf(ingredient.getDuration()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(ingredient.getImage()));
        final boolean isExpanded = position==mExpandedPosition;
        holder.details.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.button.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.editButton.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.details.setText(ingredient.getDetails());
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(position);
            }
        });
        System.out.println("Displaying view Card");
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    /***
     * Class definition of IngredientView holder which contains the attributes for our ingredient cards
     */
    class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewDuration;
        ImageView imageView;
        TextView details;
        Button button, editButton;

        public IngredientViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewDescription);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
            imageView = itemView.findViewById(R.id.imageView);
            details = itemView.findViewById(R.id.textDetails);
            button = itemView.findViewById(R.id.button2);
            editButton = itemView.findViewById(R.id.editButton);
            editButton.setOnClickListener(v -> {
                DialogFragment editFragment = new EditIngredientFragment();
                editFragment.show(fragmentManager, "edit");
            });
        }
    }
}
