package com.example.cornerstoreapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
    public static final String TABLE =
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
        db.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        onCreate(db);
    }


    public long addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_COLUMN_NAME, product.getName());
        values.put(DATABASE_COLUMN_DESCRIPTION, product.getDescription());
        values.put(DATABASE_COLUMN_SELLER, product.getSeller());
        values.put(DATABASE_COLUMN_PRICE, product.getPrice());
        values.put(DATABASE_COLUMN_PICTURE_URL, product.getPictureUrl());

        long id = db.insert(DATABASE_TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_ID)));
                product.setName(cursor.getString(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_DESCRIPTION)));
                product.setSeller(cursor.getString(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_SELLER)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_PRICE)));
                product.setPictureUrl(cursor.getString(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_PICTURE_URL)));
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }
}
