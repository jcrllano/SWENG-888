package com.example.listviewapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter {

    ArrayList<ListItem> list;
    Context context;

    public CustomArrayAdapter(Context context, ArrayList<ListItem> list) {
        this.context = context;
        this.list = list;
    }

    public View getView(View view, int position, ViewGroup viewGroup) {
        TextView title = view.findViewById(R.id.listItemTitle);
        TextView subTitle = view.findViewById(R.id.listItemSubTitle);

        ListItem listItem = list.get(position);
        title.setText(listItem.getTitle());
        subTitle.setText(listItem.getSubtitle());

        return view;
    }
}
