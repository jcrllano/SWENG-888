package com.example.cornerstoreapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CornerStore.db";
    public static final Integer DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE_NAME = "products";
    public static final String DATABASE_COLUMN_ID = "id";
    public static final String DATABASE_COLUMN_NAME = "name";
    public static final String DATABASE_COLUMN_DESCRIPTION = "description";
    public static final String DATABASE_COLUMN_SELLER = "seller";
    public static final String DATABASE_COLUMN_PRICE = "price";
    public static final String DATABASE_COLUMN_PICTURE_URL = "pictureUrl";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    public static final String CREATE_TABLE_PRODUCTS =
            "CREATE TABLE " + DATABASE_TABLE_NAME  + " (" +
                    DATABASE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    DATABASE_COLUMN_NAME + " TEXT, " +
                    DATABASE_COLUMN_DESCRIPTION + " TEXT, " +
                    DATABASE_COLUMN_SELLER + " TEXT, " +
                    DATABASE_COLUMN_PRICE + " REAL, " +
                    DATABASE_COLUMN_PICTURE_URL + " TEXT" +
                    ");";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
