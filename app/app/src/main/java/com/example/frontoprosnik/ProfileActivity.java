package com.example.frontoprosnik;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProfileActivity extends AppCompatActivity {
    private TextView textViewProfileUsernameResponse;
    private TextView textViewProfileAgeResponse;
    private TextView textViewProfileSexResponse;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        token = intent.getStringExtra("token_key");
        textViewProfileUsernameResponse = (TextView) findViewById(
                R.id.textViewProfileUsernameResponse);
        textViewProfileAgeResponse = (TextView) findViewById(R.id.textViewProfileAgeResponse);
        textViewProfileSexResponse = (TextView) findViewById(R.id.textViewProfileSexResponse);
        getAccountRequest();
    }

    private void getAccountRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://31.40.51.218:8080/api/account/info";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (!response.equals(null)) {
                            try {
                                textViewProfileUsernameResponse.setText(response.getString(
                                        "username"));
                                textViewProfileAgeResponse.setText(response.getString("age"));
                                textViewProfileSexResponse.setText(response.getBoolean(
                                        "sexM") ? "Мужской" : "Женский");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e("Your Array Response", "Data Null");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
            }
        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                params.put("Authorization", "Bearer "+ token);
                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        requestQueue.add(request);
    }
}