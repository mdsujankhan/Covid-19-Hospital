package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class DoctorHome extends AppCompatActivity {
    Button showApplicants, showAppointments, addPatients;
    TextView goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        goToLogin = findViewById(R.id.backToLoginDoctor);
        showAppointments = findViewById(R.id.showAppointments);
        addPatients = findViewById(R.id.addPatientByDoctor);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHome.this, MainActivity.class);
                startActivity(intent);
            }
        });

        showAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHome.this, ShowAllAppointments.class);
                startActivity(intent);
            }
        });

        addPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHome.this, PatientRegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}