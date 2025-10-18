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
    //This method set the products into an array list
    public List<Product> productList;
    public Context context;

    // This is the custom constructor
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }
    //This creates the product holder for the view class
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        return new ProductViewHolder(view);
    }
    //This method bind the products list into the layout view on the main acitivity
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {
        Product productItem = productList.get(position);

        productViewHolder.textProductName.setText(productItem.getName());
        productViewHolder.textSellerName.setText(productItem.getSeller());
        productViewHolder.textDescription.setText(productItem.getDescription());
        productViewHolder.textPrice.setText("$ " +productItem.getPrice());
        productViewHolder.imageProduct.setImageResource(productItem.getPictureID());

        //This method set the check box unchecked and lets user check it or uncheck it
        productViewHolder.checkBoxList.setOnCheckedChangeListener(null);
        productViewHolder.checkBoxList.setChecked(productItem.Selected());

        productViewHolder.checkBoxList.setOnCheckedChangeListener((buttonView, isChecked) -> {
            productItem.setSelected(isChecked);
        });
    }

    //This method gets the count on items so that the recycler can use it
    @Override
    public int getItemCount() {
        return productList.size();
    }

    //This method makes the scrolling through the product item list much easier
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textProductName, textSellerName, textPrice, textDescription;
        ImageView imageProduct;

        CheckBox checkBoxList;

        //This method sett he items into the layout view
        public ProductViewHolder(@NonNull View itemView) {

            //This calls the parent constructor fo the recycler view
            super(itemView);

            //This next block set the products into the variable to be used by the recycler
            textProductName = itemView.findViewById(R.id.textProductName);
            textDescription = itemView.findViewById(R.id.textDescription);
            textSellerName = itemView.findViewById(R.id.textSellerName);
            textPrice = itemView.findViewById(R.id.textPrice);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            checkBoxList= itemView.findViewById(R.id.checkboxList);
        }
    }
}
