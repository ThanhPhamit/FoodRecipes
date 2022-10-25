package com.example.foodrecipes.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Listeners.RecipeClickListener;
import com.example.foodrecipes.Models.Recipe;
import com.example.foodrecipes.Models.SimilarRecipe;
import com.example.foodrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapters extends RecyclerView.Adapter<RecipeAdapters.RandomRecipeViewHolder> {
    Activity context;
    int layoutId;
    ArrayList list;
    RecipeClickListener recipeClickListener;

    public RecipeAdapters(Activity context, int layoutId, ArrayList list, RecipeClickListener recipeClickListener) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
        this.recipeClickListener = recipeClickListener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false), layoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        switch (layoutId){
            case R.layout.list_random_recipe:
                Recipe recipe = (Recipe) list.get(position);

                holder.tvTitle.setText(recipe.getTitle());
                holder.tvTitle.setSelected(true);
                holder.tvServings.setText(recipe.getServings() + " Servings");

                holder.tvLikes.setText(recipe.getAggregateLikes() + " Likes");
                holder.tvTimes.setText(recipe.getReadyInMinutes() + " Minutes");
                holder.tvDes.setText(recipe.getSummary());

                //using to load image
                Picasso.get().load(recipe.getImage()).into(holder.imgFood);

                holder.random_list_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recipeClickListener.onRecipeClicked(String.valueOf(recipe.getId()));
                    }
                });
                break;
            case R.layout.list_similar_recipe:
                SimilarRecipe similarRecipe = (SimilarRecipe) list.get(position);

                holder.tvTitle.setText(similarRecipe.getTitle());
                holder.tvTitle.setSelected(true);
                holder.tvServings.setText(similarRecipe.getServings() + " Servings");

                //using to load image
                String imageSize = "312x231";
                Picasso.get().load(context.getString(R.string.get_recipe_image) + similarRecipe.getId() + "-" + imageSize + "." + similarRecipe.getImageType()).into(holder.imgFood);

                holder.random_list_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recipeClickListener.onRecipeClicked(String.valueOf(similarRecipe.getId()));
                    }
                });
                break;
            default:
                Log.d("ERROR", "Dont find layout for recipe adapter");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
        CardView random_list_container;
        TextView tvTitle, tvServings, tvLikes, tvTimes, tvDes;
        ImageView imgFood;


        public RandomRecipeViewHolder(@NonNull View itemView, int layoutId) {
            super(itemView);
            switch (layoutId){
                case R.layout.list_random_recipe:
                    random_list_container = (CardView) itemView.findViewById(R.id.random_recipe_list_container);
                    tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
                    tvServings = (TextView) itemView.findViewById(R.id.tvFoodServings);
                    tvLikes = (TextView) itemView.findViewById(R.id.tvFoodLikes);
                    tvTimes = (TextView) itemView.findViewById(R.id.tvFoodTimes);
                    imgFood = (ImageView) itemView.findViewById(R.id.imgFood);
                    tvDes = (TextView) itemView.findViewById(R.id.tvFoodDescription);
                    break;
                case R.layout.list_similar_recipe:
                    random_list_container = (CardView) itemView.findViewById(R.id.cardViewSimilarRecipe);
                    tvTitle = (TextView) itemView.findViewById(R.id.textViewSimilarRecipeTitle);
                    tvServings = (TextView) itemView.findViewById(R.id.textViewSimilarRecipeServings);
                    imgFood = (ImageView) itemView.findViewById(R.id.imageViewSimilarRecipe);
                    break;
                default:
                    Log.d("ERROR", "Dont find layout for recipe adapter");
                    break;
            }
        }
    }

}
