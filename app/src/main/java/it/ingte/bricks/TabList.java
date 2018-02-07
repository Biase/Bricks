package it.ingte.bricks;

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

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class TabList extends Fragment {
    ListView lst;
    MaterialSearchView searchView;
    ArrayList<Info> original = MainActivity.info;
    ArrayList<Info> result = new ArrayList<>();
    ArrayList<Info> a = new ArrayList<>();
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
    final String az = "A-Z";
    final String za = "Z-A";
    final String startDateCresc = "Crescente";
    final String startDateDesc = "Decrescente";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String s = data.getStringExtra("resultPrice");
            String t = data.getStringExtra("resultProvince");
            String b = data.getStringExtra("resultAlfabetic");
            String dc = data.getStringExtra("resultStartDate");
            boolean activate = false;

            if (requestCode == 1 && resultCode == RESULT_OK) {
                if (prezzo1.equals(s)) {
                    activate = true;
                    for (Info i : original) {
                        if (i.getEligibleExpenditure() >= 0 && i.getEligibleExpenditure() <= 25000) {
                            result.add(i);

                        }

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
                } else if (prezzo2.equals(s)) {
                    activate = true;
                    for (Info i : original) {
                        if (i.getEligibleExpenditure() > 25000 && i.getEligibleExpenditure() <= 50000) {
                            result.add(i);
                        }
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


                } else if (prezzo3.equals(s)) {
                    activate = true;
                    for (Info i : original) {
                        if (i.getEligibleExpenditure() > 50000 && i.getEligibleExpenditure() <= 75000) {
                            result.add(i);
                        }
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
                } else if (prezzo4.equals(s)) {
                    activate = true;
                    for (Info i : original) {
                        if (i.getEligibleExpenditure() > 75000 && i.getEligibleExpenditure() <= 100000) {
                            result.add(i);
                        }
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
                } else if (prezzo5.equals(s)) {
                    activate = true;
                    for (Info i : original) {
                        if (i.getEligibleExpenditure() > 100000) {
                            result.add(i);
                            Log.d("RESULT", "" + i.getBeneficiaryName() + "" + i.getProvince());
                        }
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
                }
                if (venezia.equals(t)) {
                    activate = true;
                    if (result.isEmpty()) {
                        for (Info i : original) {
                            if (i.getProvince().equals("VENEZIA")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });

                    } else {
                        for (Info i : result) {
                            if (i.getProvince().equals("VENEZIA")) {
                                a.add(i);

                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });

                    }

                } else if (treviso.equals(t)) {
                    activate = true;
                    if (result.isEmpty()) {
                        for (Info i : original) {
                            if (i.getProvince().equals("TREVISO")) {
                                a.add(i);
                            }
                        }

                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    } else {
                        for (Info i : result) {
                            if (i.getProvince().equals("TREVISO")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    }
                } else if (verona.equals(t)) {
                    activate = true;
                    if (result.isEmpty()) {
                        for (Info i : original) {
                            if (i.getProvince().equals("VERONA")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    } else {
                        for (Info i : result) {
                            if (i.getProvince().equals("VERONA")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    }
                } else if (vicenza.equals(t)) {
                    activate = true;
                    if (result.isEmpty()) {
                        for (Info i : original) {
                            if (i.getProvince().equals("VICENZA")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    } else {
                        for (Info i : result) {
                            if (i.getProvince().equals("VICENZA")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    }
                } else if (rovigo.equals(t)) {
                    activate = true;
                    if (result.isEmpty()) {
                        for (Info i : original) {
                            if (i.getProvince().equals("ROVIGO")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    } else {
                        for (Info i : result) {
                            if (i.getProvince().equals("ROVIGO")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    }
                } else if (padova.equals(t)) {
                    activate = true;
                    if (result.isEmpty()) {
                        for (Info i : original) {
                            if (i.getProvince().equals("PADOVA")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    } else {
                        for (Info i : result) {
                            if (i.getProvince().equals("PADOVA")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    }
                } else if (belluno.equals(t)) {
                    activate = true;
                    if (result.isEmpty()) {
                        for (Info i : original) {
                            if (i.getProvince().equals("BELLUNO")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    } else {
                        for (Info i : result) {
                            if (i.getProvince().equals("BELLUNO")) {
                                a.add(i);
                            }
                        }
                        generateList(a);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                intent.putExtra("myInfo", a.get(i));
                                startActivity(intent);
                            }
                        });
                    }

                }
                if (az.equals(b)) {
                    activate = true;
                    if (result.isEmpty()) {
                        Collections.sort(original, new Comparator<Info>() {

                            @Override
                            public int compare(Info o1, Info o2) {
                                String s1 = o1.getBeneficiaryName();
                                String s2 = o2.getBeneficiaryName();
                                return s1.compareToIgnoreCase(s2);
                            }
                        });
                        generateList(original);
                    } else {
                        Collections.sort(a, new Comparator<Info>() {
                            @Override
                            public int compare(Info o1, Info o2) {
                                String s1 = o1.getBeneficiaryName();
                                String s2 = o2.getBeneficiaryName();
                                return s1.compareToIgnoreCase(s2);
                            }
                        });

                        generateList(a);

                    }
                } else if (za.equals(b)) {
                    activate = true;
                    if (result.isEmpty()) {
                        Collections.sort(original, new Comparator<Info>() {
                            @Override
                            public int compare(Info o1, Info o2) {
                                String s1 = o1.getBeneficiaryName();
                                String s2 = o2.getBeneficiaryName();
                                return s2.compareToIgnoreCase(s1);

                            }
                        });
                        generateList(original);
                    } else {
                        Collections.sort(a, new Comparator<Info>() {
                            @Override
                            public int compare(Info o1, Info o2) {
                                String s1 = o1.getBeneficiaryName();
                                String s2 = o2.getBeneficiaryName();
                                return s2.compareToIgnoreCase(s1);
                            }
                        });
                        generateList(a);
                    }
                }
                if(startDateCresc.equals(dc)) {
                    activate = true;
                    if (result.isEmpty()) {
                        Collections.sort(original, new Comparator<Info>() {
                            DateFormat f = new SimpleDateFormat("dd/MM/yy");
                            @Override
                            public int compare(Info o1, Info o2) {
                                try {
                                    return f.parse(o1.getStartOperation()).compareTo(f.parse(o2.getStartOperation()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                return 0;
                            }
                        });
                        generateList(original);
                    }
                    else{
                        Collections.sort(a, new Comparator<Info>() {
                            DateFormat f = new SimpleDateFormat("dd/MM/yy");
                            @Override
                            public int compare(Info o1, Info o2) {
                                try {
                                    return f.parse(o1.getStartOperation()).compareTo(f.parse(o2.getStartOperation()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                return 0;
                            }
                        });

                        generateList(a);
                    }
                }
                else if (startDateDesc.equals(dc)){
                    activate=true;
                    if (result.isEmpty()) {
                        Collections.sort(original, new Comparator<Info>() {
                            DateFormat f = new SimpleDateFormat("dd/MM/yy");
                            @Override
                            public int compare(Info o1, Info o2) {
                                try {
                                    return f.parse(o2.getStartOperation()).compareTo(f.parse(o1.getStartOperation()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                return 0;
                            }
                        });
                        generateList(original);
                    }
                    else{
                        Collections.sort(a, new Comparator<Info>() {
                            DateFormat f = new SimpleDateFormat("dd/MM/yy");
                            @Override
                            public int compare(Info o1, Info o2) {
                                try {
                                    return f.parse(o2.getStartOperation()).compareTo(f.parse(o1.getStartOperation()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                return 0;
                            }
                        });

                        generateList(a);
                    }
                }


                else{
                    if (!activate) {
                        for (Info i : original) {
                            result.add(i);
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
                    }
                }
            }

        } else {
            for (Info i : original) {
                result.add(i);
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
        }


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
                result.clear();
                a.clear();
                Intent intent = new Intent(TabList.this.getActivity(), Filter.class);
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
                populateList();
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                        intent.putExtra("myInfo", original.get(i));
                        startActivity(intent);
                    }
                });


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
                return true;
            }
        });

    }

    public void generateList(ArrayList<Info> source) {
        MyCustomAdapter adapter = new MyCustomAdapter(getContext(), R.layout.da_text, source);
        lst.setAdapter(adapter);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_list, container, false);
        lst = (ListView) rootView.findViewById(R.id.lstView);
        populateList();
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                intent.putExtra("myInfo", original.get(i));
                startActivity(intent);
            }
        });


        return rootView;
    }


    public void populateList() {
        lst.setAdapter(new MyCustomAdapter(getContext(), R.layout.da_text, original));

    }


}




