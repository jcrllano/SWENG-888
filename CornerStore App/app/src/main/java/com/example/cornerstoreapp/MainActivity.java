package com.example.cornerstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //This method sets the recycler view on the recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //This calls the databasehelper class
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        addProducts(databaseHelper);
        //This sets the products into an list of products gathered from the database
        List<Product> products = databaseHelper.getAllProducts();
        productAdapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(productAdapter);

        //This calls the click from the layout submit button
        Button buttonCart = findViewById(R.id.buttonCartList);
        //This initiates an activity when the button is clicked
        buttonCart.setOnClickListener(v -> {
            //This set the product list into an array list
            ArrayList<Product> productList = new ArrayList<>();
            for (Product product : products) {
                if (product.Selected()) {
                    productList.add(product);
                    Log.d("this are the product:", product.getName());
                }
            }
            //This initiates the intent
            Intent intent = new Intent(MainActivity.this, com.example.cornerstoreapp.CartList.class);
            intent.putExtra("selected_products", productList);
            startActivity(intent);

        });
    }

    public void addProducts(DatabaseHelper db) {
        if (db.getAllProducts().isEmpty()) {
            db.addProduct(new Product(1,"Red Apple", "This a honeycrisp apple","Mountain Apple", 1,R.drawable.apple));
            db.addProduct(new Product(2,"Banana", "This is a organic banana","Rain Forest Banana", 0.75,R.drawable.banana));
            db.addProduct(new Product(3, "Strawberry", "This is an organic strawberry", "California StrawBerry Farms", 0.15, R.drawable.strawberry));
            db.addProduct(new Product(4, "Blueberry","This is a bluebbery from the farm","Blueberry Farm", 1.5, R.drawable.blueberry));
        }
    }
}