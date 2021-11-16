package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19hospital.model.Appointment;

import org.json.JSONException;
import org.json.JSONObject;

public class DoctorAppointActivity extends AppCompatActivity {
    Button submitAppointment;
    TextView goToLogin;
    EditText name, age, gender, email,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appoint);

        submitAppointment = findViewById(R.id.bookAppointButton);
        goToLogin = findViewById(R.id.backLoginPatient);

        name = findViewById(R.id.appointment_name);
        age = findViewById(R.id.appointment_age);
        gender = findViewById(R.id.appointment_gender);
        email = findViewById(R.id.appointment_email);
        contact = findViewById(R.id.appointment_contact);


        submitAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestQueue queue = Volley.newRequestQueue(DoctorAppointActivity.this);
                String url =MainActivity.ipPort+"bookAppoinment";

                Appointment a = new Appointment();

                a.setName(name.getText().toString());
                a.setAge(age.getText().toString());
                a.setGender(gender.getText().toString());
                a.setEmail(email.getText().toString());
                a.setContact(contact.getText().toString());

                JSONObject object = new JSONObject();
                try {
                    Log.v("name",a.getName());
                    Log.v("name",a.getAge());
                    Log.v("name",a.getGender());
                    Log.v("name",a.getContact());
                    Log.v("name",a.getEmail());

                    object.put("id",0);
                    object.put("name", a.getName());
                    object.put("age", a.getAge());
                    object.put("gender",a.getGender());
                    object.put("contact",a.getContact());
                    object.put("email",a.getEmail());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(DoctorAppointActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DoctorAppointActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);

            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorAppointActivity.this, PatientHome.class);
                startActivity(intent);
            }
        });
    }
}