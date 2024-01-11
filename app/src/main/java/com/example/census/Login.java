package com.example.census;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Login extends AppCompatActivity {
    private EditText txt;
    private String password;
    private int errorCount=0;
    ConstraintLayout mLayout;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LayoutColor = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mLayout = (ConstraintLayout) findViewById(R.id.layoutLog);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int colorCode = sharedPreferences.getInt(LayoutColor, 0);
        mLayout.setBackgroundColor(colorCode);
    }
    public void onclick(View view) {
        txt = (EditText) findViewById(R.id.loginPassword);
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        password = sharedPreferences.getString("password","");
        if(password.equals(txt.getText().toString())){
            Intent intent = new Intent(Login.this,Home.class);
            startActivity(intent);
            finish();
        }else {
            txt.setError("wrong password");
            errorCount++;
            if(errorCount>=3){
                Context context = getApplicationContext();
                CharSequence text = "Wrong password, Closing App";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Login.this.finish();
                System.exit(0);
            }
        }
    }

    public void onclickchg(View view) {
        Intent intent2 = new Intent(Login.this,ChangePassword.class);
        startActivity(intent2);
    }
}