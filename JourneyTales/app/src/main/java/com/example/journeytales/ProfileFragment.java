package com.example.journeytales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    private GridView gridPosts;
    private List<Integer> imageList;
    private ImageAdapter adapter;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        //Connect UI elements
        gridPosts = view.findViewById(R.id.grid_posts);
        floatingActionButton = view.findViewById(R.id.fab_add_post);

        //Initialize image list
        imageList = new ArrayList<>();
        imageList.add(R.drawable.image1);
        imageList.add(R.drawable.image2);

        //Set up adapter
        adapter = new ImageAdapter(requireContext(), imageList);
        gridPosts.setAdapter(adapter);

        //FAB click listener
        floatingActionButton.setOnClickListener(v -> addNewPostImage());

        return view;
    }

    private void addNewPostImage() {
        int newImageId = R.drawable.image1; // make sure this drawable exists
        imageList.add(newImageId);
        adapter.notifyDataSetChanged();
        gridPosts.smoothScrollToPosition(imageList.size() - 1);
        Toast.makeText(requireContext(), "New post added!", Toast.LENGTH_SHORT).show();
    }
}
