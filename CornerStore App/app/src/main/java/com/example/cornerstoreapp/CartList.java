package com.example.cornerstoreapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartList extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = (ArrayList<Product>) getIntent().getSerializableExtra("selected_products");
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
    }

}
