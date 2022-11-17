package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class IntroduceActivity extends AppCompatActivity {
    Button btnNext;
    private static int LIMIT = 5000;
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(IntroduceActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        handler.postDelayed(runnable, LIMIT);

        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                Intent intent = new Intent(IntroduceActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}