package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientHome extends AppCompatActivity {
    Button goToApply, goToAppoint, getPrescription, getInvoice, getReport;
    TextView goToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        goToApply = findViewById(R.id.applyForCovidTest);
        goToAppoint = findViewById(R.id.appointButton);
        goToHome= findViewById(R.id.backToHomePatient);
        getPrescription= findViewById(R.id.prescriptionButton);
        getInvoice= findViewById(R.id.invoiceButton);
        getReport= findViewById(R.id.reportButton);

        goToApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientHome.this, CovidTestApplication.class);
                startActivity(intent);
            }
        });

        goToAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientHome.this, DoctorAppointActivity.class);
                startActivity(intent);
            }
        });

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientHome.this, MainActivity.class);
                startActivity(intent);
            }
        });

        getPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientHome.this, ContactInputForPrescriptionActivity.class);
                startActivity(intent);
            }
        });

        getInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientHome.this, ContactInputActivity.class);
                startActivity(intent);
            }
        });

        getReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientHome.this, InputNIDActivity.class);
                startActivity(intent);
            }
        });
    }
}