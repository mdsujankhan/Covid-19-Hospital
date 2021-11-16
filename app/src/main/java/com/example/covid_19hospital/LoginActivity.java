package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19hospital.model.Patient;
import com.example.covid_19hospital.util.DBHelper;

public class LoginActivity extends AppCompatActivity {
    private TextView reg;
    private EditText pEmail, pPassword;
    private Button login;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reg = findViewById(R.id.loginPageQuestion);
        login = findViewById(R.id.loginButtonPatient);
        loadingPB = findViewById(R.id.idPBLoading);
        loadingPB.setVisibility(View.VISIBLE);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, PatientRegistrationActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pEmail = findViewById(R.id.loginEmailPatient);
                pPassword = findViewById(R.id.loginPasswordPatient);
                loadingPB.setVisibility(View.GONE);

                Patient patient = new Patient();
                patient.setEmail(pEmail.getText().toString());
                patient.setPassword(pPassword.getText().toString());

                DBHelper dbHelper = new DBHelper(LoginActivity.this);
                Patient p = dbHelper.getPatient(patient);

                if (patient.getPassword().equals(p.getPassword())){
                    Intent intent = new Intent(LoginActivity.this, PatientHome.class);
                    startActivity(intent);

                }else
                    Toast.makeText(LoginActivity.this, "Email or Password doesn't match!!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}