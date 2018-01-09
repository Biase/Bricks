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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_list, container, false);

        //Carico gli elementi nella tab
        perform(rootView);


        return rootView;
    }

    /**
     * Permette di caricare l'elenco dei progetti all'interno della tab dedicata
     */
    public void perform(View v) {
        //Prendo la lista dove caricare gli elementi
        ListView lst = v.findViewById(R.id.lstView);

        //Creo l'adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<> (
                getActivity(),                                            //Non ne ho idea
                R.layout.da_text,                                         //Elemento da usare dove stampare l'item da inserire nella lista
                MainActivity.lstSource  //Lista di elementi (Stringhe)
        );


        lst.setAdapter(adapter);
    }



}
