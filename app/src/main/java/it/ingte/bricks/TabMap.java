package it.ingte.bricks;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TabMap extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    GoogleMap mMap;
    Marker prev = null;
    MapView mMapView;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_maps, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInStanceState) {
        super.onViewCreated(view, savedInStanceState);
        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng trevigiani = new LatLng(45.489090, 12.237890);
        LatLng mimmo = new LatLng(45.482344, 12.238054);

        MarkerOptions mkm = new MarkerOptions();
        MarkerOptions mkt = new MarkerOptions();

        mMap.addMarker(mkm.position(mimmo).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pallinored)));
        mMap.addMarker(mkt.position(trevigiani).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pallinored)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mimmo, 10));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(trevigiani, 10));
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (prev != null && prev != marker) {
            prev.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.pallinored));
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            prev = marker;
        } else {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            prev = marker;
        }
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.dialog, null);
        bottomSheetDialog.setContentView(parentView);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parentView.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
        bottomSheetDialog.show();
        return false;
    }
}