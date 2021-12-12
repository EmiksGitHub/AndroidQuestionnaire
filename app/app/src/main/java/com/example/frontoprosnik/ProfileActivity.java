package com.example.frontoprosnik;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button btnProfileAttempts;
    private Button btnProfileChangeNickname;
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
        btnProfileAttempts = (Button) findViewById(R.id.btnProfileAttempts);
        btnProfileChangeNickname = (Button) findViewById(R.id.btnProfileChangeNickname);
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnProfileAttempts:
                        goToAttemptsActivity();
                        break;
                    case R.id.btnProfileChangeNickname:
                        goToChangeNicknameActivity();
                        break;
                }
            }
        };
        btnProfileAttempts.setOnClickListener(buttonClickListener);
        btnProfileChangeNickname.setOnClickListener(buttonClickListener);

        getAccountRequest();
    }

    private void getAccountRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL =  getResources().getString(R.string.URL) + "/api/account/info";
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
                                        "sexM") ? getResources().getString(R.string.male) : getResources().getString(R.string.female));
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

    public void goToAttemptsActivity() {
        Intent i = new Intent(this, AttemptsActivity.class);
        i.putExtra("token_key", token);
        startActivity(i);
    }

    public void goToChangeNicknameActivity() {
        Intent i = new Intent(this, ChangeNicknameActivity.class);
        i.putExtra("token_key", token);
        startActivity(i);
    }
}