package zfani.assaf.bee_stop.ui.activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import zfani.assaf.bee_stop.R;


public class YouTubeActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube);
        designStatusBar();
        ((YouTubePlayerView) findViewById(R.id.player)).initialize("AIzaSyAHAw3k4gYmxEOE0i7eqhBiTBNlWHJs17s", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("ngvyYzJcPn4");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    private void designStatusBar() {
        int statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary);
        float[] hsv = new float[3];
        Color.colorToHSV(statusBarColor, hsv);
        hsv[2] *= 0.8f;
        statusBarColor = Color.HSVToColor(hsv);
        getWindow().setStatusBarColor(statusBarColor);
    }
}
