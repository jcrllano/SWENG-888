package com.example.listviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        ArrayList<ListItem> list = new ArrayList<>();
        list.add(new ListItem("SWENG 888", "Mobile Application Dev", "This class is about developing apps"));
        list.add(new ListItem("SWENG 521", "Database Design", "This Class is about designing databases"));

        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(this, list);
        listView.setAdapter(customArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem clickedItem = list.get(position);

                Toast.makeText(MainActivity.this, "Clicked " + clickedItem.getSubtitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DetailListActivity.class);
                intent.putExtra("Item", clickedItem);
                startActivity(intent);
            }
        });
    }
}