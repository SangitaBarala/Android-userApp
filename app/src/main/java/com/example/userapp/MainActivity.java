package com.example.userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText txtEmail;
    EditText txtPassword;
    TextView lblMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        lblMsg = findViewById(R.id.lblMsg);
    }
    public void btnLoginClick(View v) throws JSONException {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        String url = "https://android.parthrai.ca/api/android/login";
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject payload = new JSONObject();
        payload.put("email",email);
        payload.put("password", password);
        JSONArray array_payload = new JSONArray();
        array_payload.put(payload);
        JsonArrayRequest loginRequest = new JsonArrayRequest(Request.Method.POST, url, array_payload, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("Response---", response.toString());
                if(response.length()== 0){
                   lblMsg.setText("Login credentials doesn't match");
                   return;
                }
                try {
                    JSONObject user = response.getJSONObject(0);
                    DashboardIntent(user);
                    Log.d("name", user.get("name").toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error---", error.toString());
            }
        });
        queue.add(loginRequest);

    }
    public void btnRegisterClick(View v){
        Intent registerIntent = new Intent(this, Register.class);
        startActivity(registerIntent);
    }
    public void DashboardIntent(JSONObject user){
        Intent dashboardIntent = new Intent(this, Dashboard.class);
        dashboardIntent.putExtra("username", user.toString());
        startActivity(dashboardIntent);
    }
}