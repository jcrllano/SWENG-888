package com.example.safeauthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private FragmentItemsBinding binding;
    private FirebaseFirestore db;
    private CollectionReference itemsRef;
    private final List<Item> items = new ArrayList<>();
    private ItemAdapter adapter;
    private ListenerRegistration listenerReg;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItemsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        itemsRef = db.collection("items");

        RecyclerView rv = binding.recycler;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemAdapter(items);
        rv.setAdapter(adapter);

        FloatingActionButton fab = binding.fabAdd;
        fab.setOnClickListener(v -> showAddDialog());

        // Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) { return false; }
            @Override public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                Item it = items.get(pos);
                itemsRef.document(it.getId()).delete();
            }
        }).attachToRecyclerView(rv);
    }

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

    @Override public void onStop() {
        super.onStop();
        if (listenerReg != null) listenerReg.remove();
    }
}
