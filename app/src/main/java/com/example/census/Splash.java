package com.example.census;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.Objects;

public class Splash extends AppCompatActivity {

    private boolean registered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        Objects.requireNonNull(getSupportActionBar()).hide();

   /*     Register reg = new Register();*/
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        registered = sharedPreferences.getBoolean("REGISTERED",false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(registered) {
                    intent = new Intent(Splash.this, Login.class);
                }else{
                    intent = new Intent(Splash.this, Register.class);
                }
                startActivity(intent);
                finish();
            }
        },3000);
        Toast.makeText(this, "Starting Census app…", Toast.LENGTH_SHORT).show();
    }

}