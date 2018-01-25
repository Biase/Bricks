package it.ingte.bricks;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

/**
 * Created by marco on 22/01/18.
 */


public class Filter extends AppCompatActivity implements Serializable {
    public Bundle bundle;


    private int select;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);
        Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        TextView te = (TextView) findViewById(R.id.textView3);
        bundle = new Bundle();
        Button button = (Button) findViewById(R.id.button1);
        te.setText("Prezzo:");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.PriceList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Filter.this.select = i + 1;
                if (select == 2) {
                    Log.d("tag", "prima scelta");
                } else
                    Log.d("tagggg", "sec scelta");


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q=0;

                ArrayList<Info> a = MainActivity.info;
                ArrayList<Info> b = new ArrayList<>();
                if (select == 2) {
                    for (Info i : a) {
                        float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
                        if (w > 100.000) {
                            b.add(i);
                            q++;


                        }
                    }



                    Log.d("qqqqqq", "ciaaaaaa" + q);
                    TabList newFragment = new TabList();
                    newFragment.setArguments(bundle);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putParcelableArrayListExtra("data", b);
                    startActivity(intent);


                 } else {
                    for (Info i : a) {
                        float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
                        if (w <= 100.000) {
                            b.add(i);
                            q++;
                        }

                    }

                    bundle.putParcelableArrayList("data", b);
                    Log.d("aaaaaaa", "baaaaaa" + q);

                }
            }
        });
    }
}


