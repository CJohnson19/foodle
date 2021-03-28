package com.example.foodle.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodle.R;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    /***
     * mCtx -> Context for the layout
     * RecipeList -> Database of all the recipes
     */
    private Context mCtx;
    private List<Recipe> recipeList;
    int mExpandedPosition = -1;


    public RecipeAdapter(Context mCtx, List<Recipe> recipeList) {
        this.mCtx = mCtx;
        this.recipeList = recipeList;
    }

    /***
     * onCreateViewHolder -> creates the card holder for the recipes
     * onBindViewHolder -> populates one instance with data about a recipe
     * getItemCount -> Implements for extending RecyclerView.Adapter
     */

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_recipe_card, null);
        System.out.println("Opening View Holder");
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        //getting the product of the specified position
        Recipe recipe = recipeList.get(position);
        holder.textViewTitle.setText(recipe.getTitle());
        holder.textViewShortDesc.setText(recipe.getDescription());
        holder.textViewDuration.setText(String.valueOf(recipe.getDuration()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(recipe.getImage()));
        final boolean isExpanded = position==mExpandedPosition;
        holder.details.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.button.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.details.setText(recipe.getDetails());
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
        return recipeList.size();
    }

    /***
     * Class definition of RecipeView holder which contains the attributes fo our recipe card
     */
    class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewDuration;
        ImageView imageView;
        TextView details;
        Button button;

        public RecipeViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewDescription);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
            imageView = itemView.findViewById(R.id.imageView);
            details = itemView.findViewById(R.id.textDetails);
            button = itemView.findViewById(R.id.button2);
        }
    }
}
