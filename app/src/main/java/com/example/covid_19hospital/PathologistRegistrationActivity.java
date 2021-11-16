package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19hospital.model.Pathologist;
import com.example.covid_19hospital.util.DBHelper;

public class PathologistRegistrationActivity extends AppCompatActivity {
    EditText name, address,  email, password, gender, contact;
    Button submitRegistration;
    TextView goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_registration);
        submitRegistration = findViewById(R.id.registrationPathologistBTN);
        goToLogin = findViewById(R.id.backToLoginPathology);

        name = findViewById(R.id.pathologist_name);
        address = findViewById(R.id.pathologist_address);
        email = findViewById(R.id.pathologist_email);
        gender = findViewById(R.id.pathologist_gender);
        contact = findViewById(R.id.pathologist_contact);
        password = findViewById(R.id.pathologist_password);


        submitRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pathologist pathologist = new Pathologist();
                pathologist.setId(0);
                pathologist.setName(name.getText().toString());
                pathologist.setAddress(address.getText().toString());
                pathologist.setGender(gender.getText().toString());
                pathologist.setContact(contact.getText().toString());
                pathologist.setEmail(email.getText().toString());
                pathologist.setPassword(password.getText().toString());

                DBHelper dbHelper = new DBHelper(PathologistRegistrationActivity.this);
                Long l = dbHelper.addPathologist(pathologist);
                if (l > 0){
                    Toast.makeText(PathologistRegistrationActivity.this, "Pathologist Added successfully!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(PathologistRegistrationActivity.this, "Pathologist couldn't Added!!", Toast.LENGTH_SHORT).show();
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PathologistRegistrationActivity.this, PathologistLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}