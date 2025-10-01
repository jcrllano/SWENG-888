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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ListItem> list = new ArrayList<>();
        list.add(new ListItem("SWENG 888", "Mobile Application Dev"));


        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, list);

        //ListView listView = (ListView) findViewById(R.id.mobile_list);
        //listView.setAdapter(adapter);
    }
}