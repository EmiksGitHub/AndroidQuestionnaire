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
import com.example.frontoprosnik.json.JSONResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ResultActivity extends AppCompatActivity {
    private TextView textViewPoints;
    private TextView textViewDescription;
    private String token;
    private int idAttempt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        token = intent.getStringExtra("token_key");
        idAttempt = Integer.parseInt(intent.getStringExtra("id_attempt_key"));
        textViewPoints = (TextView) findViewById(R.id.textViewPoints);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        getResultRequest();
    }

    private void getResult(JSONResult jsonResult) {
        textViewPoints.setText(Integer.toString(jsonResult.getPoints_general()));
        textViewDescription.setText(jsonResult.getDescription_general());
    }

    private void getResultRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map params = new HashMap();
        params.put("id", idAttempt);
        String URL = "http://192.168.43.108:8080/api/test/getResults";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (!response.equals(null)) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("results");
                                JSONObject jsonObject = jsonArray.getJSONObject(6);
                                JSONResult jsonResult = new JSONResult();
                                jsonResult.setDescription_general(jsonObject.getString("description"));
                                jsonResult.setPoints_general(jsonObject.getInt("points"));
                                getResult(jsonResult);
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