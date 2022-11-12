package com.example.foodrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Models.ExtendedIngredient;
import com.example.foodrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IngredientsApdapter extends RecyclerView.Adapter<IngredientsApdapter.IngredientsViewHolder>{
    Context context;
    int layoutId;
    ArrayList<ExtendedIngredient> list;

    public IngredientsApdapter(Context context, int layoutId, ArrayList<ExtendedIngredient> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        ExtendedIngredient ingredient = list.get(position);
        holder.tvIngredientsName.setText(ingredient.getName());
        holder.tvIngredientsName.setSelected(true);
        holder.tvIngredientsQuantity.setText(ingredient.getOriginal());
        holder.tvIngredientsQuantity.setSelected(true);
        Picasso.get().load(context.getString(R.string.ingredient_image_url_250x250) + ingredient.getImage()).into(holder.imageViewIngredients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class IngredientsViewHolder extends RecyclerView.ViewHolder{
        TextView tvIngredientsQuantity, tvIngredientsName;
        ImageView imageViewIngredients;
        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredientsQuantity = (TextView) itemView.findViewById(R.id.tvIngredientsQuantity);
            tvIngredientsName = (TextView) itemView.findViewById(R.id.tvIngredientsName);
            imageViewIngredients = (ImageView) itemView.findViewById(R.id.imageViewIngredients);
        }
    }
}
