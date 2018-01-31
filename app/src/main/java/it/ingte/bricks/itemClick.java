package it.ingte.bricks;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class itemClick extends AppCompatActivity {
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click);
        ListView listView = (ListView) findViewById(R.id.myList);
        Info info = (Info) getIntent().getParcelableExtra("myInfo");
        list.add("Beneficiario : " + info.getBeneficiaryName());
        list.add("Costo : €" + info.getEligibleExpenditure());
        list.add("Data di inizio : " + info.getStartOperation());
        list.add("Data di fine : " + info.getEndOpeation());
        list.add("Città : " + info.getTown());
        list.add("CAP :" + info.getCap());
        list.add("Provincia :"+ info.getProvince());
        list.add("Sommario :" + info.getOperationSummary());
        list.add("Breve descrizione :" + info.getOperationName());
        list.add("latitudine :"+ info.getLat());
        list.add("longitudine :"+ info.getLng());
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, list) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setTypeface(null, Typeface.BOLD);

                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(list.get(position).substring(0, list.get(position).indexOf(":")));

                text2.setText(list.get(position).substring(list.get(position).indexOf(":") + 1, list.get(position).length()));

                return view;
            }
        };
        listView.setAdapter(adapter);


    }
}
