package com.example.frontoprosnik;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class RegActivity extends AppCompatActivity implements
        TextWatcher, CompoundButton.OnCheckedChangeListener {

    private EditText editTextRegNickname;
    private EditText editTextRegPassword;
    private Button btnRegReg;
    private EditText editTextRegDate;
    private String date;
    boolean ignore = false;
    private String dateNew;
    private Boolean sexM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        editTextRegNickname = (EditText) findViewById(R.id.editTextRegNickname);
        editTextRegPassword = (EditText) findViewById(R.id.editTextRegPassword);
        Switch switchRegSex = (Switch) findViewById(R.id.switchRegSex);
        if (switchRegSex != null) {
            switchRegSex.setOnCheckedChangeListener(this);
        }
        editTextRegDate = (EditText) findViewById(R.id.editTextRegDate);
        btnRegReg = (Button) findViewById(R.id.btnRegReg);
        editTextRegDate.addTextChangedListener(this);
        View.OnClickListener ButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeData(v);
            }
        };
        btnRegReg.setOnClickListener(ButtonClickListener);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            sexM = true;
        } else {
            sexM = false;
        }
    }

    public void changeData(View v) {
        if (editTextRegDate.getText().toString() != "") {
            date = editTextRegDate.getText().toString();
            dateNew = date.substring(6) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);
            regRequest(v);
        }
    }

    public void regRequest(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://31.40.51.218:8080/api/auth/signup";
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
                try {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            (response.getString("message")), Toast.LENGTH_SHORT);
                    goToAuthActivity();
                    toast.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response error", "" + error);
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