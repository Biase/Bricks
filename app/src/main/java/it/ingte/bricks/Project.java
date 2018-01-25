package it.ingte.bricks;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.coreutils.BuildConfig;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by davide on 24/01/18.
 */


public class Project extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.project);

        TextView tv1 = findViewById(R.id.textView1);
        tv1.setText("IL PROGETTO");

        TextView tv2 = findViewById(R.id.textView2);
        tv2.setText("Bricks: diffusione fondi europei sul nostro territorio");

        TextView tv3 = findViewById(R.id.textView3);
        tv3.setText("Questa applicazione mostra la distribuzione dei fondi europei nel nostro territorio.\n" +
                "Il progetto nasce per il corso Ingegneria del Software all'università Ca'Foscari di Venezia.");
    }

}

