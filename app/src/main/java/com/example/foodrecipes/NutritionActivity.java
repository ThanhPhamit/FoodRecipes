package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NutritionActivity extends AppCompatActivity {
    int id;
    TextView tvMealName;
    ProgressDialog dialog;
    TouchImageView imageViewNutrition;
    Spinner spinnerNutrition;
    private final ArrayList<String> nutritionTypes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        setControl();
        setEvent();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        nutritionTypes.add("Label");
        nutritionTypes.add("Chart");
    }

    private void setEvent() {
        tvMealName.setSelected(true);
        tvMealName.setText(getIntent().getStringExtra("recipeName"));
//        Picasso.get().load("https://api.spoonacular.com/recipes/1082038/nutritionWidget.png?apiKey=09a3d04ccbe14911b574c7d613716671").into(imageViewNutrition);
        spinnerNutrition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(NutritionActivity.this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    Toast.makeText(NutritionActivity.this, "true", Toast.LENGTH_SHORT).show();
                    Picasso.get().load("https://api.spoonacular.com/recipes/" + id + "/nutritionLabel.png?apiKey=" + getString(R.string.api_key)).into(imageViewNutrition);
                }else{
                    Picasso.get().load("https://api.spoonacular.com/recipes/" + id + "/nutritionWidget.png?apiKey=" + getString(R.string.api_key)).into(imageViewNutrition);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setControl() {
        tvMealName = findViewById(R.id.tvMealName);
        dialog = new ProgressDialog(NutritionActivity.this);
        dialog.setTitle("Loading...");
        imageViewNutrition = findViewById(R.id.imageViewNutrition);
        spinnerNutrition = findViewById(R.id.spinnerNutrition);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(NutritionActivity.this, R.array.nutrition_types, R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinnerNutrition.setAdapter(arrayAdapter);
    }


}