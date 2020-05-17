package zfani.assaf.bee_stop.ui.map;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import zfani.assaf.bee_stop.data.Station;

public class MapViewModel extends ViewModel {

    private final MutableLiveData<List<Station>> stationList;

    public MapViewModel() {
        stationList = new MutableLiveData<>();
    }

    MutableLiveData<List<Station>> getStationList() {
        FirebaseFirestore.getInstance().collection("Stations").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot documentSnapshots = task.getResult();
                if (documentSnapshots != null) {
                    stationList.setValue(documentSnapshots.toObjects(Station.class));
                }
            }
        });
        return stationList;
    }
}