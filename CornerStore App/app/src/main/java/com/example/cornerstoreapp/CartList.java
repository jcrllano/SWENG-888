package com.example.cornerstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

        Button buttonEmail = findViewById(R.id.btnSendEmail);
        buttonEmail.setOnClickListener(v -> sendEmail());
    }

    public void sendEmail() {
        StringBuilder stringBuilder = new StringBuilder("Products List:\n\n");
        for (Product product : productList) {
            stringBuilder.append(product.getName());
            stringBuilder.append(" $");
            stringBuilder.append(product.getPrice());

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(android.net.Uri.parse("mailto:"));
            intent.putExtra(intent.EXTRA_EMAIL, new String[]{"dolphinsnotredamfan@yahoo.com"});
            intent.putExtra(intent.EXTRA_TEXT, stringBuilder.toString());

            startActivity(intent);
        }
    }

}
