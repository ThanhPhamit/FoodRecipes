package com.example.foodrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Models.Instruction;
import com.example.foodrecipes.Models.Step;
import com.example.foodrecipes.R;

import java.util.ArrayList;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepAdapter.InstructionsStepViewHolder> {
    Context context;
    int layoutId;
    ArrayList<Step> list;

    public InstructionStepAdapter(Context context, int layoutId, ArrayList<Step> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepAdapter.InstructionsStepViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsStepViewHolder holder, int position) {
        Step step = list.get(position);
        holder.tvInstructionStepNumber.setText(String.valueOf(step.getNumber()));
        holder.tvInstructionStepTitle.setText(step.getStep());

        if (step.getIngredients().size() == 0){
            holder.tvIngredientsLabel.setVisibility(View.GONE);
            holder.recyclerViewInstructionIngredients.setVisibility(View.GONE);
        }else{
            holder.recyclerViewInstructionIngredients.setHasFixedSize(true);
            holder.recyclerViewInstructionIngredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            InstructionStepIngredientsAdapter instructionStepIngredientsAdapter = new InstructionStepIngredientsAdapter(context, R.layout.list_instruction_step_equipment, step.getIngredients());
            holder.recyclerViewInstructionIngredients.setAdapter(instructionStepIngredientsAdapter);
        }

        if (step.getEquipment().size() == 0){
            holder.tvEquipmentLabel.setVisibility(View.GONE);
            holder.recyclerViewInstructionEquipments.setVisibility(View.GONE);
        }else{
            holder.recyclerViewInstructionEquipments.setHasFixedSize(true);
            holder.recyclerViewInstructionEquipments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            InstructionStepEquipmentsAdapter instructionStepEquipmentsAdapter = new InstructionStepEquipmentsAdapter(context, R.layout.list_instruction_step_equipment, step.getEquipment());
            holder.recyclerViewInstructionEquipments.setAdapter(instructionStepEquipmentsAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InstructionsStepViewHolder extends RecyclerView.ViewHolder{
        TextView tvInstructionStepNumber, tvInstructionStepTitle, tvEquipmentLabel, tvIngredientsLabel;
        RecyclerView recyclerViewInstructionEquipments, recyclerViewInstructionIngredients;
        public InstructionsStepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInstructionStepNumber = (TextView) itemView.findViewById(R.id.tvInstructionStepNumber);
            tvInstructionStepTitle = (TextView) itemView.findViewById(R.id.tvInstructionStepTitle);
            tvEquipmentLabel = (TextView) itemView.findViewById(R.id.tvEquipmentLabel);
            tvIngredientsLabel = (TextView) itemView.findViewById(R.id.tvIngredientsLabel);
            recyclerViewInstructionEquipments = (RecyclerView) itemView.findViewById(R.id.recyclerViewInstructionEquipments);
            recyclerViewInstructionIngredients = (RecyclerView) itemView.findViewById(R.id.recyclerViewInstructionIngredients);
        }
    }
}
