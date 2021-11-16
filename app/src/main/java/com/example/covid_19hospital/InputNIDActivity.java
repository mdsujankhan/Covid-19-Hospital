package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class InputNIDActivity extends AppCompatActivity {
    EditText nidNumber;
    Button getReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nidactivity);
        nidNumber= findViewById(R.id.patientNID);
        getReport= findViewById(R.id.getCovidButton);

        getReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputedNID = nidNumber.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(InputNIDActivity.this);
                String url =MainActivity.ipPort+"patient/getReportAPI";


                JSONObject object = new JSONObject();
                try {
                    object.put("nidNo",inputedNID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    //  Toast.makeText(InputNIDActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(InputNIDActivity.this, CovidReportActivity.class);
                                    intent.putExtra("name", response.get("name").toString());
                                    intent.putExtra("gender", response.get("gender").toString());
                                    intent.putExtra("address", response.get("address").toString());
                                    intent.putExtra("nidNo", response.get("nidNo").toString());
                                    intent.putExtra("result", response.get("result").toString());
                          Log.v("result", response.get("result").toString());
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InputNIDActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });
    }
}