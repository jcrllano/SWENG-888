package com.example.cornerstoreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

public List<Product> productList;
public Context context;

// Constructor
public ProductAdapter(Context context, List<Product> productList) {
    this.context = context;
    this.productList = productList;
}

@NonNull
@Override
public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
    return new ProductViewHolder(view);
}

@Override
public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {
    Product productItem = productList.get(position);

    productViewHolder.textProductName.setText(productItem.getName());
    productViewHolder.textSellerName.setText(productItem.getSeller());
    productViewHolder.textDescription.setText(productItem.getDescription());
    productViewHolder.textPrice.setText("$ " +productItem.getPrice());
    productViewHolder.imageProduct.setImageResource(productItem.getPictureID());

    productViewHolder.checkBoxList.setOnCheckedChangeListener(null);
    productViewHolder.checkBoxList.setChecked(productItem.Selected());

    productViewHolder.checkBoxList.setOnCheckedChangeListener((buttonView, isChecked) -> {
        productItem.setSelected(isChecked);
    });
}

@Override
public int getItemCount() {
    return productList.size();
} 

// ViewHolder class
static class ProductViewHolder extends RecyclerView.ViewHolder {
    TextView textProductName, textSellerName, textPrice, textDescription;
    ImageView imageProduct;

    CheckBox checkBoxList;

    public ProductViewHolder(@NonNull View itemView) {

        super(itemView);

        textProductName = itemView.findViewById(R.id.textProductName);
        textDescription = itemView.findViewById(R.id.textDescription);
        textSellerName = itemView.findViewById(R.id.textSellerName);
        textPrice = itemView.findViewById(R.id.textPrice);
        imageProduct = itemView.findViewById(R.id.imageProduct);
        checkBoxList= itemView.findViewById(R.id.checkboxList);
    }
}
}
