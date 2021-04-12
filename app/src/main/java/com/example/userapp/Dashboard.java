package com.example.userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
      //  String email = intent.getStringExtra("username");
        try {
            JSONObject userObj = new JSONObject(intent.getStringExtra("username"));
            TextView lblWelcome = findViewById(R.id.lblWelcome);
            lblWelcome.setText("welcome "+ userObj.get("name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}