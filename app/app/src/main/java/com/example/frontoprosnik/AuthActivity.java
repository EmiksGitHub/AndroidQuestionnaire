package com.example.frontoprosnik;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AuthActivity extends AppCompatActivity {

    public EditText editTextNickname;
    public EditText editTextPassword;
    public Button btnAuthSubmit;
    public String token = "null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnAuthSubmit = (Button) findViewById(R.id.btnAuthSubmit);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    public void goToRegActivity(View view) {
        Intent reg = new Intent(this, RegActivity.class);
        startActivity(reg);
    }

    public void btnAuth(View view) throws InterruptedException {
        editTextNickname = (EditText) findViewById(R.id.editTextNickname);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        String nickname = editTextNickname.getText().toString();
        String password = editTextPassword.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://31.40.51.218:8080/api/auth/signin";
        HashMap<String, String> params = new HashMap<>();
        params.put("password", password);
        params.put("username", nickname);

        JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            token = response.getString("accessToken");
                            Log.d("Ключ", token);
                            startActivity(token);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //editTextRegNickname.setText(error.toString());
                Log.d("НЕ Ключ", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        requestQueue.add(request_json);
    }



    public void startActivity(String string) {
        Intent main = new Intent(this, MainActivity.class);
        main.putExtra("token_key", string);
        startActivity(main);
    }
}