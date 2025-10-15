package com.example.cornerstoreapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        //Product product = new Product(1,"Red Apple", "This a honeycrisp apple","MountainApple", 1,R.drawable.apple);
        //databaseHelper.addProduct(product);
        addProducts(databaseHelper);
        List<Product> products = databaseHelper.getAllProducts();
        productAdapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(productAdapter);
        for (Product p : products) {
            Log.d("product:", p.toString());
        }
    }

    public void addProducts(DatabaseHelper db) {
        if (db.getAllProducts().isEmpty()) {
            db.addProduct(new Product(1,"Red Apple", "This a honeycrisp apple","MountainApple", 1,R.drawable.apple));
            db.addProduct(new Product(2,"Banana", "This is a organic banana","RainForestBanana", 0.75,R.drawable.banana));
        }
    }
}