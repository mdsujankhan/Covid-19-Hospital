package com.example.covid_19hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrescriptionActivity extends AppCompatActivity {
    TextView downloadBTN, downloadPrescription, nameTV, genderTV, addressTV, testsTV, medicine1TV, medicine2TV, medicine3TV, medicine4TV, medicine5TV, medicine6TV;
    private String name, gender, address, tests, medicine1, medicine2, medicine3, medicine4, medicine5, medicine6, medicine1Time, medicine2Time, medicine3Time, medicine4Time, medicine5Time, medicine6Time;
    TextView tvFee, tvTest, tvRent, tvPathology, tvName, tvGender, tvaddress, tvTotal, downloadInvoice;
    private Bitmap bitmap;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        downloadPrescription = findViewById(R.id.downloadPrescription);
        nameTV = findViewById(R.id.viewPrescriptionNameTV);
        genderTV = findViewById(R.id.viewPrescriptionGenderTV);
        addressTV = findViewById(R.id.viewInvoiceAddressTV);
        testsTV = findViewById(R.id.tvTests);
        medicine1TV = findViewById(R.id.tvMedicine1);
        medicine2TV = findViewById(R.id.tvMedicine2);
        medicine3TV = findViewById(R.id.tvMedicine3);
        medicine4TV = findViewById(R.id.tvMedicine4);
        medicine5TV = findViewById(R.id.tvMedicine5);
        medicine6TV = findViewById(R.id.tvMedicine1);

        linear = findViewById(R.id.prescriptionLayout);
        downloadBTN = findViewById(R.id.downloadPrescription);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                name = null;
                gender = null;
                tests = null;
                medicine1 = null;
                medicine2 = null;
                medicine3 = null;
                medicine4 = null;
                medicine5 = null;
                medicine6 = null;
                medicine1Time = null;
                medicine2Time = null;
                medicine3Time = null;
                medicine4Time = null;
                medicine5Time = null;
                medicine6Time = null;

            } else {
                name = extras.getString("name");
                gender = extras.getString("gender");
                tests = extras.getString("tests");
                medicine1 = extras.getString("medicine_1");
                medicine2 = extras.getString("medicine_2");
                medicine3 = extras.getString("medicine_3");
                medicine4 = extras.getString("medicine_4");
                medicine5 = extras.getString("medicine_5");
                medicine6 = extras.getString("medicine_6");
                medicine1Time = extras.getString("medicine_1_time");
                medicine2Time = extras.getString("medicine_2_time");
                medicine3Time = extras.getString("medicine_3_time");
                medicine4Time = extras.getString("medicine_4_time");
                medicine5Time = extras.getString("medicine_5_time");
                medicine6Time = extras.getString("medicine_6_time");
                Log.v("name", name + gender);


            }
        } else {
            name = (String) savedInstanceState.getSerializable("name");
            gender = (String) savedInstanceState.getSerializable("gender");
            tests = (String) savedInstanceState.getSerializable("tests");
            medicine1 = (String) savedInstanceState.getSerializable("medicine_1");
            medicine2 = (String) savedInstanceState.getSerializable("medicine_2");
            medicine3 = (String) savedInstanceState.getSerializable("medicine_3");
            medicine4 = (String) savedInstanceState.getSerializable("medicine_4");
            medicine5 = (String) savedInstanceState.getSerializable("medicine_5");
            medicine6 = (String) savedInstanceState.getSerializable("medicine_6");
            medicine1Time = (String) savedInstanceState.getSerializable("medicine_1_time");
            medicine2Time = (String) savedInstanceState.getSerializable("medicine_2_time");
            medicine3Time = (String) savedInstanceState.getSerializable("medicine_3_time");
            medicine4Time = (String) savedInstanceState.getSerializable("medicine_4_time");
            medicine5Time = (String) savedInstanceState.getSerializable("medicine_5_time");
            medicine6Time = (String) savedInstanceState.getSerializable("medicine_6_time");
        }

        nameTV.setText(name);
        genderTV.setText(gender);
        testsTV.setText(tests);
        medicine1TV.setText(medicine1 + "                   " + medicine1Time);
        medicine2TV.setText(medicine2 + "                   " + medicine2Time);
        medicine3TV.setText(medicine3 + "                   " + medicine3Time);
        medicine4TV.setText(medicine4 + "                   " + medicine4Time);
        medicine5TV.setText(medicine5 + "                   " + medicine5Time);


        downloadPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = LoadBitmap(linear, linear.getWidth(), linear.getHeight());
                downloadBTN.setVisibility(View.INVISIBLE);
                createPdf();
            }
        });
    }

    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private void createPdf() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;

        int convertHighet = (int) hight, convertWidth = (int) width;

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        // write the document content
        // String targetPdf = "/sdcard/Download/admit.pdf";
        String targetPdf = "/sdcard/Download/prescription_" + name + ".pdf";
        // String targetPdf = "/Download/admit.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "Prescription downloaded successfully", Toast.LENGTH_SHORT).show();
        openPdf();

    }

    private void openPdf() {
        File file = new File("sdcard/Download/prescription_" + name + ".pdf");
        //File file = new File(Environment.getExternalStorageDirectory(),"report.pdf");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
               // Toast.makeText(this, "No Application for pdf view", Toast.LENGTH_SHORT).show();
            }
        }
    }
}