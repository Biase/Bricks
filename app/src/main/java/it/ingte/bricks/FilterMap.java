package it.ingte.bricks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Mimmo on 22/01/18.
 */


public class FilterMap extends AppCompatActivity implements Serializable {

    private int select;
    Spinner spin1;
    Spinner spin2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_map);

        TextView text0 = (TextView) findViewById(R.id.text0);

        spin1 = (Spinner) findViewById(R.id.spinner1);
        spin2 = (Spinner) findViewById(R.id.spinner2);


        TextView text1 = (TextView) findViewById(R.id.textSpin1);
        TextView text2 = (TextView) findViewById(R.id.textSpin2);


        Button button = (Button) findViewById(R.id.button1);


        text0.setText("SELEZIONA I FILTRI DA APPLICARE");
        text1.setText("Importo :");
        text2.setText("Provincia :");

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
    }

    public void ritorna(View v) {
        String textPrice = spin1.getSelectedItem().toString();
        String textProvince = spin2.getSelectedItem().toString();
        Intent i = new Intent(this, TabMap.class);
        if(spin1.getSelectedItem() == null && spin2.getSelectedItem() == null){
        } else {
            i.putExtra("resultPrice", textPrice);
            i.putExtra("resultProvince", textProvince);
        }
        setResult(RESULT_OK, i);
        finish();
    }
}