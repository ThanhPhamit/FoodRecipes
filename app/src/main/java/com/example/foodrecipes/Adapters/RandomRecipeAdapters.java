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

import com.example.foodrecipes.Models.Recipe;
import com.example.foodrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RandomRecipeAdapters extends RecyclerView.Adapter<RandomRecipeAdapters.RandomRecipeViewHolder> {
    Activity context;
    int layoutId;
    ArrayList<Recipe> list;

    public RandomRecipeAdapters(Activity context, int layoutId, ArrayList<Recipe> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeAdapters.RandomRecipeViewHolder holder, int position) {
        final int pos = position;
        Recipe recipe = list.get(pos);
        Log.d("Log", recipe.title);

        holder.tvTitle.setText(recipe.title);
        holder.tvTitle.setSelected(true);
        holder.tvServings.setText(recipe.servings + " Servings");
        holder.tvLikes.setText(recipe.aggregateLikes + " Likes");
        holder.tvTimes.setText(recipe.readyInMinutes + " Minutes");
        holder.tvDes.setText(recipe.summary);
        //using to load image
        Picasso.get().load(recipe.image).into(holder.imgFood);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvServings, tvLikes, tvTimes, tvDes;
        ImageView imgFood;


        public RandomRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvServings = (TextView) itemView.findViewById(R.id.tvFoodServings);
            tvLikes = (TextView) itemView.findViewById(R.id.tvFoodLikes);
            tvTimes = (TextView) itemView.findViewById(R.id.tvFoodTimes);
            imgFood = (ImageView) itemView.findViewById(R.id.imgFood);
            tvDes = (TextView) itemView.findViewById(R.id.tvFoodDescription);
        }
    }

}
