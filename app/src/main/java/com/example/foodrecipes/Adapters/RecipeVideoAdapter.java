package com.example.foodrecipes.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.Listeners.VideoRecipeClickListener;
import com.example.foodrecipes.Models.ExtendedIngredient;
import com.example.foodrecipes.Models.RecipeVideo;
import com.example.foodrecipes.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeVideoAdapter extends RecyclerView.Adapter<RecipeVideoAdapter.RecipeVideoViewHolder>{
    Context context;
    int layoutId;
    ArrayList<RecipeVideo> list;
    VideoRecipeClickListener videoRecipeClickListener;

    public RecipeVideoAdapter(Context context, int layoutId, ArrayList<RecipeVideo> list, VideoRecipeClickListener videoRecipeClickListener) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
        this.videoRecipeClickListener = videoRecipeClickListener;
    }

    @NonNull
    @Override
    public RecipeVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeVideoAdapter.RecipeVideoViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeVideoViewHolder holder, int position) {
        RecipeVideo recipeVideo = list.get(position);
        holder.tvTitle.setText(recipeVideo.getShortTitle());
        holder.tvLongTitle.setText(recipeVideo.getTitle());
        holder.ratingBarVideo.setRating((float) (recipeVideo.getRating() * 5));
        Picasso.get().load(recipeVideo.getThumbnail()).into(holder.imageViewRecipeVideoThumbnail, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                holder.imageViewRecipeVideoThumbnail.setImageResource(R.drawable.no_image_available_icon);
            }
        });
        holder.imageViewRecipeVideoThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoRecipeClickListener.onThumnailClicked(recipeVideo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecipeVideoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLongTitle;
        ImageView imageViewRecipeVideoThumbnail;
        RatingBar ratingBarVideo;


        public RecipeVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvLongTitle = (TextView) itemView.findViewById(R.id.tvLongTitle);
            imageViewRecipeVideoThumbnail = (ImageView) itemView.findViewById(R.id.imageViewRecipeVideoThumbnail);
            ratingBarVideo = (RatingBar) itemView.findViewById(R.id.ratingBarVideo);
        }
    }
}
