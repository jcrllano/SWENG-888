package com.example.listviewapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class DetailListActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_listview);

        TextView title = findViewById(R.id.detailTitle);
        TextView subTitle = findViewById(R.id.detailSubtitle);
        TextView details = findViewById(R.id.details);

        ListItem list = (ListItem) getIntent().getSerializableExtra("Item");
        if (list != null) {
            title.setText(list.getTitle());
            subTitle.setText(list.getSubtitle());
            details.setText(list.getDetails());
        }

        Toast.makeText(this, "View Details", Toast.LENGTH_SHORT).show();
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(DetailListActivity.this, "Returned to main successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
