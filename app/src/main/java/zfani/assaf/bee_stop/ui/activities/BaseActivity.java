package zfani.assaf.bee_stop.ui.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import zfani.assaf.bee_stop.R;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        designStatusBar();
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
