package com.beelineshopping.beelineandroidapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shelby on 2/12/2016.
 */
public class BeelineDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BeelineContract.FoodDetails.TABLE_NAME + " (" +
                    BeelineContract.FoodDetails._ID + " INTEGER PRIMARY KEY," +
                    BeelineContract.FoodDetails.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    BeelineContract.FoodDetails.COLUMN_NAME_AISLE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BeelineContract.FoodDetails.TABLE_NAME;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Food.db";

    public BeelineDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
