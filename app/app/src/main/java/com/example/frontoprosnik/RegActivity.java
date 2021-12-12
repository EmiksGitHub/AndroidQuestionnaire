package com.example.frontoprosnik;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity implements TextWatcher {

    private EditText editTextRegNickname;
    private EditText editTextRegPassword;
    private Button btnRegReg;
    private EditText editTextRegDate;
    private String date;
    private Boolean ignore = false;
    private String dateNew;
    private Boolean sexM;
    private RadioButton radioButtonRegSex1;
    private RadioButton radioButtonRegSex2;
    private boolean isSexChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        editTextRegNickname = (EditText) findViewById(R.id.editTextRegNickname);
        editTextRegPassword = (EditText) findViewById(R.id.editTextRegPassword);
        radioButtonRegSex1 = (RadioButton) findViewById(R.id.radioButtonRegSex1);
        radioButtonRegSex2 = (RadioButton) findViewById(R.id.radioButtonRegSex2);
        editTextRegDate = (EditText) findViewById(R.id.editTextRegDate);
        btnRegReg = (Button) findViewById(R.id.btnRegReg);
        editTextRegDate.addTextChangedListener(this);
        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton)v;
                switch (rb.getId()) {
                    case R.id.radioButtonRegSex1: {
                        sexM = true;
                        isSexChecked = true;
                        break;
                    }
                    case R.id.radioButtonRegSex2: {
                        sexM = false;
                        isSexChecked = true;
                        break;
                    }
                }
            }
        };
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeData(v);
            }
        };
        btnRegReg.setOnClickListener(buttonClickListener);
        radioButtonRegSex1.setOnClickListener(radioButtonClickListener);
        radioButtonRegSex2.setOnClickListener(radioButtonClickListener);

    }

    public void changeData(View v) {
        date = editTextRegDate.getText().toString();
        if (editTextRegNickname.getText().toString().length() <= 3) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.reg_warn_1), Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (editTextRegNickname.getText().toString().length() > 30) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.reg_warn_2), Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (editTextRegPassword.getText().toString().length() <= 3) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.reg_warn_3), Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (!isSexChecked) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.reg_warn_4), Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        try {
                            if (date.length()==10 &&
                                    Integer.parseInt(date.substring(6)) <= Calendar.getInstance().get(Calendar.YEAR) &&
                                    Integer.parseInt(date.substring(3,5)) <= 12 &&
                                    Integer.parseInt(date.substring(0,2)) <=
                                            getMaxDaysInMonth(Integer.parseInt(date.substring(3,5)),
                                                    Integer.parseInt(date.substring(6)))) {
                                dateNew = date.substring(6) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);
                                regRequest(v);
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        getResources().getString(R.string.reg_warn_5), Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } catch (Exception e) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.reg_warn_5), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            }
        }
    }

    public int getMaxDaysInMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        // Note: 0-based months
        cal.set(year, month-1, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public void regRequest(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL =  getResources().getString(R.string.URL) + "/api/auth/signup";
        Map params = new HashMap();
        params.put("age", dateNew);
        params.put("password", editTextRegPassword.getText().toString());
        params.put("sexM", sexM);
        params.put("username", editTextRegNickname.getText().toString());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.reg_succ), Toast.LENGTH_SHORT);
                goToAuthActivity();
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response error", "" + error);
                Toast toast = Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.reg_fail), Toast.LENGTH_SHORT);
                toast.show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        requestQueue.add(request);
    }

    private void goToAuthActivity() {
        Intent i = new Intent(this, AuthActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) { //Автор кода - EmiksGitHub
        date = editable.toString().trim(); //Разработано с нуля всего за 2,5 часа...
        if (!ignore) {
            if (date.length() == 2 || date.length() == 5) {
                date = date + ".";
                editable.replace(0,editable.length(),date);
                ignore = true;
            }
        }
        if (date.length() == 2 || date.length() == 5) {
            date = date.substring(0,date.length()-1);
            editable.replace(0,editable.length(),date);
            ignore = false;
        }
        if (date.length()==4) ignore = false;
        if (date.length()==3) ignore = true;
        if (date.length()>10) {
            date = date.substring(0,10);
            editable.replace(0,editable.length(),date);
        }
        /*s1 = date.substring(0,2); s2 = date.substring(4,5); s3 = date.substring(7,10);
        if (s1.contains(".") || s1.contains("/") || s1.contains("-")) {
            date = "";
            editable.replace(0,editable.length(),date);
        }*/
        /*if (date.length() == 1 && (
                Integer.parseInt(date.substring(0,1)) != 0 ||
                Integer.parseInt(date.substring(0,1)) != 1 ||
                Integer.parseInt(date.substring(0,1)) != 2 ||
                Integer.parseInt(date.substring(0,1)) != 3)) {
            date = "";
            editable.replace(0,editable.length(),date);
        }
        if (date.length() == 4 && (
                Integer.parseInt(date.substring(0,1)) != 0 ||
                Integer.parseInt(date.substring(0,1)) != 1)) {
            date = date.substring(0,2);
            editable.replace(0,editable.length(),date);
        }
        if (date.length() == 7 && (
                Integer.parseInt(date.substring(0,1)) != 1 ||
                Integer.parseInt(date.substring(0,1)) != 2)) {
            date = date.substring(0,5);
            editable.replace(0,editable.length(),date);
        }*/
    }
}