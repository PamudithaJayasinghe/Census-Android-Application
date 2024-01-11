package com.example.census;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Home extends AppCompatActivity {
    ConstraintLayout mLayout;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LayoutColor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLayout = (ConstraintLayout) findViewById(R.id.layoutH);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int colorCode = sharedPreferences.getInt(LayoutColor, 0);
        mLayout.setBackgroundColor(colorCode);
    }

    public void onclickad(View view) {
        Intent intentad = new Intent(Home.this, Adddata.class);
        startActivity(intentad);
    }

    public void onclickld(View view) {
        Intent intentld = new Intent(Home.this, Listdata.class);
        startActivity(intentld);
    }

    public void onclickpref(View view) {
        Intent intentpref = new Intent(Home.this, Colorpicker.class);
        startActivity(intentpref);
    }


}
