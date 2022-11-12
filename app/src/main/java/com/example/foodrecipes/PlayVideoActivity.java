package com.example.foodrecipes;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipes.Listeners.YoutubeVideosResponseListener;
import com.example.foodrecipes.Models.YoutubeVideoResponse;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.text.SimpleDateFormat;

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    ScrollView scrollViewDescription;
    TextView tvTitle, tvViews1, tvChannel, tvNumberLikes, tvNumberViews, tvYears, tvDates, tvDescription;
    private String youtubeID;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView youTubePlayerViewRecipe;
    private boolean mAutoRotation = false;
    private YouTubePlayer myYouTubePlayer;
    private boolean isYoutubeFullScreen = false;
    RequestManager requestManager;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        mAutoRotation = Settings.System.getInt(getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
        youtubeID = getIntent().getStringExtra("youtubeID");
        setControl();
//        Turn on full screen when starting screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);youTubePlayerViewRecipe = findViewById(R.id.youTubePlayerViewRecipe);
//        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo("dkJzaTedTk8");
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(PlayVideoActivity.this, "Initialization Failed", Toast.LENGTH_SHORT).show();
//            }
//        };
        setEvent();
    }

    private void setEvent() {
        youTubePlayerViewRecipe.initialize(getString(R.string.youtube_api_key), this);
        requestManager = new RequestManager(PlayVideoActivity.this);
        requestManager.getYoutubeVideos(youtubeVideosResponseListener, youtubeID);
        dialog.setTitle("Loading...");
        dialog.show();
    }

    private void setControl() {
        youTubePlayerViewRecipe = findViewById(R.id.youTubePlayerViewRecipe);
        tvTitle = findViewById(R.id.tvTitle);
        tvViews1 = findViewById(R.id.tvViews1);
        tvChannel = findViewById(R.id.tvChannel);
        tvNumberLikes = findViewById(R.id.tvNumberLikes);
        tvNumberViews = findViewById(R.id.tvNumberViews);
        tvYears = findViewById(R.id.tvYears);
        tvDates = findViewById(R.id.tvDates);
        tvDescription = findViewById(R.id.tvDescription);
        scrollViewDescription = findViewById(R.id.scrollViewDescription);
        dialog = new ProgressDialog(this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        //Tell the player you want to control the fullscreen change
        if (mAutoRotation) {
            youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                    | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                    | YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
                    | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        } else {
            youTubePlayer.addFullscreenControlFlag(
                    YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                            | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                            | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        }
        youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean b) {
                isYoutubeFullScreen = true;
            }
        });
        if (!wasRestored) {
            youTubePlayer.loadVideo(youtubeID);
        }
        myYouTubePlayer = youTubePlayer;
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(getString(R.string.youtube_api_key), this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubePlayerViewRecipe;
    }

    //Call when changing configuration
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        Set full screen when change configuration
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isYoutubeFullScreen == true) {
            myYouTubePlayer.setFullscreen(false);
            return true;
        } else {
            finish();
            return false;
        }
    }

    private final YoutubeVideosResponseListener youtubeVideosResponseListener = new YoutubeVideosResponseListener() {
        @Override
        public void didFetch(YoutubeVideoResponse response, String message) {
            dialog.dismiss();
            tvTitle.setText(response.youtubeVideos.get(0).getSnippet().getLocalized().getTitle());
            Long viewCount = Long.parseLong(response.youtubeVideos.get(0).getStatistics().getViewCount());
            tvViews1.setText(withSuffix(viewCount) + " views");
            tvChannel.setText(response.youtubeVideos.get(0).getSnippet().getChannelTitle() + " channel");
            tvNumberLikes.setText(response.youtubeVideos.get(0).getStatistics().getLikeCount());
            tvNumberViews.setText(response.youtubeVideos.get(0).getStatistics().getViewCount());
            String years = new SimpleDateFormat("yyyy").format(response.youtubeVideos.get(0).getSnippet().getPublishedAt());
            tvYears.setText(years);
            String dateString = new SimpleDateFormat("MMM dd").format(response.youtubeVideos.get(0).getSnippet().getPublishedAt());
            tvDates.setText(dateString);
            tvDescription.setText(response.youtubeVideos.get(0).getSnippet().getLocalized().getDescription());
        }

        @Override
        public void didError(String message) {
            scrollViewDescription.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayVideoActivity.this);
            builder.setTitle("API FAILURE !");
            builder.setMessage(message);
            builder.setNegativeButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                // If user click no then dialog box is canceled.
                dialog.cancel();
                PlayVideoActivity.this.finish();
            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }
    };

    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f%c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp - 1));
    }
}