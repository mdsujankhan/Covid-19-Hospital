package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddminHome extends AppCompatActivity {
    Button showReportButton, percentIncomeReport, monthlyIncomeReport, graphicallyIncomeReport, registration;
    TextView goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmin_home);
        goToLogin = findViewById(R.id.backToLoginaAdmin);
        showReportButton = findViewById(R.id.viewReportButton);
        percentIncomeReport = findViewById(R.id.viewReportPercentageButton);
        monthlyIncomeReport = findViewById(R.id.viewReportMonthlyButton);
        graphicallyIncomeReport = findViewById(R.id.viewReportGraphicallyButton);
        registration = findViewById(R.id.registrationByAdmin);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddminHome.this, MainActivity.class);
                startActivity(intent);
            }
        });

        showReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddminHome.this, IncomeReportActivity.class);
                startActivity(intent);
            }
        });

        percentIncomeReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddminHome.this, PercentageIncomeActivity.class);
                startActivity(intent);
            }
        });

        monthlyIncomeReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddminHome.this,IncomeReportMonthlyActivity.class);
                startActivity(intent);
            }
        });

        graphicallyIncomeReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddminHome.this, PieChartReportActivity.class);
                startActivity(intent);
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddminHome.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}