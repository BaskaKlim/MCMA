package com.mcma.blood_pressure_diary.bodyweight;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BodyWeightReadingDAO extends SQLiteOpenHelper {

    private final String LOG_TAG = "BodyWeightReadingDAO";

    private final String TABLENAME = "BodyWeight";
    private final String COL_WEIGHT_VALUE = "bodyWeight";
    private final String COL_TIME_STAMP = "timeStamp";

    /* queries */

    private final String CREATE = "CREATE TABLE " + TABLENAME +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_WEIGHT_VALUE + " TEXT, " +
            COL_TIME_STAMP + " TEXT; ";

    private final String DROP = "DROP TABLE IF EXISTS " + TABLENAME;

    /* constructors */
    public BodyWeightReadingDAO(@Nullable Context context,
                                @Nullable String name,
                                @Nullable SQLiteDatabase.CursorFactory factory,
                                int version) {
        super(context, name, factory, version);
        Log.i(LOG_TAG, CREATE);
        Log.i(LOG_TAG, DROP);
    }

    public BodyWeightReadingDAO(@Nullable Context context,
                                @Nullable String name,
                                @Nullable SQLiteDatabase.CursorFactory factory,
                                int version,
                                @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public BodyWeightReadingDAO(@Nullable Context context,
                                @Nullable String name,
                                int version,
                                @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TOD0: create database
        sqLiteDatabase.execSQL(CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP);
        onCreate(sqLiteDatabase);

    }
}
