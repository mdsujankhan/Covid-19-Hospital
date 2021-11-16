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
import com.example.covid_19hospital.model.TestApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ContactInputActivity extends AppCompatActivity {
    Button getInvoice;
    EditText contact;
    String inputedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_input);
        getInvoice = findViewById(R.id.getInvoiceButton);

        contact = findViewById(R.id.patientContact);


        getInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputedContact = contact.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(ContactInputActivity.this);
                String url =MainActivity.ipPort+"patient/createInvoiceAPI";


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
                                  //  Toast.makeText(ContactInputActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(ContactInputActivity.this, InvoiceActivity.class);
                                    intent.putExtra("name", response.get("name").toString());
                                    intent.putExtra("gender", response.get("gender").toString());
                                    intent.putExtra("address", response.get("address").toString());
                                    intent.putExtra("doctorFee", response.get("doctorFee").toString());
                                    intent.putExtra("covidTestFee", response.get("covidTestFee").toString());
                                    intent.putExtra("pathologyBill", response.get("pathologyBill").toString());
                                    intent.putExtra("cavinRent", response.get("cavinRent").toString());
                                    intent.putExtra("totalBill", response.get("totalBill").toString());
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ContactInputActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });
    }
}