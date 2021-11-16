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
import com.example.covid_19hospital.model.TestApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class CovidTestApplication extends AppCompatActivity {
    Button apply;
    TextView goToLogin;
    EditText name, age, address, nid,method, transactionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_test_application);
        apply = findViewById(R.id.submitApplycation);
        goToLogin = findViewById(R.id.backToLoginPatient);

        name= findViewById(R.id.applicant_name);
        age = findViewById(R.id.applicant_age);
        address = findViewById(R.id.applicant_address);
        nid = findViewById(R.id.applicant_nid);
        method = findViewById(R.id.applicant_transaction_method);
        transactionId = findViewById(R.id.applicant_transaction_id);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(CovidTestApplication.this);
                String url =MainActivity.ipPort+"submitAPI";

                TestApplication testApplication = new TestApplication();

                testApplication.setName(name.getText().toString());
                testApplication.setAddress(address.getText().toString());
                testApplication.setAge(Integer.valueOf(age.getText().toString()));
                testApplication.setNid(nid.getText().toString());
                testApplication.setMethod(method.getText().toString());
                testApplication.setTransactionID(transactionId.getText().toString());

                JSONObject object = new JSONObject();
                try {
                    object.put("id",0);
                    object.put("name", testApplication.getName());
                    object.put("age", testApplication.getAge());
                    object.put("address",testApplication.getAddress());
                    object.put("nidNo", testApplication.getNid());
                    object.put("paymentMethod",testApplication.getMethod());
                    object.put("transactionId",testApplication.getTransactionID());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(CovidTestApplication.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CovidTestApplication.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);

            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CovidTestApplication.this, PatientHome.class);
                startActivity(intent);
            }
        });
    }
}