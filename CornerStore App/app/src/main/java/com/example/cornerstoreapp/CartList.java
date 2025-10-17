package com.example.cornerstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartList extends AppCompatActivity {
    //This calls the recycler view class widget
    RecyclerView recyclerView;
    //This calls the custom product adapter class
    ProductAdapter productAdapter;
    //This variable sets the product class into an array list
    ArrayList<Product> productList;
    //This is a boolean that will become true once the email has been sent
    public boolean emailSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //gets the content fron the layout view
        setContentView(R.layout.cart_list_view);

        //this gets the layout of the recycler view import class
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //This passes the data from the main activity intent into the product list array
        productList = (ArrayList<Product>) getIntent().getSerializableExtra("selected_products");

        //This will set the product list into the custom adapter constructor
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        //This gets the clciking of the button from the layout and perform an activity
        Button buttonEmail = findViewById(R.id.btnSendEmail);
        buttonEmail.setOnClickListener(v -> sendEmail());
    }

    //This class will be triggered when the send button email is clicked
    public void sendEmail() {
        //This will create a string to append the products list tot  eh email body
        StringBuilder stringBuilder = new StringBuilder("Products List:\n\n");
        for (Product product : productList) {
            stringBuilder.append(product.getName());
            stringBuilder.append(" $");
            stringBuilder.append(product.getPrice());
            stringBuilder.append("\n");
        }
            //This calls the intent so that the email can be send
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            //this set the email address on the recipient address
            intent.setData(android.net.Uri.parse("mailto:"));
            intent.putExtra(intent.EXTRA_EMAIL, new String[]{"dolphinsnotredamfan@yahoo.com"});
            intent.putExtra(intent.EXTRA_SUBJECT, "My Products");
            intent.putExtra(intent.EXTRA_TEXT, stringBuilder.toString());

            //This starts the send email activity
            startActivity(intent);
            //Once the activity has been called the boolean will set to true
            emailSent = true;
    }

    //This class call a toast and delete entries from the recycler view
   @Override
    protected void onResume() {
        super.onResume();
        if (emailSent && productList != null && !productList.isEmpty()) {
            Toast.makeText(this, "Email sent successfully", Toast.LENGTH_SHORT).show();
            //this i called after the toast disapears and this will delete the view items
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    productList.clear();
                    productAdapter.notifyDataSetChanged();
                }
            }, 3000);
            //Once the email has been sent, this is set to false so that it reset the boolean
            emailSent = false;
        }
    }

}
