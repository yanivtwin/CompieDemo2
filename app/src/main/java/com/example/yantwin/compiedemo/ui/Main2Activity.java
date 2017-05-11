package com.example.yantwin.compiedemo.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.example.yantwin.compiedemo.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
*   youtube activity implements oninitialed listener from youtube
*   and extends youtubebaseactivity
 */
public class Main2Activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY = "AIzaSyD-wiZrOQcXhYVHM_d5QxYYv5TqSQTzg4c";  //APIKEY from google dev console

    //https://www.youtube.com/watch?v=<VIDEO_ID>
    @Bind(R.id.youtube_player_view)
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout
        setContentView(R.layout.activity_main2);

        //binding with butterknife
        ButterKnife.bind(Main2Activity.this);

        youTubePlayerView.initialize(API_KEY, this);     //initiazile the youtube player with the api key will return to either onInitializationFailure or onInitializationSuccess
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) { // failed to initialize youtube
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {// success to initialize youtube
        if(null== player) return;

        // Start buffering
        if (!wasRestored) {
            player.cueVideo(getIntent().getExtras().getString(MainActivity.EXTRA_MESSAGE));
        }
    }
}