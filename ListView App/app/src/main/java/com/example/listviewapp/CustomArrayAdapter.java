package com.example.listviewapp;

import android.content.Context;
import android.view.LayoutInflater;
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
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView title = convertView.findViewById(R.id.listItemTitle);
        TextView subTitle = convertView.findViewById(R.id.listItemSubTitle);

        ListItem listItem = list.get(position);
        title.setText(listItem.getTitle());
        subTitle.setText(listItem.getSubtitle());

        return convertView;
    }
}
