package com.example.journeytales;

import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PostImage extends AppCompatActivity {
    private GridView gridPosts;
    private List<Integer> imageList;
    private void addNewPostImage() {
        // If the user picks an image, this will be a URI or file path.
        int newImageId = R.drawable.new_post_image; // Example resource ID

        //Updates the data source
        imageList.add(newImageId);

        // This will notify the adapter to refresh the GridView
        adapter.notifyDataSetChanged();

        // This will scroll to the new item if needed
        gridPosts.smoothScrollToPosition(imageList.size() - 1);

        Toast.makeText(this, "New post added!", Toast.LENGTH_SHORT).show();
    }
}
