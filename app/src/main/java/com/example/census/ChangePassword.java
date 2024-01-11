package com.example.census;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        EditText txt1 = (EditText) findViewById(R.id.oldp);
        EditText txt2 = (EditText) findViewById(R.id.newp);
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        String password = sharedPreferences.getString("password", "");
        if(password.equals(txt1.getText().toString())){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("password", txt2.getText().toString());
            editor.apply();
            Intent intent = new Intent(ChangePassword.this,Home.class);
            startActivity(intent);
            finish();
        }
    }
}