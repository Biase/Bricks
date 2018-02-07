package it.ingte.bricks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Domenico on 06/02/2018.
 */

public class Start extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //Sets the layout of welcome_screen.xml
            setContentView(R.layout.activity_home);

    }


    public void ritorna(View v) {
        Intent i = new Intent(this, CircularBar.class);
        setResult(RESULT_OK, i);
        finish();
    }

}