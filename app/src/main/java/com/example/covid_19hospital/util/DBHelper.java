package com.example.covid_19hospital.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.covid_19hospital.model.Doctor;
import com.example.covid_19hospital.model.Pathologist;
import com.example.covid_19hospital.model.Patient;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "COVID19HOSPITAL";
    private static final int VERSION = 1;
    String sql,doctors,pathologists;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sql = "Create table users(id INTEGER PRIMARY KEY, name TEXT, password TEXT, email TEXT, address TEXT, age INTEGER, contact_no TEXT, time_shcedule TEXT, department TEXT )";
        doctors = "Create table doctors(id INTEGER PRIMARY KEY, name TEXT, password TEXT, email TEXT, address TEXT, time_shcedule TEXT, department TEXT )";
        pathologists = "Create table pathologists(id INTEGER PRIMARY KEY, name TEXT, password TEXT, email TEXT, address TEXT, gender TEXT, contact TEXT )";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(doctors);
        sqLiteDatabase.execSQL(pathologists);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ sql);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + doctors );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + pathologists );

        onCreate(sqLiteDatabase);

    }

    public Long addUser(Patient u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("name",u.getName());
        val.put("address",u.getAddress());
        val.put("age",Integer.valueOf(u.getAge()));
        val.put("contact_no ",u.getContactNumber());
        val.put("email",u.getEmail());
        val.put("password",u.getPassword());
        Long l =db.insert("users",null, val);
        db.close();
        return l;
    }

    public Long addDoctor(Doctor d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("name",d.getName());
        val.put("address",d.getAddress());
        val.put("email",d.getEmail());
        val.put("password",d.getPassword());
        Long l =db.insert("doctors",null, val);
        db.close();
        return l;
    }

    public Patient getPatient(Patient patient) {
    SQLiteDatabase db = this.getWritableDatabase();
    String query = "select * from users where email = '"+patient.getEmail()+"'";
        Cursor c = db.rawQuery(query,null);
        Patient p = new Patient();
        if (c.moveToFirst()){
            p.setId(Integer.parseInt(c.getString(0)));
            p.setEmail(c.getString(3));
            p.setPassword(c.getString(2));
        }
        db.close();
        return p;
    }

    public Doctor getDoctor(Doctor doctor) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from doctors where email = '"+doctor.getEmail()+"'";
        Cursor c = db.rawQuery(query,null);
        Doctor d = new Doctor();
        if (c.moveToFirst()){
            d.setId(Integer.parseInt(c.getString(0)));
            d.setEmail(c.getString(3));
            d.setPassword(c.getString(2));
        }
        db.close();
        return d;
    }

    public Long addPathologist(Pathologist pathologist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("name",pathologist.getName());
        val.put("address",pathologist.getAddress());
        val.put("email",pathologist.getEmail());
        val.put("gender",pathologist.getGender());
        val.put("contact",pathologist.getContact());
        val.put("password",pathologist.getPassword());
        Long l =db.insert("pathologists",null, val);
        db.close();
        return l;
    }

    public Pathologist getPathologist(Pathologist pathologist) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from pathologists where email = '"+pathologist.getEmail()+"'";
        Cursor c = db.rawQuery(query,null);
        Pathologist p = new Pathologist();
        if (c.moveToFirst()){
            p.setId(Integer.parseInt(c.getString(0)));
            p.setEmail(c.getString(3));
            p.setPassword(c.getString(2));
        }
        db.close();
        return p;
    }
}
