package it.ingte.bricks;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class TabMap extends Fragment implements OnMapReadyCallback, OnClusterItemClickListener<Person>, OnRequestPermissionsResultCallback {
    ArrayList<Info> myData;
    ArrayList<Person> personInfo;
    ListView lst;
    GoogleMap mMap;
    ViewGroup container;
    MapView mMapView;
    View mView;
    ClusterManager<Person> mClusterManager;
    ArrayList<Person> clusterPerson = new ArrayList<>();
    private final int MY_PERMISSION_FINE_LOCATION = 101;

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
        mClusterManager = new ClusterManager<>(getContext(), mMap);

        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
        }

        mClusterManager.setOnClusterItemClickListener(this);
        addPersonItems();
        mClusterManager.cluster();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permission, grantResult);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                }
                break;
        }
    }


    private void addPersonItems() {
        //int pos = 0;
        ArrayList<Info> info = MainActivity.manager.getDbhelper().getData();
        for(Info i: info) {
            Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince());
            if(!exist(clusterPerson, p.getPosition())){
                clusterPerson.add(p);
                mClusterManager.addItem(p);
            }
        }
    }

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


    ArrayList<Info> original = MainActivity.info;
    MaterialSearchView searchView;
    ArrayList<Info> result = new ArrayList<>();

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = ((MainActivity) getActivity()).searchView;
        searchView.setMenuItem(searchItem);
        MenuItem filter = menu.findItem(R.id.action_filter);
        searchView.setMenuItem(filter);
        filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(TabMap.this.getActivity(), Filter.class);
                startActivityForResult(intent, 1);
                return false;
            }
        });

        /*funzioni per search */

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {


            }

            @Override
            public void onSearchViewClosed() {

                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(TabMap.this.getActivity(), itemClick.class);
                        intent.putExtra("myInfo", original.get(i));
                        startActivity(intent);
                    }
                }
                );


            }

        });


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(final String query) {
                result = MainActivity.manager.getDbhelper().getResult(query);

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                }


                if (result.isEmpty()) {
                    Toast.makeText(getContext(), "no result from search", Toast.LENGTH_SHORT).show();

                }
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(TabMap.this.getActivity(), itemClick.class);
                        intent.putExtra("myInfo", result.get(i));
                        startActivity(intent);

                    }
                });

                result = MainActivity.manager.getDbhelper().getResult(query);
                clusterPerson.clear();
                mClusterManager.clearItems();

                for(Info i: result) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince());
                    if(!exist(clusterPerson, p.getPosition())){
                        clusterPerson.add(p);
                        mClusterManager.addItem(p);
                    }
                }

                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }


        });

    }


}