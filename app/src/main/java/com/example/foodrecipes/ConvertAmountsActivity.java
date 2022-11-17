package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.example.foodrecipes.Models.Amount;

import java.util.ArrayList;
import java.util.function.DoubleUnaryOperator;

public class ConvertAmountsActivity extends AppCompatActivity {
    ImageView imageViewScale;
    Button btnConvert;
    EditText edtSourceUnit;
    TextView tvSourceUnit;
    Spinner spinnerSourceUnit, spinnerTargetUnit;
    ArrayList<Amount.AMOUNTS> spinnerData = new ArrayList<>();
    Amount.AMOUNTS sourceUnit = Amount.AMOUNTS.OUNCE;
    Amount.AMOUNTS targetUnit = Amount.AMOUNTS.GRAM;
    double sourceAmount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_amounts);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewScale.setImageResource(R.drawable.balance_scale);
                double result = (sourceAmount * sourceUnit.toGrams) / targetUnit.toGrams;
                tvSourceUnit.setText(result + "");
            }
        });

        edtSourceUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                imageViewScale.setImageResource(R.drawable.unbalance_scale);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                sourceAmount = Double.parseDouble(editable.toString());
            }
        });
    }

    private void setControl() {
        imageViewScale = findViewById(R.id.imageViewScale);
        btnConvert = findViewById(R.id.btnConvert);

        spinnerData.add(Amount.AMOUNTS.OUNCE);
        spinnerData.add(Amount.AMOUNTS.CUP);
        spinnerData.add(Amount.AMOUNTS.OZ);
        spinnerData.add(Amount.AMOUNTS.GRAM);
        spinnerData.add(Amount.AMOUNTS.PINCH);
        spinnerData.add(Amount.AMOUNTS.POUND);
        spinnerData.add(Amount.AMOUNTS.TABLESPOON);
        spinnerData.add(Amount.AMOUNTS.TEASPOON);

        spinnerSourceUnit = findViewById(R.id.spinnerSourceUnit);
//        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, spinnerData, R.layout.spinner_text);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_text_convert_amount, spinnerData);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinnerSourceUnit.setAdapter(arrayAdapter);
        spinnerSourceUnit.setOnItemSelectedListener(sourceSpinnerSelectedListener);

        spinnerTargetUnit = findViewById(R.id.spinnerTargetUnit);
//        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, spinnerData, R.layout.spinner_text);
        spinnerTargetUnit.setAdapter(arrayAdapter);
        spinnerTargetUnit.setOnItemSelectedListener(targetSpinnerSelectedListener);
        spinnerTargetUnit.setSelection(3);

        edtSourceUnit = findViewById(R.id.edtSourceUnit);
        tvSourceUnit = findViewById(R.id.tvSourceUnit);
    }

    //    Tag spinner
    private final AdapterView.OnItemSelectedListener sourceSpinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            imageViewScale.setImageResource(R.drawable.unbalance_scale);
            sourceUnit = spinnerData.get(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final AdapterView.OnItemSelectedListener targetSpinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            imageViewScale.setImageResource(R.drawable.unbalance_scale);
            targetUnit = spinnerData.get(i);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}