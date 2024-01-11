package com.example.census;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity {
    private EditText txt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt = (EditText) findViewById(R.id.newPassword);
    }
    public void onclick(View view){
        String passwordInput = txt.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(passwordInput.isEmpty()){
            txt.setError("Password Field Cannot Be Empty...");
        } else if (passwordInput.length()<3) {
            txt.setError("Password should be more than 3 characters...");
        }else {
            txt.setError(null);
            editor.putString("password", txt.getText().toString());
            editor.putBoolean("REGISTERED",true);
            editor.apply();
            Intent intent = new Intent(Register.this,Home.class);
            startActivity(intent);
            finish();
        }
    }
}