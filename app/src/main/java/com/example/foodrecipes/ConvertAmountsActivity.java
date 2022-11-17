package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodrecipes.Models.Amount;

public class ConvertAmountsActivity extends AppCompatActivity {
    ImageView imageViewScale;
    Button btnConvert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_amounts);
        setControl();
        setEvent();
        Toast.makeText(this, Amount.AMOUNTS.OUNCE.toString(), Toast.LENGTH_LONG).show();
    }

    private void setEvent() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewScale.setImageResource(R.drawable.balance_scale);
            }
        });
    }

    private void setControl() {
        imageViewScale = findViewById(R.id.imageViewScale);
        btnConvert = findViewById(R.id.btnConvert);
    }
}