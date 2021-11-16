package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class DetailsAppointment extends AppCompatActivity {
    private Button deleteButton, appointmentButton;
    private String newString, name, age, gender,date;
    private TextView id, nameShow, ageShow, genderShow, contactShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_appointment);
        deleteButton = findViewById(R.id.deleteAppointment);
        appointmentButton = findViewById(R.id.approveAppointment);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                name = null;
                age = null;
                gender = null;
                date = null;
                newString = null;
            } else {
                newString = extras.getString("id");
                name = extras.getString("name");
                age = extras.getString("age");
                gender = extras.getString("gender");
                date = extras.getString("date");
                Log.v("name", age + name + gender + newString);
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("id");
            name = (String) savedInstanceState.getSerializable("name");
            age = (String) savedInstanceState.getSerializable("age");
            gender = (String) savedInstanceState.getSerializable("gender");
            date = (String) savedInstanceState.getSerializable("date");
        }

        // setContentView(R.layout.layoutName);
        id = (TextView) findViewById(R.id.showAppointmentID);
        nameShow = (TextView) findViewById(R.id.showAppointmentNAME);
        ageShow = (TextView) findViewById(R.id.showAppointmentAGE);
        genderShow = (TextView) findViewById(R.id.showAppointmentGENDER);
        contactShow = (TextView) findViewById(R.id.showAppointmentCONTACT);


        id.setText(newString);
        nameShow.setText(name);
        ageShow.setText(age);
        genderShow.setText(gender);
        contactShow.setText(date);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(DetailsAppointment.this);
                String url =MainActivity.ipPort + "doctor/deleteAPI";

                String getID = id.getText().toString();
                Log.v("id",getID);
                Toast.makeText(DetailsAppointment.this, getID, Toast.LENGTH_SHORT).show();

                Appointment a = new Appointment();
                a.setId(Integer.parseInt(getID));

                JSONObject object = new JSONObject();
                try {
                    object.put("id", a.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(DetailsAppointment.this, "Appointment Deleted!", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsAppointment.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });





        appointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(DetailsAppointment.this);
                String url =MainActivity.ipPort + "doctor/updateAppointmentStatusAPI";

                String getID = id.getText().toString();
//                Log.v("id",getID);
//                Toast.makeText(DetailsAppointment.this, getID, Toast.LENGTH_SHORT).show();

                Appointment a = new Appointment();
                a.setId(Integer.parseInt(getID));

                JSONObject object = new JSONObject();
                try {
                    object.put("id", a.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(DetailsAppointment.this, "Appointment Deleted!", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsAppointment.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });
    }
}