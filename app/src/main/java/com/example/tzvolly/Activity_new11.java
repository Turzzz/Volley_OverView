package com.example.tzvolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_new11 extends AppCompatActivity {

    SharedPreferences sharedPreferences ;
    String name ;
    TextView useerNameBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new11);

        sharedPreferences = this.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("namebn",null);

        useerNameBN = findViewById(R.id.usernameBNId);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        useerNameBN.setText(name);

    }
}

