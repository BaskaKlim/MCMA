package com.mcma.blood_pressure_diary.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.mcma.blood_pressure_diary.MainActivity;
import com.mcma.blood_pressure_diary.activities.BloodPressureActivity;
import com.mcma.blood_pressure_diary.activities.BodyWeightActivity;
import com.mcma.blood_pressure_diary.models.bloodpressure.BloodPressureReading;

import java.util.LinkedList;

public class BloodPressureReadingDAO extends HealthDiaryDAO {

    /* constructors */
    public BloodPressureReadingDAO(Context context) {
        super(context);
    }

    /* DAO methods */

    //TODO: insert
    public long addBloodPressureRecord(BloodPressureReading bloodPressure) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SYS_PRESSURE_VALUE, bloodPressure.getSys());
        values.put(COL_DIA_PRESSURE_VALUE, bloodPressure.getDia());
        values.put(COL_TIME_STAMP, bloodPressure.getTimeStamp());
        //return insert in long
        return database.insert(TABLE_NAME_1, null, values);
    }

    //TODO: selects

    public LinkedList<BloodPressureReading> getAllBloodPressureRecords() {
        SQLiteDatabase database = getReadableDatabase();
        LinkedList<BloodPressureReading> bloodPressureRecords = new LinkedList<>();
        Cursor query = database.query(TABLE_NAME_1,
                new String[]{COL_SYS_PRESSURE_VALUE, COL_DIA_PRESSURE_VALUE},
                null,
                null,
                null,
                null,
                null);

        while (query.moveToNext()) {
            int i1 = query.getColumnIndex(COL_SYS_PRESSURE_VALUE);
            int i2 = query.getColumnIndex(COL_DIA_PRESSURE_VALUE);
            int sysPressureValue = query.getInt(i1);
            int diaPressureValue = query.getInt(i2);

            BloodPressureReading bloodPressureRecord = new BloodPressureReading(sysPressureValue, diaPressureValue);
            bloodPressureRecords.add(bloodPressureRecord);
        }

        return bloodPressureRecords;
    }

}
