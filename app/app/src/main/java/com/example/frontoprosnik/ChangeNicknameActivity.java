package com.example.frontoprosnik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangeNicknameActivity extends AppCompatActivity {

    public EditText editTextNickname;
    public Button btnChangeSubmit;
    public String token = "null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_nickname);
        btnChangeSubmit = (Button) findViewById(R.id.btnChangeSubmit);
        editTextNickname = (EditText) findViewById(R.id.editTextNickname);
        Intent intent = getIntent();
        token = intent.getStringExtra("token_key");

        View.OnClickListener btnCliclListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNicknameRequest();
            }
        };

        btnChangeSubmit.setOnClickListener(btnCliclListener);
    }

    private void changeNicknameRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map params = new HashMap();
        params.put("name", editTextNickname.getText().toString());
        String URL =  getResources().getString(R.string.URL) + "/api/account/change";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.nicknameChangeSucc) + editTextNickname.getText().toString(), Toast.LENGTH_LONG).show();
                        goToProfileActivity();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nicknameChangeFailed), Toast.LENGTH_LONG).show();
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

    public void goToProfileActivity() {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("token_key", token);
        startActivity(i);
    }
}