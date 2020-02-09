package com.example.tzvolly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//

    public void parseForVolley(View view) {

        String url = "http://demo.olivineltd.com/primary_attendance/api/admin/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                Log.d("onResponse: ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name = jsonObject.getString("users_name_en");
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String pass = "123456";
                String mob_no = "01722222222";
                Map<String,String> params = new HashMap<>();
                params.put("mobile_no",mob_no);
                params.put("password",pass);
                return params;

            }
        };

        Volley.newRequestQueue(this).add(stringRequest);


    }




}
