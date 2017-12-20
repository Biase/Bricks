package it.ingte.bricks;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by davide on 17/12/2017.
 */

public class TabList extends Fragment {

    String[] lstSource = {
            "Jack Daniel's",
            "Absolute",
            "Belvedere",
            "Greygoose",
            "Bulldog",
            "Bombay"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_list, container, false);
        perform(rootView);

        return rootView;
    }

    public void perform(View v) {
        ListView lst = v.findViewById(R.id.lstView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lstSource);
        lst.setAdapter(adapter);
    }

}
