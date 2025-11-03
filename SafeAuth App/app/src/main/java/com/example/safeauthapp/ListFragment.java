package com.example.safeauthapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safeauthapp.databinding.FragmentItemsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

// This class manages the item list using Firebase Firestore.
public class ListFragment extends Fragment {
    private FragmentItemsBinding binding;
    private FirebaseFirestore db;
    private CollectionReference itemsRef;
    private final List<Item> items = new ArrayList<>();
    private ItemAdapter adapter;
    private ListenerRegistration listenerReg;

    //This returns the root view to display the fragment UI
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItemsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    //This method Initializes Firestore and the items collection
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        itemsRef = db.collection("items");

        RecyclerView rv = binding.recycler;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemAdapter(items);
        rv.setAdapter(adapter);

        FloatingActionButton fab = binding.fabAdd;
        fab.setOnClickListener(v -> showAddDialog());

        // This method enables swipe left or right to delete an item.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.@NonNull ViewHolder viewHolder, RecyclerView.@NonNull ViewHolder target) { return false; }
            @Override public void onSwiped(RecyclerView.@NonNull ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                Item it = items.get(pos);
                itemsRef.document(it.getId()).delete();
            }
        }).attachToRecyclerView(rv);
    }

    //This method loads the dialog_add_item.xml layout and Adds a new Item document to the items Firestore collection.
    private void showAddDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_item, null);
        EditText etTitle = dialogView.findViewById(R.id.etTitle);
        EditText etDesc  = dialogView.findViewById(R.id.etDesc);
        new AlertDialog.Builder(requireContext())
                .setTitle("Add Item")
                .setView(dialogView)
                .setPositiveButton("Save", (d, w) -> {
                    String title = etTitle.getText().toString().trim();
                    String desc  = etDesc.getText().toString().trim();
                    if (title.isEmpty()) {
                        Toast.makeText(getContext(), "Title required", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    itemsRef.add(new Item(null, title, desc));
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    //This method is called when the fragment starts, it listens in real time to changes in the Firestore "items" collection.
    @Override public void onStart() {
        super.onStart();
        listenerReg = itemsRef.orderBy("title")
                .addSnapshotListener((snap, e) -> {
                    if (e != null || snap == null) return;
                    items.clear();
                    for (DocumentSnapshot ds : snap.getDocuments()) {
                        Item it = ds.toObject(Item.class);
                        if (it != null) {
                            it.setId(ds.getId());
                            items.add(it);
                        }
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    //This method is called when the fragment is no longer visible
    @Override public void onStop() {
        super.onStop();
        if (listenerReg != null) listenerReg.remove();
    }
}
