package com.example.foodrecipes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipes.Adapters.RecipeAdapter;
import com.example.foodrecipes.Adapters.RecipeVideoAdapter;
import com.example.foodrecipes.Listeners.RecipeVideosResponseListerner;
import com.example.foodrecipes.Listeners.VideoRecipeClickListener;
import com.example.foodrecipes.Models.RecipeVideo;
import com.example.foodrecipes.Models.RecipeVideoResponse;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link foodVideosFragment#} factory method to
 * create an instance of this fragment.
 */
public class foodVideosFragment extends Fragment {
    String type = "";
    String query = "";
    SearchView searchView;
    Spinner spinnerTypes;
    View view;
    RecyclerView recyclerViewRecipeVideo;
    RecipeVideoAdapter recipeVideoAdapter;
    RequestManager requestManager;
    TextView tvNoRecipeVideo;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_videos, container, false);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        spinnerTypes.setOnItemSelectedListener(onItemSelectedListener);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                query = s;
                handleSearchVideos();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void setControl() {
        spinnerTypes = view.findViewById(R.id.spinnerTypes);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.types, R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinnerTypes.setAdapter(arrayAdapter);

        searchView = view.findViewById(R.id.searchViewRecipeVideo);
        requestManager = new RequestManager(getContext());
        tvNoRecipeVideo = view.findViewById(R.id.tvNoRecipeVideo);
        recyclerViewRecipeVideo = view.findViewById(R.id.recyclerViewRecipeVideo);

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loading...");
    }

    private final AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (adapterView.getSelectedItem().toString() == "none") {
                type = "";
            } else {
                type = adapterView.getSelectedItem().toString();
            }
            handleSearchVideos();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private void handleSearchVideos() {
        requestManager.getRecipeVideos(listerner, query, type);
        dialog.show();
    }

    private final RecipeVideosResponseListerner listerner = new RecipeVideosResponseListerner() {
        @Override
        public void didFetch(RecipeVideoResponse response, String message) {
            dialog.dismiss();
            if (response.videos.size() == 0) {
                tvNoRecipeVideo.setVisibility(View.VISIBLE);
                recyclerViewRecipeVideo.setVisibility(View.GONE);
            } else {
                tvNoRecipeVideo.setVisibility(View.GONE);
                recyclerViewRecipeVideo = view.findViewById(R.id.recyclerViewRecipeVideo);
                recyclerViewRecipeVideo.setHasFixedSize(true);
                recyclerViewRecipeVideo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recipeVideoAdapter = new RecipeVideoAdapter(getContext(), R.layout.list_recipe_video_item, response.videos, videoRecipeClickListener);
                recyclerViewRecipeVideo.setAdapter(recipeVideoAdapter);
            }
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

    private final VideoRecipeClickListener videoRecipeClickListener = new VideoRecipeClickListener() {
        @Override
        public void onThumnailClicked(RecipeVideo recipeVideo) {
            Intent intent = new Intent(getContext(), PlayVideoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("youtubeID", recipeVideo.getYouTubeId());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}