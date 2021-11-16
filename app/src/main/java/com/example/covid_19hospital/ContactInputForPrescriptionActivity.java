package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ContactInputForPrescriptionActivity extends AppCompatActivity {
    Button getPrescription;
    EditText contact;
    String inputedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_input_for_prescription);

        getPrescription = findViewById(R.id.getPrescriptionButton);
        contact = findViewById(R.id.patientContactForPrescription);

        getPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputedContact = contact.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(ContactInputForPrescriptionActivity.this);
                String url =MainActivity.ipPort+"patient/getPrescriptionAPI";


                JSONObject object = new JSONObject();
                try {
                    object.put("contact",inputedContact);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                               //     Toast.makeText(ContactInputForPrescriptionActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(ContactInputForPrescriptionActivity.this, PrescriptionActivity.class);
                                    intent.putExtra("name", response.get("name").toString());
                                    intent.putExtra("gender", response.get("gender").toString());
                                    intent.putExtra("medicine_1", response.get("medicine_1").toString());
                                    intent.putExtra("medicine_2", response.get("medicine_2").toString());
                                    intent.putExtra("medicine_3", response.get("medicine_3").toString());
                                    intent.putExtra("medicine_4", response.get("medicine_4").toString());
                                    intent.putExtra("medicine_5", response.get("medicine_5").toString());
                                    intent.putExtra("medicine_6", response.get("medicine_6").toString());
                                    intent.putExtra("tests", response.get("tests").toString());
                                    intent.putExtra("medicine_1_time", response.get("medicine_1_time").toString());
                                    intent.putExtra("medicine_2_time", response.get("medicine_2_time").toString());
                                    intent.putExtra("medicine_3_time", response.get("medicine_3_time").toString());
                                    intent.putExtra("medicine_4_time", response.get("medicine_4_time").toString());
                                    intent.putExtra("medicine_5_time", response.get("medicine_5_time").toString());
                                    intent.putExtra("medicine_6_time", response.get("medicine_6_time").toString());
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(ContactInputForPrescriptionActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });

    }
}