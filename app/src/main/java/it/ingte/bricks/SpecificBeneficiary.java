package it.ingte.bricks;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.ActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.quadtree.PointQuadTree;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import android.support.v7.widget.Toolbar;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.graphics.Typeface.BOLD;

/**
 * Created by Domenico on 27/02/2018.
 */

public class SpecificBeneficiary extends AppCompatActivity implements OnChartValueSelectedListener{
    static float max;
    static float min;

    View barChartView;
    BarChart barChart;
    ListView lst;
    static ArrayList<Info> original = new ArrayList<>();
    ArrayList<Info> result = new ArrayList<>();
    ArrayList<Info> a = new ArrayList<>();

    //filtri
    final String prezzo1 = "Tra 0.00 € e 500.000 €";
    final String prezzo2 = "Tra 500.000 € e 1.000.000 €";
    final String prezzo3 = "Superiore 1.000.000 €";
    int valPrezzo = 0;

    final String ds5 = "2015";
    final String ds6 = "2016";
    final String ds7 = "2017";

    ArrayList<String> beneficiari = new ArrayList<>();
    static ArrayList<Double> mediaImporto = new ArrayList<>();

    MaterialSearchView searchView;
    String CodeBeneficiary;
    LatLng temp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_bene_spe);

        CodeBeneficiary = getIntent().getStringExtra("beneficiaryCode");
        temp = (LatLng) getIntent().getParcelableExtra("position");

        TextView titolo = (TextView) findViewById(R.id.TextViewTitolo);
        titolo.setText(getIntent().getStringExtra("name")+"\nNumero progetti: "+getIntent().getIntExtra("nproject",0));

        barChart = (BarChart) findViewById(R.id.bargraph);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> theDates = new ArrayList<>();
        original = new ArrayList<>();
        mediaImporto = new ArrayList<>();
        Listbeneficiary(CodeBeneficiary, temp);

        for(int i = 0; i < original.size(); i++){
            beneficiari.add(original.get(i).getBeneficiaryName());
            double valore = mediaImporto.get(i);
            float val = (float) valore;
            barEntries.add(new BarEntry(val, i));
            theDates.add("");
        }
        MyBarDataSetSpeBen barDataSet = new MyBarDataSetSpeBen(barEntries, "Beneficiari",original);
        //String smin = String.format("%1$.2f", 500000,00);
        //String smax = String.format("%1$.2f", 1000000,00);
        //String [] name = {"Valori unità di misura del Grafico:"," - Valori asse X -> Beneficiario"," - Valori asse Y -> Importo totale dei progetti in €","Importo < di " + prova(smin) + "€","Importo >= di "  + prova(smin) + "€ e < di " + prova(smax) + "€","Importo >= di " + prova(smax) + "€"};
        String [] name = {"Valori unità di misura del Grafico:"," - Valori asse X -> Anno inizio del progetto"," - Valori asse Y -> Importo del progetto in €","Anno 2015","Anno 2016","Anno 2017"};
        int [] color = {0x00FFFFFF, 0x00FFFFFF, 0x00FFFFFF, 0xBB00008B, 0xBB7B68EE, 0x9900BEEE};
        barDataSet.setColors(color);
        BarData theData = new BarData(theDates, barDataSet);
        Legend l = barChart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
        l.setEnabled(true);
        //l.setPosition();
        l.setCustom(color, name);
        barChart.setX(0f);
        barChart.setY(0f);
        barChart.setScaleY(1.0f);
        barChart.setScaleX(1.0f);
        barChart.getLegend().setWordWrapEnabled(true);
        barChart.setData(theData);
        //barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.setDescription("");
        barChart.callOnClick();
        barChart.setOnChartValueSelectedListener(this);

        lst = (ListView) findViewById(R.id.lstView);
        populateList();
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                intent.putExtra("myInfo", original.get(i));
                startActivity(intent);
                /*
                Intent intent = new Intent(SpecificBeneficiary.this, ProvaActivity.class);
                intent.putExtra("name", original.get(i).getBeneficiaryName());
                intent.putExtra("operation", original.get(i).getOperationName());
                intent.putExtra("town", original.get(i).getTown());
                LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                intent.putExtra("position", position);
                intent.putExtra("control", 0);
                intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                startActivity(intent);
                /*
                Intent intent = new Intent(TabBeneficiary.this.getActivity(), itemClick.class);
                intent.putExtra("myInfo", original.get(i));
                */
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String t = data.getStringExtra("resultProvince");
            String op = data.getStringExtra("resultOrderPrice");
            boolean activate = false;

            if (requestCode == 1 && resultCode == RESULT_OK) {
                original.clear();
                mediaImporto.clear();
                Listbeneficiary(CodeBeneficiary, temp);

                Log.i("return procva ",""+t);
                Log.i("return aba",""+op);

                if (prezzo1.equals(t)) {
                    activate = true;
                    generateList(original);
                    for (int i = 0; i < original.size(); i++) {
                        if (!(original.get(i).getEligibleExpenditure() < 500000)) {
                            original.remove(i);
                            mediaImporto.remove(i);
                            i--;
                        }
                    }
                    barChart.clear();
                    populateGraphic();
                    barChart.zoom(0f, 0f, 0f, 0f);
                    generateList(original);
                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                            intent.putExtra("myInfo", original.get(i));
                            /*intent.putExtra("name", original.get(i).getBeneficiaryName());
                            intent.putExtra("operation", original.get(i).getOperationName());
                            intent.putExtra("town", original.get(i).getTown());
                            LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                            intent.putExtra("position", position);
                            intent.putExtra("control", 0);
                            intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                            */
                            startActivity(intent);
                        }
                    });
                } else if (prezzo2.equals(t)) {
                    activate = true;
                    generateList(original);
                    for (int i = 0; i < original.size(); i++) {
                        if (!((original.get(i).getEligibleExpenditure() >= 500000) && (original.get(i).getEligibleExpenditure() < 1000000))) {
                            original.remove(i);
                            mediaImporto.remove(i);
                            i--;
                        }
                    }
                    barChart.clear();
                    populateGraphic();
                    barChart.zoom(0f, 0f, 0f, 0f);
                    generateList(original);
                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                            intent.putExtra("myInfo", original.get(i));
                            /*intent.putExtra("name", original.get(i).getBeneficiaryName());
                            intent.putExtra("operation", original.get(i).getOperationName());
                            intent.putExtra("town", original.get(i).getTown());
                            LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                            intent.putExtra("position", position);
                            intent.putExtra("control", 0);
                            intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                            */
                            startActivity(intent);
                        }
                    });
                } else if (prezzo3.equals(t)) {
                    activate = true;
                    generateList(original);
                    for (int i = 0; i < original.size(); i++) {
                        if (!(original.get(i).getEligibleExpenditure() >= 1000000)) {
                            original.remove(i);
                            mediaImporto.remove(i);
                            i--;
                        }
                    }
                    barChart.clear();
                    populateGraphic();
                    barChart.zoom(0f, 0f, 0f, 0f);
                    generateList(original);
                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                            intent.putExtra("myInfo", original.get(i));
                            /*intent.putExtra("name", original.get(i).getBeneficiaryName());
                            intent.putExtra("operation", original.get(i).getOperationName());
                            intent.putExtra("town", original.get(i).getTown());
                            LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                            intent.putExtra("position", position);
                            intent.putExtra("control", 0);
                            intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                            */
                            startActivity(intent);
                        }
                    });
                }
                if (ds5.equals(op)) {
                    for (int i = 0; i < original.size(); i++) {
                        if (!original.get(i).getStartOperation().substring(6,8).equals("15")) {
                            original.remove(i);
                            mediaImporto.remove(i);
                            i--;
                        }
                    }
                    barChart.clear();
                    populateGraphic();
                    barChart.zoom(0f, 0f, 0f, 0f);
                    generateList(original);
                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                            intent.putExtra("myInfo", original.get(i));
                            /*intent.putExtra("name", original.get(i).getBeneficiaryName());
                            intent.putExtra("operation", original.get(i).getOperationName());
                            intent.putExtra("town", original.get(i).getTown());
                            LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                            intent.putExtra("position", position);
                            intent.putExtra("control", 0);
                            intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                            */
                            startActivity(intent);
                        }
                    });
                } else if (ds6.equals(op)) {
                    for (int i = 0; i < original.size(); i++) {
                        if (!original.get(i).getStartOperation().substring(6,8).equals("16")) {
                            original.remove(i);
                            mediaImporto.remove(i);
                            i--;
                        }
                    }
                    barChart.clear();
                    populateGraphic();
                    barChart.zoom(0f, 0f, 0f, 0f);
                    generateList(original);
                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                            intent.putExtra("myInfo", original.get(i));
                            /*intent.putExtra("name", original.get(i).getBeneficiaryName());
                            intent.putExtra("operation", original.get(i).getOperationName());
                            intent.putExtra("town", original.get(i).getTown());
                            LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                            intent.putExtra("position", position);
                            intent.putExtra("control", 0);
                            intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                            */
                            startActivity(intent);
                        }
                    });
                } else if (ds7.equals(op)) {
                    for (int i = 0; i < original.size(); i++) {
                        if (!original.get(i).getStartOperation().substring(6,8).equals("17")) {
                            original.remove(i);
                            mediaImporto.remove(i);
                            i--;
                        }
                    }
                    barChart.clear();
                    populateGraphic();
                    barChart.zoom(0f, 0f, 0f, 0f);
                    generateList(original);
                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                            intent.putExtra("myInfo", original.get(i));
                            /*intent.putExtra("name", original.get(i).getBeneficiaryName());
                            intent.putExtra("operation", original.get(i).getOperationName());
                            intent.putExtra("town", original.get(i).getTown());
                            LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                            intent.putExtra("position", position);
                            intent.putExtra("control", 0);
                            intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                            */
                            startActivity(intent);
                        }
                    });
                }
                else {
                    if (!activate) {
                        /*
                        for (Info i : original) {
                            result.add(i);
                        }
                        */

                        original.clear();
                        mediaImporto.clear();
                        Listbeneficiary(CodeBeneficiary, temp);
                        barChart.clear();
                        populateGraphic();
                        barChart.zoom(0f, 0f, 0f, 0f);
                        generateList(original);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                                //Log.i("vado qua ma non va",""+original.get(i));
                                intent.putExtra("myInfo", original.get(i));
                                /*intent.putExtra("name", original.get(i).getBeneficiaryName());
                                intent.putExtra("operation", original.get(i).getOperationName());
                                intent.putExtra("town", original.get(i).getTown());
                                LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                                intent.putExtra("position", position);
                                intent.putExtra("control", 0);
                                intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                                */
                                startActivity(intent);
                            }
                        });
                    }
                }
            }


        } else {
            original.clear();
            mediaImporto.clear();
            Listbeneficiary(CodeBeneficiary, temp);
            barChart.clear();
            populateGraphic();
            barChart.zoom(0f, 0f, 0f, 0f);
            generateList(original);
            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Log.i("vado qua ma non va",""+original.get(i));
                    Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                    intent.putExtra("myInfo", original.get(i));

                    /*intent.putExtra("name", original.get(i).getBeneficiaryName());
                    intent.putExtra("operation", original.get(i).getOperationName());
                    intent.putExtra("town", original.get(i).getTown());
                    LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                    intent.putExtra("position", position);
                    intent.putExtra("control", 0);
                    intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                    */
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem contact = menu.findItem(R.id.action_contact);
        contact.setVisible(false);
        MenuItem project = menu.findItem(R.id.action_project);
        project.setVisible(false);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);
       /*
        searchView = findViewById(R.id.search_view2);
        searchView.setMenuItem(searchItem);
*/
        MenuItem filter = menu.findItem(R.id.action_filter);
        filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                //Log.i("sono qui","sono qui");
                result.clear();
                a.clear();
                Intent intent = new Intent(SpecificBeneficiary.this, FilterSpecificBeneficiary.class);
                startActivityForResult(intent, 1);
                return false;
            }
        });

/*
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {

            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                original.clear();
                mediaImporto.clear();
                Listbeneficiary(CodeBeneficiary, temp);
                barChart.clear();
                populateGraphic();
                barChart.zoom(0f, 0f, 0f, 0f);
                generateList(original);
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                        intent.putExtra("name", original.get(i).getBeneficiaryName());
                        intent.putExtra("operation", original.get(i).getOperationName());
                        intent.putExtra("town", original.get(i).getTown());
                        LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                        intent.putExtra("position", position);
                        intent.putExtra("control", valPrezzo);
                        intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                        startActivity(intent);

                    }
                });
            }

        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(final String query) {
                //pulisco
                original.clear();
                mediaImporto.clear();
                Listbeneficiary(CodeBeneficiary, temp);
                barChart.clear();
                populateGraphic();
                barChart.zoom(0f, 0f, 0f, 0f);
                generateList(original);

                result = MainActivity.manager.getDbhelper().getResult(query);
                InputMethodManager imm = (InputMethodManager) SpecificBeneficiary.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }

                if (result.isEmpty()) {
                    Toast.makeText(SpecificBeneficiary.this, "no result from search", Toast.LENGTH_SHORT).show();
                    original.clear();
                    mediaImporto.clear();
                    barChart.clear();
                } else {
                    Log.i("result size: "," "+result.size());

                    for (int i = 0; i < original.size(); i++) {
                        if (original.get(i).getProvince().contains(query.toUpperCase()) || original.get(i).getTown().contains(query.toUpperCase()) || original.get(i).getBeneficiaryName().contains(query.toUpperCase())) {
                        } else {
                            original.remove(i);
                            mediaImporto.remove(i);
                            i--;
                        }
                    }
                    barChart.clear();
                    populateGraphic();
                    barChart.zoom(0f, 0f, 0f, 0f);
                    generateList(original);
                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
                            intent.putExtra("beneficiaryCode", original.get(i).getBeneficiarycode());
                            intent.putExtra("name", original.get(i).getBeneficiaryName());
                            intent.putExtra("operation", original.get(i).getOperationName());
                            intent.putExtra("town", original.get(i).getTown());
                            LatLng position = new LatLng(original.get(i).getLat(),original.get(i).getLng());
                            intent.putExtra("position", position);
                            intent.putExtra("control", 0);
                            intent.putExtra("nproject", countProject(original.get(i).getBeneficiaryName(), original.get(i).getLat(), original.get(i).getLng()));
                            startActivity(intent);
                        }
                    });
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MaterialSearchView x = findViewById(R.id.search_view2);
                x.setVisibility(View.VISIBLE);

                return true;
            }
        });
*/












        /*
        MenuItem filter = menu.findItem(R.id.action_filter);
        searchView.setMenuItem(filter);
        filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                //Log.i("sono qui","sono qui");
                result.clear();
                a.clear();
                Intent intent = new Intent(SpecificBeneficiary.this, FilterBeneficiary.class);
                startActivityForResult(intent, 1);
                return false;
            }
        });
        */
        return true;
    }

    public void generateList(ArrayList<Info> source) {
        MyCustomAdapterSpeBen adapter = new MyCustomAdapterSpeBen(this, R.layout.da_text, source);
        lst.setAdapter(adapter);
    }


    public void populateList() {
        lst.setAdapter(new MyCustomAdapterSpeBen(this, R.layout.da_text, original));

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        //   Intent intent = new Intent(TabBeneficiary.this.getActivity(), ActivityBeneficiary.class);
        /*
        BarData pro = barChart.getBarData();
        IBarDataSet pro2 = pro.getFirstLeft();
        BarEntry pro3 = pro2.getEntryForXIndex(dataSetIndex);
        */
        // intent.putExtra("id",e.getXIndex());
        // startActivity(intent);
        //Intent intent = new Intent(SpecificBeneficiary.this, ProvaActivity.class);

        Intent intent = new Intent(SpecificBeneficiary.this, itemClick.class);
        intent.putExtra("myInfo", original.get(e.getXIndex()));
        startActivity(intent);
        /*
        intent.putExtra("name", original.get(e.getXIndex()).getBeneficiaryName());
        intent.putExtra("operation", original.get(e.getXIndex()).getOperationName());
        intent.putExtra("town", original.get(e.getXIndex()).getTown());
        LatLng pos = new LatLng(original.get(e.getXIndex()).getLat(),original.get(e.getXIndex()).getLng());
        intent.putExtra("position", pos);
        intent.putExtra("control", valPrezzo);
        intent.putExtra("nproject", countProject(original.get(e.getXIndex()).getBeneficiaryName(), original.get(e.getXIndex()).getLat(), original.get(e.getXIndex()).getLng()));
        startActivity(intent);
    */}

    @Override
    public void onNothingSelected() {

    }

    public void Listbeneficiary(String key, LatLng pos){
        ArrayList<Info> init = MainActivity.info;
        Collections.sort(init, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                String s1 = o1.getBeneficiaryName();
                String s2 = o2.getBeneficiaryName();
                return s1.compareToIgnoreCase(s2);
            }
        });
/*
        ArrayList<Info> orderPos = new ArrayList<>();
        ArrayList<Info> initTemp = new ArrayList<>();
        orderPos.add(init.get(0));
        //  Log.i("init",""+0+" "+init.get(0).getBeneficiaryName()+" "+init.get(0).getTown()+" "+init.size());


        for(int i = 1; i < init.size(); i++){
            // Log.i("init",""+i+" "+init.get(i).getBeneficiaryName()+" "+init.get(i).getTown()+" "+init.size());
            //LatLng posx = new LatLng(init.get(i-1).getLat(), init.get(i-1).getLng());
            //LatLng posy = new LatLng(init.get(i).getLat(), init.get(i).getLng());
            if(i == init.size()-1){
                if ((init.get(i - 1).getBeneficiaryName().equalsIgnoreCase(init.get(i).getBeneficiaryName()))) {
                    orderPos.add(init.get(i));
                    Collections.sort(orderPos, new Comparator<Info>() {
                        @Override
                        public int compare(Info o1, Info o2) {
                            String s1 = o1.getTown();
                            String s2 = o2.getTown();
                            return s1.compareToIgnoreCase(s2);
                        }
                    });
                    int length = orderPos.size();
                    for (int k = 0; k < orderPos.size(); k++) {
                        initTemp.add(orderPos.get(k));
                    }
                } else {
                    Collections.sort(orderPos, new Comparator<Info>() {
                        @Override
                        public int compare(Info o1, Info o2) {
                            String s1 = o1.getTown();
                            String s2 = o2.getTown();
                            return s1.compareToIgnoreCase(s2);
                        }
                    });
                    int length = orderPos.size();
                    for (int k = 0; k < orderPos.size(); k++) {
                        initTemp.add(orderPos.get(k));
                    }
                    initTemp.add(init.get(i));
                }
            }
            else {
                if ((init.get(i - 1).getBeneficiaryName().equalsIgnoreCase(init.get(i).getBeneficiaryName()))) {
                    orderPos.add(init.get(i));
                } else {
                    Collections.sort(orderPos, new Comparator<Info>() {
                        @Override
                        public int compare(Info o1, Info o2) {
                            String s1 = o1.getTown();
                            String s2 = o2.getTown();
                            return s1.compareToIgnoreCase(s2);
                        }
                    });
                    int length = orderPos.size();
                    for (int k = 0; k < orderPos.size(); k++) {
                        initTemp.add(orderPos.get(k));
                    }
                    orderPos.clear();
                    orderPos.add(init.get(i));
                }
            }
        }
/*
        for(int i = 0; i < initTemp.size(); i++){
                Log.i("initTemp",""+i+" "+initTemp.get(i).getBeneficiaryName()+" "+initTemp.get(i).getTown()+" "+initTemp.size());
        }
*//*

        original.add(initTemp.get(0));
        //Log.i("Original",""+0+" "+init.get(0).getBeneficiaryName()+" "+init.get(0).getTown());
        for(int i = 1; i < initTemp.size(); i++){
            //LatLng posx = new LatLng(init.get(i-1).getLat(), init.get(i-1).getLng());
            //LatLng posy = new LatLng(init.get(i).getLat(), init.get(i).getLng());
            if(!((initTemp.get(i-1).getBeneficiaryName().equalsIgnoreCase(initTemp.get(i).getBeneficiaryName())) && (initTemp.get(i-1).getTown().equalsIgnoreCase(initTemp.get(i).getTown())))){
                original.add(initTemp.get(i));
                //Log.i("Original",""+i+" "+init.get(i).getBeneficiaryName()+" "+init.get(i).getTown());
            }
        }

/*
        for(int i = 0; i < initTemp.size(); i++){
                Log.i("initTemp",""+i+" "+initTemp.get(i).getBeneficiaryName()+" "+initTemp.get(i).getTown());
        }
*/
        Log.i("original size: ",""+original.size());
        original.clear();
        mediaImporto.clear();
        for(int i = 0; i < init.size(); i++){
            if (init.get(i).getBeneficiarycode().equalsIgnoreCase(key) && init.get(i).getLat() == pos.latitude && init.get(i).getLng() == pos.longitude) {
                original.add(init.get(i));
                mediaImporto.add((init.get(i).getEligibleExpenditure()));
            }
        }
        Log.i("original size: ",""+original.size());



/*
        int cont = 0;
        double somma = 0;
        for(int i = 0; i < initTemp.size()-1; i++){
            if(i == initTemp.size()-2){
                if (!((initTemp.get(i).getBeneficiaryName().equalsIgnoreCase(initTemp.get(i + 1).getBeneficiaryName())) && (initTemp.get(i).getTown().equalsIgnoreCase(initTemp.get(i + 1).getTown())))) {
                    //mediaImporto.add((somma + initTemp.get(i).getEligibleExpenditure()) / (cont + 1));
                    mediaImporto.add((somma + initTemp.get(i).getEligibleExpenditure()));
                    mediaImporto.add(initTemp.get(i+1).getEligibleExpenditure());
                } else {
                    //mediaImporto.add((somma + initTemp.get(i).getEligibleExpenditure() + initTemp.get(i+1).getEligibleExpenditure()) / (cont + 2));
                    mediaImporto.add((somma + initTemp.get(i).getEligibleExpenditure() + initTemp.get(i+1).getEligibleExpenditure()));
                }
            } else {
                if (!((initTemp.get(i).getBeneficiaryName().equalsIgnoreCase(initTemp.get(i + 1).getBeneficiaryName())) && (initTemp.get(i).getTown().equalsIgnoreCase(initTemp.get(i + 1).getTown())))) {
                    //mediaImporto.add((somma + initTemp.get(i).getEligibleExpenditure()) / (cont + 1));
                    mediaImporto.add((somma + initTemp.get(i).getEligibleExpenditure()));
                    cont = 0;
                    somma = 0;
                } else {
                    somma += initTemp.get(i).getEligibleExpenditure();
                    cont++;
                }
            }
        }
*/
        double mini = mediaImporto.get(0);
        double maxi = mediaImporto.get(0);
        for(int i = 1; i < mediaImporto.size(); i++) {
            if(mediaImporto.get(i) < mini){
                mini = mediaImporto.get(i);
            }
            if(mediaImporto.get(i) > maxi){
                maxi = mediaImporto.get(i);
            }
        }
        quickSort(mediaImporto, original, 0, mediaImporto.size()-1); //ordina in prezzo decrescente

        min = (float) mini;
        max = (float) maxi;
        Log.i("Min"," "+min);
        Log.i("Max"," "+max);
        Log.i("original"," "+original.size());
        Log.i("mediaImporto"," "+mediaImporto.size());
    }



    public int countProject(String ben, double x, double y){
        int pro = 0;
        ArrayList<Info> init = MainActivity.info;
        LatLng posx = new LatLng(x, y);
        for(int i = 0; i < init.size(); i++){
            LatLng posy = new LatLng(init.get(i).getLat(), init.get(i).getLng());
            if ((ben.equalsIgnoreCase(init.get(i).getBeneficiaryName()) && (posx.equals(posy)))){
                pro++;
            }
        }
        return pro;
    }


    public int partition(ArrayList<Double> arr, ArrayList<Info> arrB, int left, int right) {
        int i = left, j = right;
        double tmp;
        Info temp;
        String pivot = arrB.get((left + right) / 2).getStartOperation();
        Date pivo = new Date(Integer.parseInt(pivot.substring(6,8)),Integer.parseInt(pivot.substring(3,5)),Integer.parseInt(pivot.substring(0,2)));
        while (i <= j) {
            String si = arrB.get(i).getStartOperation();
            Date di = new Date(Integer.parseInt(si.substring(6,8)),Integer.parseInt(si.substring(3,5)),Integer.parseInt(si.substring(0,2)));
            while (di.compareTo(pivo) < 0) {
                i++;
                si = arrB.get(i).getStartOperation();
                di = new Date(Integer.parseInt(si.substring(6,8)),Integer.parseInt(si.substring(3,5)),Integer.parseInt(si.substring(0,2)));
            }
            String sj = arrB.get(j).getStartOperation();
            Date dj = new Date(Integer.parseInt(sj.substring(6,8)),Integer.parseInt(sj.substring(3,5)),Integer.parseInt(sj.substring(0,2)));
            while (dj.compareTo(pivo) > 0) {
                j--;
                sj = arrB.get(j).getStartOperation();
                dj = new Date(Integer.parseInt(sj.substring(6,8)),Integer.parseInt(sj.substring(3,5)),Integer.parseInt(sj.substring(0,2)));
            }
            if (i <= j) {
                tmp = arr.get(i);
                temp = arrB.get(i);

                arr.set(i, arr.get(j));
                arrB.set(i, arrB.get(j));

                arr.set(j, tmp);
                arrB.set(j, temp);

                i++;
                j--;
            }
        };
        return i;
    }

    public void quickSort(ArrayList<Double> arr, ArrayList<Info> arrB, int left, int right) {
        int index = partition(arr, arrB, left, right);
        if (left < index - 1)
            quickSort(arr, arrB, left, index - 1);
        if (index < right)
            quickSort(arr, arrB, index, right);
    }

    public String aggiustaStr(String s){
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '.'){
                if(s.length()-i == 2){
                    s += "0";
                }
                String t = s.substring(0,i);
                t += ",";
                t += s.substring(i+1, s.length());
                s = t;
                int j = i-4;
                while(j >= 0){
                    String x = s.substring(0,j+1);
                    x += ".";
                    x += s.substring(j+1, s.length());
                    s = x;
                    j-=3;
                }
            }
        }
        return s;
    }

    public String prova(String s){
        int cont = -1;
        boolean tr = false;
        for(int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == ',') {
                cont = 4;
                tr = true;
            }
            if (tr) {
                cont--;
            }
            if (cont == 0 && i != 0) {
                cont = 3;
                String t = s.substring(0, i);
                t += ".";
                t += s.substring(i, s.length());
                s = t;
            }
        }
        return s;
    }


    public void populateGraphic(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> theDates = new ArrayList<>();
        for(int i = 0; i < original.size(); i++){
            beneficiari.add(original.get(i).getBeneficiaryName());
            double valore = mediaImporto.get(i);
            float val = (float) valore;
            barEntries.add(new BarEntry(val, i));
            theDates.add("");
        }
        //barChart.zoom(0f, 0f, 0f, 0f, YAxis.AxisDependency.LEFT);
        MyBarDataSetSpeBen barDataSet = new MyBarDataSetSpeBen(barEntries, "Beneficiari", original);
        //String smin = ""+(max/3);
        //String smax = ""+((max/3)*2);
        //String smin = String.format("%1$.2f", 500000,00);
        //String smax = String.format("%1$.2f", 1000000,00);

        String [] name = {"Valori unità di misura del Grafico:"," - Valori asse X -> Anno inizio del progetto"," - Valori asse Y -> Importo del progetto in €","Anno 2015","Anno 2016","Anno 2017"};
        int [] color = {0x00FFFFFF, 0x00FFFFFF, 0x00FFFFFF, 0xBB00008B, 0xBB7B68EE, 0x9900BEEE};

        barDataSet.setColors(color);
        BarData theData = new BarData(theDates, barDataSet);

        Legend l = barChart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
        l.setEnabled(true);
        //l.setPosition();
        l.setCustom(color, name);
        barChart.getLegend().setWordWrapEnabled(true);
        barChart.setData(theData);
        //barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.setDescription("");
        barChart.callOnClick();
        barChart.setOnChartValueSelectedListener(this);
        barChart.zoom(0f, 0f, 0f, 0f, YAxis.AxisDependency.LEFT);
    }
}
