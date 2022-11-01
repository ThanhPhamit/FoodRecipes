package com.example.foodrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Models.Equipment;
import com.example.foodrecipes.Models.Ingredient;
import com.example.foodrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InstructionStepEquipmentsAdapter extends RecyclerView.Adapter<InstructionStepEquipmentsAdapter.InstructionStepEquipmentsViewHolder>{
    Context context;
    int layoutId;
    ArrayList<Equipment> list;

    public InstructionStepEquipmentsAdapter(Context context, int layoutId, ArrayList<Equipment> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepEquipmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepEquipmentsAdapter.InstructionStepEquipmentsViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepEquipmentsViewHolder holder, int position) {
        Equipment equipment  = list.get(position);
        holder.tvInstructionStepEquipmentName.setText(equipment.getName());
        holder.tvInstructionStepEquipmentName.setSelected(true);
        Picasso.get().load(context.getString(R.string.equipment_image_url_100x100) + equipment.getImage()).into(holder.imageViewInstructionStepEquipment);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InstructionStepEquipmentsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewInstructionStepEquipment;
        TextView tvInstructionStepEquipmentName;

        public InstructionStepEquipmentsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewInstructionStepEquipment = (ImageView) itemView.findViewById(R.id.imageViewInstructionStepEquipment);
            tvInstructionStepEquipmentName = (TextView) itemView.findViewById(R.id.tvInstructionStepEquipmentName);
        }
    }
}
