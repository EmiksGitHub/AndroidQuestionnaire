package com.example.frontoprosnik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.frontoprosnik.adapter.AttemptAdapter;
import com.example.frontoprosnik.json.JSONResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AttemptsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String token;
    private int amountFinishedAttempts = 0;
    private List<JSONResult> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempts);
        Intent intent = getIntent();
        token = intent.getStringExtra("token_key");
        getResultsRequest();
    }

    public void getResultsRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.URL) + "/api/test/attempts";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArrayAttempts = response.getJSONArray("attempts");
                            for (int i = 0; i<jsonArrayAttempts.length();i++) {
                                JSONObject jsonObjectAttempts = jsonArrayAttempts.getJSONObject(i);
                                if (jsonObjectAttempts.getBoolean("finished")) {
                                    amountFinishedAttempts++;
                                }
                            }
                            for (int i = 0; i<jsonArrayAttempts.length();i++) {
                                JSONObject jsonObjectAttempts = jsonArrayAttempts.getJSONObject(i);
                                if (jsonObjectAttempts.getBoolean("finished")) {
                                    JSONResult jsonResult = new JSONResult();

                                    jsonResult.setId_attempt(jsonObjectAttempts.getLong("id"));
                                    jsonResult.setData_attempt(jsonObjectAttempts.getString("date"));

                                    JSONArray jsonArrayResults = jsonObjectAttempts.getJSONArray("results");
                                    for (int j = 0; j<jsonArrayResults.length();j++) {
                                        JSONObject jsonObjectResults = jsonArrayResults.getJSONObject(j);
                                        JSONObject jsonObjectFactor = jsonObjectResults.getJSONObject("factor");
                                        int idFactor = jsonObjectFactor.getInt("id");
                                        if (idFactor == 1) {
                                            jsonResult.setDescription_plan(jsonObjectResults.getString("description"));
                                            jsonResult.setPoints_plan(jsonObjectResults.getInt("points"));

                                            jsonResult.setName_plan(jsonObjectFactor.getString("name"));
                                            jsonResult.setQcriticMale_plan(jsonObjectFactor.getInt("qcriticMale"));
                                            jsonResult.setMcriticMale_plan(jsonObjectFactor.getInt("mcriticMale"));
                                            jsonResult.setQcriticFemale_plan(jsonObjectFactor.getInt("qcriticFemale"));
                                            jsonResult.setMcriticFemale_plan(jsonObjectFactor.getInt("mcriticFemale"));
                                        }
                                        if (idFactor == 2) {
                                            jsonResult.setDescription_cele(jsonObjectResults.getString("description"));
                                            jsonResult.setPoints_cele(jsonObjectResults.getInt("points"));

                                            jsonResult.setName_cele(jsonObjectFactor.getString("name"));
                                            jsonResult.setQcriticMale_cele(jsonObjectFactor.getInt("qcriticMale"));
                                            jsonResult.setMcriticMale_cele(jsonObjectFactor.getInt("mcriticMale"));
                                            jsonResult.setQcriticFemale_cele(jsonObjectFactor.getInt("qcriticFemale"));
                                            jsonResult.setMcriticFemale_cele(jsonObjectFactor.getInt("mcriticFemale"));
                                        }
                                        if (idFactor == 3) {
                                            jsonResult.setDescription_nast(jsonObjectResults.getString("description"));
                                            jsonResult.setPoints_nast(jsonObjectResults.getInt("points"));

                                            jsonResult.setName_nast(jsonObjectFactor.getString("name"));
                                            jsonResult.setQcriticMale_nast(jsonObjectFactor.getInt("qcriticMale"));
                                            jsonResult.setMcriticMale_nast(jsonObjectFactor.getInt("mcriticMale"));
                                            jsonResult.setQcriticFemale_nast(jsonObjectFactor.getInt("qcriticFemale"));
                                            jsonResult.setMcriticFemale_nast(jsonObjectFactor.getInt("mcriticFemale"));
                                        }
                                        if (idFactor == 4) {
                                            jsonResult.setDescription_fiks(jsonObjectResults.getString("description"));
                                            jsonResult.setPoints_fiks(jsonObjectResults.getInt("points"));

                                            jsonResult.setName_fiks(jsonObjectFactor.getString("name"));
                                            jsonResult.setQcriticMale_fiks(jsonObjectFactor.getInt("qcriticMale"));
                                            jsonResult.setMcriticMale_fiks(jsonObjectFactor.getInt("mcriticMale"));
                                            jsonResult.setQcriticFemale_fiks(jsonObjectFactor.getInt("qcriticFemale"));
                                            jsonResult.setMcriticFemale_fiks(jsonObjectFactor.getInt("mcriticFemale"));
                                        }
                                        if (idFactor == 5) {
                                            jsonResult.setDescription_samo(jsonObjectResults.getString("description"));
                                            jsonResult.setPoints_samo(jsonObjectResults.getInt("points"));

                                            jsonResult.setName_samo(jsonObjectFactor.getString("name"));
                                            jsonResult.setQcriticMale_samo(jsonObjectFactor.getInt("qcriticMale"));
                                            jsonResult.setMcriticMale_samo(jsonObjectFactor.getInt("mcriticMale"));
                                            jsonResult.setQcriticFemale_samo(jsonObjectFactor.getInt("qcriticFemale"));
                                            jsonResult.setMcriticFemale_samo(jsonObjectFactor.getInt("mcriticFemale"));
                                        }
                                        if (idFactor == 6) {
                                            jsonResult.setDescription_orie(jsonObjectResults.getString("description"));
                                            jsonResult.setPoints_orie(jsonObjectResults.getInt("points"));

                                            jsonResult.setName_orie(jsonObjectFactor.getString("name"));
                                            jsonResult.setQcriticMale_orie(jsonObjectFactor.getInt("qcriticMale"));
                                            jsonResult.setMcriticMale_orie(jsonObjectFactor.getInt("mcriticMale"));
                                            jsonResult.setQcriticFemale_orie(jsonObjectFactor.getInt("qcriticFemale"));
                                            jsonResult.setMcriticFemale_orie(jsonObjectFactor.getInt("mcriticFemale"));
                                        }
                                        if (idFactor == 7) {
                                            jsonResult.setDescription_general(jsonObjectResults.getString("description"));
                                            jsonResult.setPoints_general(jsonObjectResults.getInt("points"));

                                            jsonResult.setName_general(jsonObjectFactor.getString("name"));
                                            jsonResult.setQcriticMale_general(jsonObjectFactor.getInt("qcriticMale"));
                                            jsonResult.setMcriticMale_general(jsonObjectFactor.getInt("mcriticMale"));
                                            jsonResult.setQcriticFemale_general(jsonObjectFactor.getInt("qcriticFemale"));
                                            jsonResult.setMcriticFemale_general(jsonObjectFactor.getInt("mcriticFemale"));
                                        }
                                    }
                                    addResultInResults(jsonResult);
                                }
                            }
                            if (amountFinishedAttempts == 0) {
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        getResources().getString(R.string.history_empty), Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.history_fail), Toast.LENGTH_SHORT);
                toast.show();
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
        queue.add(stringRequest);
    }


    private void addResultInResults(JSONResult jsonResult) {
        resultsList.add(jsonResult);
        if (resultsList.size() == amountFinishedAttempts) {
            letsTry();
        }
    }

    private void letsTry() {
        ListView ListViewAttemptsResultList = findViewById(R.id.ListViewAttemptsResultList);
        AttemptAdapter adapter = new AttemptAdapter(this, R.layout.list_attempt, resultsList);
        ListViewAttemptsResultList.setAdapter(adapter);
        ListViewAttemptsResultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                JSONResult jsonResult = adapter.getItem(position);
                goToResultActivity(jsonResult);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void goToResultActivity(JSONResult jsonResult) {
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("token_key", token);
        i.putExtra("JSONResult", jsonResult);
        i.putExtra("isAuth", "yes");
        startActivity(i);
    }
}
