package com.example.frontoprosnik;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class RegActivity extends AppCompatActivity {

    private EditText editTextRegName;
    private EditText editTextRegSName;
    private EditText editTextRegNickname;
    private EditText editTextRegPassword;
    private Button btnRegReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        editTextRegName = (EditText) findViewById(R.id.editTextRegName);
        editTextRegSName = (EditText) findViewById(R.id.editTextRegSName);
        editTextRegNickname = (EditText) findViewById(R.id.editTextNickname);
        editTextRegPassword = (EditText) findViewById(R.id.editTextPassword);
        btnRegReg = (Button) findViewById(R.id.btnRegReg);
    }

    public void goToAuthActivity(View view) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://31.40.51.218:8080/api/auth/signup";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("age", 19);
            jsonBody.put("password", editTextRegPassword.getText());
            jsonBody.put("sexM", true);
            jsonBody.put("username", editTextRegNickname.getText());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    editTextRegName.setText(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    editTextRegName.setText(error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Intent auth = new Intent(this, AuthActivity.class);
        //startActivity(auth);
    }
}