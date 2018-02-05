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
import static android.app.Activity.RESULT_OK;


public class TabMap extends Fragment implements OnMapReadyCallback, OnClusterItemClickListener<Person>, OnRequestPermissionsResultCallback {
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
        setHasOptionsMenu(true);
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float) 7.9));
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
        ArrayList<Info> info = MainActivity.manager.getDbhelper().getData();
        for(Info i: info) {
            Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), 0);
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

    int project = 0;

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
        project = 0;
        for (Info i: info){
            if(person.getControl() == 0) {
                if (i.getBeneficiaryName().equalsIgnoreCase(person.getBeneficiaryName()) && i.getTown().equalsIgnoreCase(person.getTown()) && i.getLat() == person.getPosition().latitude && i.getLng() == person.getPosition().longitude) {
                    project++;
                }
            }
            if(person.getControl() == 1) {
                if (person.getPrice() >= 0 && person.getPrice() <= 25000 && i.getBeneficiaryName().equalsIgnoreCase(person.getBeneficiaryName()) && i.getTown().equalsIgnoreCase(person.getTown()) && i.getLat() == person.getPosition().latitude && i.getLng() == person.getPosition().longitude) {
                    project++;
                }
            }
            if(person.getControl() == 2) {
                if (person.getPrice() > 25000 && person.getPrice() <= 50000 && i.getBeneficiaryName().equalsIgnoreCase(person.getBeneficiaryName()) && i.getTown().equalsIgnoreCase(person.getTown()) && i.getLat() == person.getPosition().latitude && i.getLng() == person.getPosition().longitude) {
                    project++;
                }
            }
            if(person.getControl() == 3) {
                if (person.getPrice() > 50000 && person.getPrice() <= 75000 && i.getBeneficiaryName().equalsIgnoreCase(person.getBeneficiaryName()) && i.getTown().equalsIgnoreCase(person.getTown()) && i.getLat() == person.getPosition().latitude && i.getLng() == person.getPosition().longitude) {
                    project++;
                }
            }
            if(person.getControl() == 4) {
                if (person.getPrice() > 75000 && person.getPrice() <= 100000 && i.getBeneficiaryName().equalsIgnoreCase(person.getBeneficiaryName()) && i.getTown().equalsIgnoreCase(person.getTown()) && i.getLat() == person.getPosition().latitude && i.getLng() == person.getPosition().longitude) {
                    project++;
                }
            }
            if(person.getControl() == 5) {
                if (person.getPrice() > 100000 && i.getBeneficiaryName().equalsIgnoreCase(person.getBeneficiaryName()) && i.getTown().equalsIgnoreCase(person.getTown()) && i.getLat() == person.getPosition().latitude && i.getLng() == person.getPosition().longitude) {
                    project++;
                }
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
                intent.putExtra("control", mimmo.getControl());
                intent.putExtra("nproject", project);
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
                Intent intent = new Intent(TabMap.this.getActivity(), FilterMap.class);
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
                clusterPerson.clear();
                mClusterManager.clearItems();
                for(Info i: MainActivity.info) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(),0);
                    if(!exist(clusterPerson, p.getPosition())){
                        clusterPerson.add(p);
                        mClusterManager.addItem(p);
                    }
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                mClusterManager.cluster();
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(final String query) {
                clusterPerson.clear();
                mClusterManager.clearItems();

                result = MainActivity.manager.getDbhelper().getResult(query);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                }


                if (result.isEmpty()) {
                    Toast.makeText(getContext(), "no result from search", Toast.LENGTH_SHORT).show();

                }
                for(Info i: result) {
                    Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(),0);
                    if(!exist(clusterPerson, p.getPosition())){
                        clusterPerson.add(p);
                        mClusterManager.addItem(p);
                    }
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                mClusterManager.cluster();
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    final String prezzo1 = "Tra 0.00 € e 25.000 €";
    final String prezzo2 = "Tra 25.000 € e 50.000 €";
    final String prezzo3 = "Tra 50.000 € e 75.000 €";
    final String prezzo4 = "Tra 75.000 € e 100.000 €";
    final String prezzo5 = "Superiore 100.000 €";
    final String venezia = "VENEZIA";
    final String treviso = "TREVISO";
    final String verona = "VERONA";
    final String vicenza = "VICENZA";
    final String rovigo = "ROVIGO";
    final String padova = "PADOVA";
    final String belluno = "BELLUNO";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            String s = data.getStringExtra("resultPrice");
            String t = data.getStringExtra("resultProvince");
            ArrayList<Info> ris = MainActivity.info;
            int controllo = 0;
            boolean activate = false;
            if (requestCode == 1 && resultCode == RESULT_OK) {
                if (prezzo1.equals(s)) {
                    activate = true;
                    controllo = 1;
                    ArrayList<Info> temp = new ArrayList<>();
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        if (i.getEligibleExpenditure() >= 0 && i.getEligibleExpenditure() <= 25000) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), 1);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (prezzo2.equals(s)) {
                    controllo = 2;
                    activate = true;
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getEligibleExpenditure() > 25000 && i.getEligibleExpenditure() <= 50000) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), 2);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (prezzo3.equals(s)) {
                    controllo = 3;
                    activate = true;
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getEligibleExpenditure() > 50000 && i.getEligibleExpenditure() <= 75000) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), 3);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (prezzo4.equals(s)) {
                    controllo = 4;
                    activate = true;
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getEligibleExpenditure() > 75000 && i.getEligibleExpenditure() <= 100000) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), 4);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (prezzo5.equals(s)) {
                    controllo = 5;
                    activate = true;
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getEligibleExpenditure() > 100000) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), 5);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                }
                if (venezia.equalsIgnoreCase(t)) {
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getProvince().equalsIgnoreCase(venezia)) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), controllo);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (verona.equalsIgnoreCase(t)) {
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getProvince().equalsIgnoreCase(verona)) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), controllo);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (vicenza.equalsIgnoreCase(t)) {
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getProvince().equalsIgnoreCase(vicenza)) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), controllo);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (treviso.equalsIgnoreCase(t)) {
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getProvince().equalsIgnoreCase(treviso)) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), controllo);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (padova.equalsIgnoreCase(t)) {
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getProvince().equalsIgnoreCase(padova)) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), controllo);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (belluno.equalsIgnoreCase(t)) {
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getProvince().equalsIgnoreCase(belluno)) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), controllo);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else if (rovigo.equalsIgnoreCase(t)) {
                    ArrayList<Info> temp = new ArrayList<>();
                    for (Info i : ris) {
                        if (i.getProvince().equalsIgnoreCase(rovigo)) {
                            temp.add(i);
                        }
                    }
                    ris = temp;
                    clusterPerson.clear();
                    mClusterManager.clearItems();
                    for (Info i : ris) {
                        Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), controllo);
                        if (!exist(clusterPerson, p.getPosition())) {
                            clusterPerson.add(p);
                            mClusterManager.addItem(p);
                        }
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                    mClusterManager.cluster();
                } else {
                    if (!activate) {
                        clusterPerson.clear();
                        mClusterManager.clearItems();
                        for (Info i : MainActivity.info) {
                            Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), 0);
                            if (!exist(clusterPerson, p.getPosition())) {
                                clusterPerson.add(p);
                                mClusterManager.addItem(p);
                            }
                        }
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
                        mClusterManager.cluster();
                    }
                }
            }
        } else {
            clusterPerson.clear();
            mClusterManager.clearItems();
            for (Info i : MainActivity.info) {
                Person p = new Person(i.getLat(), i.getLng(), i.getBeneficiaryName(), "", i.getEligibleExpenditure(), i.getOperationName(), i.getOperationSummary(), i.getTown(), i.getStartOperation(), i.getEndOpeation(), i.getCap(), i.getProvince(), 0);
                if (!exist(clusterPerson, p.getPosition())) {
                    clusterPerson.add(p);
                    mClusterManager.addItem(p);
                }
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.758344, 11.908054), (float)7.9));
            mClusterManager.cluster();
        }
    }
}