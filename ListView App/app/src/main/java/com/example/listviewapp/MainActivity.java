package com.example.listviewapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ListItem> list = new ArrayList<>();
        list.add(new ListItem("SWENG 888", "Mobile Application Dev"));

        listView.findViewById(R.id.listView);

        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(this, list);
        listView.setAdapter(customArrayAdapter);
    }
}