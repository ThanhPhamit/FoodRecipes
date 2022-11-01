package com.example.foodrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Models.ExtendedIngredient;
import com.example.foodrecipes.Models.Instruction;
import com.example.foodrecipes.R;

import java.util.ArrayList;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.InstructionsViewHolder> {
    Context context;
    int layoutId;
    ArrayList<Instruction> list;

    public InstructionsAdapter(Context context, int layoutId, ArrayList<Instruction> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsAdapter.InstructionsViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        Instruction instruction = list.get(position);
        if (instruction.getName().length() == 0){
            holder.tvInstructionName.setVisibility(View.GONE);
        }else{
            holder.tvInstructionName.setText(instruction.getName());
        }
        holder.recyclerViewInstructionsSteps.setHasFixedSize(true);
        holder.recyclerViewInstructionsSteps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        InstructionStepAdapter stepAdapter = new InstructionStepAdapter(context, R.layout.list_instruction_step_item, instruction.getSteps());
        holder.recyclerViewInstructionsSteps.setAdapter(stepAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InstructionsViewHolder extends RecyclerView.ViewHolder{
        TextView tvInstructionName;
        RecyclerView recyclerViewInstructionsSteps;
        public InstructionsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInstructionName = (TextView) itemView.findViewById(R.id.tvInstructionName);
            recyclerViewInstructionsSteps = (RecyclerView) itemView.findViewById(R.id.recyclerViewInstructionsSteps);
        }
    }
}
