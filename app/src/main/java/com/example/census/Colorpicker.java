package com.example.census;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Colorpicker extends AppCompatActivity {

    ConstraintLayout mLayout;
    int DefaultCol;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LayoutColor = "";
    private Button ColChg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorpicker);

        mLayout = (ConstraintLayout) findViewById(R.id.layoutC);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int colorCode = sharedPreferences.getInt(LayoutColor, 0);
        mLayout.setBackgroundColor(colorCode);
        DefaultCol = ContextCompat.getColor(Colorpicker.this, R.color.white);
        ColChg = (Button) findViewById(R.id.colorchg);
        ColChg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
                mLayout.setBackgroundColor(DefaultCol);
            }
        });
    }
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker= new AmbilWarnaDialog(this, DefaultCol, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                DefaultCol = color;
                mLayout.setBackgroundColor(DefaultCol);
                SharedPreferences colorPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = colorPrefs.edit();
                editor.putInt(LayoutColor, DefaultCol);
                editor.apply();
            }
        });
        colorPicker.show();
    }
}