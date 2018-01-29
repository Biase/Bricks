package it.ingte.bricks;

import it.ingte.bricks.ProvaActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;

import java.util.ArrayList;

public class TabMap extends Fragment implements OnMapReadyCallback, OnClusterItemClickListener<Person>{
    ArrayList<Info> myData;
    ArrayList<Person> personInfo;
    ListView lst;
    GoogleMap mMap;
    ViewGroup container;
    MapView mMapView;
    View mView;
    ClusterManager<Person> mClusterManager;
    ArrayList<Person> clusterPerson = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
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
        setupMap(mMap);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.482344, 12.238054), 9));
        //googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //       LatLng trevigiani = new LatLng(45.489090, 12.237890);
        //       LatLng mimmo = new LatLng(45.482344, 12.238054);

//        MarkerOptions mkm = new MarkerOptions();
//        MarkerOptions mkt = new MarkerOptions();

//        mMap.addMarker(mkm.position(mimmo).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pallinored)));
//        mMap.addMarker(mkt.position(trevigiani).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pallinored)));

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mimmo, 10));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(trevigiani, 10));

//        mMap.setOnMarkerClickListener(this);

        mClusterManager = new ClusterManager<>(getContext(), mMap);

        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mClusterManager.setOnClusterItemClickListener(this);
        addPersonItems();
        mClusterManager.cluster();
    }

    private void addPersonItems() {
        //int pos = 0;
        ArrayList<Info> info = MainActivity.manager.getDbhelper().getData();
        for(Info i: info) {
            String capSplit = i.getCap();
            Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince());
            if(!exist(clusterPerson, p.getPosition())){
                clusterPerson.add(p);
                mClusterManager.addItem(p);
            }
        }
        //mClusterManager.addItem(new Person(mCap[pos][1], mCap[pos][2], "Hai selezionato questo progetto!", " "));
        //mClusterManager.addItem(new Person(0, 0, "Hai selezionato questo progetto!", " "));

        /**
         mClusterManager.addItem(new Person(45.482344, 12.228054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.492344, 12.228054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.502344, 12.228054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.582344, 12.228054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.448344, 12.238454, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.462344, 12.248954, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.482344, 12.228854, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.448244, 12.134154, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.462344, 12.248054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.482744, 12.228054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.648144, 12.148054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.462544, 12.288054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.492344, 12.328054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.448344, 12.122054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.462344, 12.248474, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.582344, 12.228994, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.448344, 12.237454, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.462344, 12.248474, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.482344, 12.228854, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.448244, 12.134554, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.467744, 12.248054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.482749, 12.228054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.648111, 12.148054, "Hai selezionato questo progetto!", " "));
         mClusterManager.addItem(new Person(45.462714, 12.288054, "Hai selezionato questo progetto!", " "));
         */}

    protected void setupMap(GoogleMap mMap) {
        if (mMap != null) {
            return;
        }

        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap.setTrafficEnabled(true);
        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);
        // permissions
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public boolean onClusterItemClick(Person person) {
        final Person mimmo = person;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.dialog, null);
        bottomSheetDialog.setContentView(parentView);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parentView.getParent());
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
        bottomSheetDialog.show();
        ArrayList<Info> info = MainActivity.manager.getDbhelper().getData();
        int project = 0;
        for (Info i: info){
            if(i.getBeneficiaryName().equalsIgnoreCase(person.getBeneficiaryName()) && i.getTown().equalsIgnoreCase(person.getTown()) && i.getLat() == person.getPosition().latitude && i.getLng() == person.getPosition().longitude){
                project++;
            }
        }
        TextView tx = (TextView) parentView.findViewById(R.id.textView2);
        tx.setText("Numero di progetti: "+project);
        Button button = (Button) parentView.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabMap.this.getActivity(), ProvaActivity.class);
                intent.putExtra("name", mimmo.getBeneficiaryName());
                intent.putExtra("operation", mimmo.getOperation());
                intent.putExtra("town", mimmo.getTown());
                intent.putExtra("position", mimmo.getPosition());
                startActivity(intent);
            }
        });
        return false;
    }

    public boolean exist(ArrayList<Person> info, LatLng pos){
        for (Person p: info){
            if(p.getPosition().longitude == pos.longitude && p.getPosition().latitude == pos.latitude){
                return true;
            }
        }
        return false;
    }

}