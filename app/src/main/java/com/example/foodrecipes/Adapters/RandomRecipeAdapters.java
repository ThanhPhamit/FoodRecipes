package com.example.foodrecipes.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Listeners.RecipeClickListener;
import com.example.foodrecipes.Models.Recipe;
import com.example.foodrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RandomRecipeAdapters extends RecyclerView.Adapter<RandomRecipeAdapters.RandomRecipeViewHolder> {
    Activity context;
    int layoutId;
    ArrayList<Recipe> list;
    RecipeClickListener recipeClickListener;

    public RandomRecipeAdapters(Activity context, int layoutId, ArrayList<Recipe> list, RecipeClickListener recipeClickListener) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
        this.recipeClickListener = recipeClickListener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        Recipe recipe = list.get(position);

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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
        CardView random_list_container;
        TextView tvTitle, tvServings, tvLikes, tvTimes, tvDes;
        ImageView imgFood;


        public RandomRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            random_list_container = (CardView) itemView.findViewById(R.id.random_recipe_list_container);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvServings = (TextView) itemView.findViewById(R.id.tvFoodServings);
            tvLikes = (TextView) itemView.findViewById(R.id.tvFoodLikes);
            tvTimes = (TextView) itemView.findViewById(R.id.tvFoodTimes);
            imgFood = (ImageView) itemView.findViewById(R.id.imgFood);
            tvDes = (TextView) itemView.findViewById(R.id.tvFoodDescription);
        }
    }

}
