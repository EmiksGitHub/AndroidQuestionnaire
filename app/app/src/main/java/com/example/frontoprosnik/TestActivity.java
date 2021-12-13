package com.example.frontoprosnik;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.frontoprosnik.json.JSONAnswer;
import com.example.frontoprosnik.json.JSONQuestion;
import com.example.frontoprosnik.json.JSONResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestActivity extends AppCompatActivity {

    private TextView textViewTestQuestion;
    private TextView textViewTest2;
    private Button btnTest7;
    private Button btnTest6;
    private Button btnTest5;
    private Button btnTest4;
    private Button btnTest3;
    private Button btnTest2;
    private Button btnTest1;
    private String token;
    private List<JSONQuestion> questionList = new ArrayList<>();
    private JSONQuestion currentQuestion = new JSONQuestion();
    private boolean running;
    private int seconds;
    private String isAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        token = intent.getStringExtra("token_key");
        isAuth = intent.getStringExtra("isAuth");
        runTimer();
        running = true;
        textViewTestQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewTest2 = (TextView) findViewById(R.id.textViewQuestionNumb);
        btnTest1 = (Button) findViewById(R.id.btnTest1);
        btnTest2 = (Button) findViewById(R.id.btnTest2);
        btnTest3 = (Button) findViewById(R.id.btnTest3);
        btnTest4 = (Button) findViewById(R.id.btnTest4);
        btnTest5 = (Button) findViewById(R.id.btnTest5);
        btnTest6 = (Button) findViewById(R.id.btnTest6);
        btnTest7 = (Button) findViewById(R.id.btnTest7);
        getAnswers();
        View.OnClickListener ButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTest1:
                        answer(1);
                        break;
                    case R.id.btnTest2:
                        answer(2);
                        break;
                    case R.id.btnTest3:
                        answer(3);
                        break;
                    case R.id.btnTest4:
                        answer(4);
                        break;
                    case R.id.btnTest5:
                        answer(5);
                        break;
                    case R.id.btnTest6:
                        answer(6);
                        break;
                    case R.id.btnTest7:
                        answer(7);
                        break;
                }
            }
        };
        btnTest1.setOnClickListener(ButtonClickListener);
        btnTest2.setOnClickListener(ButtonClickListener);
        btnTest3.setOnClickListener(ButtonClickListener);
        btnTest4.setOnClickListener(ButtonClickListener);
        btnTest5.setOnClickListener(ButtonClickListener);
        btnTest6.setOnClickListener(ButtonClickListener);
        btnTest7.setOnClickListener(ButtonClickListener);
    }

    public void getAnswers () {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.URL) + "/api/test/answers";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("answers");
                            for (int i = 0; i<jsonArray.length();i++) {
                                JSONAnswer jsonAnswer = new JSONAnswer();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                jsonAnswer.setAnswerText(jsonObject.getString("anserText"));
                                jsonAnswer.setNumber(jsonObject.getInt("number"));
                                switch (jsonAnswer.getNumber()) {
                                    case 1:
                                        btnTest1.setText(jsonAnswer.getAnswerText());
                                        break;
                                    case 2:
                                        btnTest2.setText(jsonAnswer.getAnswerText());
                                        break;
                                    case 3:
                                        btnTest3.setText(jsonAnswer.getAnswerText());
                                        break;
                                    case 4:
                                        btnTest4.setText(jsonAnswer.getAnswerText());
                                        break;
                                    case 5:
                                        btnTest5.setText(jsonAnswer.getAnswerText());
                                        break;
                                    case 6:
                                        btnTest6.setText(jsonAnswer.getAnswerText());
                                        break;
                                    case 7:
                                        btnTest7.setText(jsonAnswer.getAnswerText());
                                        break;
                                }
                                getQuestion();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewTestQuestion.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    public void getQuestion() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.URL) + "/api/test/questions";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("questions");
                            for (int i = 0; i<jsonArray.length();i++) {
                                JSONQuestion jsonQuestion = new JSONQuestion();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                jsonQuestion.setQuestionText(jsonObject.getString(
                                        "questionText"
                                ));
                                jsonQuestion.setNumber(jsonObject.getInt("number"));
                                jsonQuestion.setId(jsonObject.getInt("id"));
                                addQuestionInQuestions(jsonQuestion);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewTestQuestion.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
    private void addQuestionInQuestions(JSONQuestion jsonQuestion) {
        questionList.add(jsonQuestion);
        if (jsonQuestion.getNumber() == 25) {
            setQuestionText(questionList, 0);
            textViewTest2.setText("1/25");
        }
    }
    private void setQuestionText(List<JSONQuestion> questionList, int currentQuestionNumber) {
        if (currentQuestionNumber == 25) {
            finishAttempt();
        } else {
            JSONQuestion jsonQuestionInThisMethod = questionList.get(currentQuestionNumber);
            currentQuestion.setId(jsonQuestionInThisMethod.getId());
            currentQuestion.setNumber(jsonQuestionInThisMethod.getNumber());
            currentQuestion.setQuestionText(jsonQuestionInThisMethod.getQuestionText());
            textViewTestQuestion.setText(jsonQuestionInThisMethod.getQuestionText());
            textViewTest2.setText(jsonQuestionInThisMethod.getId() + "/25");
        }
    }
    public void answer(int numberButton) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map params = new HashMap();
        params.put("idA", numberButton);
        params.put("idQ", Long.toString(currentQuestion.getId()));
        String URL =  getResources().getString(R.string.URL) + "/api/test/giveAnswer";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (!response.equals(null)) {
                    setQuestionText(questionList,currentQuestion.getId());
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
    public void finishAttempt() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map params = new HashMap();
        String URL =  getResources().getString(R.string.URL) + "/api/test/finishAttempt";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            getResultRequest(response.getLong("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    private void getResultRequest(long id) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map params = new HashMap();
        params.put("id", id);
        String URL =  getResources().getString(R.string.URL) + "/api/test/getResults";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            JSONResult jsonResult = new JSONResult();
                            for (int i = 0; i<jsonArray.length();i++) {
                                JSONObject jsonObjectResults = jsonArray.getJSONObject(i);
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

                            goToResultActivity(jsonResult);

                        } catch (JSONException e) {
                            e.printStackTrace();
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

    private void goToResultActivity(JSONResult jsonResult) {
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("token_key", token);
        i.putExtra("JSONResult", jsonResult);
        i.putExtra("isAuth", isAuth);
        startActivity(i);
        finish();
    }

    private void runTimer() {
        final TextView textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = (seconds%3600)/60;
                int secon = seconds%60;
                String time = String.format("%02d:%02d", minutes, secon);
                textViewTimer.setText(time);
                if (running) {
                    seconds++;
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
}