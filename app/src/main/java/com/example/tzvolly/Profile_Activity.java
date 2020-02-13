package com.example.tzvolly;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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



public class Profile_Activity extends AppCompatActivity {
    String nameEn,nameBn,mobile,email,usertype;
    TextView useerNameBN,userNameEN,mobileno,emailS,addresstpe;
    EditText mobET,passET;
    SharedPreferences sharedPreferences ;
    String A,B;
    Button profilebt;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        sharedPreferences = this.getSharedPreferences("myapp", Context.MODE_PRIVATE);


        useerNameBN = findViewById(R.id.usernameBNId);
        userNameEN = findViewById(R.id.usernameBNId);
        mobileno = findViewById(R.id.mobileID);
        emailS = findViewById(R.id.emailID);
        addresstpe = findViewById(R.id.addressTypeID);

        mobET = findViewById(R.id.mobET);
        passET = findViewById(R.id.passET);


        profilebt  = findViewById(R.id.profileID);
        profilebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Activity.this, Activity_new11.class);
                startActivity(intent);
            }
        });

    }




    public void parseForVolley(View view) {

        A = mobET.getText().toString();
        B = passET.getText().toString();


        String url = "http://demo.olivineltd.com/primary_attendance/api/admin/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                Log.d("onResponse: ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);


                    nameBn = jsonObject.getString("users_name_bn");
                    mobile = jsonObject.getString("users_mobile");
                    email = jsonObject.getString("users_email");
                    usertype = jsonObject.getString("users_address_type");
                    nameEn = jsonObject.getString("users_name_en");

                    sharedPreferences.edit().putString("namebn",nameBn).apply();
                    sharedPreferences.edit().putString("mob",mobile).apply();
                    sharedPreferences.edit().putString("email",email).apply();
                    sharedPreferences.edit().putString("type",usertype).apply();
                    sharedPreferences.edit().putString("nameen",nameEn).apply();





                    useerNameBN.setText(nameBn);
                    userNameEN.setText(nameEn);
                    emailS.setText(email);
                    mobileno.setText(mobile);
                    addresstpe.setText(usertype);




                    Toast.makeText(Profile_Activity.this, email, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Profile_Activity.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                }


        )

        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
            String pass = "123456";
            String mob_no = "01722222222";
            Map<String,String> params = new HashMap<>();
            params.put("mobile_no",A);
            params.put("password",B);
            return params;

        }
        };

        Volley.newRequestQueue(this).add(stringRequest);


    }

}

