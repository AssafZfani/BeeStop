package zfani.assaf.bee_stop.ui.send_message;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import zfani.assaf.bee_stop.R;

public class SendMessageFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send_message, container, false);
        String mailto = "mailto:Hilmarkuhnemann@gmail.com?&body=" + Uri.encode("שם:" + "<br/>" + "טלפון:" + "<br/>" + "תיאור בקשה:", "\\");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mailto));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        requireActivity().startActivityForResult(intent, 10001);
        return root;
    }
}
