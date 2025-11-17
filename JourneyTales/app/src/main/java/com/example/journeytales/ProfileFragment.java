package com.example.journeytales;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    //This are the constants
    private static final int PICK_IMAGE = 100;
    private static final int PICK_LOCATION = 200;
    //This are the variables
    private GridView gridPosts;
    private List<Uri> imageList;
    private ImageAdapter adapter;
    private FloatingActionButton floatingActionButton;

    private Uri selectedImageUri;
    private double selectedLat = 0;
    private double selectedLng = 0;

    private AlertDialog activeDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        gridPosts = view.findViewById(R.id.grid_posts);
        floatingActionButton = view.findViewById(R.id.fab_add_post);

        imageList = new ArrayList<>();

        adapter = new ImageAdapter(requireContext(), imageList);
        gridPosts.setAdapter(adapter);

        floatingActionButton.setOnClickListener(v -> openCreatePostDialog());

        gridPosts.setOnItemClickListener((parent, v, position, id) -> {
            Uri img = imageList.get(position);
            showImageFullScreen(img);
        });

        return view;
    }

    private void openCreatePostDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_post, null);

        ImageView imagePreview = dialogView.findViewById(R.id.image_preview);
        View btnPickImage = dialogView.findViewById(R.id.btn_pick_image);
        View btnPickLocation = dialogView.findViewById(R.id.btn_pick_location);
        View btnSave = dialogView.findViewById(R.id.btn_save_post);
        EditText etDescription = dialogView.findViewById(R.id.et_description);

        builder.setView(dialogView);
        activeDialog = builder.create();
        activeDialog.show();

        btnPickImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE);
        });

        btnPickLocation.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MapLocation.class);
            startActivityForResult(intent, PICK_LOCATION);
        });

        btnSave.setOnClickListener(v -> {
            if (selectedImageUri == null) {
                Toast.makeText(requireContext(), "Please choose an image", Toast.LENGTH_SHORT).show();
                return;
            }

            imageList.add(selectedImageUri);
            adapter.notifyDataSetChanged();

            activeDialog.dismiss();
            Toast.makeText(requireContext(), "Post Created!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != getActivity().RESULT_OK || data == null) return;

        if (requestCode == PICK_IMAGE) {
            selectedImageUri = data.getData();
            Toast.makeText(requireContext(), "Image selected!", Toast.LENGTH_SHORT).show();

            if (activeDialog != null) {
                ImageView imagePreview = activeDialog.findViewById(R.id.image_preview);
                imagePreview.setImageURI(selectedImageUri);
            }
        }

        if (requestCode == PICK_LOCATION) {
            selectedLat = data.getDoubleExtra("lat", 0);
            selectedLng = data.getDoubleExtra("lng", 0);

            Toast.makeText(requireContext(),
                    "Location selected: " + selectedLat + ", " + selectedLng,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void showImageFullScreen(Uri imageUri) {

        ImageView imageView = new ImageView(requireContext());
        imageView.setImageURI(imageUri);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setBackgroundColor(Color.BLACK);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(requireContext(),
                        android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        builder.setView(imageView);
        AlertDialog dialog = builder.create();

        imageView.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
