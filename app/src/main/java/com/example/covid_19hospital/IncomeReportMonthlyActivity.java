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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IncomeReportMonthlyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_report_monthly);
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
        TableLayout tl = findViewById(R.id.incomeMonthlyTable);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Month", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "Doctor Fee", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "Cavin", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "Pathology", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "Covid-19", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tl.addView(tr, getTblLayoutParams());
    }

    public void addData() {

        TableLayout tl = findViewById(R.id.incomeMonthlyTable);

        RequestQueue queue = Volley.newRequestQueue(IncomeReportMonthlyActivity.this);
        String url = MainActivity.ipPort + "admin/getMonthlyIncomeAPI";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<JSONObject> items = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                items.add(response.getJSONObject(i));
                                //Toast.makeText(ShowAllApplicants.this,response.getJSONObject(i).get("name").toString() , Toast.LENGTH_SHORT).show();
                                TableRow tr = new TableRow(IncomeReportMonthlyActivity.this);
                                tr.setLayoutParams(getLayoutParams());
                                tr.addView(getTextView(i, response.getJSONObject(i).get("month").toString(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(IncomeReportMonthlyActivity.this, R.color.purple_200)));
                                tr.addView(getTextView(i, response.getJSONObject(i).get("doctorFee").toString() +" Tk.", Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(IncomeReportMonthlyActivity.this, R.color.purple_200)));
                                tr.addView(getTextView(i, response.getJSONObject(i).get("cavinRent").toString() +" Tk.", Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(IncomeReportMonthlyActivity.this, R.color.purple_500)));
                                tr.addView(getTextView(i, response.getJSONObject(i).get("pathologyBill").toString() +" Tk.", Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(IncomeReportMonthlyActivity.this, R.color.purple_500)));
                                tr.addView(getTextView(i, response.getJSONObject(i).get("covidTest").toString() +" Tk.", Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(IncomeReportMonthlyActivity.this, R.color.purple_500)));
                                tl.addView(tr, getTblLayoutParams());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(IncomeReportMonthlyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

    }

    @Override
    public void onClick(View view) {
        TextView idTextView, firstTextView, secondTextView, thirdTextView;
        String name, address, nid, iddd;
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
            Intent i = new Intent(IncomeReportMonthlyActivity.this, ApproveApplicationActivity.class);

            i.putExtra("id", iddd);
            i.putExtra("name", name);
            i.putExtra("address", address);
            i.putExtra("nid", nid);

            startActivity(i);
        }

    }
}