package it.ingte.bricks;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 22/01/18.
 */


public class Filter extends AppCompatActivity{


    private int select;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);
        final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        TextView te = (TextView)findViewById(R.id.textView3);
        Button button = (Button) findViewById(R.id.button1);
        te.setText("Prezzo:");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.PriceList,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              Filter.this.select= i + 1;
              if(select==2){
                  Log.d("tag","prima scelta");
              }
              else
                  Log.d("tagggg","sec scelta");


                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }




        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Info> a = MainActivity.info;
                Intent intent= new Intent(getApplicationContext(),TabList.class);
                if(select==2){
                    intent.putExtra("myinfo",MainActivity.manager.getDbhelper().getPriceMajor());
                    startActivity(intent);

                }


            }
        });






    }
}

