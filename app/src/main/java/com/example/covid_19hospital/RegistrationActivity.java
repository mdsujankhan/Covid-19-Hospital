package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {
    private Button patientReg , doctorReg;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        patientReg = findViewById(R.id.patientReg);
        doctorReg =findViewById(R.id.doctorReg);

        patientReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, PatientRegistrationActivity.class);
                startActivity(intent);
            }
        });
        doctorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, DoctorRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}