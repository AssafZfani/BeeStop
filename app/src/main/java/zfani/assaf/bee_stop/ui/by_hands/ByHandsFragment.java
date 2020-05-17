package zfani.assaf.bee_stop.ui.by_hands;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import zfani.assaf.bee_stop.R;

public class ByHandsFragment extends Fragment {

    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_by_hands, container, false);
        final WebView wbWebView = root.findViewById(R.id.wbWebView);
        wbWebView.getSettings().setJavaScriptEnabled(true);
        wbWebView.setWebViewClient(new WebViewClient());
        wbWebView.loadUrl("https://www.bayadaim.org.il/2014/04/%D7%93%D7%91%D7%95%D7%A8%D7%99%D7%9D-%D7%A2%D7%9C-%D7%A1%D7%A3-%D7%93%D7%9C%D7%AA%D7%99/");
        return root;
    }
}
