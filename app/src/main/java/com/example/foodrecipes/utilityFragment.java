package com.example.foodrecipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link utilityFragment#} factory method to
 * create an instance of this fragment.
 */
public class utilityFragment extends Fragment {
    LinearLayout linearLayoutFoodTrivia, linearLayoutConvertAmounts;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_utility, container, false);
        setControl();
        setEvent();


        return view;
    }

    private void setEvent() {
        linearLayoutFoodTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FoodTriviaActivity.class));
            }
        });

        linearLayoutConvertAmounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ConvertAmountsActivity.class));
            }
        });
    }

    private void setControl() {
        linearLayoutFoodTrivia = view.findViewById(R.id.linearLayoutFoodTrivia);
        linearLayoutConvertAmounts = view.findViewById(R.id.linearLayoutConvertAmounts);
    }
}