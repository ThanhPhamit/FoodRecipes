package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.foodrecipes.Listeners.RecipeCardResponeListener;
import com.example.foodrecipes.Models.RecipeCardRespone;
import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Picasso;

public class RecipeCardActivity extends AppCompatActivity {
    private int id;
    TouchImageView touchImageViewRecipeCard;
    RequestManager requestManager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_card);
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        touchImageViewRecipeCard = findViewById(R.id.touchImageViewRecipeCard);

        requestManager = new RequestManager(this);
        requestManager.getRecipeCard(recipeCardResponeListener, id);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
    }

    private final RecipeCardResponeListener recipeCardResponeListener = new RecipeCardResponeListener() {
        @Override
        public void didFetch(RecipeCardRespone response, String message) {
            dialog.hide();
            Toast.makeText(RecipeCardActivity.this, response.getRecipeCardUrl(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(RecipeCardActivity.this);
            builder.setTitle("API FAILURE !");
            builder.setMessage(message);
            builder.setNegativeButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                // If user click no then dialog box is canceled.
                dialog.cancel();
            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }
    };
}