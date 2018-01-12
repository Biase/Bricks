package it.ingte.bricks;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static DBmanager manager;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    // Variabili
    MaterialSearchView searchView;

    private static String[] lstSource;


    public static String [] getLstSource() {
        return lstSource;
    }


    private void populateList() {
        //Dove inseriamo dentro la lettura da editare
        String param;
        String id;
        //Lista contenente la roba da stampare
        ArrayList<String> rec = new ArrayList<>();

        //Creazione del cursore per i dati dal DB
        Cursor c = MainActivity.manager.getDatabaseAccess().rawQuery("SELECT Beneficiaryname, Town FROM record",null);

        //Scorre il cursore e inserisce nell'arraylist il contenuto
        while(c.moveToNext()) {
           // id = c.getString(0);
            param = c.getString(1);
            param = param.split(":")[0];
            rec.add(c.getString(0) + " in " + param);
        }

        lstSource = new String[rec.size()];
        for (int i = 0; i < lstSource.length; i++) {
            lstSource[i] = (String) rec.get(i);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new DBmanager(this);

        //Qui sotto mettiamo la nostra roba

        //Riempiamo la lista con la roba
        populateList();






        //Fino a qua

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three (two in our case)
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        /**
         * Function for search bar
         */

        searchView = findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {

            @Override
            public void onSearchViewShown() {
                Log.e("onSearchViewShown", "show");
            }

            @Override
            public void onSearchViewClosed() {
                // If closed search view , lstView will return default
                Log.e("onSearchViewClosed", "in");
                generateList(lstSource);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()) {
                    List<String> lstFound = new ArrayList<>();
                    for(String items : lstSource) {
                        items=items.toLowerCase();
                        if(items.contains(newText.toLowerCase())) {
                            lstFound.add(items);
                        }
                    }

                    generateListTemp(lstFound);
                }
                else {
                    // if search text is null
                    // return default
                    generateList(lstSource);
                }

                return true;
            }
        });


        /**
         * Function for fab button
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Per ora lasciatelo non si sa mai", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void generateList(String[] source) {
        ListView lst = findViewById(R.id.lstView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.da_text, source);
        lst.setAdapter(adapter);
    }
    public void generateListTemp(List<String> source) {
        ListView lst = findViewById(R.id.lstView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.da_text, source);
        lst.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_project) {
            return true;
        }
        if (id == R.id.action_we) {
            return true;
        }
        if (id == R.id.action_contact) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Return the current tabs
            switch (position) {
                case 0:
                    TabMap tab1 = new TabMap();
                    return tab1;
                case 1:
                    TabList tab2 = new TabList();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

}
