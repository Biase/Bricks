package it.ingte.bricks;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.miguelcatalan.materialsearchview.MaterialSearchView;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static DBmanager manager;
    public static ArrayList<Info> info;
    private ViewPager mViewPager;
    MaterialSearchView searchView;


    private static String[] lstSource;

    public static String[] getLstSource() {
        return lstSource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(MainActivity.this, CircularBar.class);
        startActivityForResult(intent, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("qui", "onCreate: Started.");
        manager = new DBmanager(this);
        info = manager.getDbhelper().getData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FSE_Bricks");

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
        searchView.showSearch(false);
        searchView.closeSearch();


        /**
         * Function for fab button
         */
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Per ora lasciatelo non si sa mai", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(getBaseContext(),
                    "Are you sure you want to quit?", Toast.LENGTH_SHORT)
                    .show();
        }
        back_pressed = System.currentTimeMillis();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
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
            Intent intentProject = new Intent(this, Project.class);
            this.startActivity(intentProject);
            return true;
        }
        if (id == R.id.action_contact) {
            Intent intentContact = new Intent(this, Contact.class);
            this.startActivity(intentContact);
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
                case 2:
                    TabBeneficiary tab3 = new TabBeneficiary();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    public ArrayList<Info> prova() {
        Bundle b = getIntent().getExtras();
        ArrayList<Info> a = null;
        if (b != null) {
            a = b.getParcelableArrayList("data");
        } else {
            Toast.makeText(getApplicationContext(), "ciao", Toast.LENGTH_SHORT).show();
        }
        return a;


    }
}
