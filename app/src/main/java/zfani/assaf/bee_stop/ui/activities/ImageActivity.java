package zfani.assaf.bee_stop.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

import zfani.assaf.bee_stop.ui.ZoomFunctionality;

public class ImageActivity extends BaseActivity {

    public static final String FILE_NAME = "file_name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("image" + getIntent().getIntExtra(ImageActivity.FILE_NAME, 1) + ".jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bmp = BitmapFactory.decodeStream(inputStream, new Rect(), options);
        ZoomFunctionality img = new ZoomFunctionality(this);
        img.setImageBitmap(bmp);
        img.setMaxZoom(4f);
        setContentView(img);
    }
}
