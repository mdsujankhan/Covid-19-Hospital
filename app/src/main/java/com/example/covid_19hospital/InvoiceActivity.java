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

import com.example.covid_19hospital.model.Bill;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class InvoiceActivity extends AppCompatActivity {
    private String name, gender,address, doctorFee, covidTestFee, pathologyBill, cavinRent, totalBill;
    TextView downloadBTN, tvFee, tvTest, tvRent, tvPathology, tvName, tvGender, tvaddress, tvTotal, downloadInvoice ;
    private Bitmap bitmap;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        tvFee = findViewById(R.id.tvDoctorFee);
        tvTest = findViewById(R.id.tvCovidTest);
        tvRent = findViewById(R.id.tvCavinRent);
        tvPathology = findViewById(R.id.tvPathology);
        tvName = findViewById(R.id.viewInvoiceNameTV);
        tvGender = findViewById(R.id.viewInvoiceGenderTV);
        tvaddress = findViewById(R.id.viewInvoiceAddressTV);
        tvTotal = findViewById(R.id.tvTotal);
        downloadInvoice = findViewById(R.id.downloadInvoice);

        linear = findViewById(R.id.invoiceLayout);
        downloadBTN = findViewById(R.id.downloadInvoice);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                name = null;
                gender = null;
                address = null;
                doctorFee = null;
                covidTestFee = null;
                pathologyBill = null;
                cavinRent = null;
                totalBill = null;

            } else {
                name = extras.getString("name");
                gender = extras.getString("gender");
                address = extras.getString("address");
                doctorFee = extras.getString("doctorFee");
                covidTestFee = extras.getString("covidTestFee");
                pathologyBill = extras.getString("pathologyBill");
                cavinRent = extras.getString("cavinRent");
                totalBill = extras.getString("totalBill");
                Log.v("name",  name + gender + address + doctorFee);


            }
        } else {
            name = (String) savedInstanceState.getSerializable("name");
            gender = (String) savedInstanceState.getSerializable("gender");
            address = (String) savedInstanceState.getSerializable("address");
            doctorFee = (String) savedInstanceState.getSerializable("doctorFee");
            covidTestFee = (String) savedInstanceState.getSerializable("covidTestFee");
            pathologyBill = (String) savedInstanceState.getSerializable("pathologyBill");
            cavinRent = (String) savedInstanceState.getSerializable("cavinRent");
            totalBill = (String) savedInstanceState.getSerializable("totalBill");
        }


        tvFee.setText(doctorFee );
        tvTest.setText(covidTestFee);
        tvRent.setText(cavinRent);
        tvPathology.setText(pathologyBill);
        tvName.setText(name);
        tvGender.setText(gender);
        tvaddress.setText(address);
        tvTotal.setText(totalBill);


        downloadInvoice.setOnClickListener(new View.OnClickListener() {
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
        String targetPdf = "/sdcard/Download/invoice_" +name+ ".pdf";
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
        Toast.makeText(this, "Report downloaded successfully", Toast.LENGTH_SHORT).show();
        openPdf();

    }

    private void openPdf() {
        File file = new File("sdcard/Download/invoice_"+name+".pdf");
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