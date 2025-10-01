package com.example.listviewapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter extends BaseAdapter {

    ArrayList<ListItem> list;
    Context context;

    public CustomArrayAdapter(Context context, ArrayList<ListItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView title = convertView.findViewById(R.id.listItemTitle);
        TextView subTitle = convertView.findViewById(R.id.listItemSubTitle);

        ListItem listItem = list.get(position);
        title.setText(listItem.getTitle());
        subTitle.setText(listItem.getSubtitle());

        return convertView;
    }
}
