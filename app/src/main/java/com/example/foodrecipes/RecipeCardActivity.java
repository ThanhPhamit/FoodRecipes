package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodrecipes.Listeners.RecipeCardResponeListener;
import com.example.foodrecipes.Models.RecipeCardRespone;
import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeCardActivity extends AppCompatActivity {
    private int id;
    private String url;
    TouchImageView touchImageViewRecipeCard;
    RequestManager requestManager;
    ProgressDialog dialog;
    ImageView btnDownload;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_card);
        setControl();

        setEvent();

//        Broard cast receiver
        registerReceivers();
    }

    private void setEvent() {
        requestManager.getRecipeCard(recipeCardResponeListener, id);
        dialog.show();
        btnDownload.setVisibility(View.GONE);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url);
                startDownload(uri);
                dialog.setTitle("Downloading...");
                dialog.show();
            }
        });
    }

    private void setControl() {
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        touchImageViewRecipeCard = findViewById(R.id.touchImageViewRecipeCard);
        btnDownload = findViewById(R.id.btnDownload);

        requestManager = new RequestManager(this);

        downloadManager = (DownloadManager) getSystemService(RecipeCardActivity.DOWNLOAD_SERVICE);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        btnDownload.setVisibility(View.GONE);
    }

    private void registerReceivers() {
        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        registerReceiver(onNotificationClick,
                new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onComplete);
        unregisterReceiver(onNotificationClick);
    }

    private final RecipeCardResponeListener recipeCardResponeListener = new RecipeCardResponeListener() {
        @Override
        public void didFetch(RecipeCardRespone response, String message) {
            dialog.hide();
            btnDownload.setVisibility(View.VISIBLE);
            url = response.getRecipeCardUrl();
            Picasso.get().load(url).into(touchImageViewRecipeCard, new Callback() {
                @Override
                public void onSuccess() {
                    btnDownload.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError(Exception e) {
                    touchImageViewRecipeCard.setImageResource(R.drawable.no_image_available_icon);
                }
            });
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(RecipeCardActivity.this);
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

    private void startDownload(Uri uri) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);
//                Set title and description
        request.setTitle("Food recipes");
        request.setDescription("Downloading recipe card...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/" + getString(R.string.app_name) + "/recipecard" + id + ".jpg");
        request.setMimeType("image/jpeg");
        downloadManager.enqueue(request);
    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
//            Toast.makeText(ctxt, "Complete", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(RecipeCardActivity.this);
            builder.setTitle("Download complete!");
            builder.setMessage("File address is Download/" + getString(R.string.app_name) + "/recipecard" + id + ".jpg");
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

    BroadcastReceiver onNotificationClick = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            Log.d("onNotificationClick", "onNotificationClick");
        }
    };

}