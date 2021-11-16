package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19hospital.model.Doctor;
import com.example.covid_19hospital.util.DBHelper;

public class DoctorRegistrationActivity extends AppCompatActivity {
    TextView backToLogin;
    Button doctorReg;
    EditText name, address,  email, password, department, timeSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        backToLogin = findViewById(R.id.backToLogin);
        doctorReg = findViewById(R.id.registrationDoctor);

        name = findViewById(R.id.doctor_name);
        address = findViewById(R.id.doctor_address);
        email = findViewById(R.id.doctor_email);
        password = findViewById(R.id.doctor_password);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorRegistrationActivity.this, DoctorLoginActivity.class);
                startActivity(intent);
            }
        });

        doctorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Doctor d = new Doctor();
                d.setName(name.getText().toString());
                d.setAddress(address.getText().toString());
                d.setEmail(email.getText().toString());
                d.setPassword(password.getText().toString());
                DBHelper dbHelper = new DBHelper(DoctorRegistrationActivity.this);
                Long l = dbHelper.addDoctor(d);
                if (l > 0){
                    Toast.makeText(DoctorRegistrationActivity.this, "Doctor Added successfully!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(DoctorRegistrationActivity.this, "Doctor couldn't Added!!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}