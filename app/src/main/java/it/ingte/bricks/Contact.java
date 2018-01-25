package it.ingte.bricks;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.coreutils.BuildConfig;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by davide on 24/01/18.
 */


public class Contact extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.project);
        TextView tv_1 = findViewById(R.id.textView1);
        tv_1.setText("PROVA");
    }

}

