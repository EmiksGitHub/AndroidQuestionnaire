package com.example.frontoprosnik;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String token;
    private Button btnMainGoTest;
    private Button btnMainAuth;
    private Button btnMainReg;
    private Button btnMainProfile;
    private Button btnLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMainGoTest = (Button) findViewById(R.id.btnMainGoTest);
        btnMainAuth = (Button) findViewById(R.id.btnMainAuth);
        btnMainReg = (Button) findViewById(R.id.btnMainReg);
        btnMainProfile = (Button) findViewById(R.id.btnMainProfile);
        btnLanguage = (Button) findViewById(R.id.btnLanguage);

        Intent intent = getIntent();
        token = intent.getStringExtra("token_key");

        if (token != null) {
            btnMainGoTest.setVisibility(View.VISIBLE);
            btnMainAuth.setVisibility(View.INVISIBLE);
            btnLanguage.setVisibility(View.INVISIBLE);
            btnMainReg.setVisibility(View.INVISIBLE);
            btnMainProfile.setVisibility(View.VISIBLE);
        } else {
            btnMainGoTest.setVisibility(View.INVISIBLE);
            btnMainAuth.setVisibility(View.VISIBLE);
            btnLanguage.setVisibility(View.VISIBLE);
            btnMainReg.setVisibility(View.VISIBLE);
            btnMainProfile.setVisibility(View.INVISIBLE);
        }

        View.OnClickListener ButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnMainGoTest:
                        goToTestActivity(v);
                        break;
                    case R.id.btnMainProfile:
                        goToProfileActivity(v);
                        break;
                    case R.id.btnMainAuth:
                        goToAuthActivity(v);
                        break;
                    case R.id.btnMainReg:
                        goToRegActivity(v);
                        break;
                }
            }
        };
        View.OnClickListener btnLanguageClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LocaleHelper.getLanguage(MainActivity.this).equals("en-us")) {
                    LocaleHelper.setLocale(MainActivity.this, "ru");
                    Locale locale = new Locale("ru");
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getBaseContext().getResources().updateConfiguration(configuration, null);
                } else {
                    LocaleHelper.setLocale(MainActivity.this, "en-us");
                    Locale locale = new Locale("en-us");
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getBaseContext().getResources().updateConfiguration(configuration, null);
                }

                recreate();
            }
        };

        btnMainGoTest.setOnClickListener(ButtonClickListener);
        btnMainProfile.setOnClickListener(ButtonClickListener);
        btnMainAuth.setOnClickListener(ButtonClickListener);
        btnMainReg.setOnClickListener(ButtonClickListener);
        btnLanguage.setOnClickListener(btnLanguageClickListener);
    }

    public void goToTestActivity(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL =  getResources().getString(R.string.URL) + "/api/test/startAttempt";
        StringRequest request = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
        };
        requestQueue.add(request);

        Intent test = new Intent(this, TestActivity.class);
        test.putExtra("token_key", token);
        startActivity(test);
    }

    public void goToAuthActivity(View view) {
        Intent auth = new Intent(this, AuthActivity.class);
        startActivity(auth);
    }

    public void goToProfileActivity(View view) {
        Intent profile = new Intent(this, ProfileActivity.class);
        profile.putExtra("token_key", token);
        startActivity(profile);
    }

    public void goToRegActivity(View view) {
        Intent reg = new Intent(this, RegActivity.class);
        startActivity(reg);
    }
}