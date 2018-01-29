package it.ingte.bricks;

import android.app.Activity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

/**
 * Created by marco on 22/01/18.
 */


public class Filter extends AppCompatActivity implements Serializable {
    public Bundle bundle;
    boolean[] elem = new boolean[10];



    private int select;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);
        Intent i = getIntent();
        ArrayList<Info> a = i.getParcelableExtra("bbb");
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
                if (select == 1) {
                    elem[0]= true;
                    for(int j=1;i<elem.length;i++){
                    elem[j]=false;
                    }
                } else
                   elem[1]=true;


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });
    }

 /*
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q=0;


                ArrayList<Info> a = MainActivity.info;
                ArrayList<Info> b = new ArrayList<>();
                if (select == 2) {
                    for (Info i : a) {
                        float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
                        if (w > 100000) {
                            b.add(i);
                            q++;

                        }
                    }

                    public void ritorna(View v){
                    Intent intent = new Intent(Filter.this,TabList.class);
                    intent.putParcelableArrayListExtra("bbb",b);
                    setResult(RESULT_OK, intent);
                    Log.d("lkjhgfds",""+b);
                    finish();
                }
                }




                 } else {
                    for (Info i : a) {
                        float w = Float.parseFloat(String.valueOf(i.getEligibleExpenditure()));
                        if (w <= 100000) {
                            b.add(i);
                            q++;
                        }

                    }

                    bundle.putParcelableArrayList("data", b);
                    Log.d("aaaaaaa", "baaaaaa" + q);

                }
            }
        }); */

    public void ritorna(View v) {
        ArrayList<Info> a = MainActivity.info;
        ArrayList<Info> result = new ArrayList<>();
        if (select == 1) {
            Intent i = new Intent(this, TabList.class);
            i.putExtra("result",elem);
            for(Boolean q : elem) {
                Log.d("Dgdfghfg", "" + q);
            }
            setResult(RESULT_OK,i);
            finish();
        }
    }
}


