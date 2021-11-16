package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLoginActivity extends AppCompatActivity {
    private Button goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        goToLogin = findViewById(R.id.loginButtonAdmin);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLoginActivity.this, AddminHome.class);
                startActivity(intent);
            }
        });

    }
}