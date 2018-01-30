package it.ingte.bricks;

import android.app.Activity;
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

import static android.app.Activity.RESULT_OK;

/**
 * Created by davide on 17/12/2017.
 */

public class TabList extends Fragment {
    ListView lst;
    MaterialSearchView searchView;
    ArrayList<Info> original = MainActivity.info;
    ArrayList<Info> result = new ArrayList<>();
    ArrayList<Info> list1 = filter1();
    ArrayList<Info> list2 = filter2();
    ArrayList<Info> list3 = filter3();
    ArrayList<Info> list4 = filter4();
    ArrayList<Info> list5 = filter5();
    ArrayList<Info> list6 = filterVe();
    ArrayList<Info> list7 = filterTre();
    ArrayList<Info> list8 = filterVer();




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        int q = 0;
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean[] res = data.getBooleanArrayExtra("result");
                for (int i = 0; i < res.length; i++) {
                    if (res[i] == true) {
                        switch (i) {
                            case 0:
                                result = list1;
                                for (Info j : result) {
                                    Log.d("jhgfdsdfghj", "jhgfdfgh" + j.getEligibleExpenditure() + " " + q);
                                    q++;
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
                                break;
                            case 1:
                                result = list2;
                                generateList(result);
                                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                        intent.putExtra("myInfo", result.get(i));
                                        startActivity(intent);
                                    }
                                });

                                break;
                            case 2:
                                result = list3;
                                generateList(result);
                                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                        intent.putExtra("myInfo", result.get(i));
                                        startActivity(intent);
                                    }
                                });
                                break;
                            case 3:
                                result = list4;
                                generateList(result);
                                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                        intent.putExtra("myInfo", result.get(i));
                                        startActivity(intent);
                                    }
                                });
                                break;
                            case 4:
                                result = list5;
                                generateList(result);
                                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(TabList.this.getActivity(), itemClick.class);
                                        intent.putExtra("myInfo", result.get(i));
                                        startActivity(intent);
                                    }
                                });
                                break;
                            case 5:
                                result.retainAll(list6);
                                generateList(result);

                                break;
                            case 6:
                                result.retainAll(list7);
                                generateList(result);
                                break;
                            case 7:
                                result.retainAll(list8);
                                generateList(result);

                        }


                    }

                }
            }


        }
    }


    public ArrayList<Info> filterVe() {
        final ArrayList<Info> ve = new ArrayList<>();
        for (Info i : original) {
            if (i.getProvince().equals("VENEZIA")) {
                ve.add(i);
            }
        }
        return ve;
    }

    public ArrayList<Info> filterTre() {
        final ArrayList<Info> tre = new ArrayList<>();
        for (Info i : original) {
            if (i.getProvince().equals("TREVISO")) {
                tre.add(i);
            }
        }
        return tre;
    }

    public ArrayList<Info> filterVer() {
        final ArrayList<Info> ver = new ArrayList<>();
        for (Info i : original) {
            if (i.getProvince().equals("VERONA")) {
                ver.add(i);
            }
        }
        return ver;
    }



    public ArrayList<Info> filter1() {
        final ArrayList<Info> first = new ArrayList<>();
        for (Info i : original) {
            float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
            if (w > 0 && w <= 25000) {
                first.add(i);

            }
        }
        return first;
    }

    public ArrayList<Info> filter2() {
        ArrayList<Info> second = new ArrayList<>();
        for (Info i : original) {
            float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
            if (w > 25000 && w <= 50000) {
                second.add(i);
            }
        }


        return second;
    }


    public ArrayList<Info> filter3() {
        ArrayList<Info> third = new ArrayList<>();
        for (Info i : original) {
            float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
            if (w > 50000 && w <= 75000) {
                third.add(i);
            }
        }
        return third;
    }

    public ArrayList<Info> filter4() {
        ArrayList<Info> fourth = new ArrayList<>();
        for (Info i : original) {
            float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
            if (w > 75000 && w <= 100000) {
                fourth.add(i);
            }
        }
        return fourth;
    }


    public ArrayList<Info> filter5() {
        ArrayList<Info> fifth = new ArrayList<>();
        for (Info i : original) {
            float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
            if (w > 100000) {
                fifth.add(i);
            }
        }
        return fifth;
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




