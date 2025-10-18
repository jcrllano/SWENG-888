package com.example.cornerstoreapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //this is the name of the database
    public static final String DATABASE_NAME = "CornerStore.db";
    //This is the version of database
    public static final Integer DATABASE_VERSION = 6;
    //This is the table name
    public static final String DATABASE_TABLE_NAME = "products";
    //This is the ID of the product
    public static final String DATABASE_COLUMN_ID = "id";
    //This is the name of the product
    public static final String DATABASE_COLUMN_NAME = "name";
    //This is the description of the product
    public static final String DATABASE_COLUMN_DESCRIPTION = "description";
    //This is the name of the seller
    public static final String DATABASE_COLUMN_SELLER = "seller";
    //This is the price of the product
    public static final String DATABASE_COLUMN_PRICE = "price";
    //This is the iD of the product image
    public static final String DATABASE_COLUMN_PICTURE_ID = "pictureID";

    //This the constrcutor of the class
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    //This method creates the table inside the database
    public static final String TABLE =
            "CREATE TABLE " + DATABASE_TABLE_NAME  + " (" +
                    DATABASE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    DATABASE_COLUMN_NAME + " TEXT UNIQUE, " +
                    DATABASE_COLUMN_DESCRIPTION + " TEXT, " +
                    DATABASE_COLUMN_SELLER + " TEXT, " +
                    DATABASE_COLUMN_PRICE + " REAL, " +
                    DATABASE_COLUMN_PICTURE_ID + " INTEGER" +
                    ");";
    //This method executes the SQL command to create the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE);
    }

    //This method updates the table whenver there is a change on it
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        onCreate(db);
    }
    //This method add the products and puts them into the table
    public long addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_COLUMN_NAME, product.getName());
        values.put(DATABASE_COLUMN_DESCRIPTION, product.getDescription());
        values.put(DATABASE_COLUMN_SELLER, product.getSeller());
        values.put(DATABASE_COLUMN_PRICE, product.getPrice());
        values.put(DATABASE_COLUMN_PICTURE_ID, product.getPictureID());

        long id = db.insert(DATABASE_TABLE_NAME, null, values);
        db.close();
        return id;
    }
    //This method gets all the products from the List arrays
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //This queries the database
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_NAME, null);
        //This maps the the database to be used in the product list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_ID)));
                product.setName(cursor.getString(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_DESCRIPTION)));
                product.setSeller(cursor.getString(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_SELLER)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_PRICE)));
                product.setPictureID(cursor.getInt(cursor.getColumnIndexOrThrow(DATABASE_COLUMN_PICTURE_ID)));
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }
}
