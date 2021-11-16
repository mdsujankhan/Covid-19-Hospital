package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PathologistHomeActivity extends AppCompatActivity {
    TextView goToHome;
    Button allCovidApplications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_home);
        goToHome = findViewById(R.id.backToHomePathologist);
        allCovidApplications = findViewById(R.id.showCovidApplications);



        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PathologistHomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        allCovidApplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PathologistHomeActivity.this, ShowAllApplicants.class);
                startActivity(intent);
            }
        });
    }
}