package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
        private Button apply, login, doctorLogin, adminLogin, pathologistLogin;
        public static String ipPort = "http://192.168.0.109:8080/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginBTN);
        doctorLogin = findViewById(R.id.doctorLoginBTN);
        adminLogin = findViewById(R.id.adminLoginBTN);
        pathologistLogin = findViewById(R.id.pathologistLoginBTN);


        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        doctorLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoctorLoginActivity.class);
                startActivity(intent);
            }
        });

        adminLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        pathologistLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PathologistLoginActivity.class);
                startActivity(intent);
            }
        });

        }
    }