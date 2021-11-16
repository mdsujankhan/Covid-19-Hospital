package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19hospital.model.Appointment;
import com.example.covid_19hospital.model.TestApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class ApproveApplicationActivity extends AppCompatActivity {
    private Button deleteButton, approveButton;
    private String newString, name, address, nidNO;
    private TextView id, nameShow, addressShow, nidNOShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_application);
        deleteButton = findViewById(R.id.deleteApplication);
        approveButton = findViewById(R.id.approveApplication);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                name = null;
                address = null;
                nidNO = null;
                newString = null;
            } else {
                newString = extras.getString("id");
                name = extras.getString("name");
                address = extras.getString("address");
                nidNO = extras.getString("nid");
                Log.v("name", address + name + nidNO + newString);
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("id");
            name = (String) savedInstanceState.getSerializable("name");
            address = (String) savedInstanceState.getSerializable("address");
            nidNO = (String) savedInstanceState.getSerializable("nidNO");
        }

        // setContentView(R.layout.layoutName);
        id = (TextView) findViewById(R.id.showIDTXT);
        nameShow = (TextView) findViewById(R.id.showNameTXT);
        addressShow = (TextView) findViewById(R.id.showAddressTXT);
        nidNOShow = (TextView) findViewById(R.id.showNIDTXT);


        id.setText(newString);
        nameShow.setText(name);
        addressShow.setText(address);
        nidNOShow.setText(nidNO);



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(ApproveApplicationActivity.this);
                String url =MainActivity.ipPort + "receptionist/deleteAPI";

                String getID = id.getText().toString();
                Toast.makeText(ApproveApplicationActivity.this, getID, Toast.LENGTH_SHORT).show();

               TestApplication a = new TestApplication();
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
                                Toast.makeText(ApproveApplicationActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ApproveApplicationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });




        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(ApproveApplicationActivity.this);
                  String url =MainActivity.ipPort + "receptionist/updateApproveStatusAPI";

                String getID = id.getText().toString();
//                Toast.makeText(ApproveApplicationActivity.this, getID, Toast.LENGTH_SHORT).show();

                TestApplication a = new TestApplication();
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
                                Toast.makeText(ApproveApplicationActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ApproveApplicationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });
    }
}