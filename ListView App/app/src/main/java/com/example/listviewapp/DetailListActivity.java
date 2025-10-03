package com.example.listviewapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class DetailListActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        //This method set the activity_listview layout
        setContentView(R.layout.activity_listview);
        //This statement gets the title content from the layout and set it into a variable
        TextView title = findViewById(R.id.detailTitle);
        //This statement gets the subtitle content from the layout and set it into a variable
        TextView subTitle = findViewById(R.id.detailSubtitle);
        //This statement gets the details content from the layout and set it into a variable
        TextView details = findViewById(R.id.details);

        //This method sets list items into an intent
        ListItem list = (ListItem) getIntent().getSerializableExtra("Item");
        //If the list is not null, it will get the details of the items
        if (list != null) {
            title.setText(list.getTitle());
            subTitle.setText(list.getSubtitle());
            details.setText(list.getDetails());
        }
        //This toast will display the viewing details when the user click on the item
        Toast.makeText(this, "Viewing Class Details", Toast.LENGTH_SHORT).show();
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(DetailListActivity.this, "Class List", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
