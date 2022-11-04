package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipes.Adapters.InstructionsAdapter;
import com.example.foodrecipes.Listeners.InstructionsListener;
import com.example.foodrecipes.Models.Instruction;

import java.util.ArrayList;

public class InstructionActivity extends AppCompatActivity {
    int id;
    TextView tvMealName;
    RecyclerView recyclerViewInstruction;
    RequestManager requestManager;
    InstructionsAdapter instructionsAdapter;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        id = Integer.parseInt(getIntent().getStringExtra("id"));
//        Log.d("recipeName", getIntent().getStringExtra("recipeName"));
        setControl();
        setEvent();
        requestManager = new RequestManager(this);
        requestManager.getInstructions(instructionsListener, id);
        dialog.show();
    }

    private void setEvent() {
        tvMealName.setSelected(true);
        tvMealName.setText(getIntent().getStringExtra("recipeName"));
    }

    private void setControl() {
        tvMealName = findViewById(R.id.tvMealName);
        recyclerViewInstruction = findViewById(R.id.recyclerViewInstruction);
        dialog = new ProgressDialog(InstructionActivity.this);
        dialog.setTitle("Loading...");
    }

    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(ArrayList<Instruction> response, String message) {
            dialog.dismiss();
            recyclerViewInstruction.setHasFixedSize(true);
            recyclerViewInstruction.setLayoutManager((new LinearLayoutManager(InstructionActivity.this, LinearLayoutManager.VERTICAL, false)));
            instructionsAdapter = new InstructionsAdapter(InstructionActivity.this, R.layout.list_instructions_item, response);
            recyclerViewInstruction.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(InstructionActivity.this);
            builder.setTitle("API FAILURE !");
            builder.setMessage(message);
            builder.setNegativeButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                // If user click no then dialog box is canceled.
                dialog.cancel();
            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();        }
    };
}