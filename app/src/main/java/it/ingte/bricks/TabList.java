package it.ingte.bricks;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Icon;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by davide on 17/12/2017.
 */

public class TabList extends Fragment {
    ListView lst;
    MaterialSearchView searchView;
    ArrayList<Info> result= MainActivity.info;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
      //  getFragmentManager();

    }

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
                Intent intent = new Intent(TabList.this.getActivity(), Filter.class);
                startActivity(intent);
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
                populateList();
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                        intent.putExtra("myInfo",MainActivity.info.get(i));
                        startActivity(intent);
                    }
                });


            }

        });


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(final String query) {

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                }

            //    result = MainActivity.manager.getDbhelper().getResult(query);
                if (result.isEmpty()) {
                    Toast.makeText(getContext(), "no result from search", Toast.LENGTH_SHORT).show();

                }
                generateList(result);
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                        intent.putExtra("myInfo", result.get(i));
                        startActivity(intent);

                    }
                });

                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
             /*  String s = newText + "";

                if (newText != null && !newText.isEmpty()) {
                    List<Info> lstFound = new ArrayList<>();
                    List<Info> all = MainActivity.manager.getDbhelper().getData();
                    Log.d("contenuto s", s);
                    for (Info items : all) {
                        if (items.getBeneficiaryName().toLowerCase().contains(newText.toLowerCase()) ||
                                items.getTown().toLowerCase().contains(newText.toLowerCase())) {
                            lstFound.add(items);
                        }
                    }
                 //   generateList(lstFound);

                } else {
                    populateList(); // if search text is null return default
                } */

                return true;
            }


        });
         /*fine parte search */


    }

    public void generateList(ArrayList<Info> source) {
        MyCustomAdapter adapter = new MyCustomAdapter(getContext(), R.layout.da_text, source);
        lst.setAdapter(adapter);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_list, container, false);
        lst = (ListView) rootView.findViewById(R.id.lstView);


     //  Bundle bundle = getArguments();
       // ArrayList<Info> a = bundle.getParcelableArrayList("data");


        populateList();

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                intent.putExtra("myInfo", MainActivity.info.get(i));
                startActivity(intent);
            }
        });



      // ArrayList<Info> a = (ArrayList) bundle.getParcelableArrayList("data");
    //   generateList(a);
        return rootView;
    }

    public void populateList() {
        lst.setAdapter(new MyCustomAdapter(getContext(), R.layout.da_text, MainActivity.info));

    }


}




