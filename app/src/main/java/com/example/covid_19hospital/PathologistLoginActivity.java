package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19hospital.model.Pathologist;
import com.example.covid_19hospital.model.Patient;
import com.example.covid_19hospital.util.DBHelper;

public class PathologistLoginActivity extends AppCompatActivity {
    TextView goToRegister;
    Button signIN;
    private TextView reg;
    private EditText pEmail, pPassword;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist_login);
        goToRegister = findViewById(R.id.loginPageQuestionPathologist);
        signIN = findViewById(R.id.loginButtonPathologist);
        pEmail = findViewById(R.id.loginEmailPathologist);
        pPassword = findViewById(R.id.loginPasswordPathologist);

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PathologistLoginActivity.this,PathologistRegistrationActivity.class);
                startActivity(intent);
            }
        });

        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pathologist pathologist = new Pathologist();
                pathologist.setEmail(pEmail.getText().toString());
                pathologist.setPassword(pPassword.getText().toString());

                DBHelper dbHelper = new DBHelper(PathologistLoginActivity.this);
                Pathologist p = dbHelper.getPathologist(pathologist);

                if (pathologist.getPassword().equals(p.getPassword())){
                    Intent intent = new Intent(PathologistLoginActivity.this, PathologistHomeActivity.class);
                    startActivity(intent);

                }else
                    Toast.makeText(PathologistLoginActivity.this, "Email or Password doesn't match!!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}