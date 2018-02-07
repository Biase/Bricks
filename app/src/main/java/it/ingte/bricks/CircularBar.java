package it.ingte.bricks;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Domenico on 07/02/2018.
 */
    public class CircularBar extends AppCompatActivity {

        ProgressBar progressBar;
        Handler handler;
        Runnable runnable;
        Timer timer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.circular_bar);
/*
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            handler = new Handler() {
                @Override
                public void publish(LogRecord record) {

                }

                @Override
                public void flush() {

                }

                @Override
                public void close() throws SecurityException {

                }
            };

            runnable = new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                    timer.cancel();
                    Intent i = new Intent(CircularBar.this, MainActivity.class);
                    setResult(RESULT_OK, i);
                    finish();
                }
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    progressBar.post(runnable);
                }
            }, 10000, 1000);

*/

            Thread timer= new Thread(){
                public void run(){
                    try {
                        sleep(2500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        Intent intent = new Intent(CircularBar.this, Start.class);
                        startActivityForResult(intent, 1);

                        Intent i = new Intent(CircularBar.this, MainActivity.class);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                }
            };
            timer.start();
        }


        @Override
        protected void onPause() {
            super.onPause();
            finish();
            /*
            Intent intent = new Intent(CircularBar.this, Start.class);
            startActivityForResult(intent, 1);

            Intent i = new Intent(CircularBar.this, MainActivity.class);
            setResult(RESULT_OK, i);
            finish();
            */
        }

    }