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


    Spinner spin1;
    Spinner spin2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);

        TextView text0 = (TextView) findViewById(R.id.text0);

        spin1 = (Spinner) findViewById(R.id.spinner1);
        spin2 = (Spinner) findViewById(R.id.spinner2);


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
                Log.d("caaaa","asdfgh"+spin1.getSelectedItem().toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.ProvinceList, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter1);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    public void ritorna(View v) {
        String textPrice = spin1.getSelectedItem().toString();
        String textProvince = spin2.getSelectedItem().toString();
        Intent i = new Intent(this, TabList.class);
        if(spin1.getSelectedItem() == null && spin2.getSelectedItem() == null){
        }
        else {
            i.putExtra("resultPrice", textPrice);
            i.putExtra("resultProvince", textProvince);
        }
        setResult(RESULT_OK, i);
        finish();

    }


}




