package com.example.census;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Adddata extends AppCompatActivity {
    private static final int CODE = 22;
    String base64String;
    String SpinVal;
    private Button camera;
    private ImageView imageView;
    ArrayList<com.example.cencusdatacollectionapp.DetailList> detailList = new ArrayList<>();

    String[] List = {"Select Gender", "Male", "Female"};
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LayoutColor = "";
    ConstraintLayout mlayout;
    private Button save;
    private EditText userName;
    private EditText userAge;
    private Spinner gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Adddata.this,
                android.R.layout.simple_spinner_dropdown_item, List);
        gender = findViewById(R.id.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SpinVal = adapterView.getItemAtPosition(i).toString();
                }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        mlayout = (ConstraintLayout) findViewById(R.id.layoutA);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int colorCode = sharedPreferences.getInt(LayoutColor, 0);
        mlayout.setBackgroundColor(colorCode);

        camera = (Button) findViewById(R.id.addImage);
        imageView = (ImageView) findViewById(R.id.imageView);
        save = (Button) findViewById(R.id.save);
        userName = (EditText) findViewById(R.id.editName);
        userAge = (EditText) findViewById(R.id.editAge);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CODE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            base64String = encodeBitmapToBase64(photo);

            byte[] decodedByteArray = Base64.decode(base64String, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);

            imageView.setImageBitmap(decodedBitmap);
        }
        else{
                        super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private String encodeBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    public void saveData(){

        String name = userName.getText().toString();
        String age = userAge.getText().toString();
        String genderValue= SpinVal;
        String photoCode = base64String;

        com.example.cencusdatacollectionapp.DetailList detail = new com.example.cencusdatacollectionapp.DetailList(name, age, genderValue, photoCode);
        detailList.add(detail);
        Preferences();
        userName.setText("");
        userAge.setText("");
        gender.setSelection(0);
        imageView.setImageResource(R.drawable.pngtreevector_add_user_icon_4101420);
    }

    private void Preferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(detailList);
        editor.putString("details", json);
        editor.apply();
    }
}