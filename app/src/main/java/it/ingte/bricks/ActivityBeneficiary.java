package it.ingte.bricks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Domenico on 01/03/2018.
 */

public class ActivityBeneficiary extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        int info = (int) getIntent().getIntExtra("id", 0);
        TextView text0 = (TextView) findViewById(R.id.text0);
        text0.setText("id: "+info+", nome:"+TabBeneficiary.original.get(info).getBeneficiaryName());

    }
}
