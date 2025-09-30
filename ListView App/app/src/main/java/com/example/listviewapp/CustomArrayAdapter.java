package com.example.listviewapp;

import android.content.Context;

import java.util.ArrayList;

public class CustomArrayAdapter {

    ArrayList<ListItem> list;
    Context context;
    public CustomArrayAdapter(Context context, ArrayList<ListItem> list) {
        this.context = context;
        this.list = list;
    }
    // then according to the position of the view assign the desired TextView 1 for the same
    TextView textView1 = currentItemView.findViewById(R.id.textView1);
        textView1.setText(currentNumberPosition.getNumberInDigit());

    // then according to the position of the view assign the desired TextView 2 for the same
    TextView textView2 = currentItemView.findViewById(R.id.textView2);
        textView2.setText(currentNumberPosition.getNumbersInText());
}
