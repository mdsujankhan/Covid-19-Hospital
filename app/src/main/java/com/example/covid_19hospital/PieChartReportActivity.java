package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PieChartReportActivity extends AppCompatActivity {
    TextView tvFee, tvTest, tvRent, tvPathology, tvTotal ,goToHome;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_report);

        tvFee = findViewById(R.id.tvDoctorFee);
        tvTest = findViewById(R.id.tvCovidTest);
        tvRent = findViewById(R.id.tvCavinRent);
        tvPathology = findViewById(R.id.tvPathology);
        tvTotal = findViewById(R.id.tvTotalIncome);
        goToHome = findViewById(R.id.backHomeAdmin);
        pieChart = findViewById(R.id.piechart);
        setData();
    }
    private void setData() {

        RequestQueue queue = Volley.newRequestQueue(PieChartReportActivity.this);
        String url = MainActivity.ipPort + "admin/getAllCovidIncomeAPI";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<JSONObject> items = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // Set the percentage of income
                                tvFee.setText( response.getJSONObject(i).get("doctorFee").toString());
                                tvTest.setText(response.getJSONObject(i).get("covidTest").toString());
                                tvRent.setText(response.getJSONObject(i).get("cavinRent").toString());
                                tvPathology.setText(response.getJSONObject(i).get("pathologyBill").toString());
                                tvTotal.setText(response.getJSONObject(i).get("totalBill").toString());


                                // Set the data and color to the pie chart
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Doctor Fee",
                                                Integer.parseInt(tvFee.getText().toString()),
                                                Color.parseColor("#FFA726")));
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Covid Test",
                                                Integer.parseInt(tvTest.getText().toString()),
                                                Color.parseColor("#66BB6A")));
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Cavin Rent",
                                                Integer.parseInt(tvRent.getText().toString()),
                                                Color.parseColor("#EF5350")));
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Pathology",
                                                Integer.parseInt(tvPathology.getText().toString()),
                                                Color.parseColor("#29B6F6")));
                                pieChart.startAnimation();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PieChartReportActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);


        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PieChartReportActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}