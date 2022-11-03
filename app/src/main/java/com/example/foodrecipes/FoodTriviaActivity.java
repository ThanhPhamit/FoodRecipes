package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipes.Listeners.FoodTriviaListener;
import com.example.foodrecipes.Models.FoodTrivia;

public class FoodTriviaActivity extends AppCompatActivity {
    TextView tvFoodTrivia;
    RequestManager requestManager;
    ProgressDialog dialog;
    ImageView btnReload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_trivia);

        setControl();
        setEvent();
        requestManager = new RequestManager(this);
        requestManager.getFoodTrivia(foodTriviaListener);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();

        tvFoodTrivia.setVisibility(View.GONE);
    }

    private void setEvent() {
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestManager.getFoodTrivia(foodTriviaListener);
                dialog.show();
            }
        });
    }

    private void setControl() {
        tvFoodTrivia = findViewById(R.id.tvFoodTrivia);
        btnReload = findViewById(R.id.btnReload);
    }

    private final FoodTriviaListener foodTriviaListener = new FoodTriviaListener() {
        @Override
        public void didFetch(FoodTrivia response, String message) {
            dialog.hide();
            tvFoodTrivia.setVisibility(View.VISIBLE);
            tvFoodTrivia.setText(response.getText());
        }

        @Override
        public void didError(String message) {
            Toast.makeText(FoodTriviaActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}