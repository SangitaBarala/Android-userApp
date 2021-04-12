package com.example.userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    EditText edName,edEmail,edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edName=findViewById(R.id.txtRegName);
        edEmail=findViewById(R.id.txtRegEmail);
        edPassword=findViewById(R.id.txtRegPassword);

    }
    public void btnRegisterClick(View v){
        String name = edName.getText().toString();
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();

        String url = "https://android.parthrai.ca/api/android/register";
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject payload = new JSONObject();
        try {
            payload.put("name", name);
            payload.put("email", email);
            payload.put("password", password);
            payload.put("dev","Sangita");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest register = new JsonObjectRequest(Request.Method.POST, url, payload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error---", error.toString());
            }
        });
        queue.add(register);
    }
}