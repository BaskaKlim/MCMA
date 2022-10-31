package com.mcma.blood_pressure_diary.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HealthDiaryDAO extends SQLiteOpenHelper {

    public final String TABLE_NAME_1 = "bloodPressure";
    public final String COL_SYS_PRESSURE_VALUE = "sysPressure";
    public final String COL_DIA_PRESSURE_VALUE = "diaPressure";
    public final String COL_TIME_STAMP = "timeStamp";
    public final String TABLE_NAME_2 = "bodyWeight";
    public final String COL_WEIGHT_VALUE = "bodyWeight";

    /* queries */

    private final String CREATE_BLOOD_PRESSURE = "CREATE TABLE " + TABLE_NAME_1 +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_SYS_PRESSURE_VALUE + " INTEGER, " +
            COL_DIA_PRESSURE_VALUE + " INTEGER, " +
            COL_TIME_STAMP + " TEXT);";

    private final String DROP_BLOOD_PRESSURE = "DROP TABLE IF EXISTS " + TABLE_NAME_1;


    private final String CREATE_BODY_WEIGHT = "CREATE TABLE " + TABLE_NAME_2 +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_WEIGHT_VALUE + " INTEGER, " +
            COL_TIME_STAMP + " TEXT);";


    private final String DROP_BODY_WEIGHT = "DROP TABLE IF EXISTS " + TABLE_NAME_2;

    public HealthDiaryDAO(@Nullable Context context) {
        super(context, "healthDiary.db", null, 1);
    }

    //TODO: execute create database query. It is called the first time a database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BLOOD_PRESSURE);
        db.execSQL(CREATE_BODY_WEIGHT);
    }

    //TODO: execute upgrade database query. It is called if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_BLOOD_PRESSURE);
        db.execSQL(DROP_BODY_WEIGHT);
        onCreate(db);
    }
}
