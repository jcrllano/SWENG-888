package com.example.safeauthapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//This class defines a custom RecyclerView adapter that displays the items added by the user
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.VH> {
    private final List<Item> data;
    public ItemAdapter(List<Item> data) {
        this.data = data;
    }

    //This method creates a new row view
    @NonNull
    @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new VH(view);
    }

    //This is called every time a row is displayed or scrolled into view.
    @Override public void onBindViewHolder(@NonNull VH vh, int pos) {
        Item it = data.get(pos);
        vh.textViewTitle.setText(it.getTitle());
        vh.textViewDesc.setText(it.getDesc());
    }

    @Override public int getItemCount() {
        return data.size();
    }

    //This method holds references to the views inside each list row
    static class VH extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDesc;
        VH(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc  = itemView.findViewById(R.id.textViewDesc);
        }
    }
}


