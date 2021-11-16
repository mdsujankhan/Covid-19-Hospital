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
import com.example.covid_19hospital.model.Patient;
import com.example.covid_19hospital.util.DBHelper;

public class DoctorLoginActivity extends AppCompatActivity {
    TextView goToRegister;
    private EditText dEmail, dPassword;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        goToRegister = findViewById(R.id.doctorLoginPageQuestion);
        login = findViewById(R.id.loginButtonDoctor);


        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorLoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dEmail = findViewById(R.id.loginEmailDoctor);
                dPassword = findViewById(R.id.loginPasswordDoctor);

                Doctor doctor = new Doctor();
                doctor.setEmail(dEmail.getText().toString());
                doctor.setPassword(dPassword.getText().toString());
                DBHelper dbHelper = new DBHelper(DoctorLoginActivity.this);
                Doctor d = dbHelper.getDoctor(doctor);

                if (doctor.getPassword().equals(d.getPassword())){
                    Intent intent = new Intent(DoctorLoginActivity.this, DoctorHome.class);
                    startActivity(intent);

                }else
                    Toast.makeText(DoctorLoginActivity.this, "Email or Password doesn't match!!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}