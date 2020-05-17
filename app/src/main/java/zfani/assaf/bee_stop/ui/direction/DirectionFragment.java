package zfani.assaf.bee_stop.ui.direction;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

import zfani.assaf.bee_stop.R;
import zfani.assaf.bee_stop.ui.activities.ImageActivity;
import zfani.assaf.bee_stop.ui.activities.PDFActivity;
import zfani.assaf.bee_stop.ui.activities.YouTubeActivity;

public class DirectionFragment extends Fragment implements View.OnClickListener {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_direction, container, false);
        LinearLayout llContainer = root.findViewById(R.id.llContainer);
        addPdfView(llContainer, R.string.pdf1, "pdf1.pdf");
        addPdfView(llContainer, R.string.pdf2, "pdf2.pdf");
        addYoutubeView(llContainer);
        for (int i = 1; i <= 18; i++) {
            addImageView(llContainer, i);
        }
        root.findViewById(R.id.tvPhone).setOnClickListener(this);
        root.findViewById(R.id.tvEmail).setOnClickListener(this);
        return root;
    }

    private void addImageView(LinearLayout llContainer, int i) {
        View view = View.inflate(llContainer.getContext(), R.layout.list_item_image, null);
        ImageView ivImage = view.findViewById(R.id.ivImage);
        InputStream inputStream = null;
        try {
            inputStream = ivImage.getContext().getAssets().open("image" + i + ".jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeStream(inputStream));
        dr.setCornerRadius(25f);
        ivImage.setImageDrawable(dr);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ImageActivity.class);
            intent.putExtra(ImageActivity.FILE_NAME, i);
            startActivity(intent);
        });
        llContainer.addView(view);
    }

    private void addPdfView(LinearLayout llContainer, int resId, String fileName) {
        View view = View.inflate(llContainer.getContext(), R.layout.list_item_text, null);
        ((TextView) view.findViewById(R.id.tvText)).setText(resId);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PDFActivity.class);
            intent.putExtra(PDFActivity.FILE_NAME, fileName);
            startActivity(intent);
        });
        llContainer.addView(view);
    }

    private void addYoutubeView(LinearLayout llContainer) {
        View view = View.inflate(llContainer.getContext(), R.layout.list_item_text, null);
        ((TextView) view.findViewById(R.id.tvText)).setText("איתמר ויסמן\nשפע הטבע - מרכז ליווי\nוהדרכה לגינון טבעי 050-5719682 shefahateva@gmail.com www.homeagriculture.org");
        view.setOnClickListener(v -> startActivity(new Intent(getContext(), YouTubeActivity.class)));
        llContainer.addView(view);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(((v.getId() == R.id.tvPhone ? "tel:" : "mailto:") + ((TextView) v).getText()))));
    }
}
