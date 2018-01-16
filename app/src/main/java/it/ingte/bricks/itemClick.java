package it.ingte.bricks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class itemClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click);
        TextView beneficary= (TextView) findViewById(R.id.beneficary);
        TextView price= (TextView) findViewById(R.id.price);
        TextView startDate= (TextView) findViewById(R.id.startDate);
        TextView endDate= (TextView) findViewById(R.id.endDate);
        TextView town= (TextView) findViewById(R.id.town);
        TextView province= (TextView) findViewById(R.id.province);
        TextView country= (TextView) findViewById(R.id.country);
        beneficary.setText("Beneficiario:\n"+getIntent().getStringExtra("beneficary"));
        price.setText("Costo: "+getIntent().getDoubleExtra("price",0));
        startDate.setText("Data di inizio: "+getIntent().getStringExtra("startDate"));
        endDate.setText("Data di fine: "+getIntent().getStringExtra("endDate"));
        town.setText("Città: "+getIntent().getStringExtra("town"));
        province.setText("Provincia: "+getIntent().getStringExtra("province"));
        country.setText("Nazione: "+getIntent().getStringExtra("country"));




    }
}
