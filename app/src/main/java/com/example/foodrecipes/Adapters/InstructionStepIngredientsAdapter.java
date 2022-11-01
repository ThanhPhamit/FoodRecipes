package com.example.foodrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Models.Ingredient;
import com.example.foodrecipes.Models.Step;
import com.example.foodrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InstructionStepIngredientsAdapter extends RecyclerView.Adapter<InstructionStepIngredientsAdapter.InstructionStepIngredientsViewHolder>{
    Context context;
    int layoutId;
    ArrayList<Ingredient> list;

    public InstructionStepIngredientsAdapter(Context context, int layoutId, ArrayList<Ingredient> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepIngredientsAdapter.InstructionStepIngredientsViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepIngredientsViewHolder holder, int position) {
        Ingredient ingredient  = list.get(position);
        holder.tvInstructionStepEquipmentName.setText(ingredient.getName());
        holder.tvInstructionStepEquipmentName.setSelected(true);
        Picasso.get().load(context.getString(R.string.ingredient_image_url_100x100) + ingredient.getImage()).into(holder.imageViewInstructionStepEquipment);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InstructionStepIngredientsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewInstructionStepEquipment;
        TextView tvInstructionStepEquipmentName;

        public InstructionStepIngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewInstructionStepEquipment = (ImageView) itemView.findViewById(R.id.imageViewInstructionStepEquipment);
            tvInstructionStepEquipmentName = (TextView) itemView.findViewById(R.id.tvInstructionStepEquipmentName);
        }
    }
}
