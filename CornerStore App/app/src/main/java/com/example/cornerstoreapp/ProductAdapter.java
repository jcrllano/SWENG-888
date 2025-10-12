package com.example.cornerstoreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

private List<Product> productList;

// Constructor
public ProductAdapter(List<Product> productList) {
    this.productList = productList;
}

@NonNull
@Override
public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_product_list, parent, false);
    return new ProductViewHolder(view);
}

@Override
public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
    Product productItem = productList.get(position);

    holder.textProductName.setText(productItem.getName());
    holder.textSellerName.setText(productItem.getSeller());
    holder.textPrice.setText(productItem.getPrice());
    holder.imageProduct.setImageResource(productItem.getPictureUrl());
}

@Override
public int getItemCount() {
    return productList.size();
}

// ViewHolder class
static class ProductViewHolder extends RecyclerView.ViewHolder {
    TextView textProductName, textSellerName, textPrice;
    ImageView imageProduct;

    public ProductViewHolder(@NonNull View itemView) {

        super(itemView);

        textProductName = itemView.findViewById(R.id.textProductName);
        textSellerName = itemView.findViewById(R.id.textSellerName);
        textPrice = itemView.findViewById(R.id.textPrice);
        imageProduct = itemView.findViewById(R.id.imageProduct);
    }
}
}
