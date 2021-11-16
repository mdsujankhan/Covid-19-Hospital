package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19hospital.model.Patient;
import com.example.covid_19hospital.util.DBHelper;

public class PatientRegistrationActivity extends AppCompatActivity {
    TextView backLogin;
    EditText name, address, age, email, password, contactNO;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        backLogin = findViewById(R.id.backToLogin);
        reg = findViewById(R.id.registrationPatient);

        name = findViewById(R.id.patient_name);
        address = findViewById(R.id.patient_address);
        contactNO = findViewById(R.id.patient_phone);
        email = findViewById(R.id.patient_email);
        password = findViewById(R.id.patient_password);
        age = findViewById(R.id.patient_age);

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient u = new Patient();
                u.setName(name.getText().toString());
                u.setAddress(address.getText().toString());
                u.setAge(age.getText().toString());
                u.setContactNumber(contactNO.getText().toString());
                u.setEmail(email.getText().toString());
                u.setPassword(password.getText().toString());
                DBHelper dbHelper = new DBHelper(PatientRegistrationActivity.this);
                Long l = dbHelper.addUser(u);
                if (l > 0){
                    Toast.makeText(PatientRegistrationActivity.this, "Patient Added successfully!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(PatientRegistrationActivity.this, "Patient couldn't Added!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}