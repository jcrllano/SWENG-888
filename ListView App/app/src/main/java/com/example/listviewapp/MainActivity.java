package com.example.listviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //this class the Listview class and sets it to a variable
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This statement the activity_mai xml layout int the main screen
        setContentView(R.layout.activity_main);
        //This set the content from the listview int the listview variable
        listView = findViewById(R.id.listView);
        //This sets the contents from the semester textview from the activity_main
        TextView semester = findViewById(R.id.semester);

        //This sets the hardcode text into a variable
        String semesterText = "Spring Semester 2026";
        //The variable is set to display the text
        semester.setText(semesterText);

        //This array list will set the content and display when a user click on the main content
        ArrayList<ListItem> list = new ArrayList<>();
        list.add(new ListItem("SWENG-888", "Mobile Application Dev", "This class is about developing apps"));
        list.add(new ListItem("INSC-521","Database Design", "This Class is about designing databases"));
        list.add(new ListItem("SWENG-877", "Web Application", "This class develops web apps for businessses"));

        //This sets the custom adapter and set the array list inside of it
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(this, list);
        //This set the adapter with the content to be display in the listview
        listView.setAdapter(customArrayAdapter);

        //This method makes the list items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //This method overides the on itemclick and displays the details when an item from the list is clicked
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem clickedItem = list.get(position);

                //This makes a small display banner at the bottom of the window
                Toast.makeText(MainActivity.this, "Clicked " + clickedItem.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DetailListActivity.class);
                intent.putExtra("Item", clickedItem);
                startActivity(intent);
            }
        });
    }
}