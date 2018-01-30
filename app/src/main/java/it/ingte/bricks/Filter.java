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
   // HashMap<Boolean,String> hashMap = new HashMap<Boolean, String>();
    boolean[] elem = new boolean[20];
    private int select;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);


        TextView text0 = (TextView) findViewById(R.id.text0);

        Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
     //   Spinner spin3 = (Spinner) findViewById(R.id.spinner3);


        TextView text1 = (TextView) findViewById(R.id.textSpin1);
        TextView text2 = (TextView) findViewById(R.id.textSpin2);

        Button button = (Button) findViewById(R.id.button1);

        text0.setText("SELEZIONA I FILTRI DA APPLICARE");
        text1.setText("Prezzo :");
        text2.setText("Provincia :");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.PriceList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Filter.this.select = i + 1;
                Log.d("selezionato","case"+select);
                switch (select){
                    case 2:
                        elem[0]=true;
                        break;
                    case 3:
                        elem[1]=true;
                        break;
                    case 4:
                        elem[2]=true;
                        break;
                    case 5:
                        elem[3]=true;
                        break;
                    case 6:
                        elem[4]=true;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.ProvinceList,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter1);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Filter.this.select = i+1;
                Log.d("selezionato","case"+select);
                switch (select){
                    case 2:
                        elem[5]=true;
                        break;
                    case 3:
                        elem[6]=true;
                        break;
                    case 4:
                        elem[6]=true;
                        break;
                    case 5:
                        elem[7]=true;
                        break;
                    case 6:
                        elem[8]=true;
                        break;
                    case 7:
                        elem[9]=true;
                        break;
                    case 8:
                        elem[10]=true;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Order,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }






    public void ritorna(View v) {
        ArrayList<Info> a = MainActivity.info;
        ArrayList<Info> result = new ArrayList<>();
        Intent i = new Intent(this, TabList.class);
        i.putExtra("result",elem);
        setResult(RESULT_OK,i);
        finish();

    }
}


