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

    //This is the constructor that will be used to pass the list items
    public CustomArrayAdapter(Context context, ArrayList<ListItem> list) {
        this.context = context;
        this.list = list;
    }
    //This  method returns  the number of items on the list
    @Override
    public int getCount() {
        return list.size();
    }

    //This method returns the position used as the identifier
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //This method returns the position used as the unique identifier
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //This statement will inflate a new view if theres is none passed.
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        //This statements gets the values from the xml view
        TextView title = convertView.findViewById(R.id.listItemTitle);
        TextView subTitle = convertView.findViewById(R.id.listItemSubTitle);

        //This statement gets the item positon
        ListItem listItem = list.get(position);
        //This statements sets the the textview values passed from the view
        title.setText(listItem.getTitle());
        subTitle.setText(listItem.getSubtitle());

        //This returns the view to be display in the view
        return convertView;
    }
}
