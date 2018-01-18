package it.ingte.bricks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        TextView cap = (TextView) findViewById (R.id.cap);
        TextView country= (TextView) findViewById(R.id.country);
        TextView description = (TextView) findViewById(R.id.description);
        TextView summary =(TextView) findViewById(R.id.summary);


        beneficary.setText("Beneficiario:\n"+getIntent().getStringExtra("beneficary"));
        price.setText("Costo: € "+getIntent().getFloatExtra("price",0)+",00");
        startDate.setText("Data di inizio: "+getIntent().getStringExtra("startDate"));
        endDate.setText("Data di fine: "+getIntent().getStringExtra("endDate"));
        town.setText("Città: "+getIntent().getStringExtra("town"));
        province.setText("Provincia: "+getIntent().getStringExtra("province"));
        cap.setText("CAP: "+getIntent().getStringExtra("cap"));
        country.setText("Nazione: "+getIntent().getStringExtra("country"));
        summary.setText("Sommario:\n"+getIntent().getStringExtra("summary"));
        description.setText("Breve descrizione: \n"+ getIntent().getStringExtra("description"));




    }
}
