package it.ingte.bricks;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davide on 17/12/2017.
 */

public class TabList extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_list, container, false);
        populateList(rootView);
        return rootView;
    }

    public void populateList(View v) {
            ListView lst;
            lst = (ListView) v.findViewById(R.id.lstView);
            //Settiamo il nuova adapter
             lst.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.da_text, MainActivity.getLstSource()));

        }

    }



