package com.example.foodrecipes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodrecipes.Adapters.RecipeAdapters;
import com.example.foodrecipes.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipes.Listeners.RecipeClickListener;
import com.example.foodrecipes.Models.RandomRecipeApiResponse;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link recipesFragment#} factory method to
 * create an instance of this fragment.
 */
public class recipesFragment extends Fragment {
    ProgressDialog dialog;
    RequestManager manager;
    RecipeAdapters recipeAdapters;
    RecyclerView recyclerView;
    View view;
    ArrayList<String> tags = new ArrayList<>();

    Spinner spinnerTags;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recipes, container, false);
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loading...");

        manager = new RequestManager(getActivity());
//        manager.getRandomRecipes(randomRecipeResponseListener);
//        dialog.show();

        searchView = view.findViewById(R.id.searchViewRecipesFragment);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinnerTags = view.findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.tags, R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinnerTags.setAdapter(arrayAdapter);
        spinnerTags.setOnItemSelectedListener(spinnerSelectedListener);
        return view;
    }

//    Random Recipe Response Listener
    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = view.findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recipeAdapters = new RecipeAdapters(getActivity(), R.layout.list_random_recipe, response.recipes, recipeClickListener);
            recyclerView.setAdapter(recipeAdapters);
        }

        @Override
        public void didError(String message) {
            Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
            toast.show();
        }
    };

//    Tag spinner
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener, tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

//    Recipe Click Listener
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(getContext(), RecipeDetailsActivity.class).putExtra("id", id));
        }
    };
}