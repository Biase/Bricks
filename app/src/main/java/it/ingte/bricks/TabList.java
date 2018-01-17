package it.ingte.bricks;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by davide on 17/12/2017.
 */

public class TabList extends Fragment {
    ListView lst;
    MaterialSearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = ((MainActivity) getActivity()).searchView;
        searchView.setMenuItem(searchItem);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {



            }

            @Override
            public void onSearchViewClosed() {
                    populateList();

                }
        });


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit (String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()) {
                    List<Info> lstFound = new ArrayList<>();
                    List<Info> all = MainActivity.manager.getDbhelper().getData();

                    for(Info items : all) {
                        if(items.getBeneficiaryName().toLowerCase().contains(newText.toLowerCase()) || items.getTown().toLowerCase().contains(newText.toLowerCase())) {
                            lstFound.add(items);
                        }
                    }

                    generateList(lstFound);
                    Log.d("POsix","hai cercato qualcosa");
                }
                else {
                    // if search text is null
                    // return default
                    populateList();
                }

                return true;
            }

        });



    }



    public void generateList(List<Info> source) {
        MyCustomAdapter adapter = new MyCustomAdapter(getContext(), R.layout.da_text,source);
        lst.setAdapter(adapter);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_list, container, false);
        lst = (ListView) rootView.findViewById(R.id.lstView);
        populateList();

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ArrayList<Info> a=MainActivity.manager.getDbhelper().getData();
                Intent intent=new Intent(TabList.this.getActivity(),itemClick.class);
                intent.putExtra("beneficary",a.get(i).getBeneficiaryName());
                intent.putExtra("price",a.get(i).getEligibleExpenditure());
                intent.putExtra("startDate",a.get(i).getStartOperation());
                intent.putExtra("endDate",a.get(i).getEndOpeation());
                intent.putExtra("town",a.get(i).getTown());
                intent.putExtra("province",a.get(i).getProvince());
                intent.putExtra("cap",a.get(i).getCap());
                intent.putExtra("country",a.get(i).getCountry());
                intent.putExtra("description",a.get(i).getOperationName());
                startActivity(intent);
            }
        });

        Log.i("Posizione", "Sono uscito dal populateList");
        return rootView;
    }

    public void populateList() {
        //Settiamo il nuova adapter
        ArrayList<Info> myData = MainActivity.manager.getDbhelper().getData();

        //stampiamo per verificare che non sia vuoto
        for(Info i : myData)
            Log.i("Infor", i.getCountry());
        lst.setAdapter(new MyCustomAdapter(getContext(), R.layout.da_text, myData));

    }

}




