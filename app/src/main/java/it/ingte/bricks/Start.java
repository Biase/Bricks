package it.ingte.bricks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by Domenico on 06/02/2018.
 */

public class Start extends AppCompatActivity implements View.OnClickListener {

    private Button ok;
    private CheckBox saveLoginCheckBox;
    private Boolean saveLogin;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    ViewPager viewPager;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carusel);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        ok = (Button) findViewById(R.id.button3);
        saveLoginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        ok.setOnClickListener(this);

            loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            loginPrefsEditor = loginPreferences.edit();

            saveLogin = loginPreferences.getBoolean("saveLogin", false);
            if (saveLogin == true) {
                saveLoginCheckBox.setChecked(true);
                Intent i = new Intent(this, CircularBar.class);
                setResult(RESULT_OK, i);
                finish();
            }
        }

    @Override
    public void onClick(View view) {
        if (view == ok) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

             if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.commit();
                 ritorna(view);
             } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
        }
        ritorna(view);
    }

    public void ritorna(View v) {
        Intent i = new Intent(this, CircularBar.class);
        setResult(RESULT_OK, i);
        finish();
    }
}