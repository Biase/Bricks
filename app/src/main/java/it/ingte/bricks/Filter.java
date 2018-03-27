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
    Spinner spin3;
    Spinner spin4;
    Spinner spin5;
    Spinner spin6;
    Spinner spin7;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);

        TextView text0 = (TextView) findViewById(R.id.text0);

        spin1 = (Spinner) findViewById(R.id.spinner1);
        spin2 = (Spinner) findViewById(R.id.spinner2);
        spin3 = (Spinner) findViewById(R.id.spinner3);
        spin4 = (Spinner) findViewById(R.id.spinner4);
        spin5 = (Spinner) findViewById(R.id.spinner5);
        spin6 = (Spinner) findViewById(R.id.spinner6);
        spin7 = (Spinner) findViewById(R.id.spinner7);

        TextView text1 = (TextView) findViewById(R.id.textSpin1);
        TextView text2 = (TextView) findViewById(R.id.textSpin2);
        TextView text3 = (TextView) findViewById(R.id.textSpin3);
        TextView text4 = (TextView) findViewById(R.id.textSpin4);
        TextView text5 = (TextView) findViewById(R.id.textSpin5);
        TextView text6 = (TextView) findViewById(R.id.textSpin6);
        TextView text7 = (TextView) findViewById(R.id.textSpin7);

        Button button = (Button) findViewById(R.id.button1);
        Button button1 = (Button) findViewById(R.id.button2);


        text0.setText("SELEZIONA I FILTRI");
        text1.setText("Importo :");
        text2.setText("Provincia :");
        text3.setText("Data inizio :");
        text4.setText("Data fine :");
        text5.setText("Ordine alfabetico :");
        text6.setText("Ordine per data :");
        text7.setText("Ordine per importo :");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.PriceList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


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

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.DateStartList, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(adapter2);
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.DateEndList, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin4.setAdapter(adapter3);
        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.Alfabetico,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin5.setAdapter(adapter4);
        spin5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.DataInizio,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin6.setAdapter(adapter5);
        spin6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.Importo,android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin7.setAdapter(adapter6);
        spin7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void ritorna(View v) {
        String textPrice = spin1.getSelectedItem().toString();
        String textProvince = spin2.getSelectedItem().toString();
        String textDateStart = spin3.getSelectedItem().toString();
        String textDateEnd = spin4.getSelectedItem().toString();
        String textAlfabetic = spin5.getSelectedItem().toString();
        String textStartDate = spin6.getSelectedItem().toString();
        String textOrderPrice = spin7.getSelectedItem().toString();
        Intent i = new Intent(this, TabList.class);
        if(spin1.getSelectedItem() == null && spin2.getSelectedItem() == null &&
                spin3.getSelectedItem() == null && spin4.getSelectedItem()== null && spin5.getSelectedItem()==null && spin6.getSelectedItem()==null && spin7.getSelectedItem()==null){
        }
        else {
            i.putExtra("resultPrice", textPrice);
            i.putExtra("resultProvince", textProvince);
            i.putExtra("resultDateStart", textDateStart);
            i.putExtra("resultDateEnd", textDateEnd);
            i.putExtra("resultAlfabetic",textAlfabetic);
            i.putExtra("resultStartDate",textStartDate);
            i.putExtra("resultOrderPrice",textOrderPrice);
        }
        setResult(RESULT_OK, i);
        finish();


    }




}




