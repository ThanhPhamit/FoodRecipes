package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipes.Adapters.IngredientsApdapter;
import com.example.foodrecipes.Adapters.InstructionsAdapter;
import com.example.foodrecipes.Adapters.RecipeAdapters;
import com.example.foodrecipes.Listeners.InstructionsListener;
import com.example.foodrecipes.Listeners.RecipeClickListener;
import com.example.foodrecipes.Listeners.RecipeDetailsListener;
import com.example.foodrecipes.Listeners.SimilarRecipesListener;
import com.example.foodrecipes.Models.Instruction;
import com.example.foodrecipes.Models.RecipeDetailsResponse;
import com.example.foodrecipes.Models.SimilarRecipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    String recipeName;
    TextView tvMealName, tvMealSource, tvMealSummary;
    Button btnInstruction;
    ImageView imageViewMeal;
    RecyclerView recyclerViewInGredients, recyclerViewSimilarRecipes;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsApdapter ingredientsApdapter;
    RecipeAdapters recipeAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        setControl();
        setEvent();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        manager.getSimilarRecipes(similarRecipesListener, id);


        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
    }

    private void setEvent() {
        btnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeDetailsActivity.this, InstructionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(id));
                bundle.putString("recipeName", recipeName);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        tvMealName = findViewById(R.id.tvMealName);
        tvMealSource = findViewById(R.id.tvMealSource);
        tvMealSummary = findViewById(R.id.tvMealSummary);
        imageViewMeal = findViewById(R.id.imageViewMealImage);
        btnInstruction = findViewById(R.id.btnInstruction);
        recyclerViewInGredients = findViewById(R.id.recyclerViewMealIngredients);
        recyclerViewSimilarRecipes = findViewById(R.id.recyclerViewSimilarRecipes);
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
//            Show recipe info
            tvMealName.setText(response.getTitle());
            tvMealName.setSelected(true);
            tvMealSource.setText("Food source: " + response.getSourceName());
            tvMealSummary.setText(response.getSummary());
            Picasso.get().load(response.getImage()).into(imageViewMeal);
//            Show ingredients recycler view
            recyclerViewInGredients.setHasFixedSize(true);
            recyclerViewInGredients.setLayoutManager(new GridLayoutManager(RecipeDetailsActivity.this, 3));
            ingredientsApdapter = new IngredientsApdapter(RecipeDetailsActivity.this, R.layout.list_meal_ingredients, response.getExtendedIngredients());
            recyclerViewInGredients.setAdapter(ingredientsApdapter);

            recipeName = response.getTitle();
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(ArrayList<SimilarRecipe> response, String message) {
            dialog.dismiss();

            recyclerViewSimilarRecipes.setHasFixedSize(true);
            recyclerViewSimilarRecipes.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            recipeAdapters = new RecipeAdapters(RecipeDetailsActivity.this, R.layout.list_similar_recipe, response, recipeClickListener);
            recyclerViewSimilarRecipes.setAdapter(recipeAdapters);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    //    Recipe Click Listener
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Intent intent = new Intent(RecipeDetailsActivity.this, RecipeDetailsActivity.class).putExtra("id", id);
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

}