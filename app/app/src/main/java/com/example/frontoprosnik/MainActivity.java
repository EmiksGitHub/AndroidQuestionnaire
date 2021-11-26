package com.example.frontoprosnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private  String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        token = intent.getStringExtra("token_key");
        Log.d("Method OnCreate", "Token is: " + token);
        if (token != null) {
            textView.setText("Вы авторизованы.");
        } else {
            textView.setText("Вы не авторизованы");
        }
        Log.d("Method OnCreate", "выполнен.");
    }

    public void goToTestActivity(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://31.40.51.218:8080/api/test/startAttempt";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals(null)) {
                    Log.d("Main.goToTestActivity", response);
                } else {
                    Log.d("Main.goToTestActivity", "Data Null");
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
                Log.d("Token is ", token);
                params.put("Authorization", "Bearer "+ token);
                return params;
            }
        };
        requestQueue.add(request);

        Intent test = new Intent(this, TestActivity.class);
        test.putExtra("token", token);
        startActivity(test);
    }

    public void goToAuthActivity(View view) {
        Intent auth = new Intent(this, AuthActivity.class);
        startActivity(auth);
    }

    public void goToStatisticActivity(View view) {
        Intent statistic = new Intent(this, StatisticActivity.class);
        startActivity(statistic);
    }
}