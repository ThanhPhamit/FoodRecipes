package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipes.Adapters.IngredientsApdapter;
import com.example.foodrecipes.Listeners.RecipeDetailsListener;
import com.example.foodrecipes.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView tvMealName, tvMealSource, tvMealSummary;
    ImageView imageViewMeal;
    RecyclerView recyclerViewInGredients;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsApdapter ingredientsApdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        setControl();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(listener, id);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
    }

    private void setControl() {
        tvMealName = findViewById(R.id.tvMealName);
        tvMealSource = findViewById(R.id.tvMealSource);
        tvMealSummary = findViewById(R.id.tvMealSummary);
        imageViewMeal = findViewById(R.id.imageViewMealImage);

    }

    private final RecipeDetailsListener listener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            tvMealName.setText(response.getTitle());
            tvMealSource.setText(response.getSourceName());
            tvMealSummary.setText("Summary:" + response.getSummary());
            Picasso.get().load(response.getImage()).into(imageViewMeal);
            recyclerViewInGredients = findViewById(R.id.recyclerViewMealIngredients);
            recyclerViewInGredients.setHasFixedSize(true);
            recyclerViewInGredients.setLayoutManager(new GridLayoutManager(RecipeDetailsActivity.this, 3));
            ingredientsApdapter = new IngredientsApdapter(RecipeDetailsActivity.this, R.layout.list_meal_ingredients, response.getExtendedIngredients());
            recyclerViewInGredients.setNestedScrollingEnabled(true);
            recyclerViewInGredients.setAdapter(ingredientsApdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}