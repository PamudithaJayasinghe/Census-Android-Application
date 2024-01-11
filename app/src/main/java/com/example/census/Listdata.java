package com.example.census;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Listdata extends AppCompatActivity {
String name;
String age;
String gender;

    String base64String;
    private TextView ldata;
    private ImageView imageView;
    ConstraintLayout mLayout;
    String displayText;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LayoutColor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);

        mLayout = (ConstraintLayout) findViewById(R.id.layoutL);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int colorCode = sharedPreferences.getInt(LayoutColor, 0);
        mLayout.setBackgroundColor(colorCode);

        ldata = (TextView) findViewById(R.id.detailsView);
        imageView = (ImageView) findViewById(R.id.imageView2);
        loadData();
        /*showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMethod();
            }
        });*/
    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String json = sharedPreferences.getString("details", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<com.example.cencusdatacollectionapp.DetailList>>() {}.getType();
        ArrayList<com.example.cencusdatacollectionapp.DetailList> detailList = gson.fromJson(json, type);
//        String name = null, age = null, gender = null, photoCode = null;
        for (com.example.cencusdatacollectionapp.DetailList detail : detailList) {
            name = detail.getName();
            age = detail.getAge();
            gender = detail.getGender();
            base64String = detail.getPhoto();
            String displayText = name + "_" + age + " Years (" + gender +")" ;
            ldata.setText(displayText);
            byte[] decodedByteArray = Base64.decode(base64String, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
            imageView.setImageBitmap(decodedBitmap);

        }
    }
/*    public void onclickUpload(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("Name");




        myRef1.setValue(name);
        myRef2.setValue(age);
        myRef3.setValue(gender);
        myRef4.setValue(base64String);


    }*/
    public void onclickUpload(View view) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

            rootRef.child("Users").child(name).child("Name").setValue(name);
            rootRef.child("Users").child(name).child("Age").setValue(age);
            rootRef.child("Users").child(name).child("Gender").setValue(gender);
            rootRef.child("Users").child(name).child("Photo").setValue(base64String);
        }
        //Toast.makeText(Listdata.this,"Data Uploaded", Toast.LENGTH_SHORT).show();

    }

