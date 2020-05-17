package zfani.assaf.bee_stop.ui.map;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import zfani.assaf.bee_stop.R;
import zfani.assaf.bee_stop.data.Station;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapViewModel mapViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.771959, 35.217018), 7));
        mapViewModel.getStationList().observe(getViewLifecycleOwner(), stations -> {
            for (Station station : stations) {
                LatLng latLng = new LatLng(station.stationCoordinate.getLatitude(), station.stationCoordinate.getLongitude());
                Marker marker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.bee)).position(latLng).title(station.ownerName).snippet(station.stationType));
                startDropMarkerAnimation(googleMap, marker);
                new Timer().schedule(new CustomTimerTask(marker), new Date(System.currentTimeMillis()));
            }
        });
        googleMap.setOnMarkerClickListener(marker -> {
            marker.showInfoWindow();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 10));
            return true;
        });
    }

    private void startDropMarkerAnimation(GoogleMap googleMap, final Marker marker) {
        final LatLng target = marker.getPosition();
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection projection = googleMap.getProjection();
        Point targetPoint = projection.toScreenLocation(target);
        final long duration = (long) (100 + (targetPoint.y * 0.6));
        Point startPoint = projection.toScreenLocation(marker.getPosition());
        startPoint.y = 0;
        final LatLng startLatLng = projection.fromScreenLocation(startPoint);
        final Interpolator interpolator = new LinearOutSlowInInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * target.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * target.latitude + (1 - t) * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    // Post again 16ms later == 60 frames per second
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    static class CustomTimerTask extends TimerTask {

        private final Marker marker;
        private final Handler handler = new Handler();

        CustomTimerTask(Marker marker) {
            this.marker = marker;
        }

        @Override
        public void run() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            final Handler handler = new Handler();
                            final long start = SystemClock.uptimeMillis();
                            final long duration = 1500;
                            final Interpolator interpolator = new BounceInterpolator();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    long elapsed = SystemClock.uptimeMillis() - start;
                                    float t = Math.max(1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                                    marker.setAnchor(0.5f, 1.0f + 2 * t);
                                    if (t > 0.0) {
                                        // Post again 16ms later.
                                        handler.postDelayed(this, 16);
                                    }
                                }
                            });
                        }
                    });
                }
            }).start();
        }
    }
}
