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
        tv2.setText("FSE Bricks: diffusione fondi europei sul territorio Veneto");

        TextView tv3 = findViewById(R.id.textView3);
        tv3.setText("Applicazione realizzata all'interno del progetto CEVID - Azione 1 in collaborazione con la Direzione Sistemi Informativi della Regione Veneto.\n\n\n\nCoordinatore\nProf. Agostino Cortesì\n\nSupervisore allo Sviluppo\nDott. Alvise Spanò\n\nSviluppatori\nDomenico Parisi\nDavide Bassetto");
    }

}

