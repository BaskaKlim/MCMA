package com.mcma.blood_pressure_diary.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mcma.blood_pressure_diary.models.bodyweight.BodyWeightReading;

import java.util.LinkedList;

public class BodyWeightReadingDAO extends HealthDiaryDAO {

    /* constructors */
    public BodyWeightReadingDAO(Context context) {
        super(context);
    }


    /* DAO methods */

    //TODO: insert
    public long addWeightRecord(BodyWeightReading weight) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_WEIGHT_VALUE, weight.getBodyWeight());
        values.put(COL_TIME_STAMP, weight.getTimeStamp());
        //return insert
        return  writableDatabase.insert(TABLE_NAME_2, null, values);
    }

    //TODO: selects
    public LinkedList<BodyWeightReading> getAllWeightRecords() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        LinkedList<BodyWeightReading> weightRecords = new LinkedList<>();
        Cursor query = readableDatabase.query(
                TABLE_NAME_2,
                new String[]{COL_WEIGHT_VALUE},
                null,
                null,
                null,
                null,
                null);
        while (query.moveToNext()) {
            int i1 = query.getColumnIndex(COL_WEIGHT_VALUE);
            int weightValue = query.getInt(i1);
            BodyWeightReading weightRecord = new BodyWeightReading(weightValue);
            weightRecords.add(weightRecord);
        }

        return weightRecords;
    }

}
