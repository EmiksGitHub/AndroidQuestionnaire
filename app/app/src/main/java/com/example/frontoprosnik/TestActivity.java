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
    List<JSONQuestion> questionList = new ArrayList<>();
    JSONQuestion currentQuestion = new JSONQuestion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        textViewTestQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewTest2 = (TextView) findViewById(R.id.textViewTest2);
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
        String url ="http://31.40.51.218:8080/api/test/answers";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
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
        String url ="http://31.40.51.218:8080/api/test/questions";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("questions");
                            for (int i = 0; i<jsonArray.length();i++) {
                                JSONQuestion jsonQuestion = new JSONQuestion();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                jsonQuestion.setQuestionText(jsonObject.getString("questionText"));
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
            currentQuestion.setId(jsonQuestionInThisMethod.getId()); // Здесь в первый раз берется id=1
            //id = currentQuestionNumber - 1
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
        String URL = "http://31.40.51.218:8080/api/test/giveAnswer";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (!response.equals(null)) {
                    Log.e("Your Array Response", response.toString());
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
        String URL = "http://31.40.51.218:8080/api/test/finishAttempt";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (!response.equals(null)) {
                            try {
                                JSONAttempt jsonAttempt = new JSONAttempt();
                                jsonAttempt.setId(response.getInt("id"));
                                goToResultActivity(jsonAttempt);
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

    private void goToResultActivity(JSONAttempt jsonAttempt) {
        /*textViewTestQuestion.setText("Тест успешно завершен! ID вашей попытки: " +
                jsonAttempt.getId());*/
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("token_key", token);
        Log.d("Ключ", token);
        i.putExtra("id_attempt_key", Integer.toString(jsonAttempt.getId()));
        startActivity(i);
    }
}