package com.example.covid_19hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19hospital.model.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowAllApplicants extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_applicants);
        addHeaders();
        addData();
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }
    @NonNull
    public TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }
    @NonNull
    public TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }
    public void addHeaders() {
        TableLayout tl = findViewById(R.id.table);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "ID", Color.WHITE, Typeface.BOLD, R.color.gray));
        tr.addView(getTextView(0, "Name", Color.WHITE, Typeface.BOLD, R.color.gray));
        tr.addView(getTextView(0, "Address", Color.WHITE, Typeface.BOLD, R.color.gray));
        tr.addView(getTextView(0, "NID Number", Color.WHITE, Typeface.BOLD, R.color.gray));
        tl.addView(tr, getTblLayoutParams());
    }

    public void addData() {

        TableLayout tl = findViewById(R.id.table);

        RequestQueue queue = Volley.newRequestQueue(ShowAllApplicants.this);
        String url =MainActivity.ipPort + "receptionist/allApplications";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<JSONObject> items = new ArrayList<>();
                        for(int i = 0;  i < response.length(); i++){
                            try {
                                items.add(response.getJSONObject(i));
                                //Toast.makeText(ShowAllApplicants.this,response.getJSONObject(i).get("name").toString() , Toast.LENGTH_SHORT).show();
                                TableRow tr = new TableRow(ShowAllApplicants.this);
                                tr.setLayoutParams(getLayoutParams());
                                tr.addView(getTextView(i, response.getJSONObject(i).get("id").toString(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(ShowAllApplicants.this, R.color.purple_200)));
                                tr.addView(getTextView(i, response.getJSONObject(i).get("name").toString(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(ShowAllApplicants.this, R.color.purple_500)));
                                tr.addView(getTextView(i, response.getJSONObject(i).get("address").toString(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(ShowAllApplicants.this, R.color.purple_500)));
                                tr.addView(getTextView(i, response.getJSONObject(i).get("nidNo").toString(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(ShowAllApplicants.this, R.color.purple_500)));
                                tl.addView(tr, getTblLayoutParams());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowAllApplicants.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

    }
    @Override
    public void onClick(View view) {
        TextView idTextView, firstTextView, secondTextView,thirdTextView;
        String name,address, nid, iddd;
        int idd;

        int id = view.getId();
        TextView tv = findViewById(id);

        TableRow t = (TableRow) view.getParent();
         idTextView = (TextView) t.getChildAt(0);
         firstTextView = (TextView) t.getChildAt(1);
         secondTextView = (TextView) t.getChildAt(2);
         thirdTextView = (TextView) t.getChildAt(3);

         name = firstTextView.getText().toString();
         address = secondTextView.getText().toString();
         nid = thirdTextView.getText().toString();
         iddd = idTextView.getText().toString();

        if (null != tv) {
            Log.i("onClick", "Clicked on row :: " + id);
            Toast.makeText(this, "Clicked on row :: " + id + ", Text :: " + tv.getText(), Toast.LENGTH_SHORT).show();
            Intent i  = new Intent(ShowAllApplicants.this, ApproveApplicationActivity.class);

            i.putExtra("id", iddd);
            i.putExtra("name", name);
            i.putExtra("address", address);
            i.putExtra("nid", nid);

            startActivity(i);
        }

    }

}