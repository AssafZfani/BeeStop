package zfani.assaf.bee_stop.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.barteksc.pdfviewer.PDFView;

import zfani.assaf.bee_stop.R;

public class PDFActivity extends BaseActivity {

    public static final String FILE_NAME = "file_name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(getIntent().getStringExtra(FILE_NAME)).load();
    }
}
