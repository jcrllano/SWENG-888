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
            .inflate(R.layout.exam_card, parent, false);
    return new ProductViewHolder(view);
}

@Override
public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
    Product productItem = productList.get(position);

    holder.examName.setText(examItem.getName());
    holder.examDate.setText(examItem.getDate());
    holder.examMessage.setText(examItem.getMessage());
    holder.examPic.setImageResource(examItem.getImage1());
    holder.examPic2.setImageResource(examItem.getImage2());
}

@Override
public int getItemCount() {
    return examList.size();
}

// ViewHolder class
static class ProductViewHolder extends RecyclerView.ViewHolder {
    TextView examName, examDate, examMessage;
    ImageView examPic, examPic2;

    public ProductViewHolder(@NonNull View itemView) {

        super(itemView);

        examName = itemView.findViewById(R.id.examName);
        examDate = itemView.findViewById(R.id.examDate);
        examMessage = itemView.findViewById(R.id.examMessage);
        examPic = itemView.findViewById(R.id.examPic);
        examPic2 = itemView.findViewById(R.id.examPic2);
    }
}
